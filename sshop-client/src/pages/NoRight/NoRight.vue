<template>
  <div class="order_father">
     <!-- </div>  -->
    <section class="order">
      <HeaderTop title="订单列表" class="order_title"></HeaderTop>
      <div>
        <section class="order_no_login">
          <!-- <img src="./images/order/person.png" /> -->
          <h3>登录后查看该页</h3>
          <button @click="toLogin">立即登陆</button>
        </section> 
      </div>
     </section> 
    <div class="line"></div>
  </div>
</template>

<script>
import HeaderTop from "../../components/HeaderTop/HeaderTop.vue";
import { mapState } from "vuex";

export default {
  data() {
    return {
      
    };
  },

created(){
},

  mounted() {
  },
  computed: {
        ...mapState(["userInfo"]),
  },
  methods: {
    toLogin(){
        this.$router.push("/login")
    },
    handleOpen(key, keyPath) {
    },
    handleClose(key, keyPath) {

    },
  },
  watch:{
     userInfo(){

     }
  },
  components: {
    HeaderTop,
  },
};
</script>

<style lang="stylus" rel="stylesheet/stylus">
@import '../../common/stylus/mixins.styl';

.el-menu-demo {
  background: #ffffff;
  z-index: 8999;
  position: relative;
  // padding: 10px 10px 0;
}

.order_father {
  height: 100%;
}

.order { // 订单
  width: 100%;

  .order_title {
    position: relative;
  }

  .header { // 头部公共css
    background-color: #02a774;
    position: relative;
    z-index: 100;
    left: 0;
    top: 0;
    width: 100%;
    height: 45px;

    .header_title {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 30%;
      color: #fff;
    }
  }

  .order_no_login {
    padding-top: 140px;
    width: 60%;
    margin: 0 auto;
    text-align: center;

    >img {
      display: block;
      width: 100%;
      height: 30%;
    }

    >h3 {
      padding: 10px 0;
      font-size: 17px;
      color: #6a6a6a;
    }

    >button {
      display: inline-block;
      background: #02a774;
      font-size: 14px;
      color: #fff;
      border: 0;
      outline: none;
      border-radius: 5px;
      padding: 10px 20px;
    }
  }
}
</style>
