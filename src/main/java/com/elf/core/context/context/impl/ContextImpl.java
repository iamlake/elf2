package com.elf.core.context.context.impl;

import com.elf.core.context.context.Context;
import com.elf.sys.org.entity.User;

import java.util.HashMap;
import java.util.Map;

public class ContextImpl implements Context {

    private User currentUser;
    private Map<Object, Object> customProperties = new HashMap<>();

    public ContextImpl() {
        this.currentUser = new User();
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void addCustomProperty(Object paramObject1, Object paramObject2) {
        this.customProperties.put(paramObject1, paramObject2);
    }

    @Override
    public Object getCustomProperty(Object paramObject) {
        return this.customProperties.get(paramObject);
    }

    @Override
    public void removeCustomProperty(Object paramObject) {
        this.customProperties.remove(paramObject);
    }

    @Override
    public void removeAllCustomProperties() {
        this.customProperties.clear();
    }

    @Override
    public void removeCurrentUser() {
        this.currentUser.setUserId(null);
        this.currentUser.setFullname(null);
        this.currentUser.setAccount(null);
        // this.currentUser.setRoles(null);
    }

    @Override
    public Map<Object, Object> getCustomProperties() {
        return this.customProperties;
    }

    @Override
    public void setCustomProperties(Map<Object, Object> paramMap) {
        this.customProperties = paramMap;
    }

    private static final long serialVersionUID = 5499258733594920432L;
}