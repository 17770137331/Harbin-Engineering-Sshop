/*
与后台交互模块 （依赖已封装的ajax函数）
 */
import ajax from './ajax'
// const BASE_URL = 'http://local:4000'
const BASE_URL = '/api'


export const reqCategorys = () => ajax(BASE_URL + '/msite/querycategories',{},'POST')

export const reqGoods = () => ajax(BASE_URL + '/msite/querygoods', {},'POST')
/**
 * 关键字搜索商品列表
 */
export const reqSearchGoods = (keyword) => ajax(BASE_URL + '/msite/querygoodsvague', {keyword},'POST')
/**
 * 账号密码登录
 */
export const reqPwdLogin = ({phone, pwd, code}) => ajax(BASE_URL + '/gateway/loginbypwd', {phone, pwd, code}, 'POST')
/**
 * 获取短信验证码
 */
export const reqSendCode = phone => ajax(BASE_URL + '/gateway/sendmessage', {phone},'POST')

// 注册账号
export const reqRegister = ({phone,pwd,code}) => ajax(BASE_URL + '/gateway/register', {phone, pwd,code}, 'POST')

/**
 * 手机号验证码登录
 */
export const reqSmsLogin = (phone, code) => ajax(BASE_URL + '/gateway/loginbymessage', {phone, code}, 'POST')
/**
 * 获取用户信息(根据会话)
 */
export const reqUserInfo = () => ajax(BASE_URL + '/gateway/getuser',{},'POST')
/**
 * 请求登出
 */
export const reqLogout = () => ajax(BASE_URL + '/gateway/logout')

//获得评价数组
export const reqSellerRatings = (goodid) => ajax(BASE_URL+'/gooddetail/getratings',{goodid},'POST')


export const getGraph=()=>ajax(BASE_URL+'/gateway/sendgraphcode',{},'POST')


export const reqGoodDetail=(goodid)=>ajax(BASE_URL+'/gooddetail/getgooddetail',{goodid},'POST')

export const reqSellerInfo=(goodid)=>ajax(BASE_URL+'/gooddetail/getsellerinfo',{goodid},'POST')

export const reqCredential=()=>ajax(BASE_URL+'/put/getcredential',{},'POST')



export const reqAliveActivies=()=>ajax(BASE_URL+'/put/getaliveactivities',{},'POST')

export const sendPutGood=(title,price,number,comment,checkList,otherlinks,cid)=>ajax(BASE_URL+'/put/putgood',{title,price,number,comment,checkList,otherlinks,cid},'POST')
export const confirmOrder=(userbuyid,usersellid,goodid,number,singleprice,totalprice,comments)=>ajax(BASE_URL+'/order/confirmorder',{userbuyid,usersellid,goodid,number,singleprice,totalprice,comments},'POST')


export const reqOrdersAll = () => ajax(BASE_URL + '/order/getordersall', {}, 'POST')
export const reqOrdersDoing = () => ajax(BASE_URL + '/order/getordersdoing', {}, 'POST')
export const reqOrdersUnrating = () => ajax(BASE_URL + '/order/getordersunrating', {}, 'POST')
export const reqOrdersFinished = () => ajax(BASE_URL + '/order/getordersfinished', {}, 'POST')
export const sendReceiveOrder = (orderid) => ajax(BASE_URL + '/order/sendreceiveorder', {orderid}, 'POST')
export const sendMakeRating = (orderid,ratescore,text) => ajax(BASE_URL + '/order/makerating', {orderid,ratescore,text}, 'POST')

export const sendUpdateProfile = (nickname, avatar) => ajax(BASE_URL + '/gateway/updateprofile', {nickname, avatar}, 'POST')

