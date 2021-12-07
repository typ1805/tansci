<template>
	<div class="home">
		首页
	</div>
</template>

<script>
	import {onMounted,reactive,toRefs} from 'vue'
	export default {
		setup() {
			const state = reactive({
				username: '小平',
			})

			onMounted(() => {

			});
			return {
				...toRefs(state),
			}
		}
	}
</script>
<style lang="less" scoped="scoped">
	.home {
		padding: 0.2rem;
		height: calc(100vh - 200px);
		display: flex;
	}
</style>
