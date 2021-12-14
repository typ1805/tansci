<template>
    <div class="menu">
		<Table :data="tableData" :column="tableTitle" :operation="true" :page="page" :loading="loading" :tableHeight="tableHeight"
            @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @setCellColor="setCellColor" @onButtonClick="onButtonClick">
            <template #search>
                <div>111</div>
            </template>
            <template #column="scope">
                <el-button @click="aaa(scope)" type="primary">Primary</el-button>
            </template>
		</Table>
    </div>
</template>
<script setup>
	import {onMounted, reactive, toRefs} from 'vue'
	import Table from '../../components/Table.vue'
    const state = reactive({
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 0,
        },
        tableHeight: window.innerHeight-180,
        tableTitle: [
            {prop:'name',label:'名称',align:'left'},
            {prop:'chineseName',label:'中文名称',type:'tag',option:{effect:'dark',type:'danger',size:'mini'}},
            {prop:'englishName',label:'英文名称',type:'button',option:{size:'mini'}},
            {prop:'status',alias:'statusName',label:'状态',type:'switch',option:{
                activeValue:1,activeColor:'#13ce66',inactiveValue:0,activeText:'否',inactiveColor:'#ff4949',inactiveText:'是'}
            },
            {prop:'type',alias:'typeName',label:'类型',type:'progress',option:{color:'#000fff'}},
            {prop:'path',label:'路由'},
            {prop:'sort',label:'排序'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
    })

    const {loading,operation,page,tableHeight,tableTitle,tableData} = toRefs(state)


    onMounted(()=>{
        state.tableData = [
            {name:'测试01',chineseName:'测试01',englishName:'测试01',status:1,type:10,path:'测试01',sort:1,updateTime:'2021-12-13 15:00:14',createTime:'2021-12-13 15:00:14'},
            {name:'测试02',chineseName:'测试02',englishName:'测试01',status:0,type:100,path:'测试01',sort:1,updateTime:'2021-12-13 15:00:14',createTime:'2021-12-13 15:00:14'},
        ]
    })

    const onSizeChange = (e) =>{
        state.page.size = e;
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
    }

    const onAdd = (row) =>{
        console.log(row)
    }

    const setCellColor = (e, callback) => {
        /**
         * e.row：表格那一行的数据
         * e.column：表格单元格那一列的信息
         */ 
        if(e.column.property === 'name'){
            callback({color: '#67C23A', fontWeight: '700'});
        } else if(e.row.delFlag === 1 && e.column.property === 'delFlagName'){
            callback({color: '#f00', fontWeight: '700'});
        } else {
            callback({});
        }
    }

    const onButtonClick = (row) =>{
        console.log(row)
    }

    const aaa = (row) => {
        console.log(row)
    }
</script>
<style lang="less" scoped>
	.menu {
	}
</style>