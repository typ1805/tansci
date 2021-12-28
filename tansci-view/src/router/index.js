import { createRouter, createWebHashHistory} from 'vue-router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

// 路由按模块分类
import common from './common.js'
import asnycRouters from './asnycRouters.js'

// createRouter 创建路由实例
const router = createRouter({
    /**
     * hash模式：createWebHashHistory，
     * history模式：createWebHistory
     */
    history: createWebHashHistory(),
    routes:[
        ...common,
        ...asnycRouters,
        {path:'/:pathMatch(.*)*', redirect:'/404'}
    ]
})

NProgress.inc(0.2)
NProgress.configure({ easing: 'ease', speed: 600, showSpinner: false })

// 设置title
router.beforeEach((to, from, next) => {
    // 启动进度条
    NProgress.start()

    // 设置头部
    if (to.meta.title) {
        document.title = to.meta.title
    } else {
        document.title = "tansci"
    }
    next()
})

router.afterEach(() => {
    // 关闭进度条
    NProgress.done()
})

export default router