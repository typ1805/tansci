## 项目

TANSCI 系统

## 简介

TANSCI 基于 SpringBoot + Vue3.2 + Element Plus 的后台管理系统

基础模块：菜单管理、角色管理、组织管理、用户管理、字典管理、日志管理（操作日志、异常日志）

Gitee: [https://gitee.com/typ1805/tansci](https://gitee.com/typ1805/tansci)

GitHub: [https://github.com/typ1805/tansci](https://github.com/typ1805/tansci)

## 架构

### 技术栈

#### 1、环境要求

| 名称 | 版本号 | 描述 |
| ---- | ---- | ---- |
| JDK | 1.8+ | 强制要求 |
| mysql | 5.7+ | 数据库 |
| node | 14.16+ | 前端环境要求 |
| npm | 6.14+ | 前端框架包管理 |
| Nginx | 1.16+ | 请求转发、反向代理 |
| Maven | 3.8+ | 项目构建，管理 |
| Git | 2.14+ | 项目版本管理 |

#### 2、后端技术

| 名称 | 版本号 | 描述 |
| ---- | ---- | ---- |
| SpringBoot | 2.6.1 | 整体架构基础 |
| Mybatis Plus | 3.4.3.1 | 数据层 |
| Druid | 1.2.6 | 连接池 |
| Spring Security | -- | 权限认证 |
| Fastjson | 1.2.75 | -- |
| JJWT | 0.9.0 | 安全认证 |
| Lombok | -- | -- |

#### 3、前端技术

| 名称 | 版本号 | 描述 |
| ---- | ---- | ---- |
| vue | 3.2.16 | 整体架构基础 |
| element-plus | 1.2.0-beta.6 | UI |
| vue-router | 4.0.12 | 路由 |
| vuex | 4.0.2 | 状态管理模式 |
| vue3-echarts | 1.0.3 | echarts图表 |
| axios | 0.24.0 | 基于promise的HTTP库 |
| nprogress | 0.2.0 | -- |
| less | 4.1.2 | -- |

## 许可证

申请中。。。

## 开发者联系

1、QQ：742354529

2、QQ群：747200630

3、微信群：加扣扣或关注公众号进

4、个人主站：[https://typ1805.gitee.io/](https://typ1805.gitee.io/)

5、个人公众号：

![欢迎关注](./sql/gzh.jpg)

## 示例组件

### 1、el-table 封装

1.1、示例

```vue
    import Table from '../../components/Table.vue'
```

1.2、可参考 ``tansci-view/src/views/system/User.vue``

```vue
    <Table :data="tableData" :column="tableTitle" :operation="true" :tableHeight="tableHeight" :page="page" :loading="loading"
           @onSizeChange="onSizeChange" @onCurrentChange="onCurrentChange" @setCellColor="setCellColor">
        <template #search>
        </template>
        <template #column="scope">
        </template>
    </Table>
```
1.3、Props及事件说明

| 参数 | 说明 | 默认值 |
| ---- | ---- | ---- |
| loading | 加载动画 | false |
| page | 分页参数 | { current: 1, size: 10, total: 0} |
| column | 字段集合 | Array |
| operation | 操作列 | 自定义插槽 |
| tableHeight | table高度 | 520px |
| headerCellStyle | 表头单元格的 style 的回调方法 | 原 header-cell-style |
| data | 数据集合 | Array |
| tree-props | 树形数据 | {children: 'children', hasChildren: 'hasChildren'} |
| onSizeChange | pageSize 改变时触发 | 原 size-change |
| onCurrentChange | current-change 改变时触发 | 原 current-change |
| onSelectionChange | 当选择项发生变化时会触发该事件 | 原 selection-change |
| setCellColor | 单元格的 style 的回调方法 | 原 cell-style |
| onButtonClick | 当column的type="button"时的click事件 | -- |
| onSwitchChange | 当column的type="switch"时的change事件 | -- |

1.4、column 配置说明

| 参数 | 说明 | 默认值 |
| ---- | ---- | ---- |
| prop | 字段名称 | String |
| label | 展示值 | String |
| alias | 列字典值名称展示 | String |
| type | 展示类型：button、tag、switch、progress | 属性配置和element属性一致 |
| option | 对type进行属性配置 | element属性一致 |
| tooltip | 当内容过长被隐藏时显示 | false |
| width | 对应列的宽度 | string / number |
| align | 对齐方式： left、center、left |
| fixed | 列是否固定在左侧或者右侧：true 、'left'、'right' | -- |

1.5、插槽

| 参数 | 说明 | 默认值 |
| ---- | ---- | ---- |
| search | 筛选条件插槽 | <template #search> |
| column | table操作列插槽，operation为true时生效 | <template #column="scope"> |

### 2、接口日志记录

2.1、使用 ``@Log``

- modul: 操作模块
- type: 操作类型
- desc: 操作说明

2.2、示例
```java
    @Log(modul = "数据字典-列表", type = Constants.SELECT, desc = "列表")
    @GetMapping("/dicList")
    public Wrapper<List<SysDic>> dicList(SysDicDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysDicService.dicList(dto));
    }
```
