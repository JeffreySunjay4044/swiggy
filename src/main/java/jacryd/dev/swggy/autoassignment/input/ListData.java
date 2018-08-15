package jacryd.dev.swggy.autoassignment.input;

import jacryd.dev.swggy.autoassignment.dto.DeliveryExecutive;
import jacryd.dev.swggy.autoassignment.dto.OrderDao;

public class ListData {

	private ListData() {
		
	}
	
	public static class OrderList extends JsonInputReader<OrderDao>{
		
	}
	
	public static class DEList extends JsonInputReader<DeliveryExecutive>{
		
	}
}
