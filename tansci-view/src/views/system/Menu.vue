<template>
    <div class="menu">
		<Table :data="tableData" :column="tableTitle" :operation="operation" :page="page" :loading="loading"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange">
		</Table>
    </div>
</template>
<script>
	import {onMounted, reactive, toRefs} from 'vue'
	import Table from '../../components/Table.vue'
    export default {
		components:{
			Table,
		},
        setup(){
			const state = reactive({
				loading: false,
				operation: {
                    add: true,
                    edit: true,
                    del: true,
                    upper: true,
                    down: true,
                },
                page: {
                    current: 1,
                    size: 10,
                    total: 0,
                },
                tableTitle: [
                    {prop:'name',label:'名称',align:'left'},
                    {prop:'chineseName',label:'中文名称'},
                    {prop:'englishName',label:'英文名称'},
                    {prop:'status',alias:'statusName',label:'状态'},
                    {prop:'type',alias:'typeName',label:'类型'},
                    {prop:'path',label:'路由'},
                    {prop:'sort',label:'排序'},
                    {prop:'updateTime',label:'更新时间'},
                    {prop:'createTime',label:'创建时间'},
                ],
                tableData:[],
			})

			onMounted(()=>{

			})

			const onSizeChange = (e) =>{
                state.page.size = e;
            }
            const onCurrentChange = (e) =>{
                state.page.current = e;
            }

			return {
				...toRefs(state),
				onSizeChange,
				onCurrentChange,
			}
		}
    }
</script>
<style lang="less" scoped>
	.home {
		padding: 0.2rem;
		height: calc(100vh - 200px);
		display: flex;
	}
</style>