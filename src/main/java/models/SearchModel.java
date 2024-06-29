package models;
import java.io.Serializable;

public class SearchModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String search;


	public SearchModel() {
		
	}
	
	public SearchModel(String search) {
		this.search=search;
		
	
	}
	public String getSearch() {
		return search;
	}
	


	public void setSearch(String search) {
		this.search=search;
	}
	
}
