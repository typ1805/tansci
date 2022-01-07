import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import * as ElIcons from '@element-plus/icons'
import router from './router'
import store from './store'

const app = createApp(App)
app.use(router)
app.use(store)
app.use(ElementPlus, {
    locale: zhCn,
    size: "default"
})
// 统一导入el-icon图标
for(let icon in ElIcons){
    app.component(icon,ElIcons[icon])
}

app.mount('#app')
