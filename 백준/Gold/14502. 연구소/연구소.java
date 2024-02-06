import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int map[][];
    static int resultMaxVal=0;
    static boolean visited[];
    static int wall[] = new int[3];
    static Queue<Node> startQ = new LinkedList<>();

    static class Node{
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static void dfs(int index, int depth){

        if(depth==3){
            bfs();
            return;
        }

        for(int i=index;i<N*M;++i){
            int row = i/M;
            int col = i%M;

            if(!visited[i] && map[row][col]==0){
                visited[i]=true;
                wall[depth]=i;
                dfs(i+1,depth+1);
                visited[i]=false;
            }
        }
    }

    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};

    static void bfs(){
        Queue<Node> q = new LinkedList<>(startQ);
        int tempMap[][] = new int[N][M];
        for(int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                tempMap[i][j]=map[i][j];
            }
        }
        boolean tempVisited[][] = new boolean[N][M];

        for(int i=0;i<3;++i){
            int row = wall[i]/M;
            int col = wall[i]%M;
            tempMap[row][col]=1;
        }

        while(!q.isEmpty()){
            Node node = q.poll();
            tempVisited[node.row][node.col]=true;

            for(int i=0;i<4;++i){
                int mx = node.row+dx[i];
                int my = node.col+dy[i];

                if(mx<0 || mx>=N || my<0 || my>=M) continue;
                if(!tempVisited[mx][my] && tempMap[mx][my]==0){
                    tempMap[mx][my]=2;
                    q.offer(new Node(mx,my));
                }
            }
        }
        countSafeArea(tempMap);
    }

    static void countSafeArea(int testMap[][]){
        int sum=0;
        for(int i=0;i<N;++i){
            for(int j=0;j<M;++j){
                if(testMap[i][j]==0) ++sum;
            }
        }
        resultMaxVal = Math.max(resultMaxVal,sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N*M];

        for(int i=0;i<N;++i){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;++j){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==2) startQ.add(new Node(i,j));
            }
        }

        dfs(0,0);

        bw.write(resultMaxVal+"\n");
        bw.flush();
        bw.close();
    }
}
