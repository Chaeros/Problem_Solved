import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M,N;
    static int graph[][];
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};

    static boolean visited[][];
    static int dayCount=0;
    static class Node{
        int x;
        int y;

        public Node(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    static void bfs(ArrayList<Node> nodes){
        Queue<Node> q = new LinkedList<>();
        for(Node node:nodes){
            q.offer(node);
        }

        while(!q.isEmpty()){
            int roundQ = q.size();
            for(int k=0;k<roundQ;++k){
                Node nowNode = q.poll();

                for(int i=0;i<4;++i){
                    int mx = nowNode.x+dx[i];
                    int my = nowNode.y+dy[i];

                    if(mx>=0 && mx<N && my>=0 && my<M){
                        if(graph[mx][my]==0){
                            graph[mx][my]=1;
                            q.offer(new Node(mx,my));
                        }
                    }
                }
            }
            ++dayCount;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];

        ArrayList<Node> startNodes = new ArrayList<>();
        for(int i=0;i<N;++i){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;++j){
                graph[i][j]=Integer.parseInt(st.nextToken());
                if(graph[i][j]==1) startNodes.add(new Node(i,j));
            }
        }

        bfs(startNodes);

        boolean allClear=true;
        outter :for(int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                if(graph[i][j]==0){
                    allClear=false;
                    break outter;
                }
            }
        }
        if(allClear) bw.write(dayCount-1+"\n");
        else bw.write(-1+"\n");
        bw.flush();
        bw.close();
    }
}