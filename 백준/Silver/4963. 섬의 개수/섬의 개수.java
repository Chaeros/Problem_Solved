import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int w,h;
	public static int map[][];
	public static boolean isVisited[][];
	
	public static int dx[] = {-1,-1,-1,0,1,1,1,0};
	public static int dy[] = {-1,0,1,1,1,0,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while( true ) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if ( w == 0 && h == 0 ) {
				break;
			}
			map = new int[h][w];
			isVisited = new boolean[h][w];
			
			for ( int r = 0 ; r < h ; ++r ) {
				st = new StringTokenizer(br.readLine());
				for ( int c = 0 ; c < w ; ++c ) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			for ( int r = 0 ; r < h ; ++r ) {
				for ( int c = 0 ; c < w ; ++c ) {
					if ( map[r][c] == 1 && !isVisited[r][c]) {
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
			int x = pos[0], y = pos[1];
			
			for ( int d = 0 ; d < 8 ; ++d ) {
				int mx = x + dx[d];
				int my = y + dy[d];
				
				if ( mx < 0 || mx >= h || my < 0 || my >= w ) continue;
				if ( isVisited[mx][my] ) continue;
				if ( map[mx][my] == 0 ) continue;
				isVisited[mx][my] = true;
				q.offer(new int[] {mx,my});
			}
		}
	}
}
