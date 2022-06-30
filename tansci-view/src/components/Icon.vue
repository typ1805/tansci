<template>
    <el-dialog v-model="prop.iconVisible" title="图标" :close-on-press-escape="false" :close-on-click-modal="false" :show-close="false" width="70%">
        <el-icon v-for="icon in iconList" :key="icon" @click="$emit('onIcon', icon)" :size="30" color="#242e42" 
            style="border: 1px solid #e4e7ed;padding: 1rem;cursor: pointer;">
            <component :is="icon"></component>
        </el-icon>
    </el-dialog>
</template>
<script setup>
    import {defineProps, reactive, toRefs} from 'vue'
    import * as ElIcons from '@element-plus/icons-vue'

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
<style lang="scss"> 
    .el-dialog__header{
        margin: 0 !important;
    }
</style>