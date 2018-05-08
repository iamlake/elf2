package com.elf.techcomp.services.service.impl;

import com.elf.sys.org.entity.User;
import com.elf.techcomp.services.entity.RestResult;
import com.elf.techcomp.services.service.MdmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: elf
 * @description: MdmServiceImpl
 * @author: Liyiming
 * @create: 2018-05-06 16:48
 **/
@Service
public class MdmServiceImpl implements MdmService {

    /**
     * @Description: getDataList [Demo for CXF]
     * @Param: []
     * @return: com.elf.techcomp.services.entity.RestResult
     * @Author: Liyiming
     * @Date: 2018/5/6
     */
    @Override
    public RestResult getDataList() {
        RestResult restResult = new RestResult();
        User u1 = new User();
        u1.setUserId("1");
        u1.setAccount("lym");
        u1.setFullname("李一鸣");
        User u2 = new User();
        u2.setUserId("2");
        u2.setAccount("wangming");
        u2.setFullname("王明");
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        restResult.setList(list);

        return restResult;
    }
}
