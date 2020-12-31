<template>
<!-- 商家信息的组件 -->
  <div class="shop-info">
    <div class="info-content">
      <section class="section">
        <h3 class="section-title">商家信息</h3>
        <div class="delivery">
          <div>
            <span class="delivery-icon">{{sellerInfo.score}}分</span>
            <span>本商家的身份真实性已经经过平台审,综合分数为{{sellerInfo.score}}，请您放心购买!</span>
          </div>
          <!-- <div class="delivery-money">配送费￥{{info.deliveryPrice}}</div> -->
        </div>
      </section>

      <!-- 分割符号 -->
      <div class="split"></div>
      <section class="section">
        <h3 class="section-title">参加的活动服务</h3>
        <div class="activity">
          <div class="activity-item" v-for="(activity, index) in sellerInfo.activities" :key="index"
              :class="supportClasses[activity.type]">
            <span class="content-tag">
              <span class="mini-tag">{{activity.title}}</span>
            </span>
            <span class="activity-content">{{activity.descripition}}</span>
          </div>
        </div>
      </section>

      <div class="split"></div>

      <section class="section">
        <h3 class="section-title">商家图片</h3>
        <div class="pic-wrapper">
          <ul class="pic-list" ref="picsUl">
            <!-- <li class="pic-item" v-for="(pic, index) in sellerInfo.avatar" :key="index">
              <img width="120" height="90" :src="pic"/>
            </li> -->
              <img width="120" height="90" :src="sellerInfo.avatar"/>
          </ul>
        </div>
      </section>

      <div class="split"></div>

      <section class="section">
        <h3 class="section-title">卖方个人信息</h3>
        <ul class="detail">
          <li><span class="bold">商家名</span> <span>{{sellerInfo.nickname}}</span></li>
          <li><span class="bold">商家电话</span> <span>{{sellerInfo.phone}}</span></li>
          <li><span class="bold">商家宣言</span> <span>{{sellerInfo.bio}}</span></li>
          <li><span class="bold">是否通过信息认证</span> 
          <span class="iconfont" :class="sellerInfo.ispass===true ? 'icon-duigou' : 'icon-cuohao'"></span>
          </li>
          <li><span class="bold">商家学校</span> <span>{{sellerInfo.school}}</span></li>
          <li><span class="bold">卖出商品数量</span> <span>{{sellerInfo.totalnumber}}</span></li>
          <li><span class="bold">商家可靠度</span> <span>{{sellerInfo.reliability}}</span></li>

        </ul>
      </section>
    </div>
  </div>
</template>

<script>
import BScroll from 'better-scroll'
import {mapState} from 'vuex'
export default {
  data () {
    return {
      supportClasses: ['activity-green', 'activity-red', 'activity-orange']
    }
  },
  computed: {
    ...mapState(['sellerInfo','info'])
  },

  mounted () {
    // 如果数据还没有, 直接结束
    if (!this.sellerInfo.length) {
      return
    }
    // 数据有了, 可以创建BScroll对象形成滑动
    this._initScroll()
  },

  methods: {
    _initScroll () {
      new BScroll('.shop-info')
      // 动态计算ul的宽度
      // const ul = this.$refs.picsUl
      // const liWidth = 120
      // const space = 6
      // const count = this.info.pics.length
      // ul.style.width = (liWidth + space) * count - space + 'px'

      // new BScroll('.pic-wrapper', {
      //   scrollX: true // 水平滑动
      // })
    }
  },

  watch: {
    sellerInfo () { // 刷新流程--> 更新数据
      this.$nextTick(() => {
        this._initScroll()
        this.$forceUpdate();
      })
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/mixins.styl"

  .shop-info
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
  .icon-duigou::before
    color: #9CCC65
  .icon-cuohao::before
    color:red

    // background: green
</style>
