import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        PriorityQueue<int[]> deliveryQ = new PriorityQueue<> (
        	new Comparator<int[]>() {
    			@Override
    			public int compare(int[] o1, int[] o2) {
    				if ( o1[1] > o2[1] ) return -1;
                    return 1;
    			}
        	}
        );
        
        PriorityQueue<int[]> pickupQ = new PriorityQueue<>(
    		new Comparator<int[]>() {
    			@Override
    			public int compare(int[] o1, int[] o2) {
    				if ( o1[1] > o2[1] ) return -1;
                    return 1;
    			}
        	}
        );
        
        for ( int i = 0 ; i < n ; ++i ){
            if ( deliveries[i] != 0 ){
                deliveryQ.offer(new int[]{deliveries[i],i+1});
            }
            if ( pickups[i] != 0 ){
                pickupQ.offer(new int[]{pickups[i],i+1});   
            }
        }
        
        int order = 0;
        int currentPosition = 0;
        int deliverySum = Arrays.stream(deliveries).sum();
        int pickupSum = Arrays.stream(pickups).sum();
        while ( (deliverySum != 0 || pickupSum != 0) || ( order % 2 == 1 )){
            int tempSum = 0;
            if ( order % 2 == 0 ){
                while ( !deliveryQ.isEmpty() ){
                    if ( tempSum == cap ) break;
                    int val[] = deliveryQ.poll();
                    if ( tempSum + val[0] <= cap ){
                        tempSum += val[0];
                        deliverySum -= val[0];
                        currentPosition = Math.max(currentPosition,val[1]);
                    }
                    else {
                        int diff = cap - tempSum;
                        val[0] -= diff;
                        tempSum += diff;
                        deliveryQ.offer(val);
                        deliverySum -= diff;
                        currentPosition = Math.max(currentPosition,val[1]);
                    }
                }
            }
            else{
                while ( !pickupQ.isEmpty() ){
                    if ( tempSum == cap ) break;
                    int val[] = pickupQ.poll();
                    if ( tempSum + val[0] <= cap ){
                        tempSum += val[0];
                        pickupSum -= val[0];
                        currentPosition = Math.max(currentPosition,val[1]);
                    }
                    else {
                        int diff = cap - tempSum;
                        val[0] -= diff;
                        tempSum += diff;
                        pickupSum -= diff;
                        currentPosition = Math.max(currentPosition,val[1]);
                        pickupQ.offer(val);
                    }
                }
                answer += (currentPosition*2);
                currentPosition = 0;
            }
            ++order;
        }
        return answer;
    }
}