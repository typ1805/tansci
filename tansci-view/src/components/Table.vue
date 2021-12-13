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
                    <el-table-column v-else-if="item.type == 'tag'" show-overflow-tooltip
                        :label="item.label" :align="item.align != null ? item.align : 'center'" :width="item.width">
                        <template #default="scope">
                            <el-tag :size="item.option.size" 
                                :effect="item.option.effect" 
                                :type="item.option.type">
                                {{valFormat(scope.row,item.prop)}}    
                            </el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column v-else-if="item.type == 'switch'" show-overflow-tooltip 
                        :label="item.label" :align="item.align != null ? item.align : 'center'" :width="item.width">
                        <template #default="scope">
                            <el-switch inline-prompt 
                                :active-color="item.option.activeColor" :active-text="item.option.activeText" 
                                :inactive-color="item.option.inactiveColor" :inactive-text="item.option.inactiveText"
                                :v-model="`scope.row.${item.prop}`"
                            ></el-switch>
                        </template>
                    </el-table-column>
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
                        <el-button v-if="operation.add" type="text" @click="$emit('onAdd', scope.row)" style="color: var(--add)">添加</el-button>
                        <el-button v-if="operation.detail" type="text" @click="$emit('onDetail', scope.row)" style="color: var(--query)">查看</el-button>
                        <el-button v-if="operation.upper && (scope.row['status'] == 0 || scope.row['status'] == 2)" type="text" @click="$emit('onUpper', scope.row)" style="color: var(--upper)">上架</el-button>
                        <el-button v-if="operation.down && scope.row['status'] == 1" type="text" @click="$emit('onDown', scope.row)" style="color: var(--down)">下架</el-button>
                        <el-button v-if="operation.edit" type="text" @click="$emit('onEdit', scope.row)" style="color: var(--edit)">编辑</el-button>
                        <el-button v-if="operation.del" type="text" @click="$emit('onDelete', scope.row)" style="color: var(--delete)">删除</el-button>
                        <el-button v-if="operation.role" type="text" @click="$emit('onRole', scope.row)" style="color: var(--role)">权限</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="pagination-wrap" v-if="page">
            <el-pagination @size-change="$emit('onSizeChange')" @current-change="$emit('onCurrentChange')"
            layout="total, sizes, prev, pager, next, jumper"
            :current-page="page.current"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="page.size"
            :total="page.total"/>
        </div>
    </div>
</template>
<script setup>
    import {defineProps, reactive, toRefs} from 'vue'
    
    const prop = defineProps({
        loading: Boolean,
        page: Object,
        column: Array,
        operation: Object,
        tableHeight: Number,
        data: Array,
    })

    const emit = defineEmits([
        'onAdd','onDetail','onUpper','onDown','onEdit','onDelete','onRole',
        'onSizeChange','onCurrentChange','onSelectionChange','setCellColor'
    ])

    const state = reactive({
        tableHeight: prop.tableHeight?prop.tableHeight:520,
        cellStyle: function(e){
            let obj = {};
            emit('setCellColor', e, (color = {}) =>{
                obj = color;
            });
            obj.padding = 0;
            return obj;
        }
    })
    const {tableHeight,cellStyle} = toRefs(state)

    function valFormat(row,val){
        return eval(`row.${val}`);
    }
</script>
<style lang="less">
</style>