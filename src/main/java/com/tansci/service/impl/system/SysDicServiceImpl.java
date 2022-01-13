package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Enums;
import com.tansci.domain.system.SysDic;
import com.tansci.domain.system.dto.SysDicDto;
import com.tansci.mapper.system.SysDicMapper;
import com.tansci.service.system.SysDicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.SysDicServiceImpl.java
 * @className：SysDicServiceImpl.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2021/7/4 14:02
 * @editNote：
 */
@Slf4j
@Service
public class SysDicServiceImpl extends ServiceImpl<SysDicMapper, SysDic> implements SysDicService {

    @Override
    public List<SysDic> dicList(SysDicDto dto) {
        List<SysDic> dicList = this.baseMapper.selectList(
                Wrappers.<SysDic>lambdaQuery()
                        .eq(Objects.nonNull(dto.getParentId()), SysDic::getParentId, dto.getParentId())
                        .eq(Objects.nonNull(dto.getGroupName()), SysDic::getGroupName, dto.getGroupName())
                        .eq(Objects.nonNull(dto.getType()), SysDic::getType, dto.getType())
                        .between(Objects.nonNull(dto.getStartTime()) && Objects.nonNull(dto.getEndTime()), SysDic::getCreateTime, dto.getStartTime(), dto.getEndTime())
                        .like(Objects.nonNull(dto.getKeyword()), SysDic::getDicLabel, dto.getKeyword()).or()
                        .like(Objects.nonNull(dto.getKeyword()), SysDic::getGroupName, dto.getKeyword()).or()
                        .like(Objects.nonNull(dto.getKeyword()), SysDic::getDicValue, dto.getKeyword())
        );

        List<SysDic> newDicList = dicList.stream().filter(item -> item.getParentId() == 0).map(item -> {
            item.setChildren(this.getChildrens(item, dicList));
            item.setTypeName(item.getType() == null ? "" : Enums.getVlaueByGroup(item.getType(), "dic_type"));
            return item;
        }).sorted((item1, item2) -> {
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
        return newDicList.size() > 0 ? newDicList : dicList;
    }

    /**
     * @MonthName： getChildrens
     * @Description： 封装树形数据
     * @Author： tanyp
     * @Date： 2021/7/6 16:10
     * @Param： [dic, list]
     * @return： java.util.List<com.kuiper.qms.domain.SysDic>
     **/
    public List<SysDic> getChildrens(SysDic dic, List<SysDic> list) {
        List<SysDic> treeDic = list.stream().filter(item -> Objects.equals(item.getParentId(), dic.getId())).map(item -> {
            // 递归添加子数据
            List<SysDic> childrens = getChildrens(item, list);
            item.setChildren(childrens);
            item.setTypeName(item.getType() == null ? "" : Enums.getVlaueByGroup(item.getType(), "dic_type"));
            return item;
        }).sorted((item1, item2) -> { // 排序
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
        return treeDic;
    }

    @Override
    public boolean del(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<SysDic> dicList = this.baseMapper.getDicChildrens(id);
        if (Objects.nonNull(dicList) && dicList.size() > 0) {
            ids.addAll(dicList.stream().map(SysDic::getId).collect(Collectors.toList()));
        }
        this.baseMapper.deleteBatchIds(ids);
        return true;
    }
}
