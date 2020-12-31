<template>
  <section class="search">
    <HeaderTop title="搜索">
    <router-link class="header_search" slot="left" to="/">
      <i class="iconfont icon-xiazai6"></i>
    </router-link>
    </HeaderTop>

    <form class="search_form" @submit.prevent="search">
      <input type="search" placeholder="请输入产品名或者商家名" class="search_input" v-model="keyword">
      <input type="submit" class="search_submit" value="提交">
    </form>
    <section class="list" v-if="!noSearchGoods">
      <ul class="list_container">
        <!--:to="'/shop?id='+item.id"-->
        <router-link :to="{path:'/gooddetail', query:{goodid:item.goodid}}" tag="li"
                     v-for="item in searchGoods" :key="item.goodid" class="list_li">
          <section class="item_left">
            <img :src="item.imglink" class="restaurant_img">
          </section>
          <section class="item_right">
            <div class="item_right_text">
              <p>
                <span>{{item.title}}</span>
              </p>
              <p>剩余量： {{item.number}}</p>
              <!-- <p>{{item.delivery_fee||item.float_minimum_order_amount}} 元起送 / 距离{{item.distance}}</p> -->
              <span class=" delivery_right">{{item.nickname}}</span>
            </div>
          </section>
        </router-link>
      </ul>
    </section>

    <div class="search_none" v-else>很抱歉！无搜索结果</div>
  </section>
</template>

<script>
import {mapState} from 'vuex'
import HeaderTop from '../../components/HeaderTop/HeaderTop.vue'
export default {

  data () {
    return {
      keyword: '',
      // imgBaseUrl: 'http://cangdu.org:8001/img/',
      noSearchGoods: false
    }
  },

  computed: {
    ...mapState(['searchGoods'])
  },

  methods: {
    search () {
      // 得到搜索关键字
      const keyword = this.keyword.trim()
      // 进行搜索
      if (keyword) {
        this.$store.dispatch('searchGoods', keyword)
      }
    }
  },

  watch: {
    searchGoods (value) {
      if (!value.length) { // 没有数据
        this.noSearchGoods = true
      } else { // 有数据
        this.noSearchGoods = false
      }
    }
  },

  components: {
    HeaderTop
  }
}
</script>


<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../common/stylus/mixins.styl"
  .search
    width 100%
    height 100%
    overflow hidden
    .header_search
      position absolute
      left 15px
      top 50%
      transform translateY(-50%)
      width 10%
      height 50%
      .icon-xiazai6
        font-size 25px
        color #fff
    .search_form
      clearFix()
      margin-top 45px
      background-color #fff
      padding 12px 8px
      input
        height 35px
        padding 0 4px
        border-radius 2px
        font-weight bold
        outline none
        &.search_input
          float left
          width 79%
          border 4px solid #f2f2f2
          font-size 14px
          color #333
          background-color #f2f2f2
        &.search_submit
          float right
          width 18%
          border 4px solid #02a774
          font-size 16px
          color #fff
          background-color #02a774

    .list
      .list_container
        background-color: #fff;
        .list_li
          display: flex;
          justify-content: center;
          padding: 10px
          border-bottom: 1px solid $bc;
          .item_left
            margin-right: 10px
            .restaurant_img
              width 50px
              height 50px
              display block
          .item_right
            font-size 12px
            flex 1
            .item_right_text
              p
                line-height 12px
                margin-bottom 6px
                &:last-child
                  margin-bottom 0
    .search_none
      margin: 0 auto
      color: #333
      background-color: #fff
      text-align: center
      margin-top: 0.125rem
  .delivery_style
    transform-origin 35px 0
    transform scale(.7)
    display inline-block
    font-size 12px
    padding 1px
    border-radius 2px
  .delivery_right
    color #02a774
    border 1px solid #02a774
</style>
