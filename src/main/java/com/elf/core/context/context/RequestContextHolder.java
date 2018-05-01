package com.elf.core.context.context;

import java.util.HashMap;
import java.util.Map;

public class RequestContextHolder
{
  private static final ThreadLocal contextHolder = new ThreadLocal();

  public static void setRequestContext(Map paramMap)
  {
    contextHolder.set(paramMap);
  }

  public static Map getRequestContext()
  {
    Map localObject = (Map)contextHolder.get();
    if (localObject == null) {
      localObject = new HashMap();
      setRequestContext((Map)localObject);
    }
    return localObject;
  }

  public static void clearPojoContext() {
    contextHolder.set(null);
  }
}