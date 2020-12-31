<template>
  <div class="order_doing">
    <div class="scrall_children">
      <div v-for="(orderItem, index) in ordersDoing" :key="index">
        <OrderItem :orderItem="orderItem"></OrderItem>
        <div class="button">
          <el-button
            type="primary"
            round
            :disabled="forbidden[index]"
            @click="receiveOrder(orderItem, index)"
          >
            {{ forbidden[index] ? "已收货" : "确认收货" }}
          </el-button>
        </div>
        <el-divider class="my_divider"></el-divider>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import BScroll from "better-scroll";
import { MessageBox, Toast } from "mint-ui";
import OrderItem from "../../../components/OrderItem/OrderItem.vue";

export default {
  data() {
    return {
      forbidden: [], //代表订单是否禁用
    };
  },
  mounted() {
    this.$store.dispatch("getOrdersDoing", () => {
    
      this._initScroll();
    });
  },
  computed: {
    ...mapState(["ordersDoing"]),
  },
  methods: {
    _initScroll() {
      if (!this.scroll) {
        this.scroll = new BScroll(".order_doing", {
          probeType: 2, // 因为惯性滑动不会触发
          click: true,
        });
      } else {
        this.scroll.refresh();
      }
    },
    receiveOrder(order, index)   
     {

       let result=this.$store.dispatch("sendReceiveOrder", order,()=>{
        this.forbidden[index] = true;
        this.$forceUpdate();
        return ;  
       })

      // Toast("操作失败，请稍后再试");
   },
  },
  watch: {
    ordersDoing() {
      if (this.ordersDoing.length) {
        this._initScroll();
      }
    },
  },


  
  components: {
    OrderItem,
  },

};
</script>

<style lang="stylus" rel="stylesheet/stylus">
.my_divider {
  margin: 12px 0;
}

.order_doing {
  height: 100%;
}

.scrall_children {
  // margin-bottom: 500px;
}

.button {
  display: flex;
  justify-content: right;
  // right :10px
}

.scrall_children {
  background: #fff;
}
</style>
