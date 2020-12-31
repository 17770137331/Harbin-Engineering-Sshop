<template>
  <div>
    <!-- 商品具体信息的组件 -->
  <div class="gooddetail">
    <div class="info-content">

      <section class="section">
        <h3 class="section-title">基本信息</h3>
        <ul class="detail">
          <li><span class="bold">商品名称</span> <span>{{gooddetail.title}}</span></li>
          <li><span class="bold">商品价格</span> <span>{{gooddetail.price}}￥</span></li>
          <li><span class="bold">商品数量</span> <span>{{gooddetail.number}}</span></li>
          <li><span class="bold">商品描述</span> <span>{{gooddetail.comment }}</span></li>
        </ul>
      </section>
      <div class="split"></div>
      <section class="section">
        <h3 class="section-title">商品图片</h3>
        <div class="pic-wrapper">
          <ul class="pic-list" ref="picsUl">
            <li class="pic-item" v-for="(pic, index) in gooddetail.otherlinks" :key="index">
              <img width="120" height="90" :src="pic"/>
            </li>
          </ul>
        </div>
      </section>      
      <div class="split"></div>
      <section class="section">
        <h3 class="section-title">评分信息</h3>
        <ul class="detail">
          <li><span class="bold">系统评分</span> <span>{{gooddetail.score}}分</span></li>
        </ul>
      </section>
      <div class="split"></div>
      
      <section class="section">
        <h3 class="section-title">优惠活动</h3>
        <div class="activity">
          <div class="activity-item" v-for="(activity, index) in sellerInfo.activities" :key="index"
              :class="supportClasses[activity.aid%3]">
            <div v-if="activity.type===0">
            <span class="content-tag" >
              <span class="mini-tag">{{activity.title}}</span>
            </span>
            <span class="activity-content">{{activity.descripition}}</span>
            </div>
          </div>
        </div>
      </section>
     <div class="split"></div>
      <section class="section">
        <h3 class="section-title">权益保障</h3>
        <div class="activity">
          <div class="activity-item" v-for="(activity, index) in sellerInfo.activities" :key="index"
              :class="supportClasses[activity.aid%3]">
            <div v-if="activity.type===1">
            <span class="content-tag" >
              <span class="mini-tag">{{activity.title}}</span>
            </span>
            <span class="activity-content">{{activity.descripition}}</span>
            </div>
          </div>
        </div>
      </section>
       <div class="split"></div>
      <section class="section">
        <h3 class="section-title" @click="showGood(gooddetail)">点击加号购买叭</h3>
        <div class="activity">
            <div class="cartcontrol-wrapper">
                 <!-- <CartControl :good="temp" v-show="gooddetail"/> -->
                 <CartControl :good="temp" v-show="gooddetail"/>

            </div>
        </div>
      </section>
        <!-- <div class="split"></div> -->
      <div class="tianchong"></div>
    </div> 
   <!-- 表示最下方的购物车的图形 -->
    <ShopCart/> 
  </div>
    <!-- 表示弹出的食物的具体信息 -->
    <Good :gooddetail="good" ref="good"/>
  </div>
</template>
<script>
//引入滑动框的代码
import BScroll from 'better-scroll'
import {mapState} from 'vuex'
import CartControl from '../../../components/CartControl/CartControl.vue'
 import Good from '../../../components/Good/Good.vue'
import ShopCart from '../../../components/ShopCart/ShopCart.vue'

export default {
  data () {
    return {
      scrollY: 0, // 商品信息的Y 轴滑动的坐标(越往下数值越小)
      supportClasses: ['activity-green', 'activity-red', 'activity-orange'],
       temp:{},
       good:{}
    }
  },
  mounted () {
        return ;
  },
  computed: {
    ...mapState(['gooddetail','sellerInfo','info']),

  },
  methods: {
  // 初始化滚动
    _initScroll () {
      // 列表显示之后创建

      this.goodDetailScroll = new BScroll('.gooddetail', {
        probeType: 2, // 因为惯性滑动不会触发
        click: true
      })


      // 给右侧列表绑定scroll监听
      this.goodDetailScroll.on('scroll', ({x, y}) => {

        this.scrollY = Math.abs(y)
      })
      // 给右侧列表绑定scroll结束的监听
      this.goodDetailScroll.on('scrollEnd', ({x, y}) => {
  
        this.scrollY = Math.abs(y)
      })

    // 动态计算ul的宽度
      const ul = this.$refs.picsUl
      const liWidth = 120
      const space = 6
      const count=this.gooddetail.otherlinks.length
      ul.style.width = (liWidth + space) * count - space + 'px'

      new BScroll('.pic-wrapper', {
        scrollX: true // 水平滑动
      })
    },

    // 显示点击的food
    showGood (gooddetail) {
      // 设置要传递给food组件的数据
      this.good = gooddetail
      // 显示good组件 (在父组件中调用子组件对象的方法)
      this.$refs.good.toggleShow()
    }
  },
  watch:{
     gooddetail() { // 刷新流程--> 更新数据
     this.temp=JSON.parse(JSON.stringify(this.gooddetail))
      this.$nextTick(() => {
        this._initScroll()
      }) 
     }
  },
  components: {
    CartControl,
    Good,
    ShopCart
  }
}
</script>
<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/mixins.styl"

  .gooddetail
    position: absolute
    top: 195px
    bottom: 0
    left: 0
    width: 100%
    background: #fff;
    overflow: hidden
    .section
      padding 16px 14px 14px
      font-size 16px
      background-color #fff
      color #666
      border-bottom 1px solid #eee
      position relative
      .section-title
        color #000
        font-weight 700
        line-height 16px
        > .iconfont
          float right
          color #ccc
      .delivery
        margin-top 16px
        font-size 13px
        line-height 18px
        .delivery-icon
          width 55px
          font-size 11px
          margin-right 10px
          display inline-block
          text-align center
          color #fff
          background-color #0097ff
          padding 1px 0
          border-radius 4px
        .delivery-money
          margin-top 5px

      .activity
        margin-top 16px
        .activity-item
          margin-bottom 12px
          display flex
          font-size 13px
          align-items center
          &.activity-green
            .content-tag
              background-color rgb(112, 188, 70)
          &.activity-red
            .content-tag
              background-color rgb(240, 115, 115)
          &.activity-orange
            .content-tag
              background-color: rgb(241, 136, 79)
          .content-tag
            display inline-block
            border-radius 2px
            width 36px
            height: 18px
            margin-right 10px
            color #fff
            font-style normal
            position relative
            .mini-tag
              position absolute
              left 0
              top 0
              right -100%
              bottom -100%
              font-size 24px
              transform scale(.5)
              transform-origin 0 0
              display flex
              align-items center
              justify-content center
      .pic-wrapper
        width: 100%
        overflow: hidden
        white-space: nowrap
        margin-top 16px
        .pic-list
          font-size: 0
          .pic-item
            display: inline-block
            margin-right: 6px
            width: 120px
            height: 90px
            &:last-child
              margin: 0
      .detail
        margin-bottom -16px
        > li
          display flex
          justify-content space-between
          align-items center
          margin-right -10px
          padding 16px 12px 16px 0
          line-height 16px
          bottom-border-1px(#ddd)
          font-size 13px
          > .bold
            font-weight 700
            color #333
          &:last-child
            border-none()
    .split
      width: 100%
      height: 16px
      border-top: 1px solid rgba(7, 17, 27, 0.1)
      border-bottom: 1px solid rgba(7, 17, 27, 0.1)
      background: #f3f5f7
  .cartcontrol-wrapper
  // background: red
    position: absolute
    right: 0
    bottom: 12px   
  .tianchong
    height 100px
    background :#fff
</style>
