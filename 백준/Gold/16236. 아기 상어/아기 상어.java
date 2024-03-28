import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int map[][];

    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};

    static int shark_x,shark_y,shark_size=2;
    static int sum=0;

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] bfs(){
        Queue<Node> q = new LinkedList<>();
        int visited[][] = new int[N][N];
        for(int i=0;i<N;++i){
            Arrays.fill(visited[i],-1);
        }
        visited[shark_x][shark_y]=0;
        q.offer(new Node(shark_x,shark_y));

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0;i<4;++i){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;

                if(map[nx][ny]<=shark_size && visited[nx][ny]==-1){
                    visited[nx][ny]=visited[now.x][now.y]+1;
                    q.offer(new Node(nx,ny));
                }
            }
        }

        return visited;
    }

    static boolean sol(int visited[][]){
        int minDistance=Integer.MAX_VALUE;
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                if(visited[i][j]>0){
                    if(minDistance>visited[i][j] && map[i][j]<shark_size && map[i][j]>0){
                        minDistance=visited[i][j];
                        shark_x=i;
                        shark_y=j;
                    }
                }
            }
        }
        if(minDistance==Integer.MAX_VALUE){
            return false;
        }
        else{
            map[shark_x][shark_y]=0;
            sum+=minDistance;
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for(int i=0;i<N;++i){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;++j){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==9){
                    shark_x=i;
                    shark_y=j;
                    map[i][j]=0;
                }
            }
        }

        int eating=0;
        while(true){
            if(!sol(bfs())){
                break;
            }
            else{
                ++eating;
            }

            if(eating==shark_size){
                ++shark_size;
                eating=0;
            }
        }
        bw.write(sum+"\n");
        bw.flush();
        bw.close();
    }
}
