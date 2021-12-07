<template>
  <el-config-provider>
    <router-view></router-view>
  </el-config-provider>
</template>
<script>
  import {onMounted} from 'vue'
  import {useStore} from "vuex"
  export default{
    setup() {
      const store = useStore()
      onMounted(()=>{
          //在页面加载时读取sessionStorage里的状态信息
          if (sessionStorage.getItem("store")) {
              store.replaceState(Object.assign({},store.state,JSON.parse(sessionStorage.getItem("store"))));
          }

          //在页面刷新时将vuex里的信息保存到sessionStorage里
          window.addEventListener("beforeunload", () => {
              sessionStorage.setItem("store", "");
              sessionStorage.setItem("store", JSON.stringify(store.state));
          });
      })

      return{
      }
    }
  }
</script>
<style>
#app {
  width: 100%;
  height: 100%;
  padding: 0;
  margin: 0;
  font-family: Avenir, Helvetica, Arial, sans-serif,"Microsoft YaHei","微软雅黑",;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
