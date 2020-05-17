<template>
  <div class="home">
    <head-top></head-top>
    <el-container style="height: 100%">
      <div style="border-right:1px solid #dcdfe6;" :style="height" class="left">
        <el-aside width="200px">
          <el-menu :default-active="this.$router.path" router>
            <el-menu-item index="/home/first">
              <i class="el-icon-odometer"></i>
              <span slot="title">数据类岗位分析系统</span>
            </el-menu-item>

            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-aim"></i>
                <span>职位分析</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="Educationjob">学历-职位关系</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="CityLevelJob">X线城市-职位关系</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-truck"></i>
                <span>薪资分析</span>
              </template>

              <el-menu-item-group>
                <el-menu-item index="/home/materialNum">物资编码申请</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/materialInfo">物资编码审批</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/materialD">打印物资数据</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-shopping-bag-1"></i>
                <span>平均薪资分析</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/home/needPlan">需求计划申请</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/needPlanp">需求计划审批</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/pruchase">请购</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/No">请购通过</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-submenu index="5">
              <template slot="title">
                <i class="el-icon-refresh"></i>
                <span>福利分析</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/home/No">物资接收</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/No">物资检验</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/No">物资入库</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/No">物资出库</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-submenu index="6">
              <template slot="title">
                <i class="el-icon-house"></i>
                <span>企业词云图</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/home/No">仓库维护</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/No">货位维护</el-menu-item>
              </el-menu-item-group>

              <el-menu-item-group>
                <el-menu-item index="/home/No">查看库存</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

            <el-menu-item index="/home/webMap">
              <i class="el-icon-discover"></i>
              <span slot="title">地图查看</span>
            </el-menu-item>

            <el-menu-item index="/home/materialD">
              <i class="el-icon-s-claim"></i>
              <span slot="title">单据管理</span>
            </el-menu-item>

            <el-menu-item index="/home/callme">
              <i class="el-icon-phone"></i>
              <span slot="title">请联系我</span>
            </el-menu-item>

          </el-menu>
        </el-aside>
      </div>

      <el-container>
        <!--主要内容-->
        <el-main>
          <div class="bar">
            <nav-menu></nav-menu>
          </div>
          <transition name="slide-fade">
            <router-view></router-view>
          </transition>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import headTop from "./HeadTop.vue"
  import navMenu from "./NavMenu.vue"
    export default {
        name: "Home",
        data() {
            return {
                height: {
                    height: ""
                },
                isCollapse: true
            };
        },
        components: {
            headTop,
            navMenu
        },
        mounted() {},
        created() {
            this.hh()
        },
        methods: {
            hh() {
                this.height.height = window.innerHeight - 60 + "px";
            },
            handleOpen(key, keyPath) {
                console.log(key, keyPath)
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        },
        computed: {},
        watch: {}
    }
</script>

<style scoped>

</style>
