<template>
    <div class="container-404" :style="defaultHeight">
        <el-image src="./src/assets/image/404.png" style="width: 30%; height:50%"></el-image>
        <div class="text">找不到您要查找的页面，请与<a href="#">我们联系</a>以报告此问题。</div>
        <div class="text team">—— Tansci</div>
        <div>
            <el-button :icon="search" round @click="goBack">
                <el-icon style="vertical-align: middle;"><Back /></el-icon>
                <span style="vertical-align: middle;">返回</span>
            </el-button>
            <el-button type="danger" :icon="Back" round @click="goHome">
                <el-icon style="vertical-align: middle;"><HomeFilled /></el-icon>
                <span style="vertical-align: middle;">首页</span>
            </el-button>
        </div>
    </div>
</template>

<script>
    import {onBeforeMount, onMounted, reactive, toRefs} from "vue"
    import {Back,HomeFilled} from "@element-plus/icons"
    import {useRouter} from "vue-router"
    export default {
        components: {
			Back,
            HomeFilled
		},
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

<style lang="less" scoped="scoped">
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