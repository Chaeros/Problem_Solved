import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int[][] dp = new int[152][152];
        int maxAlp = 0;
        int maxCop = 0;
        
        for(int p=0;p<problems.length;++p)
        {
            maxAlp = Math.max(maxAlp,problems[p][0]);
            maxCop = Math.max(maxCop,problems[p][1]);
        }
        maxAlp=Math.max(maxAlp,alp);
        maxCop=Math.max(maxCop,cop);
        
        for(int a=0;a<=maxAlp;++a){
            for(int c=0;c<=maxCop;++c){
                dp[a][c]=999999999;
            }
        }
        
        dp[alp][cop] = 0;
        
        for(int a=alp;a<=maxAlp;++a)
        {
            for(int c=cop;c<=maxCop;++c)
            {
                dp[a+1][c]=Math.min(dp[a+1][c],dp[a][c]+1);
                dp[a][c+1]=Math.min(dp[a][c+1],dp[a][c]+1);
                for(int p=0;p<problems.length;++p)
                {
                    int alpVal=problems[p][0];
                    int copVal=problems[p][1];
                    int alpReward=problems[p][2];
                    int copReward=problems[p][3];
                    int cost=problems[p][4];
                    
                    if(a<alpVal || c<copVal) continue;
                    int nextAlp = Math.min(maxAlp,a+alpReward);
                    int nextCop = Math.min(maxCop,c+copReward);
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop],dp[a][c]+cost);
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}