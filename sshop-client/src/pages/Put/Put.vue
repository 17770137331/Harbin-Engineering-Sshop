<template>
  <section class="search">
    <HeaderTop title="发布" class="myput"/>

<div class="myScroll">
 <section class="child">
    <div class="basic">
      <mt-field label="商品名" placeholder="请输入商品名" v-model="title"></mt-field>
      <mt-field label="商品价格" placeholder="请输入商品价格，最大两小数" type="input" v-model="price" v-only-number="{max:100,min:0,precision:0}"></mt-field>
      <mt-field label="商品数量" placeholder="请输入您要卖的商品数量，最大为100" type="number" v-only-number="{max:100,min:0,precision:0}" v-model="number"></mt-field>
      <!-- 参加的活动 -->
      <mt-field label="商品介绍" placeholder="商品介绍" type="textarea" rows="4" v-model="comment"></mt-field>
    </div>
    <div class="activity">
      <el-checkbox-group v-model="checkList" v-if="showAliveActivities">
        <el-checkbox v-for="activity in aliveActivities" 
        :key="activity.aid" 
        :label="activity.aid"
        >{{activity.title}}
      </el-checkbox>
      </el-checkbox-group>
    </div>
    <div>
      <el-divider></el-divider>
      <div class="zhonglei">
        商品种类：
      </div>
      <el-radio v-model="cid"  v-for="category in categorys" :key="category.cid" :label="category.cid">{{category.title}}</el-radio>
      
    </div>

    <el-divider></el-divider>
    <div class="uploda"> 
      <el-upload
        class="upload-demo"
        action="#"
        :before-upload="beforeAvatarUpload"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="10"
        :on-exceed="handleExceed"
        :on-progress="upLoad"
        :file-list="otherlinks">
        <el-button size="small" type="primary" @click="getCredential()">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过5Mb</div>
      </el-upload>
    </div>

    <div class="fabu">
      <el-row >
        <el-button round @click="putGood">发布</el-button>
      </el-row>
    </div>
 </section>

    



</div>



<AlertTip :alertText="alertText" v-show="alertShow" @closeTip="closeTip"/>



  </section>

</template>

<script>
import COS from 'cos-js-sdk-v5' //脚手架安装
import {mapState} from 'vuex'
import HeaderTop from '../../components/HeaderTop/HeaderTop.vue'
import AlertTip from '../../components/AlertTip/AlertTip.vue'
import { MessageBox, Toast } from 'mint-ui'
import {sendPutGood} from '../../api'
import BScroll from 'better-scroll'

export default {
  data () {
    return {
      putFlag:'',
      cid: '',      //表示单选框
      imgList: [],       //表示图片的地址数组，其中用户每每上传一张图片就可以将地址进行保存
                        //当用户删除图片时，我们也将这个图片的地址进行删除
      uploadFile: '',   //表示上传的文件流
      uploadFileName:' ',   //上传的文件名
      checkList: [],
      // value: ["a","b","c"],
      title: '',
      price:'',
      number: '',
      comment:'',
      keyword: '',
      noSearchShops: false,
      otherlinks: [],     //表示要显示的图片的列表
      myotherlinks:[],
      flag:'' ,        //因为用户在删除图片之前总是会先确认一遍，所以在此我们通过beforemove函数来
                        //记录用户要删除的那个内容地址，然后再在地址数组删除就行
      showAliveActivities: false,
      alertText: '', // 提示文本
      alertShow: false, // 是否显示警告框
    }
  },

created(){
  this.putFlag=localStorage.getItem("putFlag")
  // console.log(this.putFlag);
 if(!(this.putFlag==="1")){
  this.$router.push("/noright");
 }
},
mounted(){

    this.$store.dispatch('getCategorys')
    this.$store.dispatch("getAliveActivities",()=>{
      
      this.showAliveActivities=true
    })

},
  computed: {
    ...mapState(['credential','userInfo','aliveActivities','categorys'])
  },

  methods: {
    getCredential(){
      //异步请求，我不确定还要不要再整个回调函数
        this.$store.dispatch('getCredential')
    },
    beforeAvatarUpload(file) {

      const isJPG = (file.type === 'image/jpeg' || 'image/png')
      this.uploadFile = file
      this.uploadFileName = file.name
      const isLt2M = (file.size / 1024 / 1024 < 5)

      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG/PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 5MB!')
      }
      return isJPG && isLt2M
    },

  progress(){


  },

      handleRemove(file, otherlinks) {

  for(let i=0;i<this.imgList.length;i++){
     if(this.imgList[i]===this.flag){
       this.imgList.splice(i,1);
       break;//该行代码变成i--,则移除所有2
     }
        }

      },
      handlePreview(file) {

      },
      handleExceed(files, otherlinks) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + otherlinks.length} 个文件`);
      },
      beforeRemove(file, otherlinks) {
        this.flag='https://sshop-1302763867.cos.ap-nanjing.myqcloud.com' + '/putImg/' +this.userInfo.userid+'/'+ file.name 
        return this.$confirm(`确定移除 ${ file.name }？`);
      },



    async upLoad() {
      var that = this
      let newfildlist={}
      newfildlist.name=that.uploadFileName
      var cos = new COS({
        getAuthorization: function(options, callback) {
          callback({  
            TmpSecretId: that.credential.tmpSecretId,
            TmpSecretKey: that.credential.tmpSecretKey,
            XCosSecurityToken: that.credential.sessionToken,
            StartTime: that.credential.startTime,
            ExpiredTime: that.credential.expiredTime,
            expiration: that.credential.expiration,
            requestId: that.credential.requestId
          })
        }
      })
      cos.putObject(
        {
           Bucket: 'sshop-1302763867',
          Region: 'ap-nanjing',
          Key: '/putImg/'+that.userInfo.userid+'/' + that.uploadFileName,
          StorageClass: 'STANDARD',
          Body: that.uploadFile, // 上传文件对象
          onProgress: function(progressData) {
          }
        },
        function(err, data) {
          if (data && data.statusCode === 200) {
            const url = 'https://sshop-1302763867.cos.ap-nanjing.myqcloud.com' + '/putImg/' +that.userInfo.userid+'/'+ that.uploadFileName //根据对应路径获取到url
            newfildlist.url=url
            that.otherlinks.push(newfildlist)
            that.imgList.push(url)
          }
        }
      )
    },
    showAlert (alertText) {
      this.alertShow = true
      this.alertText = alertText
    },
    // 关闭警告框
    closeTip () {
      this.alertShow = false
      this.alertText = ''
    },

    putGood(){
        MessageBox.confirm('确认发布吗').then(
     async action => {
          // 请求退出
           if(!this.title||!this.price||!this.number||!this.comment||!this.cid){
             this.showAlert("商品信息不全，请完善商品信息！")
             return ;
           }

           const result=await sendPutGood(this.title,this.price,this.number,this.comment,this.checkList,this.otherlinks,this.cid)
            if(result.code===0){
             
              Toast({
                message: '发布成功',
                iconClass: 'icon icon-success'
              }); 
                this.cid='',
                this.imgList=[],
                this.uploadFile=''
                this.uploadFileName=''
                this.checkList=[]
                this.title=''
                this.price=''
                this.number=''
                this.comment=''
                this.otherlinks=[]
                this.myotherlinks=[]
                this.flag=''
            }else{
  
              Toast('发布失败，请稍后再试');

            }
       },
        action => {

        }
      )
    },
     _initScroll () {
  
      if(!this.scroll){
          this.scroll = new BScroll('.myScroll', {
          probeType: 2, // 因为惯性滑动不会触发
          click: true
        })
      }else{
        this.scroll.refresh()
      }

     }
  },

  watch: {
    otherlinks(){
      this._initScroll()
    },
    aliveActivities(){
      this._initScroll()
    },
    categorys(){

      this._initScroll();
    }



  },

  components: {
    HeaderTop,
    AlertTip
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  .myput
    position :relative
  .split
    width: 100%
    height: 16px
    border-top: 1px solid rgba(7, 17, 27, 0.1)
    border-bottom: 1px solid rgba(7, 17, 27, 0.1)
    background: #f3f5f7
  .fabu
    display :flex
    justify-content :center
    padding:20px 0 0 0
  .zhonglei
    display block
    padding: 0 0 16px 0
  .search
    height :100%
  .myScroll
    height: 70%
  // .child
  //   height :900px
</style>
