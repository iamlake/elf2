package com.elf.core.context.context;

import com.elf.sys.org.entity.User;

import java.io.Serializable;
import java.util.Map;

public abstract interface Context extends Serializable {

    public static final String ELF_CONTEXT_KEY = "com.elf.core.context.context.ELFContext";

    public abstract User getCurrentUser();

    public abstract void setCurrentUser(User currentUser);

    public abstract void addCustomProperty(Object paramObject1, Object paramObject2);

    public abstract Object getCustomProperty(Object paramObject);

    public abstract void removeCustomProperty(Object paramObject);

    public abstract void removeAllCustomProperties();

    public abstract void removeCurrentUser();

    public abstract Map<Object, Object> getCustomProperties();

    public abstract void setCustomProperties(Map<Object, Object> paramMap);
}