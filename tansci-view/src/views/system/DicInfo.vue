<template>
    <div class="dic">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="true" :page="false" :loading="loading">
                <template #search>
                    <div><el-input @change="onSearch" v-model="searchForm.keyword" placeholder="请输入关键字筛选"></el-input></div>
                </template>
                <template #column="scope">
                    <el-button @click="onAdd(scope)" type="text" style="color:var(--add)">添加</el-button>
                    <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                    <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
                </template>
            </Table>
            <el-dialog title="新增字典" v-model="addVisible" :show-close="false">
                <el-form :model="addForm" :rules="rules" ref="addRuleForm" label-position="right" label-width="100px">
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <el-form-item label="分组名" prop="groupName" :rules="[{required: true, message: '分组名不能为空', trigger: 'blur'}]">
                                <el-input v-model="addForm.groupName" placeholder="请输入分组名称" style="width:100%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="字典类型" prop="type" :rules="[{required: true, message: '字典类型不能为空', trigger: 'change'}]">
                                <el-select v-model="addForm.type" placeholder="请选择字典类型" style="width:100%">
                                    <el-option v-for="item in typeList" :key="item" :label="item.dicLabel" :value="item.dicValue"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <el-form-item label="标签名" prop="dicLabel" :rules="[{required: true, message: '标签名不能为空', trigger: 'blur'}]">
                                <el-input v-model="addForm.dicLabel" placeholder="请输入标签名" style="width:100%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="数据值" prop="dicValue" :rules="[
                                {required: true, message: '数据值不能为空', trigger: 'blur'},
                                {pattern: /^([0-9]+\.?[0-9]*|-[0-9]+\.?[0-9]*)$/, message: '数据值必须是数字', trigger: 'blur'}]">
                                <el-input v-model="addForm.dicValue" placeholder="请输入数据值" autocomplete="off" style="width:100%"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col :span="12">
                            <el-form-item label="排序" prop="sort" :rules="[{required: true, message: '排序不能为空', trigger: 'blur'}]">
                                <el-input-number v-model="addForm.sort" :min="0" :max="999" style="width:100%"></el-input-number>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col>
                            <el-form-item label="备注" prop="remarks">
                                <el-input type="textarea" v-model="addForm.remarks" placeholder="请输入备注" :rows="3" maxlength="120" show-word-limit style="width:100%"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="addVisible = false">取 消</el-button>
                    <el-button type="primary" @click="onAddDic">确 定</el-button>
                    </span>
                </template>
            </el-dialog>
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import Table from '../../components/Table.vue'
    import {dicList, dicSave, dicUpdate, dicDel, getGroupNameByList} from '../../api/systemApi.js'

    const addRuleForm = ref(null)
    const state = reactive({
        searchForm:{
            keyword: null,
        },
        loading: false,
        tableTitle: [
            {prop:'dicLabel',label:'字典标签',align:'left'},
            {prop:'groupName',label:'分组名称'},
            {prop:'type',alias:'typeName',label:'字典类型'},
            {prop:'dicValue',label:'字典值'},
            {prop:'remarks',label:'备注'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        addVisible: false,
        addForm:{
            id:'',
            parentId:'',
            groupName:'',
            type:'',
            dicLabel:'',
            dicValue:'',
            sort: 0,
            remarks:''
        },
        typeList:[],
        operate: '',
    });

    const {
        searchForm,tableHeight,loading,tableTitle,tableData,
        addVisible,addForm,typeList,operate
    } = toRefs(state)

    onMounted(()=>{
        onDicList();
    });

    const onDicList = () =>{
        state.loading = true;
        dicList(state.searchForm).then(res=>{
            state.loading = false;
            state.tableData = res.result;
        });
    }

    const onSearch = () =>{
        onDicList();
    }

    const onTypeList = () =>{
        getGroupNameByList({
            groupName:'sys_dic_type'
        }).then(res=>{
            state.typeList = res.result;
        });
    }

    const onAddDic = async () =>{
        if(state.operate == 1){
            const form = unref(addRuleForm);
            if(!form) return;
            await form.validate();
            dicSave(state.addForm).then(res=>{
                if(res){
                    state.addVisible = false;
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    state.addForm = {
                        parentId:'',
                        groupName:'',
                        type:'',
                        dicLabel:'',
                        dicValue:'',
                        sort: 0,
                        remarks:''
                    };
                    onDicList();
                }
            })
        } else if (state.operate == 2) {
            dicUpdate(state.addForm).then(res=>{
                if(res){
                    state.addVisible = false;
                    ElMessage.success({
                        message: '更新成功！',
                        type: 'success'
                    });
                    state.addForm = {
                        id:'',
                        parentId:'',
                        groupName:'',
                        type:'',
                        dicLabel:'',
                        dicValue:'',
                        sort: 0,
                        remarks:''
                    };
                    onDicList();
                }
            })
        }
    }

    const onAdd = (val) =>{
        console.log(val.column.row.parentId)
        state.addForm = {
            id: null,
            parentId: val.column.row.id,
            groupName: null,
            type: null,
            dicLabel: null,
            dicValue: null,
            sort: 0,
            remarks: null
        }
        onTypeList();
        state.addVisible = true;
        state.operate = 1;
    }
    const onEdit = (val) =>{
        if(val.column.row.parentId == 0){
            ElMessage.warning({
                type: 'warning',
                message: '系统根节点不允许编辑!'
            });
            return;
        }
        state.addForm = {
            id: val.column.row.id,
            parentId: val.column.row.parentId,
            groupName: val.column.row.groupName,
            type: val.column.row.type,
            dicLabel: val.column.row.dicLabel,
            dicValue: val.column.row.dicValue,
            sort: val.column.row.sort,
            remarks: val.column.row.remarks
        }
        state.addVisible = true;
        state.operate = 2;
    }
    const onDelete = (val) =>{
        if(val.column.row.parentId == 0){
            ElMessage.warning({
                type: 'warning',
                message: '系统根节点不允许删除!'
            });
            return;
        }
        ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            let param = {
                id: val.column.row.id
            }
            dicDel(param).then(res=>{
                if(res){
                    ElMessage.success({
                        type: 'success',
                        message: '删除成功!'
                    });
                    onDicList();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }
</script>
<style lang="scss" scoped>
</style>