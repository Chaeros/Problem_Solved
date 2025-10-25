import java.util.*;

class Solution {
    
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> deletedNode = new Stack<>();
        int[] pre=new int[n];
        int[] after=new int[n];
        boolean[] isExist=new boolean[n];
        String answer = "";
        
        for(int i=0;i<n;++i){
            pre[i]= i-1;
            after[i]=i+1;
            isExist[i]=true;
        }
        after[n-1]=-1;
        
        for(int i=0;i<cmd.length;++i){
            char command = cmd[i].charAt(0);
            if(command=='U'){
                int diff=Integer.parseInt(cmd[i].substring(2));
                while(diff-- > 0) k=pre[k]; 
            }
            else if(command=='D'){
                int diff=Integer.parseInt(cmd[i].substring(2));
                while(diff-- > 0) k=after[k];
            }
            else if(command=='C'){
                isExist[k]=false;
                deletedNode.push(k);
                
                if(after[k]!=-1) pre[after[k]]=pre[k];
                if(pre[k]!=-1) after[pre[k]]=after[k];
                
                if(after[k]==-1) k=pre[k];
                else k=after[k];
            }
            else if(command=='Z'){
                int node = deletedNode.pop();
                isExist[node]=true;
                
                if(pre[node]!=-1) after[pre[node]]=node;
                if(after[node]!=-1) pre[after[node]]=node;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(isExist[i] ? 'O' : 'X');
        }
        answer=sb.toString();
        
        return answer;
    }
}