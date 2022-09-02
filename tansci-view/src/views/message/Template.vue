<template>
    <div class="template">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="true" :page="page" :loading="loading"
                @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @setCellColor="setCellColor">
                <template #search>
                    <div><el-button type="primary" @click="onAddTemplate">新增模板</el-button></div>
                    <div>
                        <el-select v-model="searchForm.type" placeholder="请选择字典类型" style="width:100%">
                            <el-option v-for="item in templateTypes" :key="item" :label="item.dicLabel" :value="item.dicValue"></el-option>
                        </el-select>
                    </div>
                    <div>
                        <el-select v-model="searchForm.businessType" placeholder="请选择业务类型" style="width:100%">
                            <el-option v-for="item in businessTypes" :key="item" :label="item.dicLabel" :value="item.dicValue"></el-option>
                        </el-select>
                    </div>
                    <div><el-input v-model="searchForm.name" placeholder="请输入名称"></el-input></div>
                    <div><el-button @click="onRefresh" icon="RefreshRight" circle></el-button></div>
                    <div><el-button @click="onSearch" type="primary" icon="Search">查询</el-button></div>
                </template>
                <template #column="scope">
                    <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                    <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
                </template>
            </Table>
        </el-card>
        <el-dialog :title="templateTitle" v-model="templateVisible" width="40%" :show-close="false">
            <el-form :model="templateForm" :rules="rules" ref="addRuleForm" label-position="left" label-width="100px">
                <el-form-item label="模板类型" prop="type" :rules="[{required: true, message: '模板类型不能为空', trigger: 'change'}]">
                    <el-select v-model="templateForm.type" placeholder="请选择字典类型" style="width:100%">
                        <el-option v-for="item in templateTypes" :key="item" :label="item.dicLabel" :value="item.dicValue"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="业务类型" prop="businessType" :rules="[{required: true, message: '业务类型不能为空', trigger: 'change'}]">
                    <el-select v-model="templateForm.businessType" placeholder="请选择业务类型" style="width:100%">
                        <el-option v-for="item in businessTypes" :key="item" :label="item.dicLabel" :value="item.dicValue"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="模板名称" prop="name" :rules="[{required: true, message: '模板名称不能为空', trigger: 'blur'}]">
                    <el-input v-model="templateForm.name" placeholder="请输入模板名称" style="width:100%" />
                </el-form-item>
                <el-form-item label="模板内容" prop="content" :rules="[{required: true, message: '模板内容不能为空', trigger: 'blur'}]">
                    <MsgTemplate ref="msgTemplateRef" :content="templateForm.content" @contentEvent="onContentEvent" style="width:100%" />
                </el-form-item>
                <el-form-item label="申请说明" prop="remark" :rules="[{required: true, message: '申请说明不能为空', trigger: 'blur'}]">
                    <el-input v-model="templateForm.remark" type="textarea" placeholder="请输入模板申请说明" :rows="3" maxlength="100" show-word-limit style="width:100%" />
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="templateVisible = false">取消</el-button>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>
<script setup>
    import {onMounted, reactive, nextTick, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import Table from '@/components/Table.vue'
    import MsgTemplate from '@/components/MsgTemplate.vue';
    import {templatePage,templateSave,templateUpdate,templateDel} from '@/api/messageApi.js'
    import {getGroupNameByList} from '@/api/systemApi.js'

    const msgTemplateRef = ref(null)
    const addRuleForm = ref(null)
    const state = reactive({
        searchForm:{
            type: null,
            businessType: null,
            name: null,
        },
        loading: false,
        page: {
            current: 1,
            size: 10,
            total: 1,
        },
        tableTitle: [
            {prop:'code',label:'模板编码'},
            {prop:'name',label:'模板名称'},
            {prop:'type',alias:'typeName',label:'模板类型'},
            {prop:'businessType',alias:'businessTypeName',label:'业务类型'},
            {prop:'state',alias:'stateName',label:'状态'},
            {prop:'creater',label:'创建人'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
            {prop:'remarks',label:'备注'}
        ],
        tableData:[],
        templateTypes:[],
        businessTypes:[],
        templateVisible: false,
        templateTitle: '添加模板',
        templateForm:{
            id: '',
            type: '',
            businessType: '',
            name: '',
            content: '',
            remark: ''
        }
    })

    const {
        searchForm,loading,page,tableHeight,tableTitle,tableData,statusList,templateTypes,businessTypes,
        templateVisible,templateTitle,templateForm
    } = toRefs(state)

    onMounted(() => {
        onTemplatePage();
        onTemplateType();
    })

    // 初始化数据
    const onTemplatePage = () =>{
        state.loading = true;
        templatePage(Object.assign(state.page,state.searchForm)).then(res=>{
            state.loading = false;
            state.tableData = res.result.records;
            state.page.current = res.result.current;
            state.page.size = res.result.size;
            state.page.total = res.result.total;
        })
    }
    const onSizeChange = (e) =>{
        state.page.size = e;
        onTemplatePage();
    }
    const onCurrentChange = (e) =>{
        state.page.current = e;
        onTemplatePage();
    }
    const onRefresh = () =>{
        state.searchForm = {
            type: null,
            businessType: null,
            name: null,
        }
        onTemplatePage();
    }
    const onSearch = () =>{
        onTemplatePage();
    }

    const onTemplateType = () =>{
        getGroupNameByList({groupName:'template_type'}).then(res=>{
            if(res){
                state.templateTypes = res.result;
            }
        });
        getGroupNameByList({groupName:'template_business_type'}).then(res=>{
            if(res){
                state.businessTypes = res.result;
            }
        });
    }

    // 添加
    const onAddTemplate = () =>{
        state.templateForm = {
            id: '',
            type: '',
            businessType: '',
            name: '',
            content: '',
            remark: ''
        }

        nextTick(()=>{
            msgTemplateRef.value.onLoad();
        })
        state.templateVisible = true;
    }

    // 编辑
    const onEdit = (val) =>{
        state.templateTitle = "修改模板";
        state.templateForm = {
            id: val.column.row.id,
            name: val.column.row.name,
            content: val.column.row.content,
            type: Number(val.column.row.type),
            businessType: Number(val.column.row.businessType),
            remark: val.column.row.remark
        }
        nextTick(()=>{
            msgTemplateRef.value.onLoad();
        })
        state.templateVisible = true;
    }

    const onContentEvent = (data) => {
		state.templateForm.content = data;
	}

    const onSubmit = async () =>{
        const form = unref(addRuleForm);
        if(!form) return;
        await form.validate();
        if (state.templateForm.id == null || state.templateForm.id == '') {
            // 添加
            templateSave(state.templateForm).then(res => {
                if (res) {
                    ElMessage.success('添加模板成功!');
                }
            });
        } else {
            // 修改
            templateUpdate(state.templateForm).then(res => {
                if (res) {
                    ElMessage.success('修改模板成功!');
                }
            });
        }
        state.templateVisible = false;
        onTemplatePage();
    }

    // 删除
    const onDelete = (val) =>{
        ElMessageBox.confirm('此操作将永久删除, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            let param = {
                id: val.column.row.id
            }
            templateDel(param).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onTemplatePage();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    const setCellColor = (e, callback) => {
        /**
         * e.row：表格那一行的数据
         * e.column：表格单元格那一列的信息
         */ 
        if(e.row.state === 0 && e.column.property === 'stateName'){
            callback({color: '#E6A23C'});
        } else if(e.row.state === 1 && e.column.property === 'stateName'){
            callback({color: '#67C23A'});
        } else if(e.row.state === 2 && e.column.property === 'stateName'){
            callback({color: '#f56c6c'});
        } else {
            callback({});
        }
    }
</script>
<style lang="scss" scoped>
</style>