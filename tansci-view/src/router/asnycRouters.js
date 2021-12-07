// 动态路由菜单  JustTwo
import Home from '../views/Home.vue'

const asnycRouters = [
    {
        path: '/home',
        name: 'home',
        meta: {title: "首页"},
        component: Home
    }
]

export default asnycRouters 