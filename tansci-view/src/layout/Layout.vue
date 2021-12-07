<template>
    <el-container class="layout-container">
        <el-header height="45">
            <div class="header">
                <div @click="onCollapse">Tansci 系统</div>
                <div>
                    <span>{{username}} 欢迎您！</span>
                    <span style="padding-right: 1rem;">{{nowTimes}}</span>
                    <el-button @click="onLogout" type="text">退出</el-button>
                </div>
            </div>
        </el-header>
        <el-container>
            <el-aside :style="defaultHeight" :width="asideWidth" style="padding-bottom:4rem;">
                <el-menu router :default-active="$route.path" :collapse="isCollapse"
                    text-color="#fff" active-text-color="#2F9688" background-color="#0f1423">
                    <template v-for="item in routers" :key="item">
                        <el-menu-item v-if="!item.children || item.children.length == 1" :index="item.path">
                            <span>{{item.meta.title}}</span>
                        </el-menu-item>
                        <Submenu v-else :data="item"></Submenu>
                    </template>
                </el-menu>
            </el-aside>
            <el-main :style="defaultHeight">
                <div class="main-view">
                    <router-view/>
                </div>
            </el-main>
        </el-container>
        <el-backtop target=".el-main"></el-backtop>
    </el-container>
</template>
<script>
    import {onMounted, onBeforeMount, reactive, toRefs} from 'vue'
    import {ElMessageBox} from 'element-plus'
    import {useRouter} from 'vue-router'
    import Submenu from "../components/Submenu.vue"
    import {useStore} from "vuex"
    import {timeFormate} from '../utils/utils'
    export default {
        components:{
            Submenu
        },
        setup() {
            const router = useRouter()
            const store = useStore()
            const state = reactive({
                isCollapse: false,
                asideWidth: '190px',
                defaultHeight: {
                    height: ''
                },
                routers: [],
                menuTags: [],
                username: store.getters.getUser.nickname,
                nowTimes: '',
            })

            onMounted(()=>{
                window.onresize = () => {
                    return (() => {
                        state.defaultHeight.height = window.innerHeight + "px";
                    })()
                }
                onNowTimes();
            })

            onBeforeMount(() => {
                state.defaultHeight.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px";
                
                // TODO
                let _routers = router.options.routes.filter(v=>v.type === 1)
                state.routers = _routers;
                console.log(state.routers)
            })

            const onCollapse = () => {
                if (state.isCollapse) {
                    state.asideWidth = '190px'
                    state.isCollapse = false
                } else {
                    state.isCollapse = true
                    state.asideWidth = '64px'
                }
            }

            const onLogout = () =>{
                ElMessageBox.confirm('您确定要退出吗？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    // logout({}).then(res=>{
                    //     if(res){
                            store.commit('delToken')
                            store.commit('delUser')
                            router.push({path: 'login'})
                    //     }
                    // });
                }).catch(e=>{
                    console.log(e)
                })
            }

            const onNowTimes = () =>{
                setInterval(()=>{
                    state.nowTimes = timeFormate(new Date());
                },1000);
            }

            return {
                ...toRefs(state),
                onCollapse,
                onLogout,
                onNowTimes
            }
        }
    }
</script>
<style lang="less" scoped>
    .layout-container{
        .el-header{
            padding: 0;
        }
        .header{
            display: flex;
            justify-content: space-between;
            line-height: 45px;
            background: #2F9688;
            color: #fff;
            padding: 0 1rem;
        }
        .el-aside{
            height: 100%;
            transition: all .5s;
            background: #0f1423;
            overflow-y: auto;
            overflow-x: hidden;
            .el-menu{
                border: none;
                .el-menu-item,.el-sub-menu__title{
                    height: 40px;
                    line-height: 40px;
                }
                .el-sub-menu__title:hover{
                    background: #0f1423 !important;
                }
                .el-menu-item:hover{
                    background: #0f1423 !important;
                }
            }
        }
        .el-aside::-webkit-scrollbar{
            width: 0px;
        }
        .el-main{
            padding: 0;
            overflow-x: hidden;
            overflow-y: auto;
        }
        .el-main::-webkit-scrollbar{
            width: 0px;
        }
        .main-view{
            padding: 0.3rem;
        }
    }
</style>