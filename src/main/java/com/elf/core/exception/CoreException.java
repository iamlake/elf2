package com.elf.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CoreException extends RuntimeException {

	private static final long serialVersionUID = 6053698889183014590L;
	protected static final Logger logger = LoggerFactory.getLogger(CoreException.class);
	private String code;
	private Object[] args;
	private String message;
	private Map<Object, Object> attributeMap = new HashMap<>();

	public CoreException(String paramString, Object[] paramArrayOfObject) {
		this.code = paramString;
		this.args = paramArrayOfObject;
	}

	public CoreException(String paramString, Throwable paramThrowable, Object[] paramArrayOfObject) {
		super(paramThrowable);
		this.code = paramString;
		this.args = paramArrayOfObject;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<Object, Object> getAttributeMap() {
		return attributeMap;
	}

	public void setAttributeMap(Map<Object, Object> attributeMap) {
		this.attributeMap = attributeMap;
	}

}