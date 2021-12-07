<template>
    <div class="container-404" :style="defaultHeight">
        <el-image src="./assets/image/404.png" style="width: 30%; height:50%"></el-image>
        <div class="text">找不到您要查找的页面，请与<a href="#">我们联系</a>以报告此问题。</div>
        <div class="text team">—— Kuiper</div>
        <div>
            <el-button icon="el-icon-back" round @click="goBack">返回</el-button>
            <el-button type="danger" icon="el-icon-s-home" round @click="goHome">首页</el-button>
        </div>
    </div>
</template>

<script>
    import {onBeforeMount, onMounted, reactive, toRefs} from "vue"
    import {useRouter} from "vue-router"
    export default {
        setup() {
            const router = useRouter()
            const state = reactive({
                defaultHeight: {
                    height: ''
                }
            })

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

            const goHome = () => {
                router.push({path: 'home'})
            }

            return {
                ...toRefs(state),
                goBack,
                goHome,
            }
        }
    }
</script>

<style lang="less">
    .container-404 {
        height: 100%;
        margin: 0 auto;
        padding-top: 4%;
        text-align: center;
        color: var(--t8);
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
            color: var(--color);
        }
    }
</style>