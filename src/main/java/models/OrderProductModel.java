package models;
import java.io.Serializable;


public class OrderProductModel implements Serializable {
	private static long serialVersionUID = 1L;

	private String orderid;
	private String productid;
	private String quantity;
	private String subtotal;

	public OrderProductModel() {
		
	}
	
	public OrderProductModel(String orderid,String productid, String quantity, String subtotal) {
		this.orderid=orderid;	
		this.productid=productid;
		this.quantity=quantity;
		this.subtotal=subtotal;
		
	
	}


	public String getOrderId() {
		return orderid;
	}
	

	public void setOrderId(String orderid) {
		this.orderid = orderid;
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
	public String getSubtotal() {
		return subtotal;
	}
	
	public void setSubtotal(String subtotal){
		this.subtotal=subtotal;
	}

	
}
