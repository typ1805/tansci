package com.tansci.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.system.SysDic;
import com.tansci.domain.system.dto.SysDicDto;

import java.util.List;

/**
 * @path：com.tansci.service.SysDicService.java
 * @className：SysDicService.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2021/7/4 13:39
 * @editNote：
 */
public interface SysDicService extends IService<SysDic> {

    List<SysDic> dicList(SysDicDto dto);

    boolean del(Integer id);
}
