package model;

public class SubcategoryData {

	public SubcategoryData(int subcategoryId, String subcategoryName) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
	}

	private int subcategoryId;
	
	private String subcategoryName;

	public int getSubcategoryId() {
		return subcategoryId;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
}
