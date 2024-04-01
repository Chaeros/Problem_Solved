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
			if ( this.count < o.count ) return -1;
			return 1;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", count=" + count + "]";
		}
		
	}
	static int dx[] = {-1,0,1,0};
	static int dy[] = {0,1,0,-1};
	
	static int dx2[] = {-1,-1,1,1};
	static int dy2[] = {-1,1,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for ( int t = 1 ; t <= T; ++t ) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken())+100;
            int startY = Integer.parseInt(st.nextToken())+100;
            int endX = Integer.parseInt(st.nextToken())+100;
            int endY = Integer.parseInt(st.nextToken())+100;
            bw.write("#"+t+" "+dijkstra(startX, startY, endX, endY)+"\n");
        }
        bw.flush();
        bw.close();
	}
	
	public static int dijkstra(int startX, int startY, int endX, int endY ) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(startX,startY,0));
		boolean visited[][] = new boolean[202][202];
		visited[startX][startY] = true;
		
		while ( !q.isEmpty() ) {
			Node now = q.poll();
			if ( now.x == endX && now.y == endY ) return now.count;
			for ( int d = 0 ; d < 4 ; ++d ) {
				int mx = now.x + dx[d];
				int my = now.y + dy[d];
				if ( mx < 0 || mx >= 202 || my < 0 || my >= 202 ) continue;
				if ( mx == endX && my == endY ) {
					return now.count + 1;
				}
			}
			
			for ( int d = 0 ; d < 4 ; ++d ) {
				int mx = now.x + dx2[d];
				int my = now.y + dy2[d];
				if ( mx < 0 || mx >= 202 || my < 0 || my >= 202 ) continue;
				if ( visited[mx][my] ) continue;
				visited[mx][my] = true;
				q.offer(new Node(mx,my,now.count+2));
			}
		}
		return -1;
	}
}
