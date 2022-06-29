package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.system.SysMenu;
import com.tansci.domain.system.dto.SysMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName： SysMenuMapper.java
 * @ClassPath： com.tansci.mapper.SysMenuMapper.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 16:57
 **/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> list(SysMenuDto dto);

    List<SysMenu> getMenuChildrens(Integer id);

}
