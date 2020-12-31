<template>
  <div class="good_container goods-wrapper" ref="wrapper" id="goodlist">
    <!-- 如果有值，就进行图标列表的循环 -->

    <li class="good_li border-1px" @click="$router.push('#')">
      <a>
        <!-- 进行图片的显示 -->
        <div class="good_left">
          <img class="good_img" :src="baseImgUrl + orderItem.imglink" />
        </div>
        <div class="good_right">
          <section class="good_detail_header">
            <!-- 商品的名字 -->
            <h4 class="good_title ellipsis">{{ orderItem.goodtitle }}</h4>
            <!-- 提供的保障 -->
            <div class="good_detail_ul">
              <span class="supports"> {{ "￥" + orderItem.singleprice }} </span>
            </div>
          </section>
          <section class="good_rating_order">
            <section class="good_rating_order_left">
              <div class="good_price">订单号：{{ orderItem.orderid }}</div>
            </section>
            <section class="good_rating_order_right">
              <span class="delivery_style delivery_right">
                x{{ orderItem.number }}
              </span>
            </section>
          </section>
          <section class="good_distance">
            <div>
              <div>优惠：{{ 0 }}</div>
              <div>总价：{{ orderItem.totalprice }}</div>
            </div>
          </section>
        </div>
      </a>
    </li>
  </div>
</template>
<script>
export default {
  data() {
    return {
      baseImgUrl: "",
    };
  },
  props: {
    orderItem: Object,
  },
  computed: {},
  components: {},
  watch: {},
  mounted() {},

  methods: {},
};
</script>




<style lang="stylus" rel="stylesheet/stylus">
@import '../../common/stylus/mixins.styl';

.good_title {
  float: left;
  width: 200px;
  color: #333;
  font-size: 16px;
  line-height: 16px;
  font-weight: 700;
}

.good_container {
  margin-bottom: 0px 0px 0px 0px;
  height: 100%;

  .good_li {
    bottom-border-1px(#f1f1f1);
    width: 100%;

    >a {
      clearFix();
      display: block;
      box-sizing: border-box;
      padding: 15px 8px;
      width: 100%;

      .good_left {
        float: left;
        box-sizing: border-box;
        width: 23%;
        height: 75px;
        padding-right: 10px;

        .good_img {
          display: block;
          width: 100%;
          height: 100%;
        }
      }

      .good_right {
        float: right;
        width: 77%;

        .good_detail_header {
          clearFix();
          width: 100%;
        }

        .good_detail_ul {
          float: right;
          margin-top: 3px;
        }
      }

      .good_rating_order {
        clearFix();
        width: 100%;
        margin-top: 14px;
        margin-bottom: 8px;

        .good_rating_order_left {
          float: left;
          color: #ff9a0d;

          .rating_section {
            float: left;
            font-size: 10px;
            color: #ff6000;
            margin-left: 4px;
          }

          .good_price {
            float: left;
            font-size: 20px;
            color: #090909;
            // transform: scale(1);
          }
        }

        .good_rating_order_right {
          float: right;
          font-size: 0;

          .delivery_style {
            transform-origin: 35px 0;
            transform: scale(0.9);
            display: inline-block;
            font-size: 20px;
            padding: 1px 3px 1px 3px;
            border-radius: 2px;
          }

          .delivery_left {
            color: #fff;
            margin-right: -10px;
            background-color: #02a774;
            border: 1px solid #02a774;
          }

          .delivery_right {
            color: #02a774;
            border: 1px solid #02a774;
          }
        }
      }

      .good_distance {
        // clearFix();
        width: 100%;
        font-size: 12px;
        display: flex;
        justify-content: space-between;

        .good_delivery_msg {
          float: left;
          transform-origin: 0;
          transform: scale(0.9);
          color: #666;
        }

        .segmentation {
          color: #ccc;
        }
      }
    }
  }
}
</style>
