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
        }
    },
    actions: {},
    modules: {}
});
