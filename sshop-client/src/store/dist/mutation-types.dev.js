"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.REMOVE_CART_GOOD = exports.MAKE_RATING = exports.REMOVE_ORDERS_DOING = exports.RECEIVE_ORDERS_FINISHED = exports.RECEIVE_ORDERS_UNRATING = exports.RECEIVE_ORDERS_DOING = exports.RECEIVE_ORDERS_ALL = exports.RECEIVE_ALIVE_ACTIVITIES = exports.RECEIVE_CREDENTIAL = exports.RECEIVE_SELLER_RATINGS = exports.RECEIVE_BUY_GOOD = exports.RECEIVE_SELLER_INFO = exports.RECEIVE_GOOD_DETAIL = exports.RECEIVE_SEARCH_GOODS = exports.CLEAR_CART = exports.DECREMENT_GOOD_COUNT = exports.INCREMENT_GOOD_COUNT = exports.RECEIVE_INFO = exports.RECEIVE_RATINGS = exports.RESET_USER_INFO = exports.RECEIVE_USER_INFO = exports.RECEIVE_SHOPS = exports.RECEIVE_GOODS = exports.RECEIVE_CATEGORYS = exports.RECEIVE_ADDRESS = void 0;

/*
包含n个mutation的type名称常量
 */
var RECEIVE_ADDRESS = 'receive_address'; // 接收地址信息

exports.RECEIVE_ADDRESS = RECEIVE_ADDRESS;
var RECEIVE_CATEGORYS = 'receive_categorys'; // 接收二手商品分类数组

exports.RECEIVE_CATEGORYS = RECEIVE_CATEGORYS;
var RECEIVE_GOODS = 'receive_goods'; // 接收二手物品数组

exports.RECEIVE_GOODS = RECEIVE_GOODS;
var RECEIVE_SHOPS = 'receive_shops'; // 接收商家数组

exports.RECEIVE_SHOPS = RECEIVE_SHOPS;
var RECEIVE_USER_INFO = 'receive_user_info'; // 接收用户信息

exports.RECEIVE_USER_INFO = RECEIVE_USER_INFO;
var RESET_USER_INFO = 'reset_user_info'; // 重置用户信息

exports.RESET_USER_INFO = RESET_USER_INFO;
var RECEIVE_RATINGS = 'receive_ratings'; // 接收商家评价数组

exports.RECEIVE_RATINGS = RECEIVE_RATINGS;
var RECEIVE_INFO = 'receive_info'; // 接收商家信息

exports.RECEIVE_INFO = RECEIVE_INFO;
var INCREMENT_GOOD_COUNT = 'increment_good_count'; // 增加food中的count，state中其实没有这个值

exports.INCREMENT_GOOD_COUNT = INCREMENT_GOOD_COUNT;
var DECREMENT_GOOD_COUNT = 'decrement_good_count'; // 减少food中的count,state中其实没有这个值

exports.DECREMENT_GOOD_COUNT = DECREMENT_GOOD_COUNT;
var CLEAR_CART = 'clear_cart'; // 清空购物车

exports.CLEAR_CART = CLEAR_CART;
var RECEIVE_SEARCH_GOODS = 'receive_search_goods'; // 接收搜索的商家数组

exports.RECEIVE_SEARCH_GOODS = RECEIVE_SEARCH_GOODS;
var RECEIVE_GOOD_DETAIL = 'receive_good_detail'; // 接收具体的商品信息

exports.RECEIVE_GOOD_DETAIL = RECEIVE_GOOD_DETAIL;
var RECEIVE_SELLER_INFO = 'receive_seller_info'; //接收的具体的商家的信息

exports.RECEIVE_SELLER_INFO = RECEIVE_SELLER_INFO;
var RECEIVE_BUY_GOOD = 'receive_buy_good'; //表示用户所加入到购物车的商品

exports.RECEIVE_BUY_GOOD = RECEIVE_BUY_GOOD;
var RECEIVE_SELLER_RATINGS = 'receive_seller_ratings'; //表示获取商家的具体的评价信息

exports.RECEIVE_SELLER_RATINGS = RECEIVE_SELLER_RATINGS;
var RECEIVE_CREDENTIAL = 'receive_credential';
exports.RECEIVE_CREDENTIAL = RECEIVE_CREDENTIAL;
var RECEIVE_ALIVE_ACTIVITIES = 'receive_alive_activities'; //表示用户在商品发布界面可选的活动集合

exports.RECEIVE_ALIVE_ACTIVITIES = RECEIVE_ALIVE_ACTIVITIES;
var RECEIVE_ORDERS_ALL = 'receive_orders_all'; //所有订单

exports.RECEIVE_ORDERS_ALL = RECEIVE_ORDERS_ALL;
var RECEIVE_ORDERS_DOING = 'receive_orders_doing'; //未完成订单

exports.RECEIVE_ORDERS_DOING = RECEIVE_ORDERS_DOING;
var RECEIVE_ORDERS_UNRATING = 'receive_orders_unrating'; //未评论订单

exports.RECEIVE_ORDERS_UNRATING = RECEIVE_ORDERS_UNRATING;
var RECEIVE_ORDERS_FINISHED = 'receive_orders_finished'; //已完成订单

exports.RECEIVE_ORDERS_FINISHED = RECEIVE_ORDERS_FINISHED;
var REMOVE_ORDERS_DOING = 'remove_orders_doing'; //要移除的刚刚确认收货的订单

exports.REMOVE_ORDERS_DOING = REMOVE_ORDERS_DOING;
var MAKE_RATING = 'make_rating'; //表示发表评价

exports.MAKE_RATING = MAKE_RATING;
var REMOVE_CART_GOOD = 'remove_cart_good'; //表示去掉某一个购物车中的物品

exports.REMOVE_CART_GOOD = REMOVE_CART_GOOD;