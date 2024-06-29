package models;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import javax.servlet.http.Part;

import utils.StringUtils;

import java.time.LocalDate;
/**
 * 
 * proper commenting style for coursework
 * 
 * 
 * 
 * slash an ashtrick then enter down 
 * ths is a student model class and represent a single object 
 * @author sushant (np01cp4a220304@gmail.com)
 */
public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String gender;
	private String email;
	private String phoneNumber;
	private String role;
	private String username;
	private String password;
	private String imageUrlFromPart;
	
	public UserModel(String firstName, String lastName, LocalDate dob, String gender, String email, 
			String phoneNumber, String role, String username, String password, Part imagePart) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.dob=dob;
		this.gender=gender;
		this.email=email;
		this.phoneNumber=phoneNumber;
		this.role=role;
		this.username=username;
		this.password=password;
		this.imageUrlFromPart=getImageUrl(imagePart);
		
	}
	public UserModel() {
		
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public String getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getRole() {
		return role;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getimageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public void setDob(LocalDate dob) {
		this.dob=dob;
	}
	public void setGender(String gender) {
		this.gender=gender;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	public void setRole(String role) {
		this.role=role;
	}
	public void setUsername(String username) {
		this.username=username;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setimageUrlFromPart(Part part) {
		this.imageUrlFromPart = getImageUrl(part);
	}
	public void setImageUrlFromDB(String imageUrl) {
		this.imageUrlFromPart = imageUrl;
	}
	
	private String getImageUrl(Part part) {
		String savePath = StringUtils.IMAGE_DIR_SAVE_PATH_2;
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
