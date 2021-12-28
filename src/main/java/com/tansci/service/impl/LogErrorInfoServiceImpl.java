package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.LogErrorInfo;
import com.tansci.mapper.LogErrorInfoMapper;
import com.tansci.service.LogErrorInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @path：com.tansci.service.impl.LogErrorInfoServiceImpl.java
 * @className：LogErrorInfoServiceImpl.java
 * @description：操作日志
 * @author：tanyp
 * @dateTime：2021/11/18 13:51
 * @editNote：
 */
@Slf4j
@Service
public class LogErrorInfoServiceImpl extends ServiceImpl<LogErrorInfoMapper, LogErrorInfo> implements LogErrorInfoService {
}
