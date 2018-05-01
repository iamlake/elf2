package com.elf.core.context.context;

import com.elf.core.context.context.impl.ContextImpl;

public class ContextHolder {
	private static ThreadLocal<Context> contextHolder = new ThreadLocal<>();

	public static void setContext(Context paramContext) {
		contextHolder.set(paramContext);
	}

	public static Context getContext() {
		Context context = contextHolder.get();
		if (context == null) {
			context = new ContextImpl();
			setContext(context);
		}
		return context;
	}
}