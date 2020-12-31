"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.sendUpdateProfile = exports.sendMakeRating = exports.sendReceiveOrder = exports.reqOrdersFinished = exports.reqOrdersUnrating = exports.reqOrdersDoing = exports.reqOrdersAll = exports.confirmOrder = exports.sendPutGood = exports.reqAliveActivies = exports.reqCredential = exports.reqSellerInfo = exports.reqGoodDetail = exports.getGraph = exports.reqSellerRatings = exports.reqLogout = exports.reqUserInfo = exports.reqSmsLogin = exports.reqRegister = exports.reqSendCode = exports.reqPwdLogin = exports.reqSearchGoods = exports.reqGoods = exports.reqCategorys = void 0;

var _ajax = _interopRequireDefault(require("./ajax"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

/*
与后台交互模块 （依赖已封装的ajax函数）
 */
// const BASE_URL = 'http://local:4000'
var BASE_URL = '/api';

var reqCategorys = function reqCategorys() {
  return (0, _ajax["default"])(BASE_URL + '/msite/querycategories', {}, 'POST');
};

exports.reqCategorys = reqCategorys;

var reqGoods = function reqGoods() {
  return (0, _ajax["default"])(BASE_URL + '/msite/querygoods', {}, 'POST');
};
/**
 * 关键字搜索商品列表
 */


exports.reqGoods = reqGoods;

var reqSearchGoods = function reqSearchGoods(keyword) {
  return (0, _ajax["default"])(BASE_URL + '/msite/querygoodsvague', {
    keyword: keyword
  }, 'POST');
};
/**
 * 账号密码登录
 */


exports.reqSearchGoods = reqSearchGoods;

var reqPwdLogin = function reqPwdLogin(_ref) {
  var phone = _ref.phone,
      pwd = _ref.pwd,
      code = _ref.code;
  return (0, _ajax["default"])(BASE_URL + '/gateway/loginbypwd', {
    phone: phone,
    pwd: pwd,
    code: code
  }, 'POST');
};
/**
 * 获取短信验证码
 */


exports.reqPwdLogin = reqPwdLogin;

var reqSendCode = function reqSendCode(phone) {
  return (0, _ajax["default"])(BASE_URL + '/gateway/sendmessage', {
    phone: phone
  }, 'POST');
}; // 注册账号


exports.reqSendCode = reqSendCode;

var reqRegister = function reqRegister(_ref2) {
  var phone = _ref2.phone,
      pwd = _ref2.pwd,
      code = _ref2.code;
  return (0, _ajax["default"])(BASE_URL + '/gateway/register', {
    phone: phone,
    pwd: pwd,
    code: code
  }, 'POST');
};
/**
 * 手机号验证码登录
 */


exports.reqRegister = reqRegister;

var reqSmsLogin = function reqSmsLogin(phone, code) {
  return (0, _ajax["default"])(BASE_URL + '/gateway/loginbymessage', {
    phone: phone,
    code: code
  }, 'POST');
};
/**
 * 获取用户信息(根据会话)
 */


exports.reqSmsLogin = reqSmsLogin;

var reqUserInfo = function reqUserInfo() {
  return (0, _ajax["default"])(BASE_URL + '/gateway/getuser', {}, 'POST');
};
/**
 * 请求登出
 */


exports.reqUserInfo = reqUserInfo;

var reqLogout = function reqLogout() {
  return (0, _ajax["default"])(BASE_URL + '/gateway/logout');
}; //获得评价数组


exports.reqLogout = reqLogout;

var reqSellerRatings = function reqSellerRatings(goodid) {
  return (0, _ajax["default"])(BASE_URL + '/gooddetail/getratings', {
    goodid: goodid
  }, 'POST');
};

exports.reqSellerRatings = reqSellerRatings;

var getGraph = function getGraph() {
  return (0, _ajax["default"])(BASE_URL + '/gateway/sendgraphcode', {}, 'POST');
};

exports.getGraph = getGraph;

var reqGoodDetail = function reqGoodDetail(goodid) {
  return (0, _ajax["default"])(BASE_URL + '/gooddetail/getgooddetail', {
    goodid: goodid
  }, 'POST');
};

exports.reqGoodDetail = reqGoodDetail;

var reqSellerInfo = function reqSellerInfo(goodid) {
  return (0, _ajax["default"])(BASE_URL + '/gooddetail/getsellerinfo', {
    goodid: goodid
  }, 'POST');
};

exports.reqSellerInfo = reqSellerInfo;

var reqCredential = function reqCredential() {
  return (0, _ajax["default"])(BASE_URL + '/put/getcredential', {}, 'POST');
};

exports.reqCredential = reqCredential;

var reqAliveActivies = function reqAliveActivies() {
  return (0, _ajax["default"])(BASE_URL + '/put/getaliveactivities', {}, 'POST');
};

exports.reqAliveActivies = reqAliveActivies;

var sendPutGood = function sendPutGood(title, price, number, comment, checkList, otherlinks, cid) {
  return (0, _ajax["default"])(BASE_URL + '/put/putgood', {
    title: title,
    price: price,
    number: number,
    comment: comment,
    checkList: checkList,
    otherlinks: otherlinks,
    cid: cid
  }, 'POST');
};

exports.sendPutGood = sendPutGood;

var confirmOrder = function confirmOrder(userbuyid, usersellid, goodid, number, singleprice, totalprice, comments) {
  return (0, _ajax["default"])(BASE_URL + '/order/confirmorder', {
    userbuyid: userbuyid,
    usersellid: usersellid,
    goodid: goodid,
    number: number,
    singleprice: singleprice,
    totalprice: totalprice,
    comments: comments
  }, 'POST');
};

exports.confirmOrder = confirmOrder;

var reqOrdersAll = function reqOrdersAll() {
  return (0, _ajax["default"])(BASE_URL + '/order/getordersall', {}, 'POST');
};

exports.reqOrdersAll = reqOrdersAll;

var reqOrdersDoing = function reqOrdersDoing() {
  return (0, _ajax["default"])(BASE_URL + '/order/getordersdoing', {}, 'POST');
};

exports.reqOrdersDoing = reqOrdersDoing;

var reqOrdersUnrating = function reqOrdersUnrating() {
  return (0, _ajax["default"])(BASE_URL + '/order/getordersunrating', {}, 'POST');
};

exports.reqOrdersUnrating = reqOrdersUnrating;

var reqOrdersFinished = function reqOrdersFinished() {
  return (0, _ajax["default"])(BASE_URL + '/order/getordersfinished', {}, 'POST');
};

exports.reqOrdersFinished = reqOrdersFinished;

var sendReceiveOrder = function sendReceiveOrder(orderid) {
  return (0, _ajax["default"])(BASE_URL + '/order/sendreceiveorder', {
    orderid: orderid
  }, 'POST');
};

exports.sendReceiveOrder = sendReceiveOrder;

var sendMakeRating = function sendMakeRating(orderid, ratescore, text) {
  return (0, _ajax["default"])(BASE_URL + '/order/makerating', {
    orderid: orderid,
    ratescore: ratescore,
    text: text
  }, 'POST');
};

exports.sendMakeRating = sendMakeRating;

var sendUpdateProfile = function sendUpdateProfile(nickname, avatar) {
  return (0, _ajax["default"])(BASE_URL + '/gateway/updateprofile', {
    nickname: nickname,
    avatar: avatar
  }, 'POST');
};

exports.sendUpdateProfile = sendUpdateProfile;