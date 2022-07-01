<script setup lang="ts">
    import {onMounted, reactive, toRefs} from "vue"

    const state = reactive({
        
    })

    const {} = toRefs(state)

    onMounted(() => {
        
    })

</script>
<template>
    <div class="main-container">
        欢迎使用 Tansci 
    </div>
</template>
<style lang="scss" scoped>
    .main-container{
        text-align: center;
        font-size: 60px;
        font-weight: 700;
        color: #fefefe;
        text-shadow: 0 0 0.5em #2F9688, 0 0 0.2em #5c5c5c;
        padding-top: 10rem;
    }
</style>