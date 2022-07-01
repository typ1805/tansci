<template>
    <div class="container-404" :style="defaultHeight">
        <el-image :src="image" style="width: 30%; height:50%"></el-image>
        <div class="text">找不到您要查找的页面，请与<a href="#">我们联系</a>以报告此问题。</div>
        <div class="text team">—— Tansci</div>
        <div>
            <el-button type="danger" icon="Back" round @click="goBack">返回</el-button>
        </div>
    </div>
</template>
<script setup>
    import {onBeforeMount, onMounted, reactive, toRefs} from "vue"
    import {useRouter} from "vue-router"

    const router = useRouter()
    const image = new URL('../../assets/image/404.png', import.meta.url).href
    const state = reactive({
        defaultHeight: {
            height: ''
        }
    })
    const {defaultHeight} = toRefs(state)

    onBeforeMount(() => {
        state.defaultHeight.height = document.body.clientHeight + "px"
    })

    onMounted(() => {
        window.onresize = () => {
            return (() => {
                state.defaultHeight.height = window.innerHeight + "px";
            })()
        }
    })

    const goBack = () => {
        router.go(-1)
    }

</script>

<style lang="scss" scoped="scoped">
    .container-404 {
        height: 100%;
        margin: 0 auto;
        padding-top: 4%;
        text-align: center;
        font-size: 14px;
        letter-spacing: 0.3px;
        .text {
            margin-bottom: 0.5em;
        }
        .team {
            margin: 1.2em 0;
            font-weight: 700;
        }
        a {
            text-decoration: none;
        }
    }
</style>