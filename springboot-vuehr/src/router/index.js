import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
// import Show from '@/components/Show'
// import ChinaMapShow from '@/components/ChinaMapShow'
// const HelloWorld = () => import('@/components/HelloWorld');
const Login = () => import('@/components/common/Login');
const Register = () => import('@/components/common/Register');
const Home = () => import('@/components/common/Home');
const HeadTop = () => import('@/components/common/HeadTop');
const NavMenu = () => import('@/components/common/NavMenu');
const First = () => import('@/components/First');
const Show = () => import('@/components/Show');
const ChinaMapShow = () => import('@/components/ChinaMapShow');
const EducationAndJobNum = () => import('@/components/EducationAndJobNum');

Vue.use(Router);

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home,
      meta: {title: '首页', requireAuth: true},
      children: [
        {
          path: '/home/first',
          name: 'First',
          component: First,
          meta: { requireAuth:true },
        }
      ]
    },
    {
      path:"/register",
      name:"Register",
      component: Register,
      meta:{ requireAuth:false}
    },
    {
      path:"/headTop",
      name:"HeadTop",
      component: HeadTop,
      meta:{ requireAuth:true}
    },
    {
      path:"/navMenu",
      name:"NavMenu",
      component: NavMenu,
      meta:{ requireAuth:true}
    },
    {
      path: '/show',
      name: 'Show',
      component: Show
    },
    {
      path: '/china',
      name: 'ChinaMapShow',
      component: ChinaMapShow
    },
    {
      path: '/educationAndJob',
      name: 'EducationAndJobNum',
      component: EducationAndJobNum
    }
  ]
})
