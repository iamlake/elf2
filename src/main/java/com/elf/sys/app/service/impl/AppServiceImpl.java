package com.elf.sys.app.service.impl;

import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.app.entity.App;
import com.elf.sys.app.mapper.AppMapper;
import com.elf.sys.app.service.AppService;
import org.springframework.stereotype.Service;

/**
 * @program: elf
 * @description: AppServiceImpl
 * @author: Liyiming
 * @create: 2018-03-17 17:21
 **/
@Service
public class AppServiceImpl extends BaseServiceImpl<AppMapper, App> implements AppService {
}
