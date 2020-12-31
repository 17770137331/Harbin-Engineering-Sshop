/*
vuex 的 mutations 模块
*/
import Vue from 'vue'
import {
  RECEIVE_ADDRESS,
  RECEIVE_CATEGORYS,
  RECEIVE_SHOPS,
  RECEIVE_GOODS,
  RECEIVE_USER_INFO,
  RESET_USER_INFO,
  RECEIVE_INFO,
  RECEIVE_RATINGS,
  INCREMENT_GOOD_COUNT,
  DECREMENT_GOOD_COUNT,
  CLEAR_CART,
  RECEIVE_SEARCH_GOODS,
  RECEIVE_GOOD_DETAIL,
  RECEIVE_SELLER_INFO,
  RECEIVE_BUY_GOOD,
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
// [方法名](state,{param}){}
export default {
    [MAKE_RATING](state, {
      order
    }) {
        state.ordersUnrating.splice(state.ordersUnrating.indexOf(order), 1)
        let flag = -1
            for (let index = 0; index < state.ordersAll.length; index++) {
              if (order === state.ordersAll[index]) {
                flag = index
                break
              }
            }

    order.status=3
    state.ordersFinished.push(order)
    },



  [REMOVE_ORDERS_DOING](state,{order}){
    
      state.ordersDoing.splice(state.ordersDoing.indexOf(order),1)

      let flag=-1
     for (let index = 0; index < state.ordersAll.length; index++) {
       if(order===state.ordersAll[index]){
         flag=index
         break
       }
     }
         order.status = 2
         state.ordersUnrating.push(order)
      state.ordersAll[flag].status = 2

  },


  [RECEIVE_ORDERS_ALL](state, {
    ordersAll
  }) {
    state.ordersAll = ordersAll
  },
  [RECEIVE_ORDERS_DOING](state, {
    ordersDoing
  }) {
    state.ordersDoing = ordersDoing
  },
  [RECEIVE_ORDERS_UNRATING](state, {
    ordersUnrating
  }) {
    state.ordersUnrating = ordersUnrating
  },
  [RECEIVE_ORDERS_FINISHED](state, {
    ordersFinished
  }) {
    state.ordersFinished = ordersFinished
  },






  [RECEIVE_ALIVE_ACTIVITIES](state, {
    aliveActivities
  }) {
    state.aliveActivities = aliveActivities
  },

  [RECEIVE_CREDENTIAL](state, {
    credential
  }) {
    state.credential = credential
  },
  [RECEIVE_ADDRESS](state, {
    address
  }) {
    state.address = address
  },
  [RECEIVE_CATEGORYS](state, {
    categorys
  }) {
    state.categorys = categorys
  },
  [RECEIVE_SHOPS](state, {
    shops
  }) {
    state.shops = shops
  },
  [RECEIVE_USER_INFO](state, {
    userInfo
  }) {
    state.userInfo = userInfo
  },
  [RESET_USER_INFO](state) {
    localStorage.setItem('orderFlag', 0)
    localStorage.setItem('putFlag', 0)
    state.userInfo = {}
  },
  [RECEIVE_INFO](state, {
    info
  }) {
    state.info = info
  },

  [RECEIVE_RATINGS](state, {
    ratings
  }) {
    state.ratings = ratings
  },

  [RECEIVE_SELLER_RATINGS](state, {
    sellerRatings
  }) {
    state.sellerRatings = sellerRatings
  },

  [RECEIVE_GOODS](state, {
    goods
  }) {
    state.goods = goods
  },
  [RECEIVE_GOOD_DETAIL](state, {
    gooddetail
  }) {
    state.gooddetail = gooddetail
  },
  [RECEIVE_BUY_GOOD](state, {
    buygood
  }) {
    state.buygood = buygood
  },

  // 增加商品的数量
  [INCREMENT_GOOD_COUNT](state, {
    buygood
  }) {
    //如果是第一次增加，我们就新建立一个这样子的属性，同时初始化值为1
    if (!buygood.count) { 
      Vue.set(buygood, 'count', 1)
      // 将food添加到cartGoods中
      //cartFoods表示购物车的商品
      state.cartGoods.push(buygood)
    } else {
      buygood.count++
    }
  },
  [DECREMENT_GOOD_COUNT](state, {
    buygood
  }) {
    //只有它是有值的时候，才去减去这个数量
    if (buygood.count) { // 只有有值才去减
      buygood.count--
      if (buygood.count === 0) {
        // 将food从cartGoods中移除
        //从购物车中将这个商品给去掉
        state.cartGoods.splice(state.cartGoods.indexOf(buygood), 1)
      }
    }
  },

  [REMOVE_CART_GOOD](state, {cartgood}){
    state.cartGoods.splice(state.cartGoods.indexOf(cartgood), 1)
  },





  [CLEAR_CART](state) {
    //这两个都要清除的亲，否则会请理的不干净
    // 清除Good中的count
    state.cartGoods.forEach(good => {
      good.count = 0
    })
    // 移除购物车中所有购物项
    state.cartGoods = []
  },
  [RECEIVE_SEARCH_GOODS](state, {
    searchGoods
  }) {
    state.searchGoods = searchGoods
  },
  [RECEIVE_SELLER_INFO](state, {
    sellerInfo
  }) {
    state.sellerInfo = sellerInfo
  }

}
