<template>
  <div id="app">
    <!--注释掉这个，页面就不会显示原本的V logo-->
    <!--<img src="./assets/logo.png">-->
    <router-view v-if="RouterState"/>
  </div>
</template>

<script>
export default {
  name: 'App',
    provide() {
      return {
          reload: this.reload
      }
    },
    data() {
      return {
          RouterState:true,
      };
    },
    methods: {
        reload() {
            this.RouterState = false;
            this.$nextTick(() => {
                this.RouterState = true;
            });
        },
    },
    created() {

    },
    destroyed(){

    }
}
</script>

<style>
/*#app {*/
/*  font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
/*  -webkit-font-smoothing: antialiased;*/
/*  -moz-osx-font-smoothing: grayscale;*/
/*  text-align: center;*/
/*  color: #2c3e50;*/
/*  !*margin-top: 60px;*!*/
/*}*/
/**{*/
/*  padding: 0;*/
/*  margin: 0;*/
/*}*/
/*html,body{*/
/*  width: 100%;*/
/*  height: 100%;*/
/*}*/
/*#app {*/
/*  height: 100%;*/
/*}*/
html,
body {
  background-color: #fff;
  height: 100%;
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
</style>
