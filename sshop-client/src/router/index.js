/*
  路由模块
*/
import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入路由组件文件夹下的组件
import Login from '../pages/Login/Login'
import Register from '../pages/Register/Register'
import NoRight from '../pages/NoRight/NoRight'

/**
 * 具体的二手商品信息模块页面
 */
import GoodDetail from '../pages/GoodDetail/GoodDetail.vue'
import GoodInfomaiton from '../pages/GoodDetail/GoodInformation/GoodInformation.vue'
import GoodRatings from '../pages/GoodDetail/GoodRatings/GoodRatings.vue'
import SellerInformation from '../pages/GoodDetail/SellerInformation/SellerInformation.vue'
// 路由组件懒加载
const Put = () => import('../pages/Put/Put.vue')
const Msite = () => import('../pages/Msite/Msite.vue')
const Search = () => import('../pages/Search/Search.vue')
const Order = () => import('../pages/Order/Order.vue')
const Profile = () => import('../pages/Profile/Profile.vue')
import ConfirmOrder from '../pages/Order/ConfirmOrder/ConfirmOrder.vue'
import OrderDetail from '../pages/Order/OrderDetail/OrderDetail.vue'
import OrderDoing from '../pages/Order/OrderList/OrderDoing.vue'
import AllOrders from '../pages/Order/OrderList/AllOrders.vue'
import OrderUnrating from '../pages/Order/OrderList/OrderUnrating.vue'
import OrderFinished from '../pages/Order/OrderList/OrderFinished.vue'

// 全局注册Vue-router组件
Vue.use(VueRouter)

// 配置路由表并导出
export default new VueRouter({
  //  去掉地址中的哈希#
  mode: 'history',
  routes: [{
    path: '/',
    redirect: '/msite'
  },

  {
    path: '/noright',
    component: NoRight,
    // 此时的Msite等都是返回路由组件的函数，只有请求对应的路由路径时(第一次)才会执行此函数并加载路由组件
    // 标识此路由是否显示FooterGuide
    meta: {
      showFooter: true
    }
  },

  {
    path: '/msite',
    component: Msite,
    // 此时的Msite等都是返回路由组件的函数，只有请求对应的路由路径时(第一次)才会执行此函数并加载路由组件
    // 标识此路由是否显示FooterGuide
    meta: {
      showFooter: true
    }
  },
  {
    path: '/search',
    component: Search,
    meta: {
      showFooter: true
    }
  },
  {
    path: '/put',
    component: Put,
    meta: {
      showFooter: true
    }
  },

  {
    path: '/profile',
    component: Profile,
    meta: {
      showFooter: true
    }
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/register',
    component: Register
  },


  {
    path: '/gooddetail',
    component: GoodDetail,
    children: [{
      path: '/gooddetail/goodinformation',
      name: 'goodinformation',
      component: GoodInfomaiton
    },
    {
      path: '/gooddetail/goodratings/',
      name: 'goodratings',
      component: GoodRatings
    },
    {
      path: '/gooddetail/sellerinformation',
      name: 'sellerinformation',
      component: SellerInformation
    },
    {
      path: '',
      redirect: '/gooddetail/goodinformation'
    }
    ]
  },


  {
    path: '/order/confirmorder',
    name: 'confirmorder',
    component: ConfirmOrder
  },
  {
    path: '/order/orderdetail',
    name: 'orderdetail',
    component: OrderDetail
  },

  // {
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
      component: AllOrders
    },
    {
      path: '/order/orderdoing',
      name: 'orderdoing',
      meta: {
        showFooter: true
      },
      component: OrderDoing
    },
    {
      path: '/order/orderunrating',
      name: 'orderunrating',
      meta: {
        showFooter: true
      },
      component: OrderUnrating
    },
    {
      path: '/order/orderfinished',
      name: 'orderfinished',
      meta: {
        showFooter: true
      },
      component: OrderFinished
    },
    {
      path: '',
      redirect: '/order/allorders'
    }
    ]
  },


  ]
})
