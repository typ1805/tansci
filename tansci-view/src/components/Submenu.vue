<template>
	<el-sub-menu :index="data.path">
		<template #title>
			<el-icon v-if="data.icon" style="vertical-align: middle;">
				<component :is="data.icon"></component>
			</el-icon>
			<span style="vertical-align: middle;">{{data.chineseName}}</span>
		</template>
		<template v-for="item in data.children" :key="item">
            <el-menu-item v-if="!item.children || item.children.length == 0" :index="item.path">
				<el-icon v-if="item.icon" style="vertical-align: middle;">
					<component :is="item.icon"></component>
				</el-icon>
				<span style="vertical-align: middle;">{{item.chineseName}}</span>
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
