<template>
	<el-space>
        <el-tag v-for="tag in menuTags" :key="tag" :effect="tag.effect" closable :disable-transitions="false" :size="size"
            @click="onTag(tag.path)" @close="onTagClose(tag)">
            {{tag.name}}
        </el-tag>
    </el-space>
</template>
<script setup>
	import {onMounted, defineProps, reactive, toRefs} from 'vue'
    import {useRouter} from 'vue-router'
    import {useStore} from "vuex"

    const router = useRouter()
    const store = useStore()
	const prop = defineProps({
        size: {
            type: String,
            default: 'default'
        }
	})

    const state = reactive({
        menuTags: store.getters.getMenus,
        size: prop.size,
    })
    const {
        menuTags,size,
    } = toRefs(state)

    onMounted(()=>{
        state.menuTags = []
        if(router.currentRoute.value){
            state.menuTags.push({
                name: router.currentRoute.value.meta.title,
                effect: 'dark',
                path: router.currentRoute.value.path
            })
        } else {
            state.menuTags.push({name:'首页',effect:'dark',path:'/home'})
        }
    })

    const onSelected = (index) =>{
        if(state.menuTags.findIndex((val) => val.path == index) === -1){
            state.menuTags.forEach(item=>{
                item.effect = 'plain'
            })
            let i = 0
            router.beforeEach((to) => {
                if(i === 0){
                    state.menuTags.push({
                        name: to.meta.title,
                        effect:'dark',
                        path: index
                    }) 
                }
                i++;
            }) 
        } else{
            state.menuTags.forEach(item=>{
                if(item.path == index){
                    item.effect = 'dark'
                } else {
                    item.effect = 'plain'
                }
            })
        }
    }

    const onTag = (path) => {
        state.menuTags.forEach(item=>{
            if(item.path == path){
                item.effect = 'dark'
            } else {
                item.effect = 'plain'
            }
        })
        router.push({path: path});
    }

    const onTagClose = (tag) => {
        if(state.menuTags.length == 1){
            state.menuTags.splice(state.menuTags.indexOf(tag), 1);
            state.menuTags.push({
                name:'首页',
                effect:'dark',
                path:'/home'
            })
            router.push({path: '/home'});
        } else {
            let index = state.menuTags.indexOf(tag);
            if(state.menuTags[index].effect == 'dark'){
                state.menuTags.splice(index, 1);
                state.menuTags[index-1].effect = 'dark';
                router.push({path: state.menuTags[index-1].path});
            } else {
                state.menuTags.splice(index, 1);
            }
        }
    }

    // 父组件调用子组件的方法必须暴露该方法
    defineExpose({
        onSelected
    })
</script>
<style lang="less" scoped>
    /deep/ .el-space__item{
        cursor: pointer;
    }
    /deep/ .el-tag--plain, .el-tag--dark{
        border: 1px solid var(--theme);
    }
</style>