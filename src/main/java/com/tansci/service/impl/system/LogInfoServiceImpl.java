package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.system.LogInfo;
import com.tansci.mapper.system.LogInfoMapper;
import com.tansci.service.system.LogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @path：com.tansci.service.impl.LogInfoServiceImpl.java
 * @className：LogInfoServiceImpl.java
 * @description：操作日志
 * @author：tanyp
 * @dateTime：2021/11/18 13:50
 * @editNote：
 */
@Slf4j
@Service
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements LogInfoService {
}
