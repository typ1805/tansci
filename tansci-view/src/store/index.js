import {createStore} from 'vuex'

export default createStore({
    state(){
        return {
            token: '', 
            user: {}
        }
    },
    mutations: {
        // 存储token
        setToken(state, token) {	
            state.token = token;
            sessionStorage.setItem('token',token);
        },
        // 清除token
        delToken(state) {
            state.token = '';
            sessionStorage.removeItem('token');
        },

        // 存储用户信息
        setUser(state,user){
            state.user = user;
            sessionStorage.setItem('user',user);
        },
        // 清除用户信息
        delUser(state){
            state.user = '';
            sessionStorage.removeItem('user');
        },

        // 存储菜单
        setMenus(state, menus) {
            state.menus = menus;
            sessionStorage.setItem('menus',menus);
        },
        // 清除菜单
        delMenus(state) {
            state.menus = [];
            sessionStorage.removeItem('menus');
        }
    },
    getters: {
        // 获取token方法
        getToken(state) {
            if (!state.token) {
                state.token = sessionStorage.getItem('token');
            }
            return state.token;
        },
        // 获取用户信息方法
        getUser(state) {
            if (!state.user) {
                state.user = sessionStorage.getItem('user');
            }
            return state.user;
        },
        // 获取菜单方法
        getMenus(state) {
            if (!state.menus) {
                state.menus = sessionStorage.getItem('menus');
            }
            return state.menus;
        }
    },
    actions: {},
    modules: {}
});
