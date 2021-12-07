<template>
	<div class="login" :style="login">
		<div class="login-right">
			<el-form :model="loginForm" :rules="rules" ref="validateForm" label-width="1em" class="login-body"
				hide-required-asterisk="false">
				<el-form-item prop="username">
					<el-input v-model="loginForm.username" prefix-icon="el-icon-user" placeholder="请输入用户名称"></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" v-model="loginForm.password" prefix-icon="el-icon-lock" show-password
						placeholder="请输入密码"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button class="noPassText" type="text" style="color: #606266;">忘记密码？</el-button>
				</el-form-item>
				<el-form-item class="login-body-button">
					<el-button class="login-submit"  round @click="submit">登录</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	import {onBeforeMount,reactive,ref,toRefs,unref} from "vue"
	import {useRouter} from 'vue-router'
	export default {
		setup() {
			const router = useRouter()
			const validateForm = ref(null)
			const state = reactive({
				login: {
					height: '',
				},
				loginForm: {
					username: '',
					password: '',
				},
			})

			const rules = {
				username: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					},
					{
						pattern: /^[a-zA-Z]\w{4,17}$/,
						message: '用户名式有误，请重新输入',
						trigger: 'blur'
					}
				],
				password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					},
					{
						pattern: /^[a-zA-Z]\w{5,17}$/,
						message: '密码格式有误，请重新输入',
						trigger: 'blur'
					}
				]
			}

			onBeforeMount(() => {
				state.login.height = document.body.clientHeight + "px"
			})

			const submit = async () => {
				const form = unref(validateForm)
				if (!form) return;
				await form.validate();

				// 登录成功后设置token到vuex中
				router.push({
					path: 'home'
				});
			}

			return {
				...toRefs(state),
				rules,
				validateForm,
				submit,
			}
		}
	}
</script>

<style lang="less">
	.login {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		align-items: center;
	}
</style>
