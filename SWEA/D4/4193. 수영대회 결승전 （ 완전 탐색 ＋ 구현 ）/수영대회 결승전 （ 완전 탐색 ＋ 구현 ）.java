import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int count;
		public Node(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		@Override
		public int compareTo(Node o) {
			if (this.count < o.count ) return -1;
			return 1;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", count=" + count + "]";
		}
	}
	
	static int N;
	static int map[][];
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T; ++t ) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for ( int r = 0 ; r < N ; ++r ) {
				st = new StringTokenizer(br.readLine());
				for ( int c = 0 ; c < N ; ++c ) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			bw.write("#"+t+" "+dijkstar(startX, startY, endX, endY)+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int dijkstar( int startX, int startY, int endX, int endY ) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(startX, startY,0));
		boolean visited[][] = new boolean[N][N];
		visited[startX][startY] = true;
		while ( !q.isEmpty() ) {
			Node now = q.poll();
			if (now.x == endX && now.y == endY ) {
				return now.count;
			}
			for ( int d = 0 ; d < 4 ; ++d ) {
				int mx = now.x + dx[d];
				int my = now.y + dy[d];
				if ( mx < 0 || mx >= N || my < 0 || my >= N ) continue;
				if ( visited[mx][my] ) continue;
				if ( map[mx][my] == 1 ) continue;
				if ( map[mx][my] == 2 ) {
					for ( int i = 2 ; i <= now.count ; i += 3 ) {
						if ( now.count == i ) {
							q.offer(new Node(mx,my,now.count+1));
							visited[mx][my] = true;
							continue;
						}
					}
					q.offer(new Node(now.x,now.y,now.count+1));
					continue;
				}
				q.offer(new Node(mx,my,now.count+1));
				visited[mx][my] = true;
			}
		}
		return -1;
	}
}
