<template>
	<div class="login" :style="loginStyle">
		<el-card shadow="always" style="padding:5rem;">
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
						<el-form-item>
							<el-checkbox v-model="loginForm.keepPassword" label="记住密码"></el-checkbox>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" round @click="submit" style="width:100%">登录</el-button>
						</el-form-item>
					</el-form>
				</div>
			</div>
		</el-card>
	</div>
</template>
<script setup>
	import {onBeforeMount,reactive,ref,toRefs,unref} from "vue"
	import {useRouter} from 'vue-router'
	import {useStore} from 'vuex'
	import {login} from '../api/systemApi'

	const store = useStore()
	const router = useRouter()
	let loginRuleForm = ref(null) 
	const state = reactive({
		loginStyle: {
			height: '',
		},
		loginForm: {
			username: '',
			password: '',
			keepPassword: null,
		},
	})

	const {loginStyle,loginForm} = toRefs(state)

	onBeforeMount(() => {
		state.loginStyle.height = (document.body.clientHeight || document.documentElement.clientHeight) + "px"
	})

	const submit = async () => {
		const form = unref(loginRuleForm)
		if (!form) return;
		await form.validate();

		// if(state.loginForm.code <= 0){
		// 	ElMessage.warning('请拖动滑块进行验证！')
		// 	return false;
		// }

		// 登录成功后设置token到vuex中
		let param = {
			username: state.loginForm.username,
			password: state.loginForm.password
		}
		login(param).then(res=>{
			if(res){
				store.commit('setToken', res.result.token);
				store.commit('setUser', res.result);
				router.push({
					path: 'home'
				});
			}
		})
	}
</script>
<style lang="less" scoped="scoped">
	.login {
		background-image: url('../assets/image/login-bg.jpg');
		background-size: 100% 100%;
		height: 100%;
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		.login-main{
			display: flex;
			flex-wrap: wrap;
			justify-content: center;
			.login-logo{
				width: 20rem;
				padding-right: 5rem;
			}
			.login-form{
				width: 20rem;
				.login-form-title{
					font-size: 18px;
					font-weight: 700;
					text-align: center;
					padding-bottom: 2rem;
				}
			}
		}
	}
</style>
