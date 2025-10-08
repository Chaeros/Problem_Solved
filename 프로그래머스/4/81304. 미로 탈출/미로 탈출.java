import java.util.*;

class Solution {
    public class Node implements Comparable<Node>{
        int cost;
        int destination;
        int state;
        Node(int cost, int destination, int state){
            this.cost=cost;
            this.destination=destination;
            this.state=state;
        }
        @Override
        public int compareTo(Node other){
            if(this.cost<other.cost) return -1;
            return 1;
        }
    }
    
    List<Node>[] adj=new ArrayList[1001];
    List<Node>[] revAdj=new ArrayList[1001];
    int[][] dist=new int[1001][1024];
    int[] trapIndex=new int[1001];
    int INF=999999999;
    
    boolean isContained(int state, int index){
        return (((1<<trapIndex[index]) & state) != 0);
    }
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps)     {
        for(int i=1;i<=n;++i){
            for(int j=0;j<1024;++j){
                dist[i][j]=INF;
            }
        }
        
        for(int i=0;i<1001;++i){
            adj[i]=new ArrayList<>();
            revAdj[i]=new ArrayList<>();
        }
        
        for(int i=1;i<=n;++i) trapIndex[i]=-1;
        for(int i=0;i<traps.length;++i) trapIndex[traps[i]]=i;
        
        for(int i=0;i<roads.length;++i){
            int s=roads[i][0];
            int e=roads[i][1];
            int c=roads[i][2];
            
            adj[s].add(new Node(c,e,0));
            revAdj[e].add(new Node(c,s,0));
        }
        
        PriorityQueue<Node> pq=new PriorityQueue<>();
        dist[start][0]=0;
        pq.offer(new Node(dist[start][0],start,0));
        
        while(!pq.isEmpty()){
            Node current=pq.poll();
            if(current.destination==end) return current.cost;
            if(dist[current.destination][current.state]!=current.cost) continue;
            for(int i=0;i<adj[current.destination].size();++i){
                Node next=adj[current.destination].get(i);
                int result=0;
                if(trapIndex[current.destination]!=-1 && isContained(current.state,current.destination)) result^=1;
                if(trapIndex[next.destination]!=-1 && isContained(current.state,next.destination)) result^=1; 
                if(result!=0) continue;
                
                int nextState=current.state;
                if(trapIndex[next.destination]!=-1) nextState^=(1<<trapIndex[next.destination]);
                if(dist[next.destination][nextState]>next.cost+current.cost){
                    dist[next.destination][nextState]=next.cost+current.cost;
                    pq.offer(new Node(
                        dist[next.destination][nextState],
                        next.destination,
                        nextState));
                }
            }
            
            for(int i=0;i<revAdj[current.destination].size();++i){
                Node next=revAdj[current.destination].get(i);
                int result=0;
                if(trapIndex[current.destination]!=-1 && isContained(current.state,current.destination)) result^=1;
                if(trapIndex[next.destination]!=-1 && isContained(current.state,next.destination)) result^=1;
                if(result!=1) continue;
                
                int nextState=current.state;
                if(trapIndex[next.destination]!=-1) nextState^=(1<<trapIndex[next.destination]);
                if(dist[next.destination][nextState]>next.cost+current.cost){
                    dist[next.destination][nextState]=next.cost+current.cost;
                    pq.offer(new Node(
                        dist[next.destination][nextState],
                        next.destination,
                        nextState));
                }
            }
        }
        return -1;
    }
}