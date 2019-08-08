package com.iktpreobuka.schoolregister.entities.dto;

public class UpdatePasswordDTO {
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordConf;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordConf() {
		return newPasswordConf;
	}
	public void setNewPasswordConf(String newPasswordConf) {
		this.newPasswordConf = newPasswordConf;
	}
	public UpdatePasswordDTO(String oldPassword, String newPassword, String newPasswordConf) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordConf = newPasswordConf;
	}
	public UpdatePasswordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
