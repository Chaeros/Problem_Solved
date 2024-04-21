import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int map[][];
	static int INF = 99999999;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		for ( int i = 1 ; i <= n ; ++ i ) {
			for ( int j = 1 ; j <= n ; ++j ) {
				if ( i == j ) continue;
				map[i][j] = INF;
			}
		}
		for ( int i = 0 ; i < m ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[u][v] = 0;
			if ( b == 0 ) {
				map[v][u] = 1;
			}
			else if ( b == 1 ) {
				map[v][u] = 0;
			}
		}
		floydWarshall();
		int k = Integer.parseInt(br.readLine());
		for ( int i = 0 ; i < k ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			sb.append(map[start][end]+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void floydWarshall() {
		for ( int k = 1 ; k <= n ; ++k ) {
			for ( int i = 1 ; i <= n ; ++i ) {
				for ( int j = 1 ; j <= n ; ++j ) {
					map[i][j] = Math.min(map[i][k]+map[k][j], map[i][j]);
				}
			}
		}
	}
	
	public static boolean search(int start, int objective) {
		Queue<Integer> q  = new ArrayDeque<Integer>();
		q.offer(start);
		boolean visited[] = new boolean[n+1];
		visited[start] = true;
		while ( !q.isEmpty() ) {
			int now = q.poll();
			for ( int i = 1 ; i <= n ; ++i ) {
				if ( map[now][i]!= INF && !visited[i] ) {
					q.offer(i);
					visited[i] = true;
					if ( i == objective ) {
						return true;
					}
				}
			}
		}
		return false;
	}
}