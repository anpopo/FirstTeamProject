package management1;

import java.io.Serializable;

public class UserDTO implements Serializable{

	private static final long serialVersionUID = 2104786937406929481L;
	private int userseq;
	private String userId;
	private String userPassword;
	private String userName;
	private String userPhoneNUm;
	
	public UserDTO() {
	}

	public UserDTO(String userId, String userPassword, String userName, String userPhoneNUm) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userPhoneNUm = userPhoneNUm;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNUm() {
		return userPhoneNUm;
	}

	public void setUserPhoneNUm(String userPhoneNUm) {
		this.userPhoneNUm = userPhoneNUm;
	}

	public int getUserseq() {
		return userseq;
	}

	public void setUserseq(int userseq) {
		this.userseq = userseq;
	}
	
	
	
	
	
	
}
