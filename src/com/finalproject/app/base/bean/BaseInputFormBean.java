package com.finalproject.app.base.bean;

public interface BaseInputFormBean {

	public void validateInput();

	public void setErrorMessage(String key, String message);

	public String getErrorMessage(String key);

	public void clearErrors();
}
