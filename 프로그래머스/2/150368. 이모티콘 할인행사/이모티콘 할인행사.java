import java.lang.*;
import java.util.*;

class Solution {
    public int size;
    public int[][] originUsers;
    public int[] originEmoticons;
    public int temp[];
    public int result[];
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        size = emoticons.length;
        originUsers = users;
        originEmoticons = emoticons;
        temp = new int[size];
        result = new int[2];
        dfs(0);
        answer = result;
        return answer;
    }
    
    public void dfs(int depth){
        if ( depth == size ){
            int tempResult[] = calculate();
            if ( result[0] < tempResult[0] ){
                result = tempResult;
            }
            else if ( result[0] == tempResult[0] ){
                result[1] = Math.max(result[1],tempResult[1]);
            }
            return;
        }
        
        for ( int i = 1 ; i <= 4; ++i ){
            temp[depth] = i;
            dfs(depth+1);
        }
    }
    
    public int[] calculate(){
        int[] result = new int[2];
        for ( int[] user : originUsers ){
            int sum = 0;
            for ( int i = 0 ; i < originEmoticons.length ; ++i ){
                if ( user[0] <= temp[i]*10 ) sum += (originEmoticons[i] * ( 100 - temp[i] * 10 ) / 100 );
            }
            if ( sum >= user[1] ){
                ++result[0];
            }
            else result[1] += sum;
        }
        return result;
    }
}