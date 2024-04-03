import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static int M,N;
	public static int map[][];
	public static int dp[][];
	public static int resultCount = 0;
	
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,1,0,-1};
	
	public static boolean visited[][];
	public static boolean isSuccess[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		visited = new boolean[M][N];
		isSuccess = new boolean[M][N];
		
		for ( int r = 0 ; r < M ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 0 ; c < N ; ++c ) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[r], -1);
		}
		
		bw.write(dfs(0,0)+"\n");
		bw.flush();
		bw.close();
	}
	
	public static int dfs(int x, int y) {
		if ( x == M-1 && y == N-1 ) {
			return 1;
		}
		if ( dp[x][y] != -1 ) return dp[x][y];
		dp[x][y] = 0;
		for ( int d = 0 ; d < 4 ; ++d ) {
			int mx = x + dx[d];
			int my = y + dy[d];
			if ( mx < 0 || mx >= M || my < 0 || my >= N ) continue;
			if ( map[x][y] <= map[mx][my] ) continue;
			dp[x][y] += dfs(mx,my);
		}
		return dp[x][y];
	}
	
}
