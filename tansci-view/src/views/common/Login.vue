<template>
	<div class="login" :style="loginStyle">
		<el-card shadow="always">
			<div class="login-main">
				<div class="login-logo">
					<el-image :src="loginLogo"  style="width: 100%; height: 100%;"></el-image>
				</div>
				<div class="login-form">
					<el-form v-if="loginMode" :model="loginForm" :rules="rules" ref="loginRuleForm">
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
						<el-form-item prop="verifyStatus" :rules="[{required: true,message: '请拖动滑块验证',trigger: 'blur'}]">
							<SlidingVerify ref="slidingVerify" :status="loginForm.verifyStatus" :successFun="onVerifySuccess" :errorFun="onVerifyError"></SlidingVerify>
						</el-form-item>
						<el-form-item>
							<el-checkbox v-model="loginForm.keepPassword" label="记住密码"></el-checkbox>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" round @click="submit" style="width:100%">登录</el-button>
						</el-form-item>
					</el-form>
					<div v-else-if="!loginMode" class="other-form">
						<div class="login-form-title">{{otherForm.qrcodeTitle}}</div>
						<el-result v-if="otherForm.status == 1" icon="success" title="扫码成功" sub-title="正在登录中，请稍后。"></el-result>
						<el-result v-if="otherForm.status == 2" icon="error" title="授权失败" sub-title="登录授权失败，请刷新重试。"></el-result>
						<el-image v-if="otherForm.status == 0" :src="otherForm.qrcodeUrl" :lazy="true" style="width:180px;height:180px;cursor: pointer;"></el-image>
						<div>
							<el-button @click="loginMode = true" type="text" icon='UserFilled'>账号密码登录</el-button>
						</div>
					</div>
					<el-divider><el-icon><star-filled /></el-icon></el-divider>
					<div class="other-login">
						<el-image v-for="item in otherForm.modes" :key="item.id" @click="otherLogin(item.id)" :src="item.icon"
							style="width:32px;height:32px;padding:0 0.4rem;cursor: pointer;">
						</el-image>
					</div>
				</div>
			</div>
		</el-card>
	</div>
</template>
<script setup>
	import {onBeforeMount,reactive,ref,toRefs,unref} from "vue"
	import {ElMessage} from 'element-plus'
	import {useRouter} from 'vue-router'
	import {useUserStore, useTokenStore} from '@/store/settings'
	import {login,wxLogin,wxCallback} from '@/api/systemApi'
	import SlidingVerify from '@/components/SlidingVerify.vue'

	const userStore = useUserStore();
	const tokenStore = useTokenStore();
	const router = useRouter()
	let loginRuleForm = ref(null) 
	let slidingVerify = ref()
	const loginLogo = new URL('../../assets/image/login-left.png', import.meta.url).href

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
		loginMode: true,
		otherForm: {
			modes:[
				{id:1,name:'微信扫码登录',icon: new URL('../../assets/image/icon/wechat.svg', import.meta.url).href},
				{id:2,name:'微博扫码登录',icon: new URL('../../assets/image/icon/weibo.svg', import.meta.url).href},
				{id:3,name:'QQ扫码登录',icon: new URL('../../assets/image/icon/qq.svg', import.meta.url).href},
			],
			qrcodeTitle: '',
			qrcodeUrl: '',
			status: 0,
		},
		socket: null,
		socketUrl: 'ws://localhost:8005/tansci/ws/',
	})
	const {loginStyle,loginForm,loginMode,otherForm,socket,socketUrl} = toRefs(state)

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

		let param = {
			username: state.loginForm.username,
			password: state.loginForm.password
		}
		login(param).then(res=>{
			if(res){
				// 存储用户信息和token
				userStore.setUser(res.result);
				tokenStore.setToken(res.result.token);
				router.push({path: 'main'});
			}
		}).catch(()=>{
			state.loginForm.verifyStatus = null;
			slidingVerify.value.onRefresh()
		})
	}

	const otherLogin = (type) =>{
		// 1、微信，2、微博，3、QQ，4、GitHub
		let modes = state.otherForm.modes;
		if(type === 1){
			let mode = modes.find(function(item){
				return item.id == type;
			});
			state.otherForm.qrcodeTitle = mode.name;

			// 请求接口获取二维码
			let id = '';
			state.otherForm.status = 0;
			wxLogin({}).then(res=>{
				if(res){
					state.otherForm.qrcodeUrl = res.result.qrcode;
					// 建立 socket 连接
					let url = state.socketUrl + res.result.state;
					webSocket(url)
				} else {
					ElMessage.warning(res.message)
				}
			})
			state.loginMode = false;
		} else {
			ElMessage.warning("暂不支持该登录方式！")
		}
	}

	const webSocket = (url) =>{
		if ('WebSocket' in window) {
			state.socket = new WebSocket(url);
			state.socket.onmessage = function (event) {
				let resp = JSON.parse(event.data);
				if(resp.status == 200){
					state.otherForm.status = 1;
					// 存储用户信息和token
					userStore.setUser(resp);
					tokenStore.setToken(resp.token);
					router.push({path: 'main'});
				} else {
					state.otherForm.status = 2;
				}
			}
			state.socket.onopen = function () {
				console.log('连接已建立')
			}
			state.socket.onclose = function () {
				console.log('连接已关闭')
			}
			state.socket.onerror = function () {
				console.log('连接异常,尝试重新连接')
				webSocket();
			}
			window.onbeforeunload = function () {
				state.socket.onclose();
			}
		}
	} 
</script>
<style lang="scss" scoped="scoped">
	.login {
		background-image: url('../../assets/image/login-bg.svg');
		background-size: 100% 100%;
		height: 100%;
		width: 100%;
		display: flex;
		flex-wrap: wrap;
		justify-content: center;
		align-items: center;
		.el-card__body{
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
			.other-form{
				text-align: center;
				.el-image{
					padding: 2px;
					border: 2px solid #67C23A;
					border-radius: 4px;
				}
			}
		}
	}
</style>
