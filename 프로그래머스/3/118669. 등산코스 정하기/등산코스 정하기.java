import java.util.*;

class Solution {
    class Node{
        int index;
        int cost;
        Node(int index, int cost){
            this.index=index;
            this.cost=cost;
        }
    }
    
    List<Node>[] graph;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n+1];
        
        for(int i=0;i<=n;++i){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<paths.length;++i){
            int aNode=paths[i][0];
            int bNode=paths[i][1];
            int weight=paths[i][2];
            
            if(isGate(aNode,gates) || isSummit(bNode,summits))
            {
                graph[aNode].add(new Node(bNode,weight));
            }
            else if(isGate(bNode,gates) || isSummit(aNode,summits))
            {
                graph[bNode].add(new Node(aNode,weight));
            }
            else
            {
                graph[aNode].add(new Node(bNode,weight));
                graph[bNode].add(new Node(aNode,weight));
            }
        }
        
        int[] answer = getResult(n,gates,summits);
        
        return answer;
    }
    
    public int[] getResult(int N, int[] gates, int[] summits){
        Queue<Node> q = new ArrayDeque<>();
        int[] intensities = new int[N+1];
        
        Arrays.fill(intensities,Integer.MAX_VALUE);
        for(int i=0;i<gates.length;++i){
            q.offer(new Node(gates[i],0));
            intensities[gates[i]]=0;
        }
        
        int mountainNo = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;
        
        while(!q.isEmpty())
        {
            Node now = q.poll();
            if(now.cost>intensities[now.index]) continue;
            
            for(int i=0;i<graph[now.index].size();++i){
                Node next = graph[now.index].get(i);
                if(intensities[next.index]>Math.max(intensities[now.index],next.cost))
                {
                    intensities[next.index]=Math.max(intensities[now.index],next.cost);
                    q.offer(new Node(next.index,intensities[next.index]));
                }
            }
        }
        
        Arrays.sort(summits); // 만약 intensity가 같은 경우 높은 수의 산봉우리 선택을 위해 정렬
        for(int summit:summits)
        {
            if(intensities[summit]<minIntensity)
            {
                minIntensity = intensities[summit];
                mountainNo = summit;
            }
        }
        return new int[]{mountainNo, minIntensity};
    }
    
    public boolean isGate(int index, int[] gates){
        for(int gate:gates){
            if(index==gate) return true;
        }
        return false;
    }
    
    public boolean isSummit(int index, int[] summits)
    {
        for(int summit:summits){
            if(index==summit) return true;
        }
        return false;
    }
}