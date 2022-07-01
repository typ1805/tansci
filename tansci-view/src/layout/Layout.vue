<template>
    <el-container class="layout-container">
        <el-aside :style="{height: defaultHeight+'px'}" :width="asideWidth" style="padding-bottom:4rem;">
            <el-card v-show="!isCollapse" shadow="always">
                <div>
                    <el-icon :size="26" style="vertical-align: middle;"><OfficeBuilding/></el-icon>
                    <span style="vertical-align: middle;padding-left:1rem;">TANSCI</span>
                </div>
            </el-card>
            <el-menu router :default-active="$route.path" :collapse="isCollapse" @select="onSelect"
                text-color="#242e42" active-text-color="#2F9688" background-color="var(--bg1)">
                <template v-for="item in routers" :key="item">
                    <el-menu-item v-if="!item.children || item.children.length == 1" :index="item.type == 1 ? item.path:''">
                        <el-icon v-if="item.icon" style="vertical-align: middle;">
                            <component :is="item.icon"></component>
                        </el-icon>
                        <span v-if="item.type == 1" style="vertical-align: middle;">{{item.chineseName}}</span>
                        <span v-else-if="item.type == 2" @click="onNestedLink(item)" style="vertical-align: middle;">{{item.chineseName}}</span>
                        <a v-else-if="item.type == 3" :href='item.url' target='_blank' style="vertical-align: middle;text-decoration: none;color:#242e42;">{{item.chineseName}}</a>
                    </el-menu-item>
                    <Submenu v-else :data="item"></Submenu>
                </template>
            </el-menu>
        </el-aside>
        <el-container>
            <el-header height="50">
                <div class="header">
                    <div style="padding-left: 0.4rem;">
                        <el-icon @click="onCollapse" :size="20" style="vertical-align: middle; cursor: pointer;">
                            <component :is="isCollapse?'Expand':'Fold'"></component>
                        </el-icon>
                        <el-icon :size="16" color="#55bc8a" style="vertical-align: middle;padding-left:2rem;">
                            <Timer/>
                        </el-icon>
                        <span style="padding-right: 2rem;vertical-align: middle;">{{nowTimes}}</span>
                    </div>
                    <div style="padding-right:0.4rem;">
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
                <TabsMenu></TabsMenu>
            </el-header>
            <el-main :style="{height: defaultHeight+'px'}">
                <router-view v-show="!iframe.isIframe" style="margin: 0.4rem;"/>
                <iframe v-show="iframe.isIframe" :src="iframe.src" :style="{height:(defaultHeight-110)+'px'}" width="100%" frameborder="0"></iframe>
            </el-main>
        </el-container>
        <el-backtop target=".el-main"></el-backtop>
    </el-container>
</template>
<script setup>
    import {onBeforeMount, onMounted, onDeactivated, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessageBox} from 'element-plus'
    import {useRouter} from 'vue-router'
    import Submenu from "../components/Submenu.vue"
    import TabsMenu from "../components/TabsMenu.vue"
    import {useUserStore, useTokenStore, useMenuStore} from '../store/settings'
    import {timeFormate} from '../utils/utils.js'
    import {modifyPass,logout} from '../api/systemApi.js'

    const router = useRouter()
    const userStore = useUserStore();
    const tokenStore = useTokenStore();
    const menuStore = useMenuStore();
    const username = userStore.getUser.username == null ? '未登录':userStore.getUser.username;
    const nowTimes = ref('');
    const validateForm = ref(null)
    const state = reactive({
        isCollapse: false,
        asideWidth: '240px',
        defaultHeight: null,
        routers: [],
        dialogPass: false,
        passForm: {
            oldPassword: '',
            password: '',
            rePassword: ''
        },
        iframe: {
            isIframe: true,
            src: '',
        },
    })

    const {
        logo,isCollapse,asideWidth,defaultHeight,routers,dialogPass,passForm,iframe
    } = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight = (document.body.clientHeight || document.documentElement.clientHeight);
    })

    onMounted(()=>{
        // 获取菜单
        state.routers = menuStore.getMenu;
        onNowTimes();

        window.addEventListener('resize', onDefaultHeight);
    })

    onDeactivated(()=>{
        window.removeEventListener('resize', onDefaultHeight, false)
    })

    const onDefaultHeight = () =>{
        state.defaultHeight = window.innerHeight
    }

    const onNestedLink = (val) =>{
        state.iframe.isIframe = true;
        state.iframe.src = val.url;
    }

    const onSelect = (e) =>{
        if(e){
            state.iframe.isIframe = false;
        }
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
                    // 清除相关缓存信息
                    userStore.delUser();
                    tokenStore.delToken();
                    menuStore.delMenu();
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
        state.passForm.username = userStore.getUser.username;
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

</script>
<style lang="scss">
    .layout-container{
        .el-header{
            padding: 0;
            background: var(--bg1);

            .header{
                display: flex;
                justify-content: space-between;
                line-height: 50px;
                color: var(--theme);
                // border:1px transparent solid;
                // border-image:linear-gradient(to right,var(--bg1),#DCDFE6,var(--bg1)) 1 10;
                // box-shadow: 0 4px 8px 0 rgba(36,46,66,.06)!important;
                padding-right: 0.4rem;
                
                .el-dialog__header{
                    background: var(--theme);
                    padding: 0 10px;
                    .el-dialog__title{
                        color: var(--theme);
                    }
                }
            }
        }

        .el-aside{
            height: 100%;
            transition: all .5s;
            background: var(--bg1);
            overflow-y: auto;
            overflow-x: hidden;
            .el-menu{
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
            .el-card{
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
            .el-dialog__header{
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
    }
</style>