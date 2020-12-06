package model;

public class UserFolderData {
	
	int folderId;
	int userId;
	String folderName;
	
	public UserFolderData() {
		this.folderId = 0;
		this.userId = 0;
		this.folderName = "";
	}

	public int getFolderId() {
		return folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	

}
