<template>
    <div v-if="verifyRefresh" class="sliding-verify">
        <div class="slider" :class="rangeStatus?'success':''" >
            <div class="i" @mousedown.self="onMove">
                {{rangeStatus?'✔':'››'}}
            </div>
            <!-- <el-icon @mousedown.self="onMove">
                <component :is="rangeStatus?successIcon:startIcon"></component>
            </el-icon> -->
            {{rangeStatus?successText:startText}}
        </div>
    </div>
</template>
<script setup>
    import {defineProps, reactive, toRefs, nextTick} from 'vue'

    const prop = defineProps({
        // 成功函数
		successFun: {
			type: Function
		},
		// 成功图标
		successIcon: {
			type: String,
			default: 'CircleCheckFilled'
		},
		// 成功文字
		successText: {
			type: String,
			default: '验证成功'
		},
		// 开始的图标
		startIcon: {
			type: String,
			default: 'DArrowRight'
		},
		// 开始的文字
		startText: {
			type: String,
			default: '请拖动滑块验证'
		},
		// 失败函数
		errorFun: {
			type: Function
		},
		// 用值来进行监听
		status: {
			type: Boolean,
            default: false
		}
    })

    const state = reactive({
        verifyRefresh: true,
        disX: 0,
		rangeStatus: prop.status
    })

    const {verifyRefresh,disX,rangeStatus} = toRefs(state)

    // 滑块移动
    function onMove (e){
        let ele = e.target;
        let startX = e.clientX;
        let eleWidth = ele.offsetWidth;
        let parentWidth =  ele.parentElement.offsetWidth;
        let MaxX = parentWidth - eleWidth -2;
        // 不运行
        if(state.rangeStatus){
            return false;
        }
        document.onmousemove = (e) => {
            let endX = e.clientX;
            state.disX = endX - startX;
            if(state.disX <= 0){
                state.disX = 0;
            }
            // 减去滑块的宽度,体验效果更好
            if(state.disX >= MaxX - eleWidth){
                state.disX = MaxX;
            }
            ele.style.transition = '.1s all';
            ele.style.transform = 'translateX('+state.disX+'px)';
            e.preventDefault();
        }
        document.onmouseup = ()=> {
            if(state.disX !== MaxX){
                ele.style.transition = '.5s all';
                ele.style.transform = 'translateX(0)';
                // 执行失败的函数
                prop.errorFun(false);
            }else{
                state.rangeStatus = true;
                // 执行成功的函数
                prop.successFun(true);
            }
            document.onmousemove = null;
            document.onmouseup = null;
        }
    }

    // 刷新
    const onRefresh = () =>{
        state.verifyRefresh = false;
        nextTick(()=>{
            state.rangeStatus = false;
            state.verifyRefresh = true;
        })
    }

    defineExpose({
        onRefresh
    })
</script>
<style scoped lang="scss">
    .sliding-verify{
        width: 100%;
        .slider{
            background-color: #EBEEF5;
            position: relative;
            transition: 1s all;
            user-select: none;
            color: #909399;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 32px; 
            border: 1px solid #dcdfe6;
        }
        .slider .i{
            position: absolute;
            left: 0;
            width: 34px; 
            height: 100%;
            color: #919191;
            background-color: #fff;
            cursor: pointer;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 20px;
        }
        .slider.success{
            background-color: #7AC23C;
            color: #fff;
            right: 0;
        }
        .slider.success .i{
            color: #7AC23C;
        }
    }
</style>