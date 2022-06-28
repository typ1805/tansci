export default[
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'login',
        meta: {title: "登录"},
        component: () => import('@/views/Login.vue')
    },
    {
        path: '/404',
        name: '404',
        meta: {title: "404"},
        component: () => import('@/views/404.vue')
    },
    {
        path: '/500',
        name: '500',
        meta: {title: "500"},
        component: () => import('@/views/500.vue')
    },
    // {
    //     path: '/home',
    //     name: 'home',
    //     type: 1,
    //     icon: "el-icon-s-home",
    //     meta: {title: "首页"},
    //     component: () => import('@/layout/Layout.vue'),
    //     children: [{
    //         path: "/home",
    //         name: "home",
    //         icon: "el-icon-s-home",
    //         meta: { title: "首页" },
    //         component: () => import('@/views/Home.vue')
    //     }]
    // },
]