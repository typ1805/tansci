<template>
    <el-container class="layout-container">
        <el-header height="60">
            <div class="header">
                <div style="padding-left: 2rem;">
                    <!-- <el-button @click="onCollapse" type="text" :icon="isCollapse?'Grid':'Menu'" >菜单折叠</el-button> -->
                    <el-button @click="onHome" type="text" icon="HomeFilled">工作台</el-button>
                    <el-link href="http://localhost:8005/tansci/doc.html" type="primary" target="_blank" :underline="false" icon="Notebook" style="padding-left: 1rem;">API 文档</el-link>
                </div>
                <div style="padding-right:0.4rem;">
                    <el-icon :size="16" color="#55bc8a" style="vertical-align: middle;padding-right:0.2rem;">
                        <Timer/>
                    </el-icon>
                    <span style="padding-right: 2rem;vertical-align: middle;">{{nowTimes}}</span>
                    <el-dropdown style="line-height: 60px;">
                        <span class="el-dropdown-link" style="color:var(--theme);">
                            <span style="cursor:pointer;vertical-align: middle;">{{username}} 欢迎您</span>
                            <el-icon style="vertical-align: middle;"><arrow-down /></el-icon>
                        </span>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item @click="onPassword" icon="Lock">修改密码</el-dropdown-item>
                                <el-dropdown-item @click="onLogout" icon="SwitchButton">退出</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                    <el-dialog v-model="dialogPass" title="修改密码" width="35%" :destroy-on-close="true" :show-close="false">
                        <el-form :model="passForm" :rules="rules" ref="validateForm" status-icon label-width="100px">
                            <el-form-item label="原始密码" prop="oldPassword" :rules="[
                                {required: true, message: '请输入原始密码', trigger: 'blur'},
                                {pattern: /^[a-zA-Z]\w{5,17}$/, message: '原始密码格式有误，请重新输入', trigger: 'blur'}]">
                                <el-input class="input-size" type="password" v-model="passForm.oldPassword" prefix-icon="Lock" autocomplete="off" style="width:100%"/>
                            </el-form-item>
                            <el-form-item label="新密码" prop="password" :rules="[
                                {required: true, message: '请输入新密码', trigger: 'blur'},
                                {pattern: /^[a-zA-Z]\w{5,17}$/, message: '以字母开头，长度在6~18之间，只能包含字母、数字和下划线', trigger: 'blur'}]">
                                <el-input class="input-size" type="password" v-model="passForm.password" prefix-icon="Lock" autocomplete="off" style="width:100%"/>
                            </el-form-item>
                            <el-form-item label="确认密码" prop="rePassword" :rules="[{validator: validatePass, trigger: 'blur'}]">
                                <el-input class="input-size" type="password" v-model="passForm.rePassword" prefix-icon="Lock" autocomplete="off" style="width:100%"/>
                            </el-form-item>
                        </el-form>
                        <template #footer>
                            <span class="dialog-footer">
                                <el-button @click="dialogPass = false">取消</el-button>
                                <el-button type="primary" @click="onPassSubmit">立即修改</el-button>
                            </span>
                        </template>
                    </el-dialog>
                </div>
            </div>
        </el-header>
        <el-container>
            <el-aside :style="defaultHeight" :width="asideWidth" style="padding-bottom:4rem;">
                <el-card v-show="!isCollapse" shadow="always">
                    <div>
                        <el-icon :size="26" style="vertical-align: middle;"><OfficeBuilding/></el-icon>
                        <span style="vertical-align: middle;padding-left:1rem;">TANSCI</span>
                    </div>
                </el-card>
                <el-menu router :default-active="$route.path" :collapse="isCollapse" @select="onSelect"
                    text-color="#242e42" active-text-color="#2F9688" background-color="var(--bg1)">
                    <template v-for="item in routers" :key="item">
                        <el-menu-item v-if="!item.children || item.children.length == 0" :index="item.path">
                            <el-icon v-if="item.icon" style="vertical-align: middle;">
                                <component :is="item.icon"></component>
                            </el-icon>
                            <span style="vertical-align: middle;">{{item.chineseName}}</span>
                        </el-menu-item>
                        <Submenu v-else :data="item"></Submenu>
                    </template>
                </el-menu>
                <div style="position: absolute;bottom: 0;width: inherit;">
                    <el-button @click="onCollapse" type="primary" color="#909399" style="width:100%;border-radius:0;">
                        <el-icon v-show="!isCollapse"><d-arrow-left /></el-icon>
                        <el-icon v-show="!isCollapse"><d-arrow-left /></el-icon>
                        <el-icon v-show="isCollapse"><d-arrow-right /></el-icon>
                        <el-icon v-show="isCollapse"><d-arrow-right /></el-icon>
                    </el-button>
                </div>
            </el-aside>
            <el-main :style="defaultHeight">
                <div class="main-view">
                    <el-card class="main-view-tag" shadow="always">
                        <MenuTag ref="menuTag" :size="'default'"></MenuTag>
                    </el-card>
                    <router-view class="main-view-content"/>
                </div>
            </el-main>
        </el-container>
        <el-backtop target=".el-main"></el-backtop>
    </el-container>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessageBox} from 'element-plus'
    import {useRouter} from 'vue-router'
    import Submenu from "../components/Submenu.vue"
    import MenuTag from "../components/MenuTag.vue"
    import {useStore} from "vuex"
    import {timeFormate} from '../utils/utils.js'
    import {modifyPass,logout} from '../api/systemApi.js'

    const router = useRouter()
    const store = useStore()
    const username = store.getters.getUser.nickname?store.getters.getUser.nickname:'管理员';
    const nowTimes = ref('');
    const validateForm = ref(null)
    const menuTag = ref(null)
    const state = reactive({
        isCollapse: false,
        asideWidth: '240px',
        defaultHeight: {
            height: ''
        },
        routers: [],
        dialogPass: false,
        passForm: {
            oldPassword: '',
            password: '',
            rePassword: ''
        }
    })

    const {
        logo,isCollapse,asideWidth,defaultHeight,routers,dialogPass,passForm,
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px";
    })

    onMounted(()=>{
        // 获取菜单
        state.routers = store.getters.getMenus;

        window.onresize = () => {
                    return (() => {
                state.defaultHeight.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px";
            })()
        }
        onNowTimes();
    })

    const onSelect = (e) =>{
        menuTag.value.onSelected(e)
    }

    const onCollapse = () => {
        if (state.isCollapse) {
            state.asideWidth = '240px'
            state.isCollapse = false
        } else {
            state.isCollapse = true
            state.asideWidth = '64px'
        }
    }

    // 退出
    const onLogout = () =>{
        ElMessageBox.confirm('您确定要退出吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            logout({}).then(res=>{
                if(res){
                    store.commit('delToken')
                    store.commit('delUser')
                    store.commit('delMenus')
                    router.push({path: 'login'})
                }
            });
        }).catch(e=>{
            console.log(e)
        })
    }

    // 当前时间
    const onNowTimes = () =>{
        setInterval(()=>{
            nowTimes.value = timeFormate(new Date());
        },1000);
    }

    // 修改密码
    const onPassword = () =>{
        state.passForm = {
            oldPassword: '',
            password: '',
            rePassword: ''
        }
        state.dialogPass = true;
    }
    const validatePass = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请再次输入密码'));
        } else if (value !== state.passForm.password) {
            callback(new Error('两次输入密码不一致'))
        } else {
            callback()
        }
    }
    const onPassSubmit = async () => {
        const form = unref(validateForm)
        if (!form) return;
        await form.validate();
        state.passForm.username = store.getters.getUser.username;
        modifyPass(state.passForm).then(res=>{
            if(res){
                ElMessageBox.alert('修改密码成功，需重新登录！', '提示', {
                    confirmButtonText: '确定',
                    type: 'warning',
                    callback: action => {
                        if ('confirm' == action) {
                            router.push({path: 'login'});
                        }
                    }
                })
            }
        });
    }

    // 跳转工作台
    const onHome = () =>{
        router.push({path: "home"});
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
            line-height: 60px;
            color: var(--theme);
            background: var(--bg1);
            border:1px transparent solid;
            border-image:linear-gradient(to right,var(--bg1),#DCDFE6,var(--bg1)) 1 10;
            box-shadow: 0 4px 8px 0 rgba(36,46,66,.06)!important;
            
            /deep/ .el-dialog__header{
                background: var(--theme);
                padding: 0 10px;
                .el-dialog__title{
                    color: var(--theme);
                }
            }
        }

        .el-aside{
            height: 100%;
            transition: all .5s;
            background: var(--bg1);
            overflow-y: auto;
            overflow-x: hidden;
            /deep/ .el-menu{
                border: none;
                .el-menu-item, .el-sub-menu__title{
                    height: 40px;
                    line-height: 40px;
                }
                .el-sub-menu__title:hover{
                    background: var(--bg1) !important;
                }
                .el-menu-item:hover{
                    background: var(--bg1) !important;
                }
            }
            /deep/ .el-card{
                margin: 0.4rem 0.6rem;
                background-color: var(--theme);
                color:#fff;
                .el-card__body{
                    padding: 1rem 2rem;
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
            background: var(--bg1);
            /deep/ .el-dialog__header{
                background: var(--theme);
                padding: 10px;
                .el-dialog__title{
                    color: #fff;
                }
            }
        }
        .el-main::-webkit-scrollbar{
            width: 0px;
        }
        .main-view{
            .main-view-tag{
                margin: 0.4rem;
                /deep/.el-card__body{
                    padding: 0.3rem 0.4rem;
                }
            }
            .main-view-content{
                margin: 0.4rem;
            }
        }
    }
</style>