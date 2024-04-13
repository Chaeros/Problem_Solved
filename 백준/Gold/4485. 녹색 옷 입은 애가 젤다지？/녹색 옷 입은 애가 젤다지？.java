import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N = 1;
	static ArrayList<Node>[][] weights;
	static int distances[][];
	static int val[][];
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,1,0,-1};
	
	static class Node implements Comparable<Node>{
		int row;
		int col;
		int distance;
		public Node(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
		@Override
		public int compareTo(Node o) {
			if ( this.distance < o.distance ) return -1;
			return 1;
		}
		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + ", distance=" + distance + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = 1;
		while ( true ) {
			N = Integer.parseInt(br.readLine());
			if ( N == 0 ) break;
			weights = new ArrayList[N][N];
			distances = new int[N][N];
			val = new int[N][N];
			for ( int r = 0 ; r < N ; ++r ) {
				st = new StringTokenizer(br.readLine());
				for ( int c = 0 ; c < N ; ++c ) {
					val[r][c] = Integer.parseInt(st.nextToken());
					weights[r][c] = new ArrayList<Node>();
				}
				Arrays.fill(distances[r], Integer.MAX_VALUE);
			}
			
			for ( int r = 0 ; r < N ; ++r ) {
				for ( int c = 0 ; c < N ; ++c ) {
					for ( int d = 0 ; d < 4 ; ++d ) {
						int mr = r + dr[d];
						int mc = c + dc[d];
						if ( mr < 0 || mr >= N || mc < 0 || mc >= N ) continue;
						weights[r][c].add(new Node(mr,mc,val[mr][mc]));
					}
				}
			}
			dijkstra(0,0);
			sb.append("Problem "+t+": "+distances[N-1][N-1]+"\n");
			++t;
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void dijkstra(int startRow, int startCol) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(startRow,startCol,val[startRow][startCol]));
		boolean visited[][] = new boolean[N][N];
		visited[startRow][startCol] = true;
		distances[startRow][startCol] = val[startRow][startCol];
		while ( !pq.isEmpty() ) {
			Node now = pq.poll();
			visited[now.row][now.col] = true;
			if ( distances[now.row][now.col] < now.distance ) continue;
			for ( Node node : weights[now.row][now.col] ) {
				if ( visited[node.row][node.col] ) continue;
				if ( distances[node.row][node.col] > now.distance + node.distance ) {
					distances[node.row][node.col] = now.distance + node.distance;
					pq.offer(new Node(node.row,node.col,distances[node.row][node.col]));
				}
			}
		}
	}
}
