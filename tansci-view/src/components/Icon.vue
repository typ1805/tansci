<template>
    <el-dialog class="icon-dialog" v-model="prop.iconVisible" title="菜单图标" :close-on-press-escape="false" :close-on-click-modal="false" :show-close="false" width="60%">
        <el-button v-for="icon in iconList" :key="icon" :icon="icon" @click="$emit('onIcon', icon)" type="text"></el-button>
    </el-dialog>
</template>
<script setup>
    import {defineProps, reactive, toRefs} from 'vue'
    import * as ElIcons from '@element-plus/icons'

    const prop = defineProps({
        iconVisible: {
            type: Boolean,
            default: false
        }
    })

    const emit = defineEmits(['onIcon'])

    const state = reactive({
        iconList: ElIcons,
    })
    const {iconList} = toRefs(state)
</script>
<style lang="less" scoped>
    .icon-dialog{
        .el-dialog__header{
            padding: 0 !important;
        }
    } 
    /deep/ .el-icon{
        font-size: 24px;
        padding: 0.4rem;
        color: #909399;
    }
</style>
