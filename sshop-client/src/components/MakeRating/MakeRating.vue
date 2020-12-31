<template>
  <div class="good" v-if="isShow">
    <div class="good-content">
      <div class="back" @click="toggleShow">
        <i class="iconfont icon-xiazai6-copy"></i>
      </div>
      <div class="content">
        <h1 class="title">订单{{ order.orderid }}评价</h1>
      </div>
      <div class="rate">
        <el-rate
          v-model="value"
          :texts="texts"
          show-text
          :allow-half="false"
          :max="10"
        >
        </el-rate>
      </div>
      <div class="area_father">
        <el-input
          type="textarea"
          :rows="6"
          placeholder="请输入内容"
          v-model="textarea"
          class="mytextarea"
        >
        </el-input>
      </div>

      <div class="tijiao" @click="makeRating(value, textarea)">
        <i class="iconfont icon-tijiao-copy"></i>
      </div>
    </div>

    <div class="good-cover" @click="toggleShow"></div>
  </div>
</template>

<script>
import { Toast } from "mint-ui";

export default {
  props: {
    order: Object,
  },

  data() {
    return {
      textarea: "",
      isShow: false,
      value: 6,
      half: true,
      texts: [
        "1分",
        "2分",
        "3分",
        "4分",
        "5分",
        "6分",
        "7分",
        "8分",
        "9分",
        "10分",
      ],
    };
  },

  methods: {
    toggleShow() {
      this.isShow = !this.isShow;
    },
    async makeRating(value, text) {


      let ratescore = value;
 

 

      const result = await this.$store.dispatch("makeRating", {
        order: this.order,
        ratescore,
        text: this.textarea,
      });
      if (result === 1) {
  
        Toast({
          message: "操作成功",
          iconClass: "icon icon-success",
        });
        this.toggleShow();
        return;
      }
 
      Toast("系统异常，请稍后再试");
    },
  },
};
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
@import '../../common/stylus/mixins.styl';

.tijiao {
  display: flex;
  justify-content: center;
}

.area_father {
  display: flex;
  justify-content: center;
}

.mytextarea {
  width: 75%;
  margin: 30px 0;
}

.icon-xiazai6-copy {
  margin-top: 10px;
}

.rate {
  display: flex;
  justify-content: center;
}

.good {
  position: fixed;
  left: 0;
  top: 0;
  bottom: 48px;
  z-index: 101;
  width: 100%;

  &.fade-enter-active, &.fade-leave-active {
    transition: opacity 0.5s;
  }

  &.fade-enter, &.fade-leave-to {
    opacity: 0;
  }

  .good-content {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    width: 80%;
    height: 65%;
    z-index: 66;
    background: #fff;
    border-radius: 5px;

    .image-header {
      position: relative;
      width: 100%;
      height: 0;
      padding-top: 100%;

      img {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
      }

      .foodpanel-desc {
        font-size: 10px;
        color: #ddd;
        letter-spacing: 0;
        position: absolute;
        bottom: 0;
        left: 0;
        right: 0;
        padding: 0 10px 10px;
      }

      .back {
        margin-top: 20px;
        position: absolute;
        top: 10px;
        left: 0;

        .icon-xiazai6 {
          display: block;
          padding: 10px;
          font-size: 20px;
          color: #fff;
        }
      }
    }

    .content {
      position: relative;
      padding: 18px;
      display: flex;
      justify-content: center;

      .title {
        line-height: 14px;
        margin-bottom: 8px;
        font-size: 14px;
        font-weight: 700;
        color: rgb(7, 17, 27);
      }

      .detail {
        margin-bottom: 18px;
        line-height: 10px;
        height: 10px;
        font-size: 0;

        .sell-count, .rating {
          font-size: 10px;
          color: rgb(147, 153, 159);
        }

        .sell-count {
          margin-right: 12px;
        }
      }

      .price {
        font-weight: 700;
        line-height: 24px;

        .now {
          margin-right: 8px;
          font-size: 14px;
          color: rgb(240, 20, 20);
        }

        .old {
          text-decoration: line-through;
          font-size: 10px;
          color: rgb(147, 153, 159);
        }
      }

      .cartcontrol-wrapper {
        position: absolute;
        right: 12px;
        bottom: 12px;
      }

      .buy {
        position: absolute;
        right: 18px;
        bottom: 18px;
        z-index: 10;
        height: 24px;
        line-height: 24px;
        padding: 0 12px;
        box-sizing: border-box;
        border-radius: 12px;
        font-size: 10px;
        color: #fff;
        background: rgb(0, 160, 220);

        &.fade-transition {
          transition: all 0.2s;
          opacity: 1;
        }

        &.fade-enter, &.fade-leave {
          opacity: 0;
        }
      }
    }
  }

  .good-cover {
    position: absolute;
    top: 0;
    right: 0;
    bottom: -48px;
    left: 0;
    z-index: 55;
    background-color: rgba(0, 0, 0, 0.5);
  }
}
</style>
