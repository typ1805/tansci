import axios from '../utils/axios'

// ==================登录=============================
/**
 * 登录
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
export function login(params) {
    return axios.post('/tansci/user/login', params);
}

/**
 * 微信扫码登录获取二维码
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
export function wxLogin(params) {
    return axios.post('/tansci/auth/wxLogin', params);
}

/**
 * 微信扫码登录获取token
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
export function wxCallback(params) {
    return axios.post('/tansci/auth/wxCallback', params);
}

/**
 * 登出
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
export function logout(params) {
    return axios.post('/tansci/user/logout', params);
}


// =================数据字典============================
/**
 * 分页列表
 * @param {*} params 
 * @returns 
 */
export function dicList(params) {
    return axios.get('/tansci/dic/dicList', { params: params });
}

/**
 * 列表
 * @param {*} params 
 * @returns 
 */
export function getGroupNameByList(params) {
    return axios.get('/tansci/dic/getGroupNameByList', { params: params });
}

/**
 * 添加字典
 * @param {*} params 
 * @returns 
 */
export function dicSave(params) {
    return axios.post('/tansci/dic/save', params);
}

/**
 * 修改字典
 * @param {*} params 
 * @returns 
 */
export function dicUpdate(params) {
    return axios.post('/tansci/dic/update', params);
}

/**
 * 删除字典
 * @param {*} params 
 * @returns 
 */
export function dicDel(params) {
    return axios.get('/tansci/dic/del', { params: params });
}

// =================用户信息===========================
/**
 * 用户列表分页
 * @param {*} params 
 * @returns 
 */
export function userPage(params) {
    return axios.get('/tansci/user/page', { params: params });
}

/**
 * 用户列表
 * @param {*} params 
 * @returns 
 */
export function userList(params) {
    return axios.get('/tansci/user/list', { params: params });
}

/**
 * 添加用户
 * @param {*} params 
 * @returns 
 */
export function addUser(params) {
    return axios.post('/tansci/user/save', params);
}

/**
 * 修改用户
 * @param {*} params 
 * @returns 
 */
export function updateUser(params) {
    return axios.post('/tansci/user/update', params);
}

/**
 * 删除用户
 * @param {*} params 
 * @returns 
 */
export function dicUser(params) {
    return axios.get('/tansci/user/del', { params: params });
}

/**
 * 修改密码
 * @param {*} params 
 * @returns 
 */
export function modifyPass(params) {
    return axios.post('/tansci/user/modifyPass', params);
}

// =====================菜单管理==================================

/**
 * 列表
 * @param {*} params 
 * @returns 
 */
export function menuList(params) {
    return axios.get('/tansci/menu/list', { params: params });
}

/**
 * 删除菜单
 * @param {*} params 
 * @returns 
 */
export function delMenu(params) {
    return axios.get('/tansci/menu/del', { params: params });
}

/**
 * 添加菜单
 * @param {*} params 
 * @returns 
 */
export function saveMenu(params) {
    return axios.post('/tansci/menu/save', params);
}

/**
 * 更新菜单
 * @param {*} params 
 * @returns 
 */
export function updateMenu(params) {
    return axios.post('/tansci/menu/update', params);
}

// =================角色管理============================

/**
 * 分页列表
 * @param {*} params 
 * @returns 
 */
export function rolePage(params) {
    return axios.get('/tansci/role/page', { params: params });
}

/**
 * 列表
 * @param {*} params 
 * @returns 
 */
export function roleList(params) {
    return axios.get('/tansci/role/list', { params: params });
}

/**
 * 删除角色
 * @param {*} params 
 * @returns 
 */
export function delRole(params) {
    return axios.get('/tansci/role/del', { params: params });
}

/**
 * 添加角色
 * @param {*} params 
 * @returns 
 */
export function saveRole(params) {
    return axios.post('/tansci/role/save', params);
}

/**
 * 更新角色
 * @param {*} params 
 * @returns 
 */
export function updateRole(params) {
    return axios.post('/tansci/role/update', params);
}

/**
 * 菜单角色列表
 * @param {*} params 
 * @returns 
 */
export function menuRoleList(params) {
    return axios.get('/tansci/role/menuRoleList', { params: params });
}

/**
 * 分配菜单角色
 * @param {*} params 
 * @returns 
 */
export function menuRoleAdd(params) {
    return axios.post('/tansci/role/menuRoleAdd', params);
}

/**
 * 用户角色详情
 * @param {*} params 
 * @returns 
 */
export function userRoleInfo(params) {
    return axios.get('/tansci/role/userRoleInfo', { params: params });
}

/**
 * 分配用户角色
 * @param {*} params 
 * @returns 
 */
export function userRoleAdd(params) {
    return axios.post('/tansci/role/userRoleAdd', params);
}

// ====================组织机构=========================
/**
 * 组织分页列表
 * @param {*} params 
 * @returns 
 */
export function orgList(params) {
    return axios.get('/tansci/org/list', { params: params });
}

/**
 * 删除组织
 * @param {*} params 
 * @returns 
 */
export function delOrg(params) {
    return axios.get('/tansci/org/del', { params: params });
}

/**
 * 添加组织
 * @param {*} params 
 * @returns 
 */
export function saveOrg(params) {
    return axios.post('/tansci/org/save', params);
}

/**
 * 更新组织
 * @param {*} params 
 * @returns 
 */
export function updateOrg(params) {
    return axios.post('/tansci/org/update', params);
}

/**
 * 组织角色详情
 * @param {*} params 
 * @returns 
 */
export function orgRoleInfo(params) {
    return axios.get('/tansci/role/orgRoleInfo', { params: params });
}

/**
 * 分配组织角色
 * @param {*} params 
 * @returns 
 */
export function orgRoleAdd(params) {
    return axios.post('/tansci/role/orgRoleAdd', params);
}

// ==============日志===============================================

/**
 * 操作日志分页
 * @param {*} params 
 * @returns 
 */
export function logInfoPage(params) {
    return axios.get('/tansci/log/logInfoPage', { params: params });
}

/**
 * 异常日志分页
 * @param {*} params 
 * @returns 
 */
export function logErrorPage(params) {
    return axios.get('/tansci/log/logErrorPage', { params: params });
}

// ====================任务管理=========================
/**
 * 任务分页
 * @param {*} params 
 * @returns 
 */
export function taskPage(params) {
    return axios.get('/tansci/taskConfig/page', { params: params });
}

/**
 * 删除任务
 * @param {*} params 
 * @returns 
 */
export function delTask(params) {
    return axios.get('/tansci/taskConfig/del', { params: params });
}

/**
 * 添加任务
 * @param {*} params 
 * @returns 
 */
export function saveTask(params) {
    return axios.post('/tansci/taskConfig/save', params);
}

/**
 * 修改任务
 * @param {*} params 
 * @returns 
 */
export function updateTask(params) {
    return axios.post('/tansci/taskConfig/update', params);
}

/**
 * 任务执行日志分页
 * @param {*} params 
 * @returns 
 */
export function taskLogPage(params) {
    return axios.get('/tansci/taskLog/page', { params: params });
}

/**
 * 清空日志
 * @param {*} params 
 * @returns 
 */
export function taskLogClear(params) {
    return axios.get('/tansci/taskLog/clear', { params: params });
}