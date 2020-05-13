import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
// import Show from '@/components/Show'
// import ChinaMapShow from '@/components/ChinaMapShow'
// const HelloWorld = () => import('@/components/HelloWorld');
const Show = () => import('@/components/Show');
const ChinaMapShow = () => import('@/components/ChinaMapShow');
const Index = () => import('@/components/Index');
const Login = () => import('@/components/Login');
const EducationAndJobNum = () => import('@/components/EducationAndJobNum');
const Home = () => import('@/components/common/Home');

Vue.use(Router);

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
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
