/*
Action:通过操作mutation间接更新state的多个方法的对象
 */

// 注意要引入api接口函数
import { test } from 'shelljs'
import {

  reqCategorys,
  reqGoods,
  reqUserInfo,
  reqLogout,
  reqSellerRatings,


  reqSearchGoods,
  reqGoodDetail,
  reqSellerInfo,

  reqCredential,
  reqAliveActivies,
  reqOrdersAll,
  reqOrdersDoing,
  reqOrdersUnrating,
  reqOrdersFinished,
  sendReceiveOrder,
  sendMakeRating,
} from '../api'
import {
  RECEIVE_ADDRESS,
  RECEIVE_CATEGORYS,
  RECEIVE_GOODS,
  RECEIVE_USER_INFO,
  RESET_USER_INFO,
  RECEIVE_RATINGS,
  RECEIVE_INFO,
  INCREMENT_GOOD_COUNT,
  DECREMENT_GOOD_COUNT,
  CLEAR_CART,
  RECEIVE_SEARCH_GOODS,
  RECEIVE_GOOD_DETAIL,
  RECEIVE_SELLER_INFO,
  RECEIVE_SELLER_RATINGS,
  RECEIVE_CREDENTIAL,
  RECEIVE_ALIVE_ACTIVITIES,
  RECEIVE_ORDERS_ALL,
  RECEIVE_ORDERS_DOING,
  RECEIVE_ORDERS_UNRATING,
  RECEIVE_ORDERS_FINISHED,
  REMOVE_ORDERS_DOING,
  MAKE_RATING,
  REMOVE_CART_GOOD,
} from './mutation-types'
export default {



  // vuex执行评价的函数操作
  async makeRating({
    commit
  }, {order,ratescore,text}) {
    // 发送异步ajax请求

    const result = await sendMakeRating(order.orderid,ratescore,text)

    // 提交一个mutation
    if (result.code === 0) {
    
      commit(MAKE_RATING, {
        order
      })
 
      return 1
    }
    return 0
  },












  // 确认订单
  //别忘了写回调函数！~！
  async sendReceiveOrder({
    commit
  }, order,callback) {
    // 发送异步ajax请求

    const result = await sendReceiveOrder(order.orderid)

    // 提交一个mutation
    if (result.code === 0) {
  
      commit(REMOVE_ORDERS_DOING, {
        order
      })

      return 1
      callback && callback()
    }
    return 0
  },


  // 异步获取所有的订单
  //别忘了写回调函数！~！
  async getOrdersAll({
    commit
  }, callback) {
    // 发送异步ajax请求
    const result = await reqOrdersAll()
    // 提交一个mutation
    if (result.code === 0) {

      const ordersAll = result.extend.ordersAll

      commit(RECEIVE_ORDERS_ALL, {
        ordersAll
      })
      callback && callback()
    }
  },

  // 异步获取所有正在进行的订单
  //别忘了写回调函数！~！
  async getOrdersDoing({
    commit
  }, callback) {
    // 发送异步ajax请求
    const result = await reqOrdersDoing()
    // 提交一个mutation
    if (result.code === 0) {
  
      const ordersDoing = result.extend.ordersDoing
   
      commit(RECEIVE_ORDERS_DOING, {
        ordersDoing
      })
      callback && callback()
    }
  },


  // 异步获取所有待评价的订单
  //别忘了写回调函数！~！
  async getOrdersUnrating({
    commit
  }, callback) {
    // 发送异步ajax请求
    const result = await reqOrdersUnrating()
    // 提交一个mutation
    if (result.code === 0) {

      const ordersUnrating = result.extend.ordersUnrating
   
      commit(RECEIVE_ORDERS_UNRATING, {
        ordersUnrating
      })
      callback && callback()
    }
  },

  // 异步获取所有已经完成订单
  //别忘了写回调函数！~！
  async getOrdersFinished({
    commit
  }, callback) {
    // 发送异步ajax请求
    const result = await reqOrdersFinished()

    // 提交一个mutation
    if (result.code === 0) {
   
      const ordersFinished = result.extend.ordersFinished
     
      commit(RECEIVE_ORDERS_FINISHED, {
        ordersFinished
      })
      callback && callback()
    }
  },



  // 异步获取可以参加的活动
  //别忘了写回调函数！~！
  async getAliveActivities({
    commit
  }, callback) {
    // 发送异步ajax请求
    const result = await reqAliveActivies()
    // 提交一个mutation
    if (result.code === 0) {
      const aliveActivities = result.extend.aliveActivities

      commit(RECEIVE_ALIVE_ACTIVITIES, {
        aliveActivities
      })
      callback && callback()
    }
  },


  // 异步获取图片上传权限
  async getCredential({
    commit
  }) {

    // 从state状态中获取到经纬度用来设置reqAddress的参数（看接口文档）
    // 1. 发送异步ajax请求
    const result = await reqCredential()
    // 2. 提交一个mutation
    if (result.code === 0) {
      const credential = result.extend.credential

      commit(RECEIVE_CREDENTIAL, {
        credential
      })
    }
  },


  // 异步获取地址
  async getAddress({
    commit,
    state
  }) {
    // 从state状态中获取到经纬度用来设置reqAddress的参数（看接口文档）
    const geohash = state.latitude + ',' + state.longitude
    // 1. 发送异步ajax请求
    const result = await reqAddress(geohash)
    // 2. 提交一个mutation
    if (result.code === 0) {
      const address = result.data
      commit(RECEIVE_ADDRESS, {
        address
      })
    }
  },

  // 异步获取食品分类列表
  async getCategorys({
    commit
  }) {
    // 发送异步ajax请求
    const result = await reqCategorys()
    // 提交一个mutation
    if (result.code === 0) {
      const categorys = result.extend.categorie
      commit(RECEIVE_CATEGORYS, {
        categorys
      })
    }
  },

  // 异步获取商品列表
  //别忘了写回调函数！~！
  //很重要！！！
  async getGoods({
    commit
  }, callback) {
    // 发送异步ajax请求
    const result = await reqGoods()
    // 提交一个mutation
    if (result.code === 0) {
      const goods = result.extend.goods
      commit(RECEIVE_GOODS, {
        goods
      })
      callback && callback()
    }
  },

  // 同步记录用户信息
  recordUser({
    commit
  }, userInfo) {
    commit(RECEIVE_USER_INFO, {
      userInfo
    })
  },
  // 异步获取用户信息
  async getUserInfo({
    commit
  }) {
    const result = await reqUserInfo()
    if (result.code === 0) {
      //一定要注意这个extend.user
      const userInfo = result.extend.user

      localStorage.setItem('orderFlag', 1)
      commit(RECEIVE_USER_INFO, {
        userInfo
      })
    }
  },
  // 异步登出
  async logout({
    commit
  }) {
    const result = await reqLogout()
    localStorage.setItem("putFlag","0")
    localStorage.setItem("orderFlag","0")
    commit(RESET_USER_INFO)
    
  },
  // 异步获取商家信息
  async getShopInfo({
    commit
  }) {
    const result = await reqShopInfo()
    if (result.code === 0) {
      const info = result.data
      commit(RECEIVE_INFO, {
        info
      })
    }
  },



  // 异步获取商家评价列表
  async getShopRatings({
    commit
  }, callback) {
    const result = await reqShopRatings()
    if (result.code === 0) {
      const ratings = result.data
      commit(RECEIVE_RATINGS, {
        ratings
      })
      // 数据更新了, 通知一下组件
      callback && callback()
    }
  },




  // 异步获取商家评价列表
  async getSellerRatings({
    commit
  }, goodid, callback) {
  
    const result = await reqSellerRatings(goodid)
    if (result.code === 0) {

      const sellerRatings = result.extend.ratings

      commit(RECEIVE_SELLER_RATINGS, {
        sellerRatings
      })
      // 数据更新了, 通知一下组件
      callback && callback()
    }
  },





  // 异步获取商家商品列表
  async getShopGoods({
    commit
  }, callback) {
    const result = await reqShopGoods()
    if (result.code === 0) {
      const goods = result.data
      commit(RECEIVE_GOODS, {
        goods
      })
      // 数据更新了, 通知一下组件
      callback && callback()
    }
  },



  // 同步更新food中的count值
  updateGoodCount({
    commit
  }, {
    isAdd,
    buygood
  }) {

    // 如果是点击了加号，我们执行这个操作，值得注意的就是形参我觉得
    if (isAdd) {
      commit(INCREMENT_GOOD_COUNT, {
        buygood
      })
    } else {
      //如果是点击了减号，我们执行这个操作
      commit(DECREMENT_GOOD_COUNT, {
        buygood
      })
    }
  },

  // 同步清空购物车
  clearCart({
    commit
  }) {
    commit(CLEAR_CART)
  },

  removeCartGood({commit},cartGood){
    
    commit(REMOVE_CART_GOOD,{cartGood})
  },

  // 异步获取商家商品列表
  async searchGoods({
    commit
  }, keyword) {

    const result = await reqSearchGoods(keyword)
    if (result.code === 0) {
      const searchGoods = result.extend.searchGoods
      commit(RECEIVE_SEARCH_GOODS, {
        searchGoods
      })
    }
  },

  // 异步获取某个商品的所有的具体的信息
  async getGoodDetail({
    commit
  }, keyword) {
    const result = await reqGoodDetail(keyword)
    if (result.code === 0) {
      const gooddetail = result.extend.gooddetail
      // const buygood= JSON.parse(JSON.stringify(gooddetail));
      // 值得注意的是这里一定要用buygood，否则会报错
      commit(RECEIVE_GOOD_DETAIL, {
        gooddetail
      })
      // commit(RECEIVE_BUY_GOOD, {buygood})
    }
  },

  async getSellerInfo({
    commit
  }, keyword) {
    const result = await reqSellerInfo(keyword)
    if (result.code === 0) {
      const sellerInfo = result.extend.sellerInfo
      commit(RECEIVE_SELLER_INFO, {
        sellerInfo
      })
    }





  },







}
