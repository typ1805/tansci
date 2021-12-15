import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/json'

NProgress.inc(0.2)
NProgress.configure({ easing: 'ease', speed: 600, showSpinner: false })

axios.interceptors.request.use(function (config) {
    config.headers.Authorization = 'Bearer '+ sessionStorage.getItem('token') || ''
    // 启动进度条
    NProgress.start()
    return config
})

axios.interceptors.response.use(res => {
    if (!res) {
        ElMessage.error("服务器异常，请稍后再试！")
        return Promise.reject(res)
    }

    // 下载文件特殊处理
    if(res.config.responseType == 'blob'){
        // 关闭进度条
        NProgress.done();
        return res;
    } else {
        if (res.data.code != 200) {
            NProgress.done();
            ElMessage.error(res.data.message)
            if (res.data.code == 403 || res.data.code == 401) router.push({path: '/login'})
            return Promise.reject(res.data)
        }
    }
    
    // 关闭进度条
    NProgress.done();
    return res.data;
})

export default axios