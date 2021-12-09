<template>
    <div class="table-container">
        <div class="search-wrap">
            <slot></slot>
        </div>
        <div class="table-wrap">
            <el-table :data="data" border stripe size="mini" :height="tableHeight" row-key="id" :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
                v-loading="loading" :header-cell-style="{background:'var(--bg3)'}" style="width: 100%;"
                @selection-change="onSelectionChange" :cell-style="cellStyle">
                <template v-for="item in column" :key="item">
                    <el-table-column v-if="!item.prop && !item.label" :fixed="item.fixed" type="selection" width="45"></el-table-column>
                    <el-table-column v-else show-overflow-tooltip 
                        :prop="item.alias==null?item.prop:item.alias" 
                        :label="item.label" 
                        :align="item.align != null ? item.align : 'center'" 
                        :width="item.width"
                        :fixed="item.fixed">
                    </el-table-column>
                </template>
                <el-table-column v-if="operation" fixed="right" label="操作" align="center" width="220">
                    <template #default="scope">
                        <el-button v-if="operation.add" type="text" @click="onAdd(scope.row)" style="color: var(--add)">添加</el-button>
                        <el-button v-if="operation.detail" type="text" @click="onDetail(scope.row)" style="color: var(--query)">查看</el-button>
                        <el-button v-if="operation.upper && (scope.row['status'] == 0 || scope.row['status'] == 2)" type="text" @click="onUpper(scope.row)" style="color: var(--upper)">上架</el-button>
                        <el-button v-if="operation.down && scope.row['status'] == 1" type="text" @click="onDown(scope.row)" style="color: var(--down)">下架</el-button>
                        <el-button v-if="operation.edit" type="text" @click="onEdit(scope.row)" style="color: var(--edit)">编辑</el-button>
                        <el-button v-if="operation.del" type="text" @click="onDelete(scope.row)" style="color: var(--delete)">删除</el-button>
                        <el-button v-if="operation.role" type="text" @click="onRole(scope.row)" style="color: var(--role)">权限</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="pagination-wrap" v-if="page">
            <el-pagination @size-change="onSizeChange" @current-change="onCurrentChange"
            layout="total, sizes, prev, pager, next, jumper"
            :current-page="page.current"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="page.size"
            :total="page.total"/>
        </div>
    </div>
</template>

<script>
    import {reactive, toRefs} from 'vue'
    export default {
        props: {
            loading: Boolean,
            page: Object,
            column: Array,
            operation: Object,
            data: Array,
        },
        setup(props,context) {
            const parent = {...context}
            const data = reactive({
                tableHeight: window.innerHeight-180,
                onAdd: (row) =>{
                    parent.emit('onAdd',row)
                },
                onDetail: (row) =>{
                    parent.emit('onDetail',row)
                },
                onUpper: (row)=>{
                    parent.emit('onUpper',row)
                },
                onDown: (row)=>{
                    parent.emit('onDown',row)
                },
                onEdit: (row) =>{
                    parent.emit('onEdit',row)
                },
                onDelete: (row) =>{
                    parent.emit('onDelete',row)
                },
                onSizeChange: (val) =>{
                    parent.emit('onSizeChange',val)
                },
                onCurrentChange: (val) =>{
                    parent.emit('onCurrentChange',val)
                },
                onSelectionChange: (val) =>{
                    parent.emit('onSelectionChange',val)
                },
                onRole: (row) =>{
                    parent.emit('onRole',row)
                },
                cellStyle: function(e){
                    let obj = {};
                    parent.emit('setCellColor',e,(color = {}) =>{
                        obj = color;
                    });
                    obj.padding = 0;
                    return obj;
                }
            });
            
            return {
                ...toRefs(data)
            }
        }
    }
</script>
<style lang="less">
</style>