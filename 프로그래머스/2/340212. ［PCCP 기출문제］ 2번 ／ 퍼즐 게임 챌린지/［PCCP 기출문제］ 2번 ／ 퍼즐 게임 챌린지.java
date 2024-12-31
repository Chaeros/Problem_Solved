class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int left=1;
        int right=100000;
        
        while(left<=right){
            int mid=(left+right)/2;
            if(binarySearch(diffs,times,limit,mid)){
                answer=mid;
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        return answer;
    }
    
    public boolean binarySearch(int[] diffs, int[] times, long limit, int level){
        int length=diffs.length;
        long result=0;
        
        for(int i=0;i<length;++i){
            if(diffs[i]<=level){
                result+=times[i];
            }
            else{
                int mistakes = diffs[i] - level;
                int timePrev = i > 0 ? times[i - 1] : 0;
                result += mistakes * (times[i] + timePrev) + times[i];
            }
            
            if(result>limit){
                return false; 
            }
        }
        return true;
    }
}