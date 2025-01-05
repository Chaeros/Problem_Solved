import java.util.*;

class Solution {
    public class Node implements Comparable<Node>{
        int minute;
        int inOut;
        Node(int minute, int inOut){
            this.minute=minute;
            this.inOut=inOut;
        }
        @Override
        public int compareTo(Node o){
            if(this.minute==o.minute){
                return this.inOut-o.inOut;
            }
            return this.minute-o.minute;
        }
    }
    
    public int solution(String[][] book_time) {
        List<Node> list=new ArrayList<>();
        for(String[] time:book_time){
            int startTime=hourToMinute(time[0]);
            int endTime=hourToMinute(time[1])+10;
            list.add(new Node(startTime,1));
            list.add(new Node(endTime,-1));
        }
        Collections.sort(list);
        
        int currentCount=0;
        int maxCount=0;
        for(Node now:list){
            currentCount+=now.inOut;
            maxCount=Math.max(currentCount,maxCount);
        }
        
        return maxCount;
    }
    
    
    public int hourToMinute(String time){
        String part[]=time.split(":");
        return (Integer.parseInt(part[0])*60)+Integer.parseInt(part[1]);
    }
}