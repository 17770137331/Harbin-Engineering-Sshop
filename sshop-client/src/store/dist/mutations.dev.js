"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _vue = _interopRequireDefault(require("vue"));

var _mutationTypes = require("./mutation-types");

var _MAKE_RATING$REMOVE_O;

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

var _default = (_MAKE_RATING$REMOVE_O = {}, _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.MAKE_RATING, function (state, _ref) {
  var order = _ref.order;
  state.ordersUnrating.splice(state.ordersUnrating.indexOf(order), 1);
  var flag = -1;

  for (var index = 0; index < state.ordersAll.length; index++) {
    if (order === state.ordersAll[index]) {
      flag = index;
      break;
    }
  }

  order.status = 3;
  state.ordersFinished.push(order);
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.REMOVE_ORDERS_DOING, function (state, _ref2) {
  var order = _ref2.order;
  state.ordersDoing.splice(state.ordersDoing.indexOf(order), 1);
  var flag = -1;

  for (var index = 0; index < state.ordersAll.length; index++) {
    if (order === state.ordersAll[index]) {
      flag = index;
      break;
    }
  }

  order.status = 2;
  state.ordersUnrating.push(order);
  state.ordersAll[flag].status = 2;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_ORDERS_ALL, function (state, _ref3) {
  var ordersAll = _ref3.ordersAll;
  state.ordersAll = ordersAll;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_ORDERS_DOING, function (state, _ref4) {
  var ordersDoing = _ref4.ordersDoing;
  state.ordersDoing = ordersDoing;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_ORDERS_UNRATING, function (state, _ref5) {
  var ordersUnrating = _ref5.ordersUnrating;
  state.ordersUnrating = ordersUnrating;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_ORDERS_FINISHED, function (state, _ref6) {
  var ordersFinished = _ref6.ordersFinished;
  state.ordersFinished = ordersFinished;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_ALIVE_ACTIVITIES, function (state, _ref7) {
  var aliveActivities = _ref7.aliveActivities;
  state.aliveActivities = aliveActivities;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_CREDENTIAL, function (state, _ref8) {
  var credential = _ref8.credential;
  state.credential = credential;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_ADDRESS, function (state, _ref9) {
  var address = _ref9.address;
  state.address = address;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_CATEGORYS, function (state, _ref10) {
  var categorys = _ref10.categorys;
  state.categorys = categorys;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_SHOPS, function (state, _ref11) {
  var shops = _ref11.shops;
  state.shops = shops;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_USER_INFO, function (state, _ref12) {
  var userInfo = _ref12.userInfo;
  state.userInfo = userInfo;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RESET_USER_INFO, function (state) {
  localStorage.setItem('orderFlag', 0);
  localStorage.setItem('putFlag', 0);
  state.userInfo = {};
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_INFO, function (state, _ref13) {
  var info = _ref13.info;
  state.info = info;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_RATINGS, function (state, _ref14) {
  var ratings = _ref14.ratings;
  state.ratings = ratings;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_SELLER_RATINGS, function (state, _ref15) {
  var sellerRatings = _ref15.sellerRatings;
  state.sellerRatings = sellerRatings;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_GOODS, function (state, _ref16) {
  var goods = _ref16.goods;
  state.goods = goods;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_GOOD_DETAIL, function (state, _ref17) {
  var gooddetail = _ref17.gooddetail;
  state.gooddetail = gooddetail;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_BUY_GOOD, function (state, _ref18) {
  var buygood = _ref18.buygood;
  state.buygood = buygood;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.INCREMENT_GOOD_COUNT, function (state, _ref19) {
  var buygood = _ref19.buygood;

  //如果是第一次增加，我们就新建立一个这样子的属性，同时初始化值为1
  if (!buygood.count) {
    _vue["default"].set(buygood, 'count', 1); // 将food添加到cartGoods中
    //cartFoods表示购物车的商品


    state.cartGoods.push(buygood);
  } else {
    buygood.count++;
  }
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.DECREMENT_GOOD_COUNT, function (state, _ref20) {
  var buygood = _ref20.buygood;

  //只有它是有值的时候，才去减去这个数量
  if (buygood.count) {
    // 只有有值才去减
    buygood.count--;

    if (buygood.count === 0) {
      // 将food从cartGoods中移除
      //从购物车中将这个商品给去掉
      state.cartGoods.splice(state.cartGoods.indexOf(buygood), 1);
    }
  }
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.REMOVE_CART_GOOD, function (state, _ref21) {
  var cartgood = _ref21.cartgood;
  state.cartGoods.splice(state.cartGoods.indexOf(cartgood), 1);
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.CLEAR_CART, function (state) {
  //这两个都要清除的亲，否则会请理的不干净
  // 清除Good中的count
  state.cartGoods.forEach(function (good) {
    good.count = 0;
  }); // 移除购物车中所有购物项

  state.cartGoods = [];
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_SEARCH_GOODS, function (state, _ref22) {
  var searchGoods = _ref22.searchGoods;
  state.searchGoods = searchGoods;
}), _defineProperty(_MAKE_RATING$REMOVE_O, _mutationTypes.RECEIVE_SELLER_INFO, function (state, _ref23) {
  var sellerInfo = _ref23.sellerInfo;
  state.sellerInfo = sellerInfo;
}), _MAKE_RATING$REMOVE_O);

exports["default"] = _default;