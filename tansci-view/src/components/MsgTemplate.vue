<template>
    <div class="container-template">
        <div class="template-content">
            <el-icon><info-filled /></el-icon>
            <span>{{contentstr}}</span>
        </div>
        <div class="template-nodes" v-for="(item,i) in nodes" :key="i">
            <el-row :gutter="20">
                <el-col :span="18">
                    <el-input @change="onValChange" v-model="item.val" size="small" style="width: 100%"></el-input>
                </el-col>
                <el-col :span="4">
                    <el-checkbox @change="onValChange" v-model="item.param">添加变量</el-checkbox>
                </el-col>
                <el-col :span="1">
                    <el-button @click="onAddNode()" v-if="item.isShow" type="text" icon="circle-plus"></el-button>
                    <el-button @click="onDelNode(i)" v-if="!item.isShow" type="text" icon="remove"></el-button>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script setup>
    import {defineProps, defineExpose, reactive, toRefs} from 'vue'

    const prop = defineProps({
        content: {
            type: String,
            default: 'default'
        }
	})
    const emit = defineEmits(['contentEvent'])
    const state = reactive({
        nodes: [
            {
                id: 1,
                val: '',
                param: false,
                isShow: true,
            }
        ],
        contentstr: '',
    })
    const { nodes,contentstr} = toRefs(state)

    const onLoad = () =>{
        if(prop.content){
            let contents = prop.content.split(/\${[param\d]+\}/g);
            let _nodes = [];
            for (let i = 0; i < contents.length; i++) {
                _nodes.push({
                    id: i+1,
                    val: contents[i],
                    param: i==contents.length-1?false:true,
                    isShow: i==0?true:false,
                });
            }
            state.nodes = _nodes;
            state.contentstr = prop.content;
        } else {
            state.nodes = [{id: 1,val: '',param: false,isShow: true}];
            state.contentstr = '';
        }
    }

    const onValChange = () =>{
        let contentstr = '';
        state.nodes.forEach(e=>{
            contentstr += e.val;
            if(e.param){
                contentstr += '${param'+e.id+'}';
            }
        });
        state.contentstr = contentstr;
        emit('contentEvent', state.contentstr)
    }

    const onAddNode = () => {
        state.nodes.push({
            id: state.nodes.length + 1,
            val: '',
            param: false,
            isShow: false,
        });
    }

    const onDelNode = (i) =>{
        state.nodes.splice(i,1);
        onValChange();
    }

    defineExpose({
        onLoad
    })
</script>
<style lang="scss" scoped>
    .container-template{
        overflow: hidden;
        border: 1px solid #DCDFE6;
        border-radius: 4px;
        .template-content{
            padding-left: 6px;
            line-height: 26px;
            span{
                padding-left: 4px;
            }
            span:hover{
                color: var(--theme);
            }
        }
        .template-nodes{
            padding-left: 6px;
        }
    }
</style>