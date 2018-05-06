package com.elf.techcomp.services.service;

import com.elf.techcomp.services.entity.DataResult;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @program: elf
 * @description: MdmService [Demo for CXF]
 * @author: Liyiming
 * @create: 2018-05-05 19:08
 **/
@Path("/mdmService")
@Service
public interface MdmService {
    /**
     * @Description: getDataList [Demo for CXF]
     * http://localhost:8088/elf/services/mdmService/result?_type=xml
     * http://localhost:8088/elf/services/mdmService/result?_type=json
     * @Param: []
     * @return: com.elf.techcomp.services.entity.DataResult
     * @Author: Liyiming
     * @Date: 2018/5/6
     */
    @GET
    @Path("/result")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    DataResult getDataList();
}
