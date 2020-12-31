<template>
  <div>
    <GoodDetailHeader :goodid="goodid" v-if="goodid != 0"></GoodDetailHeader>
    <div class="tab">
      <div class="tab-item">
        <!-- 使用replace的方式进行路由切换 （路由懒加载） -->
        <router-link
          :to="{
            path: '/gooddetail/goodinformation',
            query: { goodid: this.goodid },
          }"
          replace
          >商品信息</router-link
        >
      </div>
      <div class="tab-item">
        <router-link
          :to="{ name: 'goodratings', query: { goodid: this.goodid } }"
          replace
          >往期评价</router-link
        >
      </div>
      <div class="tab-item">
        <router-link
          :to="{
            path: '/gooddetail/sellerinformation',
            query: { goodid: this.goodid },
          }"
          replace
          >卖家信息</router-link
        >
      </div>
    </div>
    <!-- 缓存路由组件对象 -->
    <keep-alive>
      <router-view />
    </keep-alive>
  </div>
</template>

<script>
import GoodDetailHeader from "../../components/GoodDetailHeader/GoodDetailHeader.vue";
import { mapState } from "vuex";
export default {
  data() {
    return {
      goodid: 0,
    };
  },

  mounted() {
    this.goodid = Number(this.$route.query.goodid);
    this.$store.dispatch("getSellerRatings", this.goodid, () => {
      this.$nextTick(() => {
        // 列表数据更新显示后执行
      });
    });
    this.$store.dispatch("getGoodDetail", this.goodid);
    this.$store.dispatch("getSellerInfo", this.goodid);
  },
  components: {
    GoodDetailHeader,
  },
};
</script>

<style lang="stylus" rel="stylesheet/stylus">
@import '../../common/stylus/mixins.styl';

.tab {
  height: 40px;
  line-height: 40px;
  background: #fff;
  bottom-border-1px(rgba(7, 17, 27, 0.1));

  .tab-item {
    float: left;
    width: 33.33333%;
    text-align: center;
    font-size: 14px;
    color: rgb(77, 85, 93);

    a {
      display: block;
      position: relative;

      &.router-link-active {
        color: #02a774;

        &::after {
          content: '';
          position: absolute;
          left: 50%;
          bottom: 1px;
          width: 35px;
          height: 2px;
          transform: translateX(-50%);
          background: #02a774;
        }
      }
    }
  }
}
</style>
