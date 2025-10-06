import java.util.*;

class Solution {
    public List<Integer>[] list = new ArrayList[17];
    public int answer=1;
    
    public int solution(int[] info, int[][] edges) {
        for (int i = 0; i < 17; ++i) {
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<edges.length;++i){
            int parent=edges[i][0];
            int child=edges[i][1];
            list[parent].add(child);
        }
        Queue<Integer> q = new ArrayDeque<>();
        dfs(0,0,0,info,q);
        return answer;
    }
    
    public void dfs(int current, int sCount, int wCount, int[] info, Queue<Integer> path){
        if(info[current]==0) ++sCount;
        else ++wCount;
        
        if(sCount<=wCount) return;
        answer = Math.max(answer,sCount);
        Queue<Integer> q = new ArrayDeque<>(path);
        
        for(int x:list[current]){
            q.offer(x);
        }
        
        int size = q.size();
        for(int i=0;i<size;++i){
            int x=q.poll();
            dfs(x,sCount,wCount,info,q);
            q.offer(x);
        }
    }
}