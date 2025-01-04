import java.util.*;

class Solution {
    
    public long[] counter;
    
    public long solution(int[] weights) {
        long answer = 0;
        
        counter=new long[4001];
        TreeSet<Integer> treeSet=new TreeSet<>();
        
        for(int i=0;i<weights.length;++i){
            ++counter[weights[i]];
            treeSet.add(weights[i]);
        }
        
        for(int x:treeSet){
            answer+=counter[x]*(counter[x]-1)/2;
            if(x*4%2==0) answer+=counter[x]*counter[x*4/2];
            if(x*4%3==0) answer+=counter[x]*counter[x*4/3];
            if(x*3%2==0) answer+=counter[x]*counter[x*3/2];
        }
        return answer;
    }
}