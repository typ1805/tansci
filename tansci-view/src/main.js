import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import * as ElIcons from '@element-plus/icons-vue'
import './styles/index.scss'

const app = createApp(App)
app.use(store)
app.use(router)
app.use(ElementPlus, {
    locale: zhCn,
    size: "default"
})
// 统一导入el-icon图标
for(let icon in ElIcons){
    app.component(icon,ElIcons[icon])
}

app.mount('#app')
