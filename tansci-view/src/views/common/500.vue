<template>
    <div class="container-500" :style="defaultHeight">
        <h1>我们很快就回来！</h1>
        <div class="text">很抱歉给您带来不便</div>
        <div class="text">但我们现在正在进行一些维护。</div>
        <div class="text">如果您需要，您可以随时与<a href="#">我们联系</a>，否则我们将很快恢复在线！</div>
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

<style lang="scss">
    .container-500 {
        height: 100%;
        margin: 0 auto;
        padding-top: 15%;
        text-align: center;
        color: #494b52;
        // background: #17191c;
        font-weight: 400;
        font-family: "Raleway", sans-serif;
        font-size: 15px;
        letter-spacing: 0.3px;

        .text {
            margin-bottom: 0.5em;
        }

        .team {
            margin: 1.2em 0;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }
    }
</style>