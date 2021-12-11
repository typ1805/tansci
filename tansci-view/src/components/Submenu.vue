<template>
	<el-sub-menu :index="data.path">
		<template #title>
			<!-- <i v-if="data.icon" :class="data.icon"></i> -->
			<span>{{data.meta.title}}</span>
		</template>
		<template v-for="item in data.children" :key="item">
            <el-menu-item v-if="!item.children || item.children.length == 0" :index="item.path">
				<!-- <i v-if="item.icon" :class="item.icon"></i> -->
				<span>{{item.meta.title}}</span>
			</el-menu-item>
			<Submenu v-else :data='item'></Submenu>
		</template>
	</el-sub-menu>
</template>
<script setup>
	import {defineProps} from 'vue'
	const props = defineProps({
		data: Array
	})
</script>
