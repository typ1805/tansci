<template>
    <div class="menu">
		<el-card class="menu-tree" shadow="never">
            <el-tree :data="treeData" :props="{children: 'children', label: 'chineseName'}" accordion highlight-current @node-click="onNodeClick"></el-tree>
        </el-card>
		<el-card class="menu-detail" shadow="never">
            <div class="detail-operate">
                <el-radio-group @change="onOperateChange" v-model="operate">
                    <el-radio-button :label="1">添加</el-radio-button>
                    <el-radio-button :label="2">编辑</el-radio-button>
                    <el-radio-button :label="3">删除</el-radio-button>
                </el-radio-group>
            </div>
            <el-form :model="addMenuForm" :rules="rules" ref="addMenuRuleForm" :disabled="operate==0 || operate==3?true:false" label-position="right" label-width="150px">
                <el-form-item label="菜单名称" prop="name" :rules="[
                    {required: true, message: '名称不能为空', trigger: 'blur'},
                    {pattern: /^[A-Za-z0-9]+$/, message: '必须是字母', trigger: 'blur'}]">
                    <el-input v-model="addMenuForm.name" placeholder="请输入名称" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="菜单类型" prop="type" :rules="[{required: true, message: '请选择类型', trigger: 'change'}]">
                    <el-select v-model="addMenuForm.type" placeholder="请选菜单类型" style="width:50%">
                        <el-option label="按钮" :value="0"></el-option>
                        <el-option label="菜单" :value="1"></el-option>
                        <el-option label="嵌套链接" :value="2"></el-option>
                        <el-option label="跳转链接" :value="3"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="菜单路由" prop="url" :rules="[{required: true, message: '路由不能为空', trigger: 'blur'}]">
                    <el-input v-model="addMenuForm.url" placeholder="请输入路由" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="中文名称" prop="chineseName" :rules="[
                    {required: true, message: '中文名称不能为空', trigger: 'blur'},
                    {pattern: /^[\u4e00-\u9fa5]{0,}$/, message: '必须是汉字', trigger: 'blur'}]">
                    <el-input v-model="addMenuForm.chineseName" placeholder="请输入中文名称" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="英文名称" prop="englishName">
                    <el-input v-model="addMenuForm.englishName" placeholder="请输入英文名称" autocomplete="off" style="width:50%"></el-input>
                </el-form-item>
                <el-form-item label="菜单图标" prop="icon" :rules="[{required: true, message: '菜单图标不能为空', trigger: 'blur'}]">
                    <el-input v-model="addMenuForm.icon" @click="onFormIcon" readonly suffix-icon="Platform" style="width:50%"></el-input>
                    <Icon :iconVisible="iconVisible" @onIcon="onIcon"/>
                </el-form-item>
                <el-form-item label="菜单顺序" prop="sort" :rules="[{required: true, message: '菜单顺序不能为空', trigger: 'blur'}]">
                    <el-input-number v-model="addMenuForm.sort" :min="0" :max="999" style="width:50%"></el-input-number>
                </el-form-item>
                <el-form-item v-show="operate != 0 && operate != 3">
                    <el-button type="primary" @click="onAddMenu">提交</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>
<script setup>
	import {onMounted, reactive, ref, unref, toRefs} from 'vue'
    import {ElMessage, ElMessageBox} from 'element-plus'
    import {menuList,delMenu,saveMenu,updateMenu} from '../../api/systemApi'
    import Icon from '../../components/Icon.vue'

    const addMenuRuleForm = ref(null)
    const state = reactive({
        treeData:[],
        operate: 0,
        menuId: null,
        iconVisible: false,
        addMenuForm:{
            id: '',
            parentId:'',
            name:'',
            type:'',
            url:'',
            chineseName:'',
            englishName:'',
            icon:'',
            sort: 0,
        },
    })
    const {treeData,operate,menuId,iconVisible,addMenuForm} = toRefs(state)

    onMounted(()=>{
        onMenuList()
    })

    const onMenuList = () =>{
        menuList({}).then(res=>{
            if(res){
                state.treeData = res.result;
            }
        })
    }

    const onNodeClick = (data) =>{
        state.operate = 0;
        state.menuId = data.id;
        state.addMenuForm = {
            id: data.id,
            parentId: data.parentId,
            name: data.name,
            type: data.type,
            url: data.url,
            chineseName: data.chineseName,
            englishName: data.englishName,
            icon: data.icon,
            sort: data.sort,
        }
    }

    const onOperateChange = (val) =>{
        if(val == 1){
            state.operate = 1;
            state.addMenuForm = {
                id: '',
                parentId: state.menuId,
                name:'',
                type:'',
                url:'',
                chineseName:'',
                englishName:'',
                icon:'',
                sort: 0,
            }
        } else if(val == 2) {
            state.operate = 2;
        } else {
            if(!state.menuId){
                ElMessage.warning("请选择要删除的菜单！")
                return false;
            }
            state.operate = 0;
            ElMessageBox.confirm('此操作将永久删除该菜单, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                delMenu({id: state.menuId}).then(res=>{
                    if(res){
                        ElMessage.success("删除成功!");
                        onMenuList();
                    }
                })
            }).catch(e=>{
                console.log(e)
            })
        }
    }

    const onFormIcon = () =>{
        state.iconVisible = true;
    }
    const onIcon = (val) =>{
        state.addMenuForm.icon = val.name;
        state.iconVisible = false;
    }

    const onAddMenu = async () =>{
        if(state.operate == 1){
            const form = unref(addMenuRuleForm);
            if(!form) return;
            await form.validate();
            saveMenu(state.addMenuForm).then(res=>{
                if(res){
                    state.addMenuVisible = false;
                    ElMessage.success({
                        message: '添加成功！',
                        type: 'success'
                    });
                    state.operate = 0;
                    state.addMenuForm = {
                        id: '',
                        parentId:'',
                        name:'',
                        type:'',
                        url:'',
                        chineseName:'',
                        englishName:'',
                        icon:'',
                        sort: 0,
                    };
                    onMenuList();
                }
            })
        } else if (state.operate == 2) {
            updateMenu(state.addMenuForm).then(res=>{
                if(res){
                    state.addMenuVisible = false;
                    ElMessage.success({
                        message: '更新成功！',
                        type: 'success'
                    });
                    state.operate = 0;
                    state.addMenuForm = {
                        id: '',
                        parentId:'',
                        name:'',
                        type:'',
                        url:'',
                        chineseName:'',
                        englishName:'',
                        icon:'',
                        sort: 0,
                    };
                    onMenuList();
                }
            })
        }
    }

</script>
<style lang="scss" scoped>
	.menu {
        display: flex;
        padding-bottom: 4rem;
        .menu-tree{
            min-width: 300px;
            .el-tree-node:focus>.el-tree-node__content{
                background-color: #fff !important;
                color: var(--theme) !important;
            }
            .el-tree--highlight-current .el-tree-node.is-current > .el-tree-node__content { 
                background-color: #fff !important;
                color: var(--theme) !important;
            }
        }
        .menu-detail{
            margin-left: 0.2rem;
            width: 100%;
            .detail-operate{
                padding: 1rem 4rem;
            }
        }
	}
</style>