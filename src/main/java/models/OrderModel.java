package models;
import java.io.Serializable;
import java.time.LocalDate;

public class OrderModel implements Serializable {
	private static long serialVersionUID = 1L;

	private String order_id;
	private LocalDate order_date;
	private String order_status;
	private String total_amt;
	private String payment;
	private String username;

	public OrderModel() {
		
	}
	
	public OrderModel(String order_id,String username,LocalDate order_date, String order_status, String total_amt, String payment) {
		this.order_id=order_id;
		this.order_date=order_date;
		this.order_status=order_status;
		this.total_amt=total_amt;
		this.payment=payment;
		this.username=username;
	
	}


	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
