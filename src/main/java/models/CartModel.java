package models;
import java.io.Serializable;


public class CartModel implements Serializable {
	private static long serialVersionUID = 1L;

	private String cartid;
	private String productid;
	private String quantity;


	public CartModel() {
		
	}
	
	public CartModel(String cartid,String productid, String quantity) {
		this.cartid=cartid;	
		this.productid=productid;
		this.quantity=quantity;
	
	
	}


	public String getCartId() {
		return cartid;
	}
	

	public void setCartId(String cartid) {
		this.cartid = cartid;
	}


	public String getProductId() {
		return productid;
	}

	public void setProductId(String productid) {
		this.productid = productid;
	}
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity){
		this.quantity=quantity;
	}

	
}
