/*
包含n个mutation的type名称常量
 */
export const RECEIVE_ADDRESS = 'receive_address' // 接收地址信息
export const RECEIVE_CATEGORYS = 'receive_categorys' // 接收二手商品分类数组
export const RECEIVE_GOODS = 'receive_goods' // 接收二手物品数组
export const RECEIVE_SHOPS = 'receive_shops' // 接收商家数组
export const RECEIVE_USER_INFO = 'receive_user_info' // 接收用户信息
export const RESET_USER_INFO = 'reset_user_info' // 重置用户信息
export const RECEIVE_RATINGS = 'receive_ratings' // 接收商家评价数组
export const RECEIVE_INFO = 'receive_info' // 接收商家信息


export const INCREMENT_GOOD_COUNT = 'increment_good_count' // 增加food中的count，state中其实没有这个值
export const DECREMENT_GOOD_COUNT = 'decrement_good_count' // 减少food中的count,state中其实没有这个值
export const CLEAR_CART = 'clear_cart' // 清空购物车

export const RECEIVE_SEARCH_GOODS = 'receive_search_goods' // 接收搜索的商家数组

export const RECEIVE_GOOD_DETAIL = 'receive_good_detail' // 接收具体的商品信息

export const RECEIVE_SELLER_INFO='receive_seller_info' //接收的具体的商家的信息

export const RECEIVE_BUY_GOOD='receive_buy_good'                    //表示用户所加入到购物车的商品
export const RECEIVE_SELLER_RATINGS='receive_seller_ratings'         //表示获取商家的具体的评价信息
export const RECEIVE_CREDENTIAL='receive_credential'
export const RECEIVE_ALIVE_ACTIVITIES='receive_alive_activities'       //表示用户在商品发布界面可选的活动集合
export const RECEIVE_ORDERS_ALL='receive_orders_all'            //所有订单
export const RECEIVE_ORDERS_DOING = 'receive_orders_doing'      //未完成订单
export const RECEIVE_ORDERS_UNRATING = 'receive_orders_unrating'    //未评论订单
export const RECEIVE_ORDERS_FINISHED = 'receive_orders_finished'        //已完成订单
export const REMOVE_ORDERS_DOING='remove_orders_doing'              //要移除的刚刚确认收货的订单
export const MAKE_RATING = 'make_rating'           //表示发表评价
export const REMOVE_CART_GOOD = 'remove_cart_good'           //表示去掉某一个购物车中的物品

