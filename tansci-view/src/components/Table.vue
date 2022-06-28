<template>
    <div class="table-container">
        <div class="search-wrap">
            <slot name="search"></slot>
        </div>
        <div class="table-wrap">
            <el-table :data="data" border stripe size="mini" :height="tableHeight" :max-height="maxHeight" row-key="id" :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
                v-loading="loading" :header-cell-style="headerCellStyle" :cell-style="cellStyle" 
                @selection-change="onSelectionChange" style="width: 100%;">
                <template v-for="item in column" :key="item">
                    <el-table-column v-if="!item.prop && !item.label" :fixed="item.fixed" type="selection" width="45"></el-table-column>
                    <!-- el-tag -->
                    <el-table-column v-else-if="item.type == 'tag'" show-overflow-tooltip
                        :label="item.label" :align="item.align != null ? item.align : 'center'" :width="item.width">
                        <template #default="scope">
                            <el-tag :size="item.option.size" 
                                :effect="item.option.effect" 
                                :type="item.option.type">
                                {{scope.row[item.prop]}}    
                            </el-tag>
                        </template>
                    </el-table-column>
                    <!-- el-button -->
                    <el-table-column v-else-if="item.type == 'button'" show-overflow-tooltip
                        :label="item.label" :align="item.align != null ? item.align : 'center'" :width="item.width">
                        <template #default="scope">
                            <el-button @click="$emit('onButtonClick',scope.row)" type="text" :size="item.option.size">
                                {{scope.row[item.prop]}}    
                            </el-button>
                        </template>
                    </el-table-column>
                    <!-- el-switch -->
                    <el-table-column v-else-if="item.type == 'switch'" show-overflow-tooltip 
                        :label="item.label" :align="item.align != null ? item.align : 'center'" :width="item.width">
                        <template #default="scope">
                            <el-switch @change="$emit('onSwitchChange',scope.row)" inline-prompt 
                                :active-value="item.option.activeValue" :active-color="item.option.activeColor" :active-text="item.option.activeText" 
                                :inactive-value="item.option.inactiveValue" :inactive-color="item.option.inactiveColor" :inactive-text="item.option.inactiveText"
                                v-model="scope.row[item.prop]"
                            ></el-switch>
                        </template>
                    </el-table-column>
                    <!-- el-progress  -->
                    <el-table-column v-else-if="item.type == 'progress'" show-overflow-tooltip 
                        :label="item.label" :align="item.align != null ? item.align : 'center'" :width="item.width">
                        <template #default="scope">
                            <el-progress :percentage="scope.row[item.prop]" 
                            :status="item.option.status"
                            :color="item.option.color"/>
                        </template>
                    </el-table-column>
                    <!-- 其他数据列 -->
                    <el-table-column v-else show-overflow-tooltip 
                        :prop="item.alias==null?item.prop:item.alias" 
                        :label="item.label" 
                        :align="item.align != null ? item.align : 'center'" 
                        :width="item.width"
                        :fixed="item.fixed">
                    </el-table-column>
                </template>
                <!-- 自定义插槽  -->
                <el-table-column v-if="operation" fixed="right" label="操作" align="center" width="220">
                    <template #default="scope">
                        <slot name="column" v-bind:column="scope"></slot>
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
<script setup>
    import {defineProps, reactive, toRefs} from 'vue'
    
    const prop = defineProps({
        loading: {
            type: Boolean,
            default: false
        },
        page: {
            type: Object,
            default:{
                current: 1,
                size: 10,
                total: 0,
            }
        }, 
        column: {
            type: Array,
            default: []
        },
        operation: { // 操作列，自定义插槽
            type: Boolean,
            default: false
        }, 
        tableHeight: {
            type: Number,
            default: null
        },
        headerCellStyle: {
            type: Object,
            default:{color:'#606266',fontWeight: 700,background:'var(--bg1)'}
        },
        data: {
            type: Array,
            default: []
        },
    })

    const emit = defineEmits([
        'onSizeChange','onCurrentChange','onSelectionChange','setCellColor',
        'onButtonClick','onSwitchChange',
    ])

    const state = reactive({
        maxHeight: window.innerHeight - 280,
        tableHeight: prop.tableHeight,
        headerCellStyle: prop.headerCellStyle,
        cellStyle: function(e){
            let obj = {};
            emit('setCellColor', e, (color = {}) =>{
                obj = color;
            });
            obj.padding = '2px';
            return obj;
        }
    })
    const {maxHeight,tableHeight,headerCellStyle,cellStyle} = toRefs(state)

    const onSizeChange = (e) =>{
        emit('onSizeChange', e)
    }
    const onCurrentChange = (e) =>{
        emit('onCurrentChange', e)
    }
</script>
<style lang="scss" scoped>
</style>