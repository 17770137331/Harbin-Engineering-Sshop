<template>
  <div class="good_container goods-wrapper" ref="wrapper" id="goodlist">
    <div>
      <ul class="good_list" v-if="goods.length">
        <li
          class="good_li border-1px"
          v-for="(good, index) in goods"
          :key="index"
          @click="
            $router.push({
              path: '/gooddetail',
              query: {
                goodid: good.goodid,
              },
            })
          "
        >
          <a>
            <div class="good_left">
              <img class="good_img" :src="baseImgUrl + good.imglink" />
            </div>
            <div class="good_right">
              <section class="good_detail_header">
                <h4 class="good_title ellipsis">{{ good.title }}</h4>

                <div class="good_detail_ul">
                  <span class="supports"> 点击量： {{ good.likes }} </span>
                </div>
              </section>
              <section class="good_rating_order">
                <section class="good_rating_order_left">
                  <div class="good_price">￥{{ good.price }}</div>
                </section>
                <section class="good_rating_order_right">
                  <span class="delivery_style delivery_right">{{
                    good.nickname
                  }}</span>
                </section>
              </section>
              <section class="good_distance">
                <p class="good_delivery_msg">
                  <span>{{ good.comment }}</span>
                  <span class="segmentation"></span>
                </p>
              </section>
            </div>
          </a>
        </li>
      </ul>
      <div class="tianchong"></div>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import Star from "../Star/Star.vue";
import BScroll from "better-scroll";
export default {
  data() {
    return {
      baseImgUrl: "",
      scrollY: 0, // 商品信息的Y 轴滑动的坐标(越往下数值越小)
    };
  },
  computed: {
    ...mapState(["goods"]),
  },
  components: {
    Star,
  },
  watch: {
    goods(value) {},
  },
  mounted() {
    this.$store.dispatch("getGoods", () => {
      // 数据更新后执行
      this.$nextTick(() => {
        // 列表数据更新显示后执行
        this._initScroll();
      });
    });
  },

  methods: {
    _initScroll() {
      this.goodsScroll = new BScroll(".goods-wrapper", {
        probeType: 2, // 因为惯性滑动不会触发
        click: true,
      });

      this.goodsScroll.on("scroll", ({ x, y }) => {
        this.scrollY = Math.abs(y);
      });
      this.goodsScroll.on("scrollEnd", ({ x, y }) => {
        this.scrollY = Math.abs(y);
      });
    },
  },
};
</script>




<style lang="stylus" rel="stylesheet/stylus">
@import '../../common/stylus/mixins.styl';

.tianchong {
  height: 350px;
  background: white;
}

.good_container {
  margin-bottom: 50px;
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

          .good_title {
            float: left;
            width: 200px;
            color: #333;
            font-size: 16px;
            line-height: 16px;
            font-weight: 700;

            &::before {
              content: '实惠！';
              display: inline-block;
              font-size: 11px;
              line-height: 11px;
              color: #333;
              background-color: #ffd930;
              padding: 2px 2px;
              border-radius: 2px;
              margin-right: 5px;
            }
          }

          .good_detail_ul {
            float: right;
            margin-top: 3px;

            .supports {
              float: left;
              font-size: 10px;
              color: #999;
              border: 1px solid #f1f1f1;
              padding: 0 2px;
              border-radius: 2px;
            }
          }
        }

        .good_rating_order {
          clearFix();
          width: 100%;
          margin-top: 18px;
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
              color: red;
              transform: scale(1);
            }
          }

          .good_rating_order_right {
            float: right;
            font-size: 0;

            .delivery_style {
              transform-origin: 35px 0;
              transform: scale(0.7);
              display: inline-block;
              font-size: 12px;
              padding: 1px;
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
          clearFix();
          width: 100%;
          font-size: 12px;

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
}
</style>
