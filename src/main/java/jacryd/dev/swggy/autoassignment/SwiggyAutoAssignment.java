package jacryd.dev.swggy.autoassignment;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jacryd.dev.swggy.autoassignment.config.ConfigurationClass;
import jacryd.dev.swggy.autoassignment.config.ConfigurationClass.ConfigurationBuilder;
import jacryd.dev.swggy.autoassignment.ds.Assign;
import jacryd.dev.swggy.autoassignment.ds.BinaryHeap;
import jacryd.dev.swggy.autoassignment.dto.DeliveryExecutive;
import jacryd.dev.swggy.autoassignment.dto.OrderDao;
import jacryd.dev.swggy.autoassignment.input.JsonInputReader;
import jacryd.dev.swggy.autoassignment.input.ListData.DEList;
import jacryd.dev.swggy.autoassignment.input.ListData.OrderList;
import jacryd.dev.swggy.autoassignment.ranking.RankingSystem;

public class SwiggyAutoAssignment {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, ParseException {
		int results = 0;
		Set<String> orderAndDelieveryExecutive = new HashSet<String>();
		ConfigurationClass classConfig = ConfigurationBuilder.build();
		List<DeliveryExecutive> deList = JsonInputReader.read("/Users/akshayanarmadha/eclipse-workspace/swggy.autoassignment/src/main/resources/DEList.json", DEList.class).listData; 	
		List<OrderDao> listOrder = JsonInputReader.read("/Users/akshayanarmadha/eclipse-workspace/swggy.autoassignment/src/main/resources/OrderList.json", OrderList.class).listData; 
		BinaryHeap<Assign> assignHeap = RankingSystem.findRank(listOrder, deList, classConfig);
		for(int i = 0 ; i < assignHeap.heapSize();i++) {
			Assign assignTemp = assignHeap.deleteMin();
			if(orderAndDelieveryExecutive.add("/de_id"+ assignTemp.getDeliveryExecutive_id()) && orderAndDelieveryExecutive.add("/order_id"+ assignTemp.getOrder_id())) {
				results++;
				System.out.println(" the order id " + assignTemp.getOrder_id() + " is assigned to de id "+ assignTemp.getDeliveryExecutive_id() + " the rank is "+ assignTemp.getRank());
			}
			
		}
		if(results < listOrder.size()) {
			System.out.println("De s are busy right now");
		}
		// for mapping the order id with de id we can put it in a map hashmap		
	
	}
	
}
