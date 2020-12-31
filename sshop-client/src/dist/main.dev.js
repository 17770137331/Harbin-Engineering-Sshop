"use strict";

var _vue = _interopRequireDefault(require("vue"));

var _App = _interopRequireDefault(require("./App"));

var _elementUi = _interopRequireDefault(require("element-ui"));

require("element-ui/lib/theme-chalk/index.css");

var _router = _interopRequireDefault(require("./router"));

var _store = _interopRequireDefault(require("./store"));

var _vueLazyload = _interopRequireDefault(require("vue-lazyload"));

var _loading = _interopRequireDefault(require("./common/imgs/loading.gif"));

var _fastclick = _interopRequireDefault(require("fastclick"));

require("./filters");

var _mintUi = require("mint-ui");

var _elInput = _interopRequireDefault(require("@/directive/el-input"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// 引入路由 其实就是router文件夹下的index.js配置好的路由表
_vue["default"].use(_elementUi["default"]);

_vue["default"].component(_mintUi.Checklist.name, _mintUi.Checklist);

_vue["default"].component(_mintUi.Field.name, _mintUi.Field);

_vue["default"].component(_mintUi.Button.name, _mintUi.Button); // <mt-button>


_vue["default"].use(_elInput["default"]); //添加此行=>使用该全局指令


_fastclick["default"].attach(document.body);

_vue["default"].config.productionTip = false;

_vue["default"].use(_vueLazyload["default"], {
  // 内部自定义一个指令lazy
  loading: _loading["default"]
});
/* eslint-disable no-new */


new _vue["default"]({
  el: '#app',
  components: {
    App: _App["default"]
  },
  template: '<App/>',
  // 为根组件加入路由
  router: _router["default"],
  store: _store["default"]
});