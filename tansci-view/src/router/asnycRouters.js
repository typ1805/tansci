// 动态路由菜单  JustTwo
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
            component: () => import('../views/Home.vue')
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
                component: () => import('../views/system/Menu.vue')
            },
            {
                path: "/role",
                name: "role",
                icon: "el-icon-menu",
                meta: { title: "角色管理" },
                component: () => import('../views/system/Role.vue')
            },
            {
                path: "/org",
                name: "org",
                icon: "el-icon-help",
                meta: { title: "组织管理" },
                component: () => import('../views/system/Org.vue')
            },
            {
                path: "/user",
                name: "user",
                icon: "el-icon-s-data",
                meta: { title: "用户列表" },
                component: () => import('../views/system/User.vue')
            },
            {
                path: "/dicInfo",
                name: "dicInfo",
                icon: "el-icon-coin",
                meta: { title: "字典列表" },
                component: () => import('../views/system/DicInfo.vue')
            },
            {
                path: "/log",
                name: "log",
                icon: "el-icon-unlock",
                meta: { title: "日志管理" },
                component: () => import('../views/system/Log.vue')
            }
        ]
    },
    {
        path: '/message',
        type: 'manage',
        name: "message",
        type: 1,
        icon: "Comment",
        meta: { title: "消息管理" },
        component: Layout,
        children: [{
                path: "/template",
                name: "template",
                icon: "Postcard",
                meta: { title: "消息模板" },
                component: () => import('../views/message/Template.vue')
            },
        ]
    },
    {
        path: '/task',
        type: 'manage',
        name: "task",
        type: 1,
        icon: "Comment",
        meta: { title: "任务管理" },
        component: Layout,
        children: [{
                path: "/taskConfig",
                name: "taskConfig",
                icon: "Postcard",
                meta: { title: "任务调度" },
                component: () => import('../views/task/TaskConfig.vue')
            },
        ]
    },

]

export default asnycRouters 