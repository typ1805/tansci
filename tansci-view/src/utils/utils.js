/**
 * 判断是否是移动端
 * @returns 
 */
export const isMobile = () => {
    if (navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)) {
        return true;
    } else {
        return false;
    }
}

/**
 * 时间格式化
 * @param {*} date 
 * @returns 
 */
export function dateTimeFormat(date) {
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
    var s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
    return Y + M + D + h + m + s;
}

/**
 * 时间格式化
 * @param {*} date 
 * @returns 
 */
export function dateFormat(date) {
    if (!date) {
        return null;
    }
    date = new Date(date);
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
    return Y + M + D;
}

/**
 * 显示当前时间（年月日时分秒）
 * @param {*} timeStamp 
 * @returns 
 */
export function timeFormate(timeStamp) {
    var year = new Date(timeStamp).getFullYear();
    var month = new Date(timeStamp).getMonth() + 1 < 10 ? "0" + (new Date(timeStamp).getMonth() + 1) : new Date(timeStamp).getMonth() + 1;
    var date = new Date(timeStamp).getDate() < 10 ? "0" + new Date(timeStamp).getDate() : new Date(timeStamp).getDate();
    var hh = new Date(timeStamp).getHours() < 10 ? "0" + new Date(timeStamp).getHours() : new Date(timeStamp).getHours();
    var mm = new Date(timeStamp).getMinutes() < 10 ? "0" + new Date(timeStamp).getMinutes() : new Date(timeStamp).getMinutes();
    var ss = new Date(timeStamp).getSeconds() < 10 ? "0" + new Date(timeStamp).getSeconds() : new Date(timeStamp).getSeconds();
    return year + "年" + month + "月" + date + "日" + hh + "时" + mm + '分' + ss + '秒';
}

/**
 * 毫秒转 dd:HH:mm:sss
 */
export function formatDuring(val) {
    let secondTime = parseInt(val) //将传入的秒的值转化为Number
    let min = 0 // 初始化分
    let h = 0 // 初始化小时
    let result = ''
    if (secondTime > 60) { //如果秒数大于60，将秒数转换成整数
        min = parseInt(secondTime / 60) //获取分钟，除以60取整数，得到整数分钟
        secondTime = parseInt(secondTime % 60) //获取秒数，秒数取佘，得到整数秒数
        if (min > 60) { //如果分钟大于60，将分钟转换成小时
            h = parseInt(min / 60) //获取小时，获取分钟除以60，得到整数小时
            min = parseInt(min % 60) //获取小时后取佘的分，获取分钟除以60取佘的分
        }
    }
    result = `${h.toString().padStart(2,'0')}:${min.toString().padStart(2,'0')}:${secondTime.toString().padStart(2,'0')}`
    return result;
}

/**
 * 判断：早上好,上午好,下午好,傍晚好,晚上好
 * @param {*} timeStamp 
 * @returns 
 */
export function timeAddress() {
    var now = new Date();
    var hour = now.getHours();
    var address = "";
    if (hour < 6) {
        address = "凌晨好!";
    } else if (hour < 9) {
        address = "早上好！";
    } else if (hour < 12) {
        address = "上午好！";
    } else if (hour < 14) {
        address = "中午好！";
    } else if (hour < 17) {
        address = "下午好！";
    } else if (hour < 19) {
        address = "傍晚好！";
    } else if (hour < 22) {
        address = "晚上好！";
    } else {
        address = "夜里好！";
    }
    return address;
}

/**
 * 遍历树结构，当节点的children为空时，删除children
 */
export function treeHandle(data) {
    return data.map((item, index) => {
        if (item.children.length > 0) {
            treeHandle(item.children)
        } else {
            delete item.children;
        }
        return item;
    })
}

/**
 * 遍历树结构，根据id删除节点
 */
export function treeDelHandle(data, id) {
    var arr = [];
    for (var i = 0; i < data.length; i++) {
        var item = data[i];
        if (item.id == id) {
            data.splice(i--, 1);
        } else {
            if (item.children && item.children.length) {
                item.children = treeDelHandle(item.children, id);
            }
            arr.push(item);
        }
    }
    return arr;
}

/**
 * 将树结构数据转扁平化数据
 */
export function treeFlattening(data = [], arr = []) {
    let i = 0;
    for (let item of data) {
        i++;
        item.nodelevel = i;
        arr.push(item);
        if (item.children && item.children.length) {
            treeFlattening(item.children, arr);
        }
    }
    return arr;
}

/**
 * 获取树的Id集合
 */
export function getTreeIds(data = [], arr = []) {
    for (let item of data) {
        arr.push(item.id);
        if (item.children && item.children.length) {
            getTreeIds(item.children, arr);
        }
    }
    return arr;
}

/**
 * 获取菜单树，获取选中Id的集合
 */
export function getCheckTreeIds(data = [], arr = []) {
    for (let item of data) {
        if (item.menuId) {
            arr.push(item.id);
        }
        if (item.children && item.children.length) {
            getCheckTreeIds(item.children, arr);
        }
    }
    return arr;
}

/**
 * 生成UUID
 * @returns 
 */
export function getUuid() {
    var s = [];
    var hexDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1)
    }
    s[14] = "4"
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1)
    s[8] = s[13] = s[18] = s[23]
    let uuid = s.join("")
    return uuid;
}

/**
 * 数字格式化为千分位
 * @param {*} num 
 * @returns 
 */
export function numberDormat(num) {
    return (num + '').replace(/(\d{1,3})(?=(\d{3})+(?:$|\.))/g, '$1,');
}

