"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

/*
State:状态对象,相当于核心文件哦
 */
var _default = {
  address: {},
  // 地址相关信息对象
  categorys: [],
  // 二手商品的分类数组
  shops: [],
  // 商家数组
  userInfo: {},
  // 用户信息
  goods: [],
  // 商品列表
  gooddetail: {},
  //具体的商品的信息
  ratings: [],
  // 商家评价列表
  info: {},
  // 商家信息
  cartGoods: [],
  // 购物车中食物的列表
  searchGoods: [],
  // 搜索得到的商品列表
  sellerInfo: {},
  //表示商家的具体信息
  buygood: {},
  //表示用户添加进购物车的good商品
  sellerRatings: [],
  //代表商家的具体评价信息
  credential: {},
  //代表用户获得的临时图片上传的权限值
  aliveActivities: [],
  //代表用户在商品发布界面可选的活动
  ordersAll: [],
  //所有的订单列表
  ordersDoing: [],
  //所有正在进行的订单列表
  ordersUnrating: [],
  //所有未评价的订单列表
  ordersFinished: [] //所有已经完成的订单列表

};
exports["default"] = _default;