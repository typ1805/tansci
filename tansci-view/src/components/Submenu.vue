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
				<span v-if="item.type == 1" style="vertical-align: middle;">{{item.chineseName}}</span>
				<span v-else-if="item.type == 2" @click="handleClick(item)" style="vertical-align: middle;">{{item.chineseName}}</span>
				<a v-else-if="item.type == 3" :href='item.url' target='_blank' style="vertical-align: middle;text-decoration: none;color:#242e42;">{{item.chineseName}}</a>
			</el-menu-item>
			<Submenu v-else :data='item' @onNestedLink="onNestedLink"></Submenu>
		</template>
	</el-sub-menu>
</template>
<script setup>
	import {defineProps, defineEmits} from 'vue'
	const props = defineProps({
		data: Array
	})

	const emit = defineEmits(['onNestedLink']);
	const handleClick = (e)=>{
		emit('onNestedLink', e)
	}
</script>
