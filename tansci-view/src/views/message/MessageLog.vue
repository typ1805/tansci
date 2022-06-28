<template>
    <div class="message-log">
        <el-card>
            开发中...
        </el-card>
    </div>
</template>
<script setup>
    import {onMounted, reactive, toRefs} from 'vue'

    const state = reactive({
        searchForm:{
            name: null,
        },
    })

    const {} = toRefs(state)

    onMounted(() => {
    })

</script>
<style lang="scss" scoped>
</style>