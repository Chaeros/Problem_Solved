import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M,K;
	public static int map[][];
	public static boolean isVisited[][];
	
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 0 ; t < T; ++t ) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			isVisited = new boolean[N][M];
			
			for ( int k = 0 ; k < K ; ++k ) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[a][b] = 1;
			}
			
			int count = 0;
			for ( int r = 0 ; r < N ; ++r ) {
				for ( int c = 0 ; c < M ; ++c ) {
					if ( map[r][c] == 1 && !isVisited[r][c]) {
						isVisited[r][c] = true;
						bfs(r,c);
						++count;
					}
				}
			}
			
			bw.write(count+"\n");
		}
		bw.flush();
		bw.close();		
	}
	
	
	public static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			
			for ( int i = 0 ; i < 4 ; ++i ) {
				int mx = x + dx[i];
				int my = y + dy[i];
				
				if ( mx < 0 || mx >= N || my < 0 || my >= M ) continue;
				if ( isVisited[mx][my] ) continue;
				if ( map[mx][my] == 0 ) continue;
				isVisited[mx][my] = true;
				q.offer(new int[] {mx,my});
			}
		}
	}
}
