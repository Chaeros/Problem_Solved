import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N,M;
	public static boolean isWalled[][];
	public static boolean isVisited[][];
	public static int map[][];
	public static List<int[]> virus = new ArrayList<>();
	
	public static int dr[] = {-1,0,1,0};
	public static int dc[] = {0,1,0,-1};
	
	public static int virusCount = 0;
	public static int minVirusCount = Integer.MAX_VALUE;
	public static int wallCount = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isWalled = new boolean[N][M];
		isVisited = new boolean[N][M];
		
		for ( int r = 0 ; r < N ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 0 ; c < M ; ++c ) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if ( map[r][c] == 2 ) virus.add(new int[] {r,c});
				if ( map[r][c] == 1 ) ++wallCount;
			}
		}
		
		dfs(0,0);
		
		bw.write((N*M - wallCount - minVirusCount) + "\n");
		bw.flush();
		bw.close();		
	}
	
	public static void dfs(int depth,int index) {
		if ( depth == 3 ) {
			virusCount = virus.size();
			isVisited = new boolean[N][M];
			bfs();
			minVirusCount = Math.min(minVirusCount, virusCount);
			return;
		}
		
		for ( int n = index ; n < N*M ; ++n ) {
			int r = n / M;
			int c = n % M;
			if ( map[r][c] == 0) {
				map[r][c] = 1;
				dfs(depth+1,n+1);
				map[r][c] = 0;
			}
		}
		
	}
	
	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		for ( int[] pos : virus ) {
			q.offer(pos);
			isVisited[pos[0]][pos[1]] = true;
		}
		
		while ( !q.isEmpty() ) {
			int size = q.size();
			for ( int i = 0 ; i < size ; ++i ) {
				int[] cPos = q.poll();
				int row = cPos[0];
				int col = cPos[1];
				
				for ( int j = 0 ; j < 4 ; ++j ) {
					int mr = row + dr[j];
					int mc = col + dc[j];
					
					if ( mr < 0 || mr >= N || mc < 0 || mc >= M ) continue;
					if ( isVisited[mr][mc] ) continue;
					if ( map[mr][mc] != 0 ) continue;
					
					isVisited[mr][mc] = true;
					virusCount++;
					q.offer(new int[] {mr,mc});
				}
			}
		}
	}
}
