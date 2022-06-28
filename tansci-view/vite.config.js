import path from "path"
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

const url = 'http://127.0.0.1:8005';

export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
            '@': path.resolve(__dirname, 'src'),
        },
    },
    // 全局样式
    css: {
        preprocessorOptions: {
            scss: {
                additionalData: `@use "@/styles/element/index.scss" as *;`,
            },
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
