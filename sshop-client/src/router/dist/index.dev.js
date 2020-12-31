"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _vue = _interopRequireDefault(require("vue"));

var _vueRouter = _interopRequireDefault(require("vue-router"));

var _Login = _interopRequireDefault(require("../pages/Login/Login"));

var _Register = _interopRequireDefault(require("../pages/Register/Register"));

var _NoRight = _interopRequireDefault(require("../pages/NoRight/NoRight"));

var _GoodDetail = _interopRequireDefault(require("../pages/GoodDetail/GoodDetail.vue"));

var _GoodInformation = _interopRequireDefault(require("../pages/GoodDetail/GoodInformation/GoodInformation.vue"));

var _GoodRatings = _interopRequireDefault(require("../pages/GoodDetail/GoodRatings/GoodRatings.vue"));

var _SellerInformation = _interopRequireDefault(require("../pages/GoodDetail/SellerInformation/SellerInformation.vue"));

var _ConfirmOrder = _interopRequireDefault(require("../pages/Order/ConfirmOrder/ConfirmOrder.vue"));

var _OrderDetail = _interopRequireDefault(require("../pages/Order/OrderDetail/OrderDetail.vue"));

var _OrderDoing = _interopRequireDefault(require("../pages/Order/OrderList/OrderDoing.vue"));

var _AllOrders = _interopRequireDefault(require("../pages/Order/OrderList/AllOrders.vue"));

var _OrderUnrating = _interopRequireDefault(require("../pages/Order/OrderList/OrderUnrating.vue"));

var _OrderFinished = _interopRequireDefault(require("../pages/Order/OrderList/OrderFinished.vue"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

function _typeof(obj) { if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") { _typeof = function _typeof(obj) { return typeof obj; }; } else { _typeof = function _typeof(obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; } return _typeof(obj); }

function _getRequireWildcardCache() { if (typeof WeakMap !== "function") return null; var cache = new WeakMap(); _getRequireWildcardCache = function _getRequireWildcardCache() { return cache; }; return cache; }

function _interopRequireWildcard(obj) { if (obj && obj.__esModule) { return obj; } if (obj === null || _typeof(obj) !== "object" && typeof obj !== "function") { return { "default": obj }; } var cache = _getRequireWildcardCache(); if (cache && cache.has(obj)) { return cache.get(obj); } var newObj = {}; var hasPropertyDescriptor = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var key in obj) { if (Object.prototype.hasOwnProperty.call(obj, key)) { var desc = hasPropertyDescriptor ? Object.getOwnPropertyDescriptor(obj, key) : null; if (desc && (desc.get || desc.set)) { Object.defineProperty(newObj, key, desc); } else { newObj[key] = obj[key]; } } } newObj["default"] = obj; if (cache) { cache.set(obj, newObj); } return newObj; }

// 路由组件懒加载
var Put = function Put() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../pages/Put/Put.vue'));
  });
};

var Msite = function Msite() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../pages/Msite/Msite.vue'));
  });
};

var Search = function Search() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../pages/Search/Search.vue'));
  });
};

var Order = function Order() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../pages/Order/Order.vue'));
  });
};

var Profile = function Profile() {
  return Promise.resolve().then(function () {
    return _interopRequireWildcard(require('../pages/Profile/Profile.vue'));
  });
};

// 全局注册Vue-router组件
_vue["default"].use(_vueRouter["default"]); // 配置路由表并导出


var _default = new _vueRouter["default"]({
  //  去掉地址中的哈希#
  mode: 'history',
  routes: [{
    path: '/',
    redirect: '/msite'
  }, {
    path: '/noright',
    component: _NoRight["default"],
    // 此时的Msite等都是返回路由组件的函数，只有请求对应的路由路径时(第一次)才会执行此函数并加载路由组件
    // 标识此路由是否显示FooterGuide
    meta: {
      showFooter: true
    }
  }, {
    path: '/msite',
    component: Msite,
    // 此时的Msite等都是返回路由组件的函数，只有请求对应的路由路径时(第一次)才会执行此函数并加载路由组件
    // 标识此路由是否显示FooterGuide
    meta: {
      showFooter: true
    }
  }, {
    path: '/search',
    component: Search,
    meta: {
      showFooter: true
    }
  }, {
    path: '/put',
    component: Put,
    meta: {
      showFooter: true
    }
  }, {
    path: '/profile',
    component: Profile,
    meta: {
      showFooter: true
    }
  }, {
    path: '/login',
    component: _Login["default"]
  }, {
    path: '/register',
    component: _Register["default"]
  }, {
    path: '/gooddetail',
    component: _GoodDetail["default"],
    children: [{
      path: '/gooddetail/goodinformation',
      name: 'goodinformation',
      component: _GoodInformation["default"]
    }, {
      path: '/gooddetail/goodratings/',
      name: 'goodratings',
      component: _GoodRatings["default"]
    }, {
      path: '/gooddetail/sellerinformation',
      name: 'sellerinformation',
      component: _SellerInformation["default"]
    }, {
      path: '',
      redirect: '/gooddetail/goodinformation'
    }]
  }, {
    path: '/order/confirmorder',
    name: 'confirmorder',
    component: _ConfirmOrder["default"]
  }, {
    path: '/order/orderdetail',
    name: 'orderdetail',
    component: _OrderDetail["default"]
  }, // {
  //   path: '/order',
  //   component: Order,
  //   meta: {
  //     showFooter: true
  //   }
  // },
  {
    path: '/order',
    meta: {
      showFooter: true
    },
    component: Order,
    children: [{
      path: '/order/allorders',
      name: 'allorders',
      meta: {
        showFooter: true
      },
      component: _AllOrders["default"]
    }, {
      path: '/order/orderdoing',
      name: 'orderdoing',
      meta: {
        showFooter: true
      },
      component: _OrderDoing["default"]
    }, {
      path: '/order/orderunrating',
      name: 'orderunrating',
      meta: {
        showFooter: true
      },
      component: _OrderUnrating["default"]
    }, {
      path: '/order/orderfinished',
      name: 'orderfinished',
      meta: {
        showFooter: true
      },
      component: _OrderFinished["default"]
    }, {
      path: '',
      redirect: '/order/allorders'
    }]
  }]
});

exports["default"] = _default;