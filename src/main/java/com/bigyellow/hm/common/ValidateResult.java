
package com.bigyellow.hm.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date 2013-10-22
 */
@SuppressWarnings("serial")
public class ValidateResult implements Serializable {
	
	public static final int SUCCESS = 0;

	private int errorCode;
	private String errorMsg;
	private List<String> errors = new ArrayList<String>();
	
	private Object obj;

	public boolean isSuccess() {
		return this.errorCode == SUCCESS;
	}


	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	 
	public List<String> getErrors() {
		return errors;
	}


	public void setErrors(List<String> errors) {
		this.errors = errors;
	}


	public ValidateResult() { 
		this.errorCode = SUCCESS;
	}
	
	public ValidateResult(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public ValidateResult(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public void resolveErrorMsg(String errorMsg) {
		setErrorCode(ErrorCode.SERVER_ERROR);
		setErrorMsg(errorMsg);
	}
	
	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}


	public <E> void resolveErrorMsg(Set<ConstraintViolation<E>> errorList) {
		this.errors.clear();
		for (ConstraintViolation<E> error : errorList) {
			this.errors.add(error.getMessage());
		}
		if(!this.errors.isEmpty() && this.getErrorCode() == SUCCESS) {
			this.setErrorCode(ErrorCode.UI_CONSTRAIN_ERROR);
		}
	}
	
}
