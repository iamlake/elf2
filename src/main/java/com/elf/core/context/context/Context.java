package com.elf.core.context.context;

import com.elf.sys.org.entity.User;

import java.io.Serializable;
import java.util.Map;

public interface Context extends Serializable {

    String ELF_CONTEXT_KEY = "com.elf.core.context.context.ELFContext";

    User getCurrentUser();

    void setCurrentUser(User currentUser);

    void addCustomProperty(Object paramObject1, Object paramObject2);

    Object getCustomProperty(Object paramObject);

    void removeCustomProperty(Object paramObject);

    void removeAllCustomProperties();

    void removeCurrentUser();

    Map<Object, Object> getCustomProperties();

    void setCustomProperties(Map<Object, Object> paramMap);
}