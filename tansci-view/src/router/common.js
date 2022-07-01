export default[
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'login',
        meta: {title: "登录"},
        component: () => import('@/views/common/Login.vue')
    },
    {
        path: '/404',
        name: '404',
        meta: {title: "404"},
        component: () => import('@/views/common/404.vue')
    },
    {
        path: '/500',
        name: '500',
        meta: {title: "500"},
        component: () => import('@/views/common/500.vue')
    },
    {
        path: '/main',
        name: 'main',
        meta: { title: "首页" },
        component: () => import('@/layout/Layout.vue'),
        children: [{
            path: "/main",
            name: "main",
            meta: { title: "首页" },
            component: () => import('@/views/common/Main.vue')
        }]
    }
]