class Solution {
    public int[] solution(int[] sequence, int k) {
        int start=0;
        int end=0;
        int last=sequence.length;
        int currentSum=sequence[0];
        int resultStart=0;
        int resultEnd=sequence.length;
        while(start<last && end<last){
            if(currentSum==k){
                if((end-start)<(resultEnd-resultStart)){
                    resultStart=start;
                    resultEnd=end;
                }
                currentSum-=sequence[start];
                ++start;
            }
            else if(currentSum<k){
                ++end;
                if(end<last) currentSum+=sequence[end];
            }
            else{
                currentSum-=sequence[start];
                ++start;
            }
        }
        int[] answer = {resultStart,resultEnd};
        return answer;
    }
}