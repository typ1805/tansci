<template>
    <div class="message-log">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :page="page" :loading="loading"
                @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @setCellColor="setCellColor">
                <template #search>
                    <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                </template>
            </Table>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'
    import Table from '@/components/Table.vue'
    import {logPage} from '@/api/messageApi'

    const state = reactive({
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'code',label:'模板编码'},
            {prop:'state',alias:'stateName',label:'状态'},
            {prop:'sendTime',label:'发送时间'},
            {prop:'content',label:'发送内容'}
        ],
        tableData:[],
    })

    const {loading,page,tableHeight,tableTitle,tableData} = toRefs(state)

    onMounted(() => {
        onLogPage();
    })

    // 初始化数据
    const onLogPage = () =>{
        state.loading = true;
        logPage(state.page).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onLogPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onLogPage();
    }
    const onRefresh = () =>{
        onLogPage();
    }

    const setCellColor = (e, callback) => {
        /**
         * e.row：表格那一行的数据
         * e.column：表格单元格那一列的信息
         */ 
        if(e.row.state === 0 && e.column.property === 'stateName'){
            callback({color: '#67C23A', fontWeight: '700'});
        } else if(e.row.state === 1 && e.column.property === 'stateName'){
            callback({color: '#f00', fontWeight: '700'});
        } else {
            callback({});
        }
    }

</script>
<style lang="scss" scoped>
</style>