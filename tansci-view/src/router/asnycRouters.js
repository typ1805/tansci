// 动态路由菜单  JustTwo
import Home from '../views/Home.vue'
import Layout from '../layout/Layout.vue'

const asnycRouters = [
    {
        path: '/home',
        name: 'home',
        type: 1,
        icon: "el-icon-s-home",
        meta: {title: "首页"},
        component: Layout,
        children: [{
            path: "/home",
            name: "home",
            icon: "el-icon-s-home",
            meta: { title: "首页" },
            component: Home
        }]
    },
    {
        path: '/system',
        type: 'manage',
        name: "system",
        type: 1,
        icon: "el-icon-setting",
        meta: { title: "系统管理" },
        component: Layout,
        children: [{
                path: "/menu",
                name: "menu",
                icon: "el-icon-menu",
                meta: { title: "菜单管理" },
                component: Home
            },
            {
                path: "/role",
                name: "role",
                icon: "el-icon-menu",
                meta: { title: "角色管理" },
                component: () => Home
            },
            {
                path: "/org",
                name: "org",
                icon: "el-icon-help",
                meta: { title: "组织管理" },
                component: () => Home
            },
            {
                path: "/user",
                name: "user",
                icon: "el-icon-s-data",
                meta: { title: "用户列表" },
                component: () => Home
            },
            {
                path: "/dicInfo",
                name: "dicInfo",
                icon: "el-icon-coin",
                meta: { title: "字典列表" },
                component: () => Home
            },
            {
                path: "/password",
                name: "password",
                icon: "el-icon-unlock",
                meta: { title: "修改密码" },
                component: () => Home
            },
            {
                path: "/log",
                name: "log",
                icon: "el-icon-unlock",
                meta: { title: "操作日志" },
                component: () => Home
            }
        ]
    },

]

export default asnycRouters 