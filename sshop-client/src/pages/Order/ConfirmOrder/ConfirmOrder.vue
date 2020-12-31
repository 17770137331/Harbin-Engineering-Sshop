<template>
  <!-- <HeaderTop title="确认订单">
    <router-link class="header_search" slot="left" to="/">
        <i class="iconfont icon-xiazai6"></i>
    </router-link>
</HeaderTop> -->
  <div class="first">
    <el-page-header @back="goBack" content="确认订单"> </el-page-header>

    <div class="myScroll">
      <div>
        <el-card
          class="box-card"
          v-for="(cartGood, index) in this.cartGoods"
          :key="index"
        >
          <div class="text item between">
            <span>买方</span>
            <span>{{ userInfo.nickname }}</span>
          </div>
          <div class="text item between">
            <span>卖家</span>
            <span>{{ cartGood.nickname }}</span>
          </div>
          <div class="text item between">
            <span>商品</span>
            <span>{{ cartGood.title }}</span>
          </div>
          <div class="text item between">
            <span>单价</span>
            <span>{{ cartGood.price }}</span>
          </div>
          <div class="text item between">
            <span>数量</span>
            <span>{{ cartGood.count }}</span>
          </div>
          <div class="text item between">
            <span>总价</span>
            <span>{{ cartGood.count * cartGood.price }}</span>
          </div>
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4 }"
            placeholder="请输入该单的备注"
            v-model="orderComment[index]"
          >
          </el-input>
          <div class="button">
            <el-button
              plain
              class="button1"
              @click="
                confirmOrder(index, cartGood, userInfo, orderComment[index])
              "
              :disabled="forbidden[index]"
              >{{ forbidden[index] ? "提交完成" : "提交订单" }}</el-button
            >
          </div>

          <el-divider class="mydivider"></el-divider>
        </el-card>
      </div>
    </div>

    <AlertTip :alertText="alertText" v-show="alertShow" @closeTip="closeTip" />
  </div>
</template>

<script>
import { mapState } from "vuex";
// import HeaderTop from "../../../components/HeaderTop/HeaderTop.vue";
import { MessageBox, Toast } from "mint-ui";
import AlertTip from "../../../components/AlertTip/AlertTip.vue";
import BScroll from "better-scroll";
import { confirmOrder } from "../../../api";

export default {
  data() {
    return {
      input: "",
      orderComment: [],
      // cartGoods: [],
      forbidden: [], //代表订单是否禁用
      alertShow: false,
      alertText: "",
    };
  },

  mounted() {

    this._initScroll();
  },
  computed: {
    ...mapState(["userInfo", "cartGoods"]),
    getForbidden() {
      return this.forbidden;
    },
  },

  methods: {
    showAlert(alertText) {
      this.alertShow = true;
      this.alertText = alertText;
    },
    closeTip() {
      this.alertShow = false;
      this.alertText = "";
    },

    goBack() {
      this.$router.push("/");
    },
    _initScroll() {
      if (!this.scroll) {
        this.scroll = new BScroll(".myScroll", {
          probeType: 2, // 因为惯性滑动不会触发
          click: true,
        });
      } else {
        this.scroll.refresh();
      }

    },
    async confirmOrder(index, cartGood, userInfo, orderComment) {
      if (!userInfo.userid) {
        this.showAlert("用户未登录，请先登录再提交订单！");
      }
      const userbuyid = userInfo.userid;

      const usersellid = cartGood.sid;
      const goodid = cartGood.goodid;
      const number = cartGood.count;
      const singleprice = cartGood.price;
      const totalprice = cartGood.price * cartGood.count;
      const comments = orderComment;
      const result = await confirmOrder(
        userbuyid,
        usersellid,
        goodid,
        number,
        singleprice,
        totalprice,
        comments
      );
      if (result.code == 0) {
        this.$store.dispatch("removeCartGood", cartGood);
        Toast("该订单提交成功");
        this.forbidden[index] = true;

        this.$forceUpdate();
      } else {
        Toast("订单" + (index + 1) + "提交失败");
      }
    },
  },

  watch: {
    cartGoods() {
      this._initScroll();

      if (this.cartGoods.length) {
        for (var i = 0; i < this.cartGoods.length; i++) {
          this.forbidden.push(false);
        }
        this.$forceUpdate();
      }
    },
  },

  components: {
    // HeaderTop,
    AlertTip,
  },
};
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
.first {
  height: 100%;
}

.icon-xiazai6 {
  font-size: 25px;
  color: #fff;
}

.text {
  font-size: 14px;
  width: 100%;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100%;
}

.between {
  display: flex;
  justify-content: space-between;
}

.myScroll {
  height: 100%;
}

.button {
  display: flex;
  justify-content: right;
  // right :10px
}

.button {
  padding: 8px 0 0 0;
}
</style>
