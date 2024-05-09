import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node>{
        private int number;
        private int seconds;

        public Node(int number, int seconds) {
            this.number = number;
            this.seconds = seconds;
        }

        public int compareTo(Node o){
            if(this.seconds<o.seconds) return -1;
            else return 1;
        }
    }

    static int distance[];
    static List<ArrayList<Node>> list = new ArrayList<>();
    static final int INF =(int)1e9;

    static void dijkstra(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start,0));
        distance[start]=0;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(distance[now.number]<now.seconds) continue;

            for(Node x:list.get(now.number)){
                if(distance[x.number]>distance[now.number]+x.seconds){
                    distance[x.number]=distance[now.number]+x.seconds;
                    q.add(new Node(x.number,distance[x.number]));
                }
            }
        }
    }

    static int infectedComNum(){
        int count=0;
        for(int x:distance){
            if(x!=INF){
                ++count;
            }
        }
        return count;
    }

    static int infectedTime(){
        int maxTime=0;
        for(int x:distance){
            if(x!=INF){
                maxTime=Math.max(maxTime,x);
            }
        }
        return maxTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;++t){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            distance = new int[n+1];
            list = new ArrayList<>();
            for(int i=0;i<=n;++i){
                list.add(new ArrayList<>());
                distance[i]=INF;
            }

            for(int i=0;i<d;++i){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                list.get(b).add(new Node(a,s));
            }
            dijkstra(c);

            bw.write(infectedComNum()+" "+infectedTime()+"\n");
            list.clear();
        }
        bw.flush();
        bw.close();
    }
}
