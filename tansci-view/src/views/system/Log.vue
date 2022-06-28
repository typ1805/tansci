<template>
    <div class="log">
        <el-card>
            <el-tabs v-model="activeName" @tab-click="onHandleClick">
                <el-tab-pane label="操作日志" name="info">
                    <Table :data="tableData" :column="tableInfoTitle" :page="page" :loading="loading"
                        @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @onButtonClick="onDetail" @setCellColor="setCellColor">
                    </Table>
                </el-tab-pane>
                <el-tab-pane label="异常日志" name="error">
                    <Table :data="tableData" :column="tableErrorTitle" :page="page" :loading="loading" :tableHeight="tableHeight"
                        @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @onButtonClick="onDetail">
                    </Table>
                </el-tab-pane>
            </el-tabs>
            <el-dialog v-model="detailVisible" title="日志详情" :show-close="false" width="60%">
                <el-descriptions v-if="activeName == 'info'" :column="3" border>
                    <el-descriptions-item label-align="right" label="功能模块">{{detail.module}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="操作类型">
                        <el-tag v-if="detail.type == 'SELECT'">{{detail.type}}</el-tag>
                        <el-tag v-else-if="detail.type == 'INSERT'" type="success">{{detail.type}}</el-tag>
                        <el-tag v-else-if="detail.type == 'UPDATE'" type="warning">{{detail.type}}</el-tag>
                        <el-tag v-else-if="detail.type == 'DELETE'" type="danger">{{detail.type}}</el-tag>
                        <el-tag v-else type="info">{{detail.type}}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label-align="right" label="操作描述">{{detail.message}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="耗时">
                        <span style="color:red;">{{detail.takeUpTime}} ms</span>
                    </el-descriptions-item>
                    <el-descriptions-item label-align="right" label="操作用户">{{detail.userName}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="请求URL">{{detail.uri}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="请求IP">{{detail.ip}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="版本号">
                        <el-tag>{{detail.version}}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label-align="right" label="创建时间">{{detail.createTime}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" :span="3" label="操作方法">{{detail.method}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" :span="3" label="请求参数">
                        <div class="detail-text scroll-div">{{detail.reqParam}}</div>
                    </el-descriptions-item>
                    <el-descriptions-item label-align="right" :span="3" label="响应参数">
                        <div class="detail-text scroll-div">{{detail.resParam}}</div>
                    </el-descriptions-item>
                </el-descriptions>
                <el-descriptions v-if="activeName == 'error'" :column="3" border>
                    <el-descriptions-item label-align="right" label="异常名称">
                        <span style="color:red;">{{detail.name}}</span>
                    </el-descriptions-item>
                    <el-descriptions-item label-align="right" label="操作用户">{{detail.userName}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="请求URL">{{detail.uri}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="请求IP">{{detail.ip}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" label="版本号">
                        <el-tag>{{detail.version}}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label-align="right" label="异常时间">{{detail.createTime}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" :span="3" label="操作方法">{{detail.method}}</el-descriptions-item>
                    <el-descriptions-item label-align="right" :span="3" label="请求参数">
                        <div class="detail-text scroll-div">{{detail.reqParam}}</div>
                    </el-descriptions-item>
                    <el-descriptions-item label-align="right" label="异常信息">
                        <div class="detail-text scroll-div" v-html="detail.message"></div>
                    </el-descriptions-item>
                </el-descriptions>
            </el-dialog>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'
    import {logInfoPage,logErrorPage} from '../../api/systemApi.js'
    import Table from '../../components/Table.vue'

    const state = reactive({
        activeName: 'info',
        loading: false,
        page: {
            current: 1,
            size: 20,
            total: 0,
        },
        tableInfoTitle: [
            {prop:'module',label:'功能模块',type:'button',option:{size:'mini'}},
            {prop:'type',label:'操作类型',type:'tag',option:{type:'success',size:'small',effect:'plain'}},
            {prop:'message',label:'操作描述'},
            {prop:'takeUpTime',label:'耗时（ms）'},
            {prop:'userName',label:'操作者'},
            {prop:'method',label:'操作方法'},
            {prop:'uri',label:'请求URL'},
            {prop:'ip',label:'请求IP'},
            {prop:'version',label:'版本号',type:'tag',option:{type:'danger',size:'small',effect:'plain'}},
            {prop:'createTime',label:'创建时间'},
        ],
        tableErrorTitle: [
            {prop:'name',label:'异常名称',type:'button',option:{size:'mini'}},
            {prop:'message',label:'异常信息',tooltip:false},
            {prop:'userName',label:'操作者'},
            {prop:'method',label:'操作方法'},
            {prop:'uri',label:'请求URL'},
            {prop:'ip',label:'请求IP'},
            {prop:'version',label:'版本号',type:'tag',option:{type:'danger',size:'small',effect:'plain'}},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        detailVisible: false,
        detail:{},
    })
    const {
        activeName,loading,operation,page,tableHeight,tableInfoTitle,tableErrorTitle,tableData,detailVisible,detail
    } = toRefs(state)

    onMounted(()=>{
        onLogPage();
    })

    const onHandleClick = (tab, event) =>{
        state.activeName = tab.paneName;
        state.page = {
            current: 1,
            size: 20,
            total: 0,
        }
        onLogPage();
    }

    const onLogPage = () =>{
        if(state.activeName == 'info'){
            state.loading = true;
            logInfoPage(state.page).then(res=>{
                state.loading = false;
                state.tableData = res.result.records;
                state.page.current = res.result.current;
                state.page.size = res.result.size;
                state.page.total = res.result.total;
            })
        } else {
            state.loading = true;
            logErrorPage(state.page).then(res=>{
                state.loading = false;
                state.tableData = res.result.records;
                state.page.current = res.result.current;
                state.page.size = res.result.size;
                state.page.total = res.result.total;
            })
        }
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onLogPage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onLogPage();
    }

    const onDetail = (val) =>{
        state.detail = val;
        state.detailVisible = true;
    }

    const setCellColor = (e, callback) => {
        /**
         * e.row：表格那一行的数据
         * e.column：表格单元格那一列的信息
         */ 
        if(e.column.property === 'takeUpTime'){
            callback({color: '#f56c6c'});
        } else {
            callback({});
        }
    }
</script>
<style lang="scss" scoped>
    .log{
        .detail-text{
            max-height: 400px;
            overflow: auto;
        }
        .el-descriptions__label{
            width: 80px;
        }
    }
</style>