package com.elf.common.util;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import java.lang.reflect.Method;
import java.util.List;

public class AnnotationUtil {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {  
        // 获取特定包下所有的类(包括接口和类)  
		ClassUtil classUtil = new ClassUtil();
        List<Class<?>> clsList = classUtil.getAllClassByPackageName("com.elf.*.*.web");
        //输出所有使用了特定注解的类的注解值  
        AnnotationUtil.validAnnotation(clsList);  
    }  
	
	public static void validAnnotation(List<Class<?>> clsList){
		if (clsList != null && clsList.size() > 0) {
			for (Class<?> cls : clsList) {
				//获取类中的所有的方法
				Method[] methods = cls.getDeclaredMethods();
				if (methods != null && methods.length > 0) {
					for (Method method : methods) {
						RequiresPermissions permissionAnnotion = (RequiresPermissions) method.getAnnotation(RequiresPermissions.class);
						if (permissionAnnotion != null) {
							//可以做权限验证
							//System.out.println(permissionAnnotion.value());
							for(String permission : permissionAnnotion.value()) {
								System.out.println(permission);
							}
						}
					}
				}
			}
		}
	}

}
