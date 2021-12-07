import Login from '../views/Login.vue'
import v404 from '../views/404.vue'
import v500 from '../views/500.vue'

export default[
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'login',
        meta: {title: "登录"},
        component: Login
    },
    {
        path: '/404',
        name: '404',
        meta: {title: "404"},
        component: v404
    },
    {
        path: '/500',
        name: '500',
        meta: {title: "500"},
        component: v500
    },
]