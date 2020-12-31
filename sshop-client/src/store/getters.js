/*
包含多个基于state的getter计算属性的对象
getter的主要作用就是基于多个state的getter计算属性的对象
 */
export default {
  totalCount (state) {
    //reduce方法是一个累加器，同时它的参数应该是函数，在此我们使用 的是 匿名函数
    return state.cartGoods.reduce((preTotal, food) => preTotal + food.count, 0)
  },

  totalPrice (state) {
    return state.cartGoods.reduce((preTotal, food) => preTotal + food.count * food.price, 0)
  },

  // //得到积极评价的各设备
  // positiveSize (state) {
  //   return state.ratings.reduce((preTotal, rating) => preTotal + (rating.rateType === 0 ? 1 : 0), 0)
  // }

    //得到积极评价的各设备
  positiveSize (state) {
    return state.sellerRatings.reduce((preTotal, sellerRating) => preTotal + (sellerRating.ratetype === 0 ? 1 : 0), 0)
  }
}
