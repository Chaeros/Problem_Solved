class Solution {
    int max = -1;
    int[] res = new int[11];
    int[] ans = new int[]{-1};
    
    public int[] solution(int n, int[] info) {
        dfs(0,n,info);
        
        if(max==-1) return new int[]{-1};
        return ans;
    }
    
    public void dfs(int depth,int n,int[] info){
        if(depth==n){
            int result=compare(info);
            if(max<=result){
                max=result;
                ans = res.clone();
            }
            return;
        }
        for(int i=0;i<info.length && info[i]>=res[i] ;++i){
            res[i]+=1;
            dfs(depth+1,n,info);
            res[i]-=1;
        }
    }
    
    public int compare(int[] info){
        int apeach=0;
        int lion=0;
        for(int i=0;i<info.length;++i){
            if(info[i]==0 && res[i]==0) continue;
            else if(info[i]>=res[i]) apeach+=10-i;
            else if(info[i]<res[i]) lion+=10-i;
        }
        if(apeach>=lion) return -1;
        return lion-apeach;
    }
}