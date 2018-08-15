package jacryd.dev.swggy.autoassignment.ranking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jacryd.dev.swggy.autoassignment.config.ConfigurationClass;
import jacryd.dev.swggy.autoassignment.ds.Assign;
import jacryd.dev.swggy.autoassignment.ds.BinaryHeap;
import jacryd.dev.swggy.autoassignment.dto.DeliveryExecutive;
import jacryd.dev.swggy.autoassignment.dto.OrderDao;
import jacryd.dev.swggy.autoassignment.utils.HaversineDistance;

public class RankingSystem {

	public static BinaryHeap<Assign> findRank(List<OrderDao> orderList, List<DeliveryExecutive> deList, ConfigurationClass configClass) throws ParseException {
		float totalDistance = 0.0f;
		double lastOrderedTime = 0 ;
		long fixedTime = TimeUnit.MINUTES.toMillis(Long.parseLong(configClass.getBoundaryOrderTime()));
		
		BinaryHeap<Assign> heap = new BinaryHeap<Assign>(orderList.size() * deList.size(), Assign[].class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		for(OrderDao order: orderList) {
			String lat1 = order.getrestaurant_location().split(",")[0].replaceAll("lat", "");
			String long1 = order.getrestaurant_location().split(",")[1].replaceAll("long", "");
			for(DeliveryExecutive de : deList) {
				String lat2 = de.getcurrent_location().split(",")[0].replaceAll("lat", "");
				String long2 = de.getcurrent_location().split(",")[1].replaceAll("long", "");
				int haversineDistance = HaversineDistance.getDistance(lat1, long1, lat2, long2);
				Date date = sdf.parse(de.getlast_order_delivered_time());
				long timeInMillis = date.getTime();
				
				if(configClass.getBoundaryDistance() >= haversineDistance && System.currentTimeMillis()-timeInMillis < fixedTime) {
					double tempDistance = Math.pow(haversineDistance, -1);
					totalDistance += tempDistance;
					lastOrderedTime += System.currentTimeMillis()-date.getTime();
				}
			}
		}
		
		for(OrderDao order: orderList) {
			String lat1 = order.getrestaurant_location().split(",")[0].replaceAll("lat", "");
			String long1 = order.getrestaurant_location().split(",")[1].replaceAll("long", "");
			for(DeliveryExecutive de : deList) {
				String lat2 = de.getcurrent_location().split(",")[0].replaceAll("lat", "");
				String long2 = de.getcurrent_location().split(",")[1].replaceAll("long", "");
				int haversineDistance = HaversineDistance.getDistance(lat1, long1, lat2, long2);
				Date date = sdf.parse(de.getlast_order_delivered_time());
				long timeInMillis = date.getTime();
				if(configClass.getBoundaryDistance() >= haversineDistance && System.currentTimeMillis()-timeInMillis < fixedTime) {
					double orderWaitingTime = ((double)(System.currentTimeMillis() -sdf.parse(order.getordered_time()).getTime())/fixedTime)*configClass.getPriorityOrderTime();
					double rank = (((double)1/haversineDistance)/totalDistance)*configClass.getPriorityDist() ;
					rank += (((double)(System.currentTimeMillis()-date.getTime()))/lastOrderedTime)*configClass.getPriorityDETime();
					rank+= orderWaitingTime;
					heap.insert(new Assign(rank, order.getId(), de.getId()));
				}
			}
		}
		
		return heap;
		
	}
	
}
