package models;
import java.io.File;
import java.io.Serializable;

import javax.servlet.http.Part;

import utils.StringUtils;

public class ProductModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String productid;
	private String modelno;
	private String productname;
	private String price;
	private String stock;
	private String description;
	private String category;
	private String username;
	private String imageUrlFromPart;

	public ProductModel() {
		
	}
	
	public ProductModel(String productid, String modelno, String productname, String price, String stock, String description, 
			String category, String username, Part imagePart) {
		this.productid=productid;
		this.modelno=modelno;
		this.productname=productname;
		this.price=price;
		this.stock=stock;
		this.description=description;
		this.category=category;
		this.username=username;
		this.imageUrlFromPart=getImageUrl(imagePart);
	
	}
	public String getProductid() {
		return productid;
	}
	public String getModelno() {
		return modelno;
	}
	public String getProductname() {
		return productname;
	}
	public String getPrice() {
		return price;
	}
	public String getStock() {
		return stock;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public String getUsername() {
		return username;
	}
	
	public String getimageUrlFromPart() {
		return imageUrlFromPart;
	}
	
	
	public void setProductid(String productid) {
		this.productid=productid;
	}

	public void setModelno(String modelno) {
		this.modelno=modelno;
	}
	public void setProductname(String productname) {
		this.productname=productname;
	}
	public void setPrice(String price) {
		this.price=price;
	}
	public void setStock(String stock) {
		this.stock=stock;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setCategory(String category) {
		this.category=category;
	}

	public void setUsername(String username) {
		this.username=username;
	}
	public void setimageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}
	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}
	
	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.jpg";
		}
		return imageUrlFromPart;
	}

}
