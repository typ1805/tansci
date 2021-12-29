<template>
	<div class="login" :style="loginStyle">
		<el-card shadow="always">
			<div class="login-main">
				<div class="login-logo">
					<el-image src="./src/assets/image/login-logo.png" style="width: 100%; height:100%"></el-image>
				</div>
				<div class="login-form">
					<el-form :model="loginForm" :rules="rules" ref="loginRuleForm">
						<div class="login-form-title">欢迎登录</div>
						<el-form-item prop="username" :rules="[
								{required: true,message: '请输入用户名',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{4,17}$/,message: '用户名式有误，请重新输入',trigger: 'blur'}]">
							<el-input v-model="state.loginForm.username" prefix-icon="Avatar" placeholder="请输入用户名称" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="password" :rules="[
								{required: true,message: '请输入密码',trigger: 'blur'},
								{pattern: /^[a-zA-Z]\w{5,17}$/,message: '密码格式有误，请重新输入',trigger: 'blur'}]">
							<el-input type="password" v-model="loginForm.password" prefix-icon="Lock" show-password placeholder="请输入密码" style="width:100%"></el-input>
						</el-form-item>
						<el-form-item prop="verifyStatus" :rules="[{required: true,message: '请拖动滑块进行验证',trigger: 'blur'}]">
							<SlidingVerify ref="slidingVerify" :status="loginForm.verifyStatus" :successFun="onVerifySuccess" :errorFun="onVerifyError"></SlidingVerify>
						</el-form-item>
						<el-form-item>
							<el-checkbox v-model="loginForm.keepPassword" label="记住密码"></el-checkbox>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" round @click="submit" style="width:100%">登录</el-button>
						</el-form-item>
					</el-form>
					<el-divider>
						<el-icon><star-filled /></el-icon>
					</el-divider>
					<div class="other-login">
						<el-button type="success" icon="Message" circle></el-button>
						<el-button type="warning" icon="Microphone" circle></el-button>
						<el-button type="danger" icon="Comment" circle></el-button>
					</div>
				</div>
			</div>
		</el-card>
	</div>
</template>
<script setup>
	import {onBeforeMount,reactive,ref,toRefs,unref} from "vue"
	import {useRouter} from 'vue-router'
	import {useStore} from 'vuex'
	import {login,menuList} from '../api/systemApi'
	import SlidingVerify from '../components/SlidingVerify.vue'

	const store = useStore()
	const router = useRouter()
	let loginRuleForm = ref(null) 
	let slidingVerify = ref()
	const state = reactive({
		loginStyle: {
			height: '',
		},
		loginForm: {
			username: '',
			password: '',
			verifyStatus: null,
			keepPassword: null,
		},
	})

	const {loginStyle,loginForm} = toRefs(state)

	onBeforeMount(() => {
		state.loginStyle.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px"
	})

	const onVerifySuccess = (e) =>{
		state.loginForm.verifyStatus = e;
	}
	const onVerifyError = (e) =>{
		state.loginForm.verifyStatus = e;
	}

	const submit = async () => {
		const form = unref(loginRuleForm)
		if (!form) return;
		await form.validate();

		// 登录成功后设置token到vuex中
		let param = {
			username: state.loginForm.username,
			password: state.loginForm.password
		}
		login(param).then(loginRes=>{
			if(loginRes){
				store.commit('setToken', loginRes.result.token);
				store.commit('setUser', loginRes.result);
				// 获取菜单
				menuList({type:1, status: 1}).then(menuRes=>{
					store.commit('setMenus', menuRes.result);
					router.push({path: 'home'});
				})
			}
		}).catch(()=>{
			state.loginForm.verifyStatus = null;
			console.log(slidingVerify)
			slidingVerify.value.onRefresh()
		})
	}
</script>
<style lang="less" scoped="scoped">
	.login {
		background: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
		height: 100%;
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		/deep/ .el-card__body{
			padding: 0;
		}
		.login-main{
			display: flex;
			flex-wrap: wrap;
			justify-content: center;
			.login-logo{
				background: var(--theme);
				width: 20rem;
				padding: 8rem 5rem;
				border-top-right-radius: 38px;
				border-bottom-right-radius: 38px;
			}
			.login-form{
				width: 20rem;
				padding: 8rem 5rem;
				.login-form-title{
					font-size: 18px;
					font-weight: 700;
					text-align: center;
					padding-bottom: 2rem;
				}
				.other-login{
					text-align: center;
				}
			}
		}
	}
</style>
