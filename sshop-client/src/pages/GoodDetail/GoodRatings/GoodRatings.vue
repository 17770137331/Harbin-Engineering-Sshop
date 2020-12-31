<template>
  <div class="ratings" ref="ratings">
    <div class="ratings-content">
      <div class="overview">
        <div class="overview-left">
          <h1 class="score">{{averagescore}}</h1>
          <div class="title">综合评分</div>
           <div class="score-wrapper mysuperstar" >
           <Star :score="averagescore/2" :size="36" />
           </div>
          <!-- <div class="rank">高于周边商家99%</div> -->
        </div>
        <div class="overview-right">
          <div class="score-wrapper">
            <span class="title">服务态度</span>
            <Star :score="4.5" :size="36" />
            <span class="score">9</span>
          </div>
          <div class="score-wrapper">
            <span class="title">商品评分</span>
            <Star :score="4" :size="36" />
            <span class="score">8</span>
          </div>
          <!-- <div class="delivery-wrapper">
            <span class="title">送达时间</span>
            <span class="delivery">{{info.deliveryTime}}分钟</span>
          </div> -->
        </div>
      </div>

      <div class="split"></div>

      <div class="ratingselect">
        <div class="rating-type border-1px">
          <span class="block positive" :class="{active: selectType===2}" @click="setSelectType(2)">
            全部<span class="count">{{sellerRatings.length}}</span>
          </span>
          <span class="block positive" :class="{active: selectType===0}" @click="setSelectType(0)">
            不满意<span class="count">{{positiveSize}}</span>
          </span>
          <span class="block negative" :class="{active: selectType===1}" @click="setSelectType(1)">
            满意<span class="count">{{sellerRatings.length-positiveSize}}</span>
          </span>
        </div>
        <div class="switch" :class="{on: onlyShowText}" @click="toggleOnlyShowText">
          <span class="iconfont icon-checkcircle"></span>
          <!-- <span class="text">只看有内容的评价</span> -->
        </div>
      </div>

      <div class="rating-wrapper">
        <ul>
          <!-- 在此有一个过滤器 -->
          <li class="rating-item" v-for="(sellerRating, index) in filterRatings" :key="index">
            <div class="avatar">
              <img width="28" height="28" :src="sellerRating.avatar">
            </div>
            <div class="content">
              <h1 class="name">{{sellerRating.nickname}}</h1>
              <div class="star-wrapper comment-title" >
                <Star :score="sellerRating.ratescore/2" :size="24" />
                <!-- <span class="delivery">{{rating.deliveryTime}}</span>
                {{sellerRating.ratetime}} -->
              <span class="delivery">{{getTime(sellerRating)}}</span>

              </div>
              <p class="text">{{sellerRating.text}}</p>
              <div class="recommend">
                <!--这里注意要写时间 -->
                <span class="iconfont" :class="sellerRating.ratetype===1 ? 'icon-zan' : 'icon-chaping'"></span>
                <!-- <span class="item" v-for="(item, index) in rating.recommend" :key="index">{{item}}</span> -->
                <span class="item" >{{sellerRating.title}}</span>

              </div>
              <!-- <div class="time">{{rating.rateTime | date-format}}</div> -->
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import BScroll from 'better-scroll'
import {mapState, mapGetters} from 'vuex'
import Star from '../../../components/Star/Star.vue'

export default {

  data () {
    return {
      onlyShowText: true, // 是否只显示有文本的评价信息
      selectType: 2 // 选择的评价类型: 0满意, 1不满意, 2全部
    }
  },
  mounted () {

    if(this.sellerRatings.length){
        this._initScroll();
    }
    
  },

  // 在computed里获取info和ratings的State数据以及返回好评数量的Getters
  computed: {
    // ...mapState(['info', 'ratings','sellerInfo','sellerRatings']),
    ...mapState(['sellerInfo','sellerRatings']),
    ...mapGetters(['positiveSize']),


  averagescore(){

    let arr=[]
    if(this.sellerRatings.length){
      arr=this.sellerRatings
      let sum=0.0
    //  arr.forEach((item,index,array)=>{
      arr.forEach((item,index,array)=>{
        //执行代码
         sum+=item.ratescore
       })
       return sum/arr.length
    }
     return "0"
  },


    filterRatings () {
      // 得到相关的数据
      const {sellerRatings, onlyShowText, selectType} = this

      // 产生一个过滤新数组
      return sellerRatings.filter(sellerRating => {
        const {ratetype, text} = sellerRating
        /*
          条件1:
              selectType: 0/1/2
              rateType: 0/1
              全部 || 满意、不满意中的一种
              selectType===2 || selectType===rateType
          条件2
              onlyShowText: true/false
              text: 有值/没值
              符合条件一的全部评价 || 符合条件一的有文字的评价
              !onlyShowText || text.length>0
          */
        // 返回一个布尔值
        return (selectType === 2 || selectType === ratetype) && (!onlyShowText || text.length > 0)
      })
    }
  },

  methods: {
    _initScroll() {
      if (!this.scroll) {
        this.scroll = new BScroll(this.$refs.ratings, {
          probeType: 2, // 因为惯性滑动不会触发
          click: true,
        });
      } else {
        this.scroll.refresh();
      }
    },


    setSelectType (selectType) {
      this.selectType = selectType
    },
    toggleOnlyShowText () {
      this.onlyShowText = !this.onlyShowText
    },
    getTime(sellerRating){
        if(sellerRating.ratetime){
          return sellerRating.ratetime.substring(0,10)
        }
        return '今日'
    }
  },
  watch:{
    sellerRatings(){

      this._initScroll()
      this.$forceUpdate();
    }

  },

  components: {
    Star
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/mixins.styl"

  .ratings
    position: absolute
    top: 195px
    bottom: 0
    left: 0
    width: 100%
    overflow: hidden
    background: #fff
    .overview
      display: flex
      padding: 18px 0
      .overview-left
        flex: 0 0 137px
        padding: 6px 0
        width: 137px
        border-right: 1px solid rgba(7, 17, 27, 0.1)
        text-align: center
        @media only screen and (max-width: 320px)
          flex: 0 0 120px
          width: 120px
        .score
          margin-bottom: 6px
          line-height: 28px
          font-size: 24px
          color: rgb(255, 153, 0)
        .title
          margin-bottom: 8px
          line-height: 12px
          font-size: 12px
          color: rgb(7, 17, 27)
        .rank
          line-height: 10px
          font-size: 10px
          color: rgb(147, 153, 159)
      .overview-right
        flex: 1
        padding: 6px 0 6px 24px
        @media only screen and (max-width: 320px)
          padding-left: 6px
        .score-wrapper
          margin-bottom: 8px
          font-size: 0
          .title
            display: inline-block
            line-height: 18px
            vertical-align: top
            font-size: 12px
            color: rgb(7, 17, 27)
          .star
            display: inline-block
            margin: 0 12px
            vertical-align: top
          .score
            display: inline-block
            line-height: 18px
            vertical-align: top
            font-size: 12px
            color: rgb(255, 153, 0)
        .delivery-wrapper
          font-size: 0
          .title
            line-height: 18px
            font-size: 12px
            color: rgb(7, 17, 27)
          .delivery
            margin-left: 12px
            font-size: 12px
            color: rgb(147, 153, 159)
    .split
      width: 100%
      height: 16px
      border-top: 1px solid rgba(7, 17, 27, 0.1)
      border-bottom: 1px solid rgba(7, 17, 27, 0.1)
      background: #f3f5f7
    .ratingselect
      .rating-type
        padding: 18px 0
        margin: 0 18px
        border-1px(rgba(7, 17, 27, 0.1))
        font-size: 0
        .block
          display: inline-block
          padding: 8px 12px
          margin-right: 8px
          line-height: 16px
          border-radius: 1px
          font-size: 12px
          color: rgb(77, 85, 93)
          background: rgba(77, 85, 93, 0.2)
          &.active
            background: $green
            color: #fff
          .count
            margin-left: 2px
            font-size: 8px
      .switch
        padding: 12px 18px
        line-height: 24px
        border-bottom: 1px solid rgba(7, 17, 27, 0.1)
        color: rgb(147, 153, 159)
        font-size: 0
        &.on
          .icon-checkcircle
            color: $green
        .icon-checkcircle
          display: inline-block
          vertical-align: top
          margin-right: 4px
          font-size: 24px
        .text
          display: inline-block
          vertical-align: top
          font-size: 12px
    .rating-wrapper
      padding: 0 18px
      .rating-item
        display: flex
        padding: 18px 0
        bottom-border-1px(rgba(7, 17, 27, 0.1))
        .avatar
          flex: 0 0 28px
          width: 28px
          margin-right: 12px
          img
            border-radius: 50%
        .content
          position: relative
          flex: 1
          .name
            margin-bottom: 4px
            line-height: 12px
            font-size: 10px
            color: rgb(7, 17, 27)
          .star-wrapper
            margin-bottom: 6px
            font-size: 0
            .star
              display: inline-block
              margin-right: 6px
              vertical-align: top
            .delivery
              display: inline-block
              vertical-align: top
              height: 12px
              line-height: 12px
              font-size: 10px
              color: rgb(147, 153, 159)
          .text
            margin-bottom: 8px
            line-height: 18px
            color: rgb(7, 17, 27)
            font-size: 12px
          .recommend
            line-height: 16px
            font-size: 0
            .icon-thumb_up, .icon-thumb_down, .item
              display: inline-block
              margin: 0 8px 4px 0
              font-size: 9px
            .icon-thumb_up
              color: $yellow
            .icon-thumb_down
              color: rgb(147, 153, 159)

            .item
              padding: 0 6px
              border: 1px solid rgba(7, 17, 27, 0.1)
              border-radius: 1px
              color: rgb(147, 153, 159)
              background: #fff
          .time
            position: absolute
            top: 0
            right: 0
            line-height: 12px
            font-size: 10px
            color: rgb(147, 153, 159)
    .mysuperstar
      // background: red
      // padding: 0 0 0 24px
      display flex
      justify-content center
    .comment-title
      display flex
      justify-content space-between
</style>
