package models;
import java.io.Serializable;


public class DashboardModel implements Serializable {
	private static long serialVersionUID = 1L;

	private String productcount;
	
	private String ordercount;
	private String sales;
	public DashboardModel() {
		
	
	}
	public DashboardModel(String productcount,String ordercount, String sales) {
		this.productcount=productcount;
		this.ordercount=ordercount;
		this.sales=sales;
	
	}
	
	public String getProductcount() {
		return productcount;
	}

	public void setProductcount(String productcount) {
		this.productcount = productcount;
	}

	public String getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(String ordercount) {
		this.ordercount = ordercount;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	

}