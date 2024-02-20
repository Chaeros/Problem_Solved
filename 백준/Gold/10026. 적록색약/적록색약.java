import java.io.*;

public class Main{
    static int N;

    static int dx[]={-1,0,1,0};
    static int dy[]={0,1,0,-1};

    static boolean dfs(int x, int y, int graph[][], boolean visited[][]){
        if(!visited[x][y]){
            visited[x][y]=true;
            for(int i=0;i<4;++i){
                int mx=x+dx[i];
                int my=y+dy[i];

                if(mx>=0 && mx<N && my>=0 && my<N){
                    if(!visited[mx][my] && graph[mx][my]==graph[x][y]){
                        dfs(mx,my,graph,visited);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int graph[][] = graph=new int[N][N];
        int graph_CW[][] = graph_CW=new int[N][N];
        boolean visited[][] = visited=new boolean[N][N];
        boolean visited_CW[][] = visited_CW=new boolean[N][N];

        for(int i=0;i<N;++i){
            String str=br.readLine();
            for(int j=0;j<N;++j){
                //적녹색약이 아닌 사람의 그래프
                if(str.charAt(j)=='R') graph[i][j]=1;
                else if(str.charAt(j)=='G') graph[i][j]=2;
                else if(str.charAt(j)=='B') graph[i][j]=3;

                //적녹색약 그래프
                if(str.charAt(j)=='B') graph_CW[i][j]=1;
                else graph_CW[i][j]=2;
            }
        }

        int count=0;
        int count_CW=0;
        for(int i=0;i<N;++i){
            for(int j=0;j<N;++j){
                if(dfs(i,j,graph,visited)) ++count;
                if(dfs(i,j,graph_CW,visited_CW)) ++count_CW;
            }
        }
        bw.write(count+"\n");
        bw.write(count_CW+"\n");
        bw.flush();
        bw.close();
    }
}