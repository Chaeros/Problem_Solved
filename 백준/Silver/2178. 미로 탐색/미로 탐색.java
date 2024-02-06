import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M;
	public static int map[][];
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,1,0,-1};
	public static boolean isVisited[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		for ( int r = 0 ; r < N ; ++r ) {
			String line = br.readLine();
			for ( int c = 0 ; c < M ; ++c ) {
				map[r][c] = Integer.parseInt(String.valueOf(line.charAt(c)));
			}
		}
		
		bfs();
		bw.write(map[N-1][M-1]+"\n");
		bw.flush();
		bw.close();		
	}
	
	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0});
		while ( !q.isEmpty() ) {
			int[] cPos = q.poll();
			int row = cPos[0];
			int col = cPos[1];
			int cVal = map[row][col];
			
			for ( int i = 0 ; i < 4 ; ++i ) {
				int mr = row + dx[i];
				int mc = col + dy[i];
				
				if ( mr < 0 || mr >= N || mc < 0 || mc >= M ) continue;
				if ( isVisited[mr][mc] ) continue;
				if ( map[mr][mc] != 1 ) continue;
				isVisited[mr][mc] = true;
				map[mr][mc] = cVal+1;
				q.offer(new int[] {mr,mc});
			}
		}
	}
}
