import Vue from 'vue'
import Router from 'vue-router'

const Login = () => import('@/components/common/Login');
const Register = () => import('@/components/common/Register');
const Home = () => import('@/components/common/Home');
const HeadTop = () => import('@/components/common/HeadTop');
const NavMenu = () => import('@/components/common/NavMenu');
const First = () => import('@/components/First');
const Educationjob = () => import('@/components/Educationjob');
const CityLevelJob = () => import('@/components/CityLevelJob');

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
          meta: { title:'数据地图',requireAuth:true },
        },
        {
          path: '/home/educationjob',
          name: 'Educationjob',
          component: Educationjob,
          meta: { title:'学历-职位关系',requireAuth:true },
        },
        {
          path: '/home/cityLevelJob',
          name: 'CityLevelJob',
          component: CityLevelJob,
          meta: { title:'X线城市-职位关系',requireAuth:true },
        },
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
    }
  ]
})
