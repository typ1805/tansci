<template>
    <div class="org">
        <el-card>
            <Table :data="tableData" :column="tableTitle" :operation="true" :page="false" :loading="loading">
                <template #search>
                    <div><el-input @change="onSearch" v-model="searchForm.name" placeholder="请输入名称筛选"></el-input></div>
                </template>
                <template #column="scope">
                    <el-button @click="onAdd(scope)" type="text" style="color:var(--add)">添加</el-button>
                    <el-button @click="onEdit(scope)" type="text" style="color:var(--edit)">编辑</el-button>
                    <el-button @click="onRole(scope)" type="text" style="color:var(--role)">权限</el-button>
                    <el-button @click="onDelete(scope)" type="text" style="color:var(--delete)">删除</el-button>
                </template>
            </Table>
            <el-dialog title="新增组织" v-model="addVisible" :show-close="false" width="30%">
                <el-form :model="addForm" :rules="rules" ref="addRuleForm" label-position="right" label-width="100px">
                    <el-row :gutter="20">
                        <el-col>
                            <el-form-item label="组织名称" prop="name" :rules="[{required: true, message: '组织名不能为空', trigger: 'blur'}]">
                                <el-input v-model="addForm.name" placeholder="请输入组织名称" style="width:100%"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row :gutter="20">
                        <el-col>
                            <el-form-item label="排序" prop="sort" :rules="[{required: true, message: '排序不能为空', trigger: 'blur'}]">
                                <el-input-number v-model="addForm.sort" :min="0" :max="999" style="width:100%"></el-input-number>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="addVisible = false">取 消</el-button>
                    <el-button type="primary" @click="onAddOrg">确 定</el-button>
                    </span>
                </template>
            </el-dialog>
            <el-dialog title="分配权限" v-model="roleVisible" width="30%" :show-close="false">
                <el-select v-model="roleId" placeholder="请选择角色" style="width: 100%">
                    <el-option v-for="item in roleData" :key="item.id" :label="item.name" :value="item.id"></el-option>
                </el-select>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="roleVisible = false">取消</el-button>
                    <el-button type="primary" @click="onAddOrgRole" :loading="roleLoading">确定</el-button>
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
    import {orgList,saveOrg,updateOrg,delOrg,roleList,orgRoleAdd,orgRoleInfo} from '../../api/systemApi.js'

    const addRuleForm = ref(null)
    const state = reactive({
        searchForm:{
            name: null,
        },
        loading: false,
        tableTitle: [
            {prop:'name',label:'组织名称',align:'left'},
            // {prop:'parentId',label:'父组织ID'},
            {prop:'sort',label:'排序'},
            {prop:'updateTime',label:'更新时间'},
            {prop:'createTime',label:'创建时间'},
        ],
        tableData:[],
        addVisible: false,
        addForm:{
            id:'',
            parentId:'',
            name:'',
            sort: 0
        },
        operate: '',
        roleVisible: false,
        roleLoading: false,
        roleData:[],
        roleId: null,
        orgId: null,
    })

    const {
        searchForm,loading,tableHeight,tableTitle,tableData,addVisible,addForm,
        operate,roleVisible,roleLoading,roleData,roleId,orgId
    } = toRefs(state)

    onMounted(()=>{
        onOrgList();
    });

    const onOrgList = () =>{
        state.loading = true;
        orgList(state.searchForm).then(res=>{
            state.loading = false;
            state.tableData = res.result;
        });
    }

    const onSearch = () =>{
        onOrgList();
    }

    const onAdd = (val) =>{
        state.addForm = {
            id: null,
            parentId: val.column.row.id,
            name: '',
            sort: 0
        }
        state.addVisible = true;
        state.operate = 1;
    }

    const onEdit = (val) =>{
        if(val.column.row.parentId == 0){
            ElMessage.warning('系统根节点不允许编辑!');
            return;
        }
        state.addForm = {
            id: val.column.row.id,
            parentId: val.column.row.parentId,
            name: val.column.row.name,
            sort: val.column.row.sort
        }
        state.addVisible = true;
        state.operate = 2;
    }

    const onAddOrg = async () =>{
        if(state.operate == 1){
            const form = unref(addRuleForm);
            if(!form) return;
            await form.validate();
            saveOrg(state.addForm).then(res=>{
                if(res){
                    state.addVisible = false;
                    ElMessage.success('添加成功！');
                    state.addForm = {
                        parentId:'',
                        name:'',
                        sort: 0
                    };
                    onOrgList();
                }
            })
        } else if (state.operate == 2) {
            updateOrg(state.addForm).then(res=>{
                if(res){
                    state.addVisible = false;
                    ElMessage.success('更新成功！');
                    state.addForm = {
                        id:'',
                        parentId:'',
                        name:'',
                        sort: 0
                    };
                    onOrgList();
                }
            })
        }
    }

    const onDelete = (val) =>{
        if(val.column.row.parentId == 0){
            ElMessage.warning('系统根节点不允许删除!');
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
            delOrg(param).then(res=>{
                if(res){
                    ElMessage.success('删除成功!');
                    onOrgList();
                }
            })
        }).catch(e=>{
            console.log(e)
        })
    }

    const onRole = (val) =>{
        state.orgId = null;
        state.roleId = null;
        roleList({status: 1}).then(res=>{
            if(res){
                state.roleData = res.result;
            }
        });
        orgRoleInfo({orgId: val.column.row.id}).then(res=>{
            if(res.result){
                state.roleId = res.result.roleId;
            }
        });
        state.orgId = val.column.row.id;
        state.roleVisible = true;
    }

    const onAddOrgRole = () =>{
        orgRoleAdd({orgId: state.orgId, roleId: state.roleId}).then(res=>{
            if(res){
                ElMessage.success('分配成功！');
            }
        })
        state.roleVisible = false;
    }
</script>
<style lang="scss" scoped>
</style>

