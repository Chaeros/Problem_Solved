import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int mapLen;
	static int map[][];
	static int startX, startY;
	static int destinationX, destinationY; 
	
	static class Position{
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int dx[] = {-1,-2,-2,-1,1,2,2,1};
	static int dy[] = {-2,-1,1,2,2,1,-1,-2};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T ; ++t ) {
			mapLen = Integer.parseInt(br.readLine());
			map = new int[mapLen][mapLen];
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			destinationX = Integer.parseInt(st.nextToken());
			destinationY = Integer.parseInt(st.nextToken());
			if ( startX == destinationX && startY == destinationY ) {
				bw.write("0\n");
			}
			else {
				bfs();
				bw.write(map[destinationX][destinationY]+"\n");
			}
			
		}
		bw.flush();
		bw.close();
	}
	
	static void bfs() {
		Queue<Position> q = new ArrayDeque<>();
		q.offer(new Position(startX,startY));
			
		while( !q.isEmpty() ) {
			Position now = q.poll();
			for ( int d = 0 ; d < 8 ; ++d ) {
				int mx = now.x + dx[d];
				int my = now.y + dy[d];
				if ( mx < 0 || mx >= mapLen || my < 0 || my >= mapLen ) continue;
				if ( map[mx][my] != 0 ) continue;
				map[mx][my] = map[now.x][now.y] + 1;
				q.offer(new Position(mx,my));
			}
		}
	}
}
