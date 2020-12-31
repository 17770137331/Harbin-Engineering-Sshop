<template>
  <div>
    <section class="profile">
      <HeaderTop title="我的"></HeaderTop>
      <section class="profile-number">
        <div class="profile-link">
          <el-upload
            class="avatar-uploader profile_image"
            action="#"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :on-progress="upLoad"
            :before-upload="beforeAvatarUpload"
            v-show="userInfo.userid"
          >
            <img
              v-if="userInfo.avatar"
              :src="imageUrl"
              class="avatar myavatar"
              @click="getCredential()"
            />

            <span v-else>
              <i class="iconfont icon-user" @click="getCredential()"></i>
            </span>
          </el-upload>

          <div class="user-info">
            <p class="user-info-top" v-if="!userInfo.userid" @click="login">
              {{ userInfo.nickname || "登录/注册" }}
            </p>
            <p class="user-info-top" v-else>
              <el-input
                v-model="nameTemp"
                placeholder=""
                class="myinputname"
                :disabled="inputdisable"
                @blur="updateProfile"
                ref="mynickname"
              ></el-input>
              <!-- {{ userInfo.nickname || "登录/注册" }} -->
              <span class="arrow xiugai" @click="toggleInput">
                <i class="iconfont icon-xiugai"></i>
              </span>
            </p>
            <p>
              <span class="user-icon">
                <i class="iconfont icon-msnui-tel icon-phone"></i>
              </span>
              <span class="icon-mobile-number">{{
                userInfo.phone || "暂无绑定手机号"
              }}</span>
            </p>
          </div>
          <span class="arrow">
            <i class="iconfont icon-previewright"></i>
          </span>
        </div>
        <!-- </router-link> -->
      </section>
      <section class="profile_info_data border-1px">
        <ul class="info_data_list">
          <a href="javascript:" class="info_data_link">
            <span class="info_data_top"><span>0.00</span>元</span>
            <span class="info_data_bottom">我的余额</span>
          </a>
          <a href="javascript:" class="info_data_link">
            <span class="info_data_top"><span>0</span>个</span>
            <span class="info_data_bottom">我的优惠</span>
          </a>
          <a href="javascript:" class="info_data_link">
            <span class="info_data_top"><span>0</span>分</span>
            <span class="info_data_bottom">我的积分</span>
          </a>
        </ul>
      </section>
      <section class="profile_my_order border-1px">
        <!-- 我的订单 -->
        <a href="javascript:" class="my_order">
          <span>
            <i class="iconfont icon-dingdan"></i>
          </span>
          <div class="my_order_div">
            <span>我的订单</span>
            <span class="my_order_icon">
              <i class="iconfont icon-previewright"></i>
            </span>
          </div>
        </a>
        <!-- 会员卡 -->
        <a href="javascript:" class="my_order">
          <span>
            <i class="iconfont icon-huiyuan-"></i>
          </span>
          <div class="my_order_div">
            <span>超级会员卡</span>
            <span class="my_order_icon">
              <i class="iconfont icon-previewright"></i>
            </span>
          </div>
        </a>
      </section>
      <section class="profile_my_order border-1px">
        <!-- 服务中心 -->
        <a href="javascript:" class="my_order">
          <span>
            <i class="iconfont icon-chengshi"></i>
          </span>
          <div class="my_order_div">
            <span>服务中心</span>
            <span class="my_order_icon">
              <i class="iconfont icon-previewright"></i>
            </span>
          </div>
        </a>
      </section>

      <section class="profile_my_order border-1px">
        <mt-button
          type="danger"
          style="width: 100%"
          v-if="userInfo.userid"
          @click="logout"
          >退出登录</mt-button
        >
      </section>
    </section>
  </div>
</template>

<script>
import COS from "cos-js-sdk-v5"; //脚手架安装
import HeaderTop from "../../components/HeaderTop/HeaderTop.vue";
import { mapState } from "vuex";
import { MessageBox, Toast } from "mint-ui";
import { sendUpdateProfile } from "../../api";

export default {
  data() {
    return {
      nameTemp: "",
      fit: "cover",
      imageUrl: "",
      uploadFileName: "",
      uploadFile: "",
      inputdisable: true,
    };
  },
  created() {
    this.$store.dispatch("getUserInfo");
  },
  components: {
    HeaderTop,
  },
  computed: {
    ...mapState(["userInfo", "credential"]),
  },
  methods: {
    async updateProfile() {
      const result = await sendUpdateProfile(this.nameTemp, this.imageUrl);
      if (result.code === 0) {
        Toast({
          message: "修改信息成功",
          iconClass: "icon icon-success",
        });
        return;
      }
      Toast("操作失败，请稍后再试");
    },

    toggleInput() {
      this.inputdisable = !this.inputdisable;
      const myinput = this.$refs.mynickname;
      setTimeout(() => {
        myinput.focus();
      }, 300);
    },

    getCredential() {
      //异步请求，我不确定还要不要再整个回调函数
      this.$store.dispatch("getCredential");
    },
    login() {
      this.$router.replace("/login");
    },
    logout() {
      MessageBox.confirm("确认退出吗?").then(
        (action) => {
          // 请求退出
          this.$store.dispatch("logout");
          Toast("退出成功");
          location.reload();
        },
        (action) => {}
      );
    },

    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },

    beforeAvatarUpload(file) {
      this.uploadFile = file;
      this.uploadFileName = file.name;
      const isJPG = file.type === "image/jpeg" || "png";
      const isLt2M = file.size / 1024 / 1024 < 5;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 或者 PNG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 5MB!");
      }
      return isJPG && isLt2M;
    },

    async upLoad() {
      var that = this;
      var cos = new COS({
        getAuthorization: function (options, callback) {
          callback({
            TmpSecretId: that.credential.tmpSecretId,
            TmpSecretKey: that.credential.tmpSecretKey,
            XCosSecurityToken: that.credential.sessionToken,
            StartTime: that.credential.startTime,
            ExpiredTime: that.credential.expiredTime,
            expiration: that.credential.expiration,
            requestId: that.credential.requestId,
          });
        },
      });
      cos.putObject(
        {
          Bucket: "sshop-1302763867",
          Region: "ap-nanjing",
          Key: "/proFile/" + that.userInfo.userid + "/" + that.uploadFileName,
          StorageClass: "STANDARD",
          Body: that.uploadFile, // 上传文件对象
          onProgress: function (progressData) {},
        },
        function (err, data) {
          if (data && data.statusCode === 200) {
            const url =
              "https://sshop-1302763867.cos.ap-nanjing.myqcloud.com" +
              "/proFile/" +
              that.userInfo.userid +
              "/" +
              that.uploadFileName; //根据对应路径获取到url

            that.imageUrl = url;
            that.updateProfile();
          }
        }
      );
    },
  },
  watch: {
    userInfo() {
      this.imageUrl = this.userInfo.avatar;
      this.nameTemp = this.userInfo.nickname;
      this.$forceUpdate();
    },
    credential() {},
  },
};
</script>

<style lang="stylus" rel="stylesheet/stylus">
@import '../../common/stylus/mixins.styl';

.xiugai {
  width: 12px !important;
  height: 12px !important;
  position: absolute !important;
  right: 140px !important;
  top: 35% !important;
}

.myinputname {
  input, .el-input.is-disabled .el-input__inner {
    background: #02a774 !important;
    color: white !important;
    border: none !important;
    font-size: 20px !important;
    font-weight: bold !important;
    cursor: default !important;
  }
}

.el-input__inner {
  background: #02a774;
  color: white;
  border: none;
  font-size: 20px;
  font-weight: bold;
}

.myavatar {
  width: 100%;
  height: 60px;
}

.profile {
  width: 100%;
  overflow: hidden;

  .header { // 头部公共css
    background-color: #02a774;
    position: fixed;
    z-index: 100;
    left: 0;
    top: 0;
    width: 100%;
    height: 45px;

    .header_search {
      position: absolute;
      left: 15px;
      top: 50%;
      transform: translateY(-50%);
      width: 10%;
      height: 50%;

      .iconfont {
        font-size: 22px;
        color: #fff;
      }
    }

    .header_title {
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 30%;
      color: #fff;
      font-size: 22px;
      text-align: center;
    }
  }

  .profile-number {
    margin-top: 45.5px;

    .profile-link {
      clearFix();
      position: relative;
      display: block;
      background: #02a774;
      padding: 20px 10px;

      .profile_image {
        float: left;
        width: 60px;
        height: 60px;
        // border-radius: 50%;
        overflow: hidden;
        vertical-align: top;

        .icon-user {
          background: #e4e4e4;
          font-size: 62px;
        }
      }

      .user-info {
        float: left;
        margin-top: 8px;
        margin-left: 15px;

        p {
          font-weight: 700;
          font-size: 18px;
          color: #fff;

          &.user-info-top {
            padding-bottom: 8px;
          }

          .user-icon {
            display: inline-block;
            margin-left: -15px;
            margin-right: 5px;
            width: 20px;
            height: 20px;

            .icon-phone {
              font-size: 30px;
              vertical-align: text-top;
            }
          }

          .icon-msnui-tel {
            font-size: 14px;
            color: #fff;
          }
        }
      }

      .arrow {
        width: 12px;
        height: 12px;
        position: absolute;
        right: 15px;
        top: 40%;

        .icon-previewright {
          color: #fff;
          font-size: 5px;
        }
      }
    }
  }

  .profile_info_data {
    bottom-border-1px(#e4e4e4);
    width: 100%;
    background: #fff;
    overflow: hidden;

    .info_data_list {
      clearFix();

      .info_data_link {
        float: left;
        width: 33%;
        text-align: center;
        border-right: 1px solid #f1f1f1;

        .info_data_top {
          display: block;
          width: 100%;
          font-size: 14px;
          color: #333;
          padding: 15px 5px 10px;

          span {
            display: inline-block;
            font-size: 30px;
            color: #f90;
            font-weight: 700;
            line-height: 30px;
          }
        }

        .info_data_bottom {
          display: inline-block;
          font-size: 14px;
          color: #666;
          font-weight: 400;
          padding-bottom: 10px;
        }
      }

      .info_data_link:nth-of-type(2) {
        .info_data_top {
          span {
            color: #ff5f3e;
          }
        }
      }

      .info_data_link:nth-of-type(3) {
        border: 0;

        .info_data_top {
          span {
            color: #6ac20b;
          }
        }
      }
    }
  }

  .profile_my_order {
    top-border-1px(#e4e4e4);
    margin-top: 10px;
    background: #fff;

    .my_order {
      display: flex;
      align-items: center;
      padding-left: 15px;

      >span {
        display: flex;
        align-items: center;
        width: 20px;
        height: 20px;

        >.iconfont {
          margin-left: -10px;
          font-size: 30px;
        }

        .icon-dingdan {
          color: #02a774;
        }

        .icon-jifen {
          color: #ff5f3e;
        }

        .icon-huiyuan- {
          color: #f90;
        }

        .icon-chengshi {
          color: #02a774;
        }
      }

      .my_order_div {
        width: 100%;
        border-bottom: 1px solid #f1f1f1;
        padding: 18px 10px 18px 0;
        font-size: 16px;
        color: #333;
        display: flex;
        justify-content: space-between;

        span {
          display: block;
        }

        .my_order_icon {
          width: 10px;
          height: 10px;

          .icon-previewright {
            color: #bbb;
            font-size: 10px;
          }
        }
      }
    }
  }
}
</style>
