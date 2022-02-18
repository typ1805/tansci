<template>
  <el-config-provider :locale="locale">
    <router-view></router-view>
  </el-config-provider>
</template>
<script>
  import {onMounted, reactive, toRefs} from 'vue'
  import {useStore} from "vuex"
  import zhCn from 'element-plus/lib/locale/lang/zh-cn'
  export default{
    setup() {
      const store = useStore()
      const state = reactive({
        locale: zhCn
      })
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
        ...toRefs(state)
      }
    }
  }
</script>
<style>
  :root{
    /* 颜色 */
    --theme: #242e42;

    /* 背景色 */
    --bg1: #eff4f9;

    /* 文字颜色 */
    --t1: #fff;
    --t2: #f4f4f4;
    --t3: #ededed;
    --t4: #d8d8d8;
    --t5: #b3b3b3;
    --t6: #999;
    --t7: #666;
    --t8: #3d3d3d;
    --t9: #181818;

    /* 操作颜色 */
    --delete: #f56c6c;
    --edit: #0084ff;
    --add: #63ba4d;
    --query: #909399;
    --down: #E6A23C;
    --upper: #67C23A;
    --role: #006000;
  }
  
  html, body {
    width: 100%;
    height: 100%;
    padding: 0;
    margin: 0;
    overflow: hidden;
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    font-size: 14px;
  }

  #nprogress .bar {
    /*自定义进度条颜色*/
    background: var(--theme) !important;
  }
</style>
