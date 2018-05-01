package com.elf.sys.ria.service.impl;

import com.elf.core.service.impl.BaseServiceImpl;
import com.elf.sys.ria.entity.App;
import com.elf.sys.ria.mapper.AppMapper;
import com.elf.sys.ria.service.AppService;
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
