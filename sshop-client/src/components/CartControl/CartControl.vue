<template>
  <div class="cartcontrol">
    <transition name="move">
      <div
        class="iconfont icon-jianshao"
        v-show="good.count"
        @click.stop="updateGoodCount(false)"
      ></div>
    </transition>
    <div class="cart-count" v-show="good.count">{{ good.count }}</div>
    <div
      class="iconfont icon-weibiaoti---copy"
      @click.prevent="updateGoodCount(true)"
      v-if="!good.count || good.count < good.number"
    ></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { MessageBox, Toast } from "mint-ui";
export default {
  props: {
    good: Object,
  },
  mounted() {},
  methods: {
    updateGoodCount(isAdd) {
      if (this.userInfo.userid == this.good.sid) {
        Toast("不允许买自己发布的商品哦，亲~~~");
        return;
      }
      this.$store.dispatch("updateGoodCount", { isAdd, buygood: this.good });
      return;
    },
  },
  computed: {
    ...mapState(["userInfo"]),
  },
};
</script>




<style lang="stylus" rel="stylesheet/stylus">
@import '../../common/stylus/mixins.styl';

.cartcontrol {
  font-size: 0;

  .cart-decrease {
    display: inline-block;
    padding: 6px;
    line-height: 24px;
    font-size: 24px;
    color: rgb(0, 160, 220);
  }

  .icon-jianshao {
    display: inline-block;
    padding: 6px;
    line-height: 24px;
    font-size: 24px;
    color: $green;

    &.move-enter-active, &.move-leave-active {
      transition: all 0.3s;
    }

    &.move-enter, &.move-leave-to {
      opacity: 0;
      transform: translateX(15px) rotate(180deg);
    }
  }

  .cart-count {
    display: inline-block;
    vertical-align: top;
    width: 12px;
    padding-top: 6px;
    line-height: 24px;
    text-align: center;
    font-size: 10px;
    color: rgb(147, 153, 159);
  }

  .icon-weibiaoti---copy {
    display: inline-block;
    padding: 6px;
    line-height: 24px;
    font-size: 24px;
    color: $green;
  }
}
</style>
