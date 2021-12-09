<template>
	<div class="login" :style="login">
		<div class="login-main">
			<div></div>	
			<el-form class="login-form" :model="loginForm" :rules="rules" ref="loginRuleForm">
				<div class="login-form-title">欢迎登录</div>
				<el-form-item prop="username" :rules="[
						{required: true,message: '请输入用户名',trigger: 'blur'},
						{pattern: /^[a-zA-Z]\w{4,17}$/,message: '用户名式有误，请重新输入',trigger: 'blur'}]">
					<el-input v-model="loginForm.username" :prefix-icon="Avatar" placeholder="请输入用户名称" style="width:100%">
						<template #prefix>
							<el-icon :size="20" style="padding-top:6px;"><Avatar/></el-icon>
						</template>
					</el-input>
				</el-form-item>
				<el-form-item prop="password" :rules="[
						{required: true,message: '请输入密码',trigger: 'blur'},
						{pattern: /^[a-zA-Z]\w{5,17}$/,message: '密码格式有误，请重新输入',trigger: 'blur'}]">
					<el-input type="password" v-model="loginForm.password" :prefix-icon="Lock" show-password placeholder="请输入密码" style="width:100%">
						<template #prefix>
							<el-icon :size="20" style="padding-top:6px;"><Lock/></el-icon>
						</template>
					</el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="text">忘记密码？</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" round @click="submit" style="width:100%">登录</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>
<script>
	import {onBeforeMount,reactive,ref,toRefs,unref} from "vue"
	import {Avatar,Lock} from "@element-plus/icons"
	import {useRouter} from 'vue-router'
	import {useStore} from 'vuex'
	import {login} from '../api/systemApi'
	export default {
		components:{
			Avatar,Lock,
		},
		setup() {
			const store = useStore()
			const router = useRouter()
			const loginRuleForm = ref(null)
			const state = reactive({
				login: {
					height: '',
				},
				loginForm: {
					username: '',
					password: '',
				},
			})

			onBeforeMount(() => {
				state.login.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px"
			})

			const submit = async () => {
				const form = unref(loginRuleForm)
				if (!form) return;
				await form.validate();

				// 登录成功后设置token到vuex中
				login(state.loginForm).then(res=>{
					if(res){
						store.commit('setToken', res.result.token);
						store.commit('setUser', res.result);
						router.push({
							path: 'home'
						});
					}
				})
			}

			return {
				...toRefs(state),
				loginRuleForm,
				submit,
			}
		}
	}
</script>
<style lang="less">
	.login {
		background-image: url('../assets/image/login-bg.jpg');
		background-size: 100% 100%;
		.login-main{
			height: 100%;
			display: flex;
			flex-wrap: wrap;
			justify-content: space-around;
			align-items: center;
			.login-form{
				padding: 2rem;
				background: rgba(0, 0, 0, 0.5);
				width: 20rem;
				border-radius: 1.2rem;
				border: 1px solid #fff;
				box-shadow: 0px 0px 20px 4px #fff;
				.login-form-title{
					color: rgb(255, 255, 255);
					text-align: center;
					font-size: 18px;
					font-weight: 700;
					padding-bottom: 2rem;
				}
			}
		}
	}
</style>
