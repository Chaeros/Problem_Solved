import java.util.*;

class Solution {
    
    private final int INF=9999999;
    
    public int solution(int x, int y, int n) {
        int dp[]=new int[1000001];
        
        Arrays.fill(dp,INF);
        
        dp[x]=0;
        
        for(int i=x;i<=y;++i){
            if(i-n>=x) dp[i]=Math.min(dp[i],dp[i-n]+1);
            if(i%2==0) dp[i]=Math.min(dp[i],dp[i/2]+1);
            if(i%3==0) dp[i]=Math.min(dp[i],dp[i/3]+1);
        }
        
        if(dp[y]==INF){
            return -1;
        }
        return dp[y];
    }
}