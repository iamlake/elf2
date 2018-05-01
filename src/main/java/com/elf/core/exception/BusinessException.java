package com.elf.core.exception;

public class BusinessException extends CoreException {
	
	private static final long serialVersionUID = -8677749459027032368L;
	private boolean isLogEnabled = false;

	public BusinessException(String paramString, Object[] paramArrayOfObject)
	  {
	    super(paramString, paramArrayOfObject);
	  }

	  public BusinessException(String paramString, Object[] paramArrayOfObject, boolean paramBoolean)
	  {
	    super(paramString, paramArrayOfObject);
	    this.isLogEnabled = paramBoolean;
	  }

	  public BusinessException(String paramString)
	  {
	    super(paramString, null);
	  }

	  public BusinessException(String paramString, boolean paramBoolean)
	  {
	    super(paramString, null);
	    this.isLogEnabled = paramBoolean;
	  }

	  public BusinessException(String paramString, Throwable paramThrowable, Object[] paramArrayOfObject)
	  {
	    super(paramString, paramThrowable, paramArrayOfObject);
	  }

	  public BusinessException(String paramString, Throwable paramThrowable, Object[] paramArrayOfObject, boolean paramBoolean)
	  {
	    super(paramString, paramThrowable, paramArrayOfObject);
	    this.isLogEnabled = paramBoolean;
	  }

	  public boolean isLogEnabled()
	  {
	    return this.isLogEnabled;
	  }

}