"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports["default"] = void 0;

var _shelljs = require("shelljs");

var _api = require("../api");

var _mutationTypes = require("./mutation-types");

/*
Action:通过操作mutation间接更新state的多个方法的对象
 */
// 注意要引入api接口函数
var _default = {
  // vuex执行评价的函数操作
  makeRating: function makeRating(_ref, _ref2) {
    var commit, order, ratescore, text, result;
    return regeneratorRuntime.async(function makeRating$(_context) {
      while (1) {
        switch (_context.prev = _context.next) {
          case 0:
            commit = _ref.commit;
            order = _ref2.order, ratescore = _ref2.ratescore, text = _ref2.text;
            _context.next = 4;
            return regeneratorRuntime.awrap((0, _api.sendMakeRating)(order.orderid, ratescore, text));

          case 4:
            result = _context.sent;

            if (!(result.code === 0)) {
              _context.next = 8;
              break;
            }

            commit(_mutationTypes.MAKE_RATING, {
              order: order
            });
            return _context.abrupt("return", 1);

          case 8:
            return _context.abrupt("return", 0);

          case 9:
          case "end":
            return _context.stop();
        }
      }
    });
  },
  // 确认订单
  //别忘了写回调函数！~！
  sendReceiveOrder: function sendReceiveOrder(_ref3, order, callback) {
    var commit, result;
    return regeneratorRuntime.async(function sendReceiveOrder$(_context2) {
      while (1) {
        switch (_context2.prev = _context2.next) {
          case 0:
            commit = _ref3.commit;
            _context2.next = 3;
            return regeneratorRuntime.awrap((0, _api.sendReceiveOrder)(order.orderid));

          case 3:
            result = _context2.sent;

            if (!(result.code === 0)) {
              _context2.next = 8;
              break;
            }

            commit(_mutationTypes.REMOVE_ORDERS_DOING, {
              order: order
            });
            return _context2.abrupt("return", 1);

          case 8:
            return _context2.abrupt("return", 0);

          case 9:
          case "end":
            return _context2.stop();
        }
      }
    });
  },
  // 异步获取所有的订单
  //别忘了写回调函数！~！
  getOrdersAll: function getOrdersAll(_ref4, callback) {
    var commit, result, ordersAll;
    return regeneratorRuntime.async(function getOrdersAll$(_context3) {
      while (1) {
        switch (_context3.prev = _context3.next) {
          case 0:
            commit = _ref4.commit;
            _context3.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqOrdersAll)());

          case 3:
            result = _context3.sent;

            // 提交一个mutation
            if (result.code === 0) {
              ordersAll = result.extend.ordersAll;
              commit(_mutationTypes.RECEIVE_ORDERS_ALL, {
                ordersAll: ordersAll
              });
              callback && callback();
            }

          case 5:
          case "end":
            return _context3.stop();
        }
      }
    });
  },
  // 异步获取所有正在进行的订单
  //别忘了写回调函数！~！
  getOrdersDoing: function getOrdersDoing(_ref5, callback) {
    var commit, result, ordersDoing;
    return regeneratorRuntime.async(function getOrdersDoing$(_context4) {
      while (1) {
        switch (_context4.prev = _context4.next) {
          case 0:
            commit = _ref5.commit;
            _context4.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqOrdersDoing)());

          case 3:
            result = _context4.sent;

            // 提交一个mutation
            if (result.code === 0) {
              ordersDoing = result.extend.ordersDoing;
              commit(_mutationTypes.RECEIVE_ORDERS_DOING, {
                ordersDoing: ordersDoing
              });
              callback && callback();
            }

          case 5:
          case "end":
            return _context4.stop();
        }
      }
    });
  },
  // 异步获取所有待评价的订单
  //别忘了写回调函数！~！
  getOrdersUnrating: function getOrdersUnrating(_ref6, callback) {
    var commit, result, ordersUnrating;
    return regeneratorRuntime.async(function getOrdersUnrating$(_context5) {
      while (1) {
        switch (_context5.prev = _context5.next) {
          case 0:
            commit = _ref6.commit;
            _context5.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqOrdersUnrating)());

          case 3:
            result = _context5.sent;

            // 提交一个mutation
            if (result.code === 0) {
              ordersUnrating = result.extend.ordersUnrating;
              commit(_mutationTypes.RECEIVE_ORDERS_UNRATING, {
                ordersUnrating: ordersUnrating
              });
              callback && callback();
            }

          case 5:
          case "end":
            return _context5.stop();
        }
      }
    });
  },
  // 异步获取所有已经完成订单
  //别忘了写回调函数！~！
  getOrdersFinished: function getOrdersFinished(_ref7, callback) {
    var commit, result, ordersFinished;
    return regeneratorRuntime.async(function getOrdersFinished$(_context6) {
      while (1) {
        switch (_context6.prev = _context6.next) {
          case 0:
            commit = _ref7.commit;
            _context6.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqOrdersFinished)());

          case 3:
            result = _context6.sent;

            // 提交一个mutation
            if (result.code === 0) {
              ordersFinished = result.extend.ordersFinished;
              commit(_mutationTypes.RECEIVE_ORDERS_FINISHED, {
                ordersFinished: ordersFinished
              });
              callback && callback();
            }

          case 5:
          case "end":
            return _context6.stop();
        }
      }
    });
  },
  // 异步获取可以参加的活动
  //别忘了写回调函数！~！
  getAliveActivities: function getAliveActivities(_ref8, callback) {
    var commit, result, aliveActivities;
    return regeneratorRuntime.async(function getAliveActivities$(_context7) {
      while (1) {
        switch (_context7.prev = _context7.next) {
          case 0:
            commit = _ref8.commit;
            _context7.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqAliveActivies)());

          case 3:
            result = _context7.sent;

            // 提交一个mutation
            if (result.code === 0) {
              aliveActivities = result.extend.aliveActivities;
              commit(_mutationTypes.RECEIVE_ALIVE_ACTIVITIES, {
                aliveActivities: aliveActivities
              });
              callback && callback();
            }

          case 5:
          case "end":
            return _context7.stop();
        }
      }
    });
  },
  // 异步获取图片上传权限
  getCredential: function getCredential(_ref9) {
    var commit, result, credential;
    return regeneratorRuntime.async(function getCredential$(_context8) {
      while (1) {
        switch (_context8.prev = _context8.next) {
          case 0:
            commit = _ref9.commit;
            _context8.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqCredential)());

          case 3:
            result = _context8.sent;

            // 2. 提交一个mutation
            if (result.code === 0) {
              credential = result.extend.credential;
              commit(_mutationTypes.RECEIVE_CREDENTIAL, {
                credential: credential
              });
            }

          case 5:
          case "end":
            return _context8.stop();
        }
      }
    });
  },
  // 异步获取地址
  getAddress: function getAddress(_ref10) {
    var commit, state, geohash, result, address;
    return regeneratorRuntime.async(function getAddress$(_context9) {
      while (1) {
        switch (_context9.prev = _context9.next) {
          case 0:
            commit = _ref10.commit, state = _ref10.state;
            // 从state状态中获取到经纬度用来设置reqAddress的参数（看接口文档）
            geohash = state.latitude + ',' + state.longitude; // 1. 发送异步ajax请求

            _context9.next = 4;
            return regeneratorRuntime.awrap(reqAddress(geohash));

          case 4:
            result = _context9.sent;

            // 2. 提交一个mutation
            if (result.code === 0) {
              address = result.data;
              commit(_mutationTypes.RECEIVE_ADDRESS, {
                address: address
              });
            }

          case 6:
          case "end":
            return _context9.stop();
        }
      }
    });
  },
  // 异步获取食品分类列表
  getCategorys: function getCategorys(_ref11) {
    var commit, result, categorys;
    return regeneratorRuntime.async(function getCategorys$(_context10) {
      while (1) {
        switch (_context10.prev = _context10.next) {
          case 0:
            commit = _ref11.commit;
            _context10.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqCategorys)());

          case 3:
            result = _context10.sent;

            // 提交一个mutation
            if (result.code === 0) {
              categorys = result.extend.categorie;
              commit(_mutationTypes.RECEIVE_CATEGORYS, {
                categorys: categorys
              });
            }

          case 5:
          case "end":
            return _context10.stop();
        }
      }
    });
  },
  // 异步获取商品列表
  //别忘了写回调函数！~！
  //很重要！！！
  getGoods: function getGoods(_ref12, callback) {
    var commit, result, goods;
    return regeneratorRuntime.async(function getGoods$(_context11) {
      while (1) {
        switch (_context11.prev = _context11.next) {
          case 0:
            commit = _ref12.commit;
            _context11.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqGoods)());

          case 3:
            result = _context11.sent;

            // 提交一个mutation
            if (result.code === 0) {
              goods = result.extend.goods;
              commit(_mutationTypes.RECEIVE_GOODS, {
                goods: goods
              });
              callback && callback();
            }

          case 5:
          case "end":
            return _context11.stop();
        }
      }
    });
  },
  // 同步记录用户信息
  recordUser: function recordUser(_ref13, userInfo) {
    var commit = _ref13.commit;
    commit(_mutationTypes.RECEIVE_USER_INFO, {
      userInfo: userInfo
    });
  },
  // 异步获取用户信息
  getUserInfo: function getUserInfo(_ref14) {
    var commit, result, userInfo;
    return regeneratorRuntime.async(function getUserInfo$(_context12) {
      while (1) {
        switch (_context12.prev = _context12.next) {
          case 0:
            commit = _ref14.commit;
            _context12.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqUserInfo)());

          case 3:
            result = _context12.sent;

            if (result.code === 0) {
              //一定要注意这个extend.user
              userInfo = result.extend.user;
              localStorage.setItem('orderFlag', 1);
              commit(_mutationTypes.RECEIVE_USER_INFO, {
                userInfo: userInfo
              });
            }

          case 5:
          case "end":
            return _context12.stop();
        }
      }
    });
  },
  // 异步登出
  logout: function logout(_ref15) {
    var commit, result;
    return regeneratorRuntime.async(function logout$(_context13) {
      while (1) {
        switch (_context13.prev = _context13.next) {
          case 0:
            commit = _ref15.commit;
            _context13.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqLogout)());

          case 3:
            result = _context13.sent;
            localStorage.setItem("putFlag", "0");
            localStorage.setItem("orderFlag", "0");
            commit(_mutationTypes.RESET_USER_INFO);

          case 7:
          case "end":
            return _context13.stop();
        }
      }
    });
  },
  // 异步获取商家信息
  getShopInfo: function getShopInfo(_ref16) {
    var commit, result, info;
    return regeneratorRuntime.async(function getShopInfo$(_context14) {
      while (1) {
        switch (_context14.prev = _context14.next) {
          case 0:
            commit = _ref16.commit;
            _context14.next = 3;
            return regeneratorRuntime.awrap(reqShopInfo());

          case 3:
            result = _context14.sent;

            if (result.code === 0) {
              info = result.data;
              commit(_mutationTypes.RECEIVE_INFO, {
                info: info
              });
            }

          case 5:
          case "end":
            return _context14.stop();
        }
      }
    });
  },
  // 异步获取商家评价列表
  getShopRatings: function getShopRatings(_ref17, callback) {
    var commit, result, ratings;
    return regeneratorRuntime.async(function getShopRatings$(_context15) {
      while (1) {
        switch (_context15.prev = _context15.next) {
          case 0:
            commit = _ref17.commit;
            _context15.next = 3;
            return regeneratorRuntime.awrap(reqShopRatings());

          case 3:
            result = _context15.sent;

            if (result.code === 0) {
              ratings = result.data;
              commit(_mutationTypes.RECEIVE_RATINGS, {
                ratings: ratings
              }); // 数据更新了, 通知一下组件

              callback && callback();
            }

          case 5:
          case "end":
            return _context15.stop();
        }
      }
    });
  },
  // 异步获取商家评价列表
  getSellerRatings: function getSellerRatings(_ref18, goodid, callback) {
    var commit, result, sellerRatings;
    return regeneratorRuntime.async(function getSellerRatings$(_context16) {
      while (1) {
        switch (_context16.prev = _context16.next) {
          case 0:
            commit = _ref18.commit;
            _context16.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqSellerRatings)(goodid));

          case 3:
            result = _context16.sent;

            if (result.code === 0) {
              sellerRatings = result.extend.ratings;
              commit(_mutationTypes.RECEIVE_SELLER_RATINGS, {
                sellerRatings: sellerRatings
              }); // 数据更新了, 通知一下组件

              callback && callback();
            }

          case 5:
          case "end":
            return _context16.stop();
        }
      }
    });
  },
  // 异步获取商家商品列表
  getShopGoods: function getShopGoods(_ref19, callback) {
    var commit, result, goods;
    return regeneratorRuntime.async(function getShopGoods$(_context17) {
      while (1) {
        switch (_context17.prev = _context17.next) {
          case 0:
            commit = _ref19.commit;
            _context17.next = 3;
            return regeneratorRuntime.awrap(reqShopGoods());

          case 3:
            result = _context17.sent;

            if (result.code === 0) {
              goods = result.data;
              commit(_mutationTypes.RECEIVE_GOODS, {
                goods: goods
              }); // 数据更新了, 通知一下组件

              callback && callback();
            }

          case 5:
          case "end":
            return _context17.stop();
        }
      }
    });
  },
  // 同步更新food中的count值
  updateGoodCount: function updateGoodCount(_ref20, _ref21) {
    var commit = _ref20.commit;
    var isAdd = _ref21.isAdd,
        buygood = _ref21.buygood;

    // 如果是点击了加号，我们执行这个操作，值得注意的就是形参我觉得
    if (isAdd) {
      commit(_mutationTypes.INCREMENT_GOOD_COUNT, {
        buygood: buygood
      });
    } else {
      //如果是点击了减号，我们执行这个操作
      commit(_mutationTypes.DECREMENT_GOOD_COUNT, {
        buygood: buygood
      });
    }
  },
  // 同步清空购物车
  clearCart: function clearCart(_ref22) {
    var commit = _ref22.commit;
    commit(_mutationTypes.CLEAR_CART);
  },
  removeCartGood: function removeCartGood(_ref23, cartGood) {
    var commit = _ref23.commit;
    commit(_mutationTypes.REMOVE_CART_GOOD, {
      cartGood: cartGood
    });
  },
  // 异步获取商家商品列表
  searchGoods: function searchGoods(_ref24, keyword) {
    var commit, result, searchGoods;
    return regeneratorRuntime.async(function searchGoods$(_context18) {
      while (1) {
        switch (_context18.prev = _context18.next) {
          case 0:
            commit = _ref24.commit;
            _context18.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqSearchGoods)(keyword));

          case 3:
            result = _context18.sent;

            if (result.code === 0) {
              searchGoods = result.extend.searchGoods;
              commit(_mutationTypes.RECEIVE_SEARCH_GOODS, {
                searchGoods: searchGoods
              });
            }

          case 5:
          case "end":
            return _context18.stop();
        }
      }
    });
  },
  // 异步获取某个商品的所有的具体的信息
  getGoodDetail: function getGoodDetail(_ref25, keyword) {
    var commit, result, gooddetail;
    return regeneratorRuntime.async(function getGoodDetail$(_context19) {
      while (1) {
        switch (_context19.prev = _context19.next) {
          case 0:
            commit = _ref25.commit;
            _context19.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqGoodDetail)(keyword));

          case 3:
            result = _context19.sent;

            if (result.code === 0) {
              gooddetail = result.extend.gooddetail; // const buygood= JSON.parse(JSON.stringify(gooddetail));
              // 值得注意的是这里一定要用buygood，否则会报错

              commit(_mutationTypes.RECEIVE_GOOD_DETAIL, {
                gooddetail: gooddetail
              }); // commit(RECEIVE_BUY_GOOD, {buygood})
            }

          case 5:
          case "end":
            return _context19.stop();
        }
      }
    });
  },
  getSellerInfo: function getSellerInfo(_ref26, keyword) {
    var commit, result, sellerInfo;
    return regeneratorRuntime.async(function getSellerInfo$(_context20) {
      while (1) {
        switch (_context20.prev = _context20.next) {
          case 0:
            commit = _ref26.commit;
            _context20.next = 3;
            return regeneratorRuntime.awrap((0, _api.reqSellerInfo)(keyword));

          case 3:
            result = _context20.sent;

            if (result.code === 0) {
              sellerInfo = result.extend.sellerInfo;
              commit(_mutationTypes.RECEIVE_SELLER_INFO, {
                sellerInfo: sellerInfo
              });
            }

          case 5:
          case "end":
            return _context20.stop();
        }
      }
    });
  }
};
exports["default"] = _default;