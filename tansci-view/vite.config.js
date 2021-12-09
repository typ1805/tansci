import path from "path"
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

const url = 'http://127.0.0.1:8005';

export default defineConfig({
    resolve: {
        alias: {
            "~/": `${path.resolve(__dirname, "src")}/`,
        },
    },

    plugins: [vue()],

    // 全局样式
    css: {
        preprocessorOptions: {
            less: {
                javascriptEnabled: true,
                additionalData: `@import "${path.resolve(__dirname, 'src/assets/css/common.less')}";`
            }
        }
    },

    // 反向代理
    server: {
        headers: {
            'Access-Control-Allow-Origin': '*',
        },
        disableHostCheck: true,
        port: 3000,
        proxy: {
            '/tansci': {
                target: url,
                changeOrigin: true,
                pathRewrite: {
                    '^/tansci': '/tansci'
                }
            }
        }
    }

})
