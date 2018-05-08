package com.elf.techcomp.services.entity;

import com.elf.sys.org.entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program: elf
 * @description: RestResult [Demo for CXF]
 * @author: Liyiming
 * @create: 2018-05-05 19:21
 **/
@XmlRootElement(name = "restResult")
public class RestResult implements Serializable {
    private List<User> list;

    private User[] array;

    private Map<String, User> map;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public User[] getArray() {
        return array;
    }

    public void setArray(User[] array) {
        this.array = array;
    }

    public Map<String, User> getMap() {
        return map;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }

    private static final long serialVersionUID = 1L;
}
