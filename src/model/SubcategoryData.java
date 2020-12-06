package model;

public class SubcategoryData {
	private int subcategoryId;
	private String subcategoryName;
	private int firstCategoryId;
	
	public SubcategoryData() {
		this.firstCategoryId = 0;
		this.subcategoryId = 0;
		this.subcategoryName = "";
	}
	
	public SubcategoryData(int subcategoryId, String subcategoryName) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcategoryName = subcategoryName;
	}

	public int getSubcategoryId() {
		return subcategoryId;
	}
	
	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public int getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(int firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}
	
	
}


