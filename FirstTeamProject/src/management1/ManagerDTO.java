package management1;

import java.io.Serializable;

public class ManagerDTO implements Serializable{
	
	
	private static final long serialVersionUID = -8227836324857095595L;
	private int managerseq;
	String managerId;
	String managerPassword;
	
	public ManagerDTO() {}

	public ManagerDTO(String managerId, String managerPassword) {
		this.managerId = managerId;
		this.managerPassword = managerPassword;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public int getManagerseq() {
		return managerseq;
	}

	public void setManagerseq(int managerseq) {
		this.managerseq = managerseq;
	}
	
	
	
	
	
}
