package jacryd.dev.swggy.autoassignment.dto;

public class DeliveryExecutive {
	
	private int id ;
	private String current_location;
	private String last_order_delivered_time;
	private boolean isBusy;
	private boolean isIncluded;
	
	
	public int getId() {
		return id;
	}
	public String getcurrent_location() {
		return current_location;
	}
	public String getlast_order_delivered_time() {
		return last_order_delivered_time;
	}
	public boolean isBusy() {
		return isBusy;
	}
	
	
}
