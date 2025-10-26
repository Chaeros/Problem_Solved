class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        int n=queue1.length;
        int[] arr=new int[n*2];
        long sum1=0;
        long sum2=0;
        
        for(int i=0;i<n;++i){
            arr[i]=queue1[i];
            arr[i+n]=queue2[i];
            sum1+=queue1[i];
            sum2+=queue2[i];
        }
        
        long target=(sum1+sum2)/2;
        int left=0;
        int right=n;
        
        int count=0;
        while(left<n*2 && right<n*2){
            if(sum1==target) return count;
            if(sum1>target){
                sum1-=arr[left++];
            }
            else if(sum1<target){
                sum1+=arr[right++];
            }
            ++count;
        }
        
        return -1;
    }
}