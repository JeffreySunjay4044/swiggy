package jacryd.dev.swggy.autoassignment.ds;

import java.util.Comparator;

public class Assign extends BaseDsForRank  implements Comparator<Assign>{

	int order_id;
	int deliveryExecutive_id;  

	public Assign(Double rank, int order_id, int 
			deliveryExecutive_id) {
		this.setRank(rank.intValue());
		this.order_id = order_id;
		this.deliveryExecutive_id = deliveryExecutive_id;
	}
	
	

	public int getOrder_id() {
		return order_id;
	}



	public int getDeliveryExecutive_id() {
		return deliveryExecutive_id;
	}



	public int compare(Assign o1, Assign o2) {
		// TODO Auto-generated method stub
		return o1.getRank()-o2.getRank();
	}
	
}
