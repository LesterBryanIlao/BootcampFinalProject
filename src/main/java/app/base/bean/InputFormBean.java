package app.base.bean;

public interface InputFormBean {

	public void validateInput();

	public void setErrorMessage(String key, String message);

	public String getErrorMessage(String key);

	public void clearErrors();
}
