<template>
	<div class="home">
		首页 {{username}}
	</div>
</template>
<script setup>
	import {onMounted,reactive,toRefs} from 'vue'
	const state = reactive({
		username: '小平',
	})

	const {username} = toRefs(state)

	onMounted(() => {
		
	})
</script>
<style lang="less" scoped>
	.home {
		padding: 0.2rem;
		height: calc(100vh - 200px);
		display: flex;
	}
</style>
