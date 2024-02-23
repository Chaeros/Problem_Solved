import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static int map[][];
	static int parent[];
	static boolean isVisited[][];
	static int dr[] = {-1,0,1,0};
	static int dc[] = {0,1,0,-1};
	static int islandCount = 0;
	static int minVal = Integer.MAX_VALUE;
	static int tempSum = 0;
	
	static class Bridge implements Comparable<Bridge> {
		int from;
		int to;
		int weight;

		public Bridge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Bridge o) {
			if ( this.weight < o.weight ) return -1;
			return 1;
		}

		@Override
		public String toString() {
			return "Bridge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	
	static int result[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		for ( int r = 0 ; r < N ; ++r ) {
			st = new StringTokenizer(br.readLine());
			for ( int c = 0 ; c < M ; ++c ) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for ( int r = 0 ; r < N ; ++r ) {
			for ( int c = 0 ; c < M ; ++c ) {
				if ( map[r][c] == 1 && !isVisited[r][c] ) {
					++islandCount;
					bfs(r,c);
				}
			}
		}
		parent = new int[islandCount+1];
		result = new int[islandCount-1];
		parentMake();
		generateBridge();
		Collections.sort(bridges);
		
		int resultSum = 0;
		for ( Bridge bridge : bridges ) {
			if ( find(bridge.from) != find(bridge.to) ) {
				union(bridge.from,bridge.to);
				resultSum += bridge.weight;
			}
		}
		
		if ( validateIsAllSameSet() ) bw.write(resultSum+"\n");
		else bw.write("-1\n");
		bw.flush();
		bw.close();
	}
	
	static PriorityQueue<Bridge> pq = new PriorityQueue<>();
	static List<Bridge> bridges = new ArrayList<>();
	private static void generateBridge() {
		for ( int r = 0 ; r < N ; ++r ) {
			for ( int c = 0 ; c < M ; ++c ) {
				if ( map[r][c] > 0 ) {
					outter : for ( int d = 0 ; d < 4 ; ++d ) {
						int length = 0;
						int mr = r;
						int mc = c;
						while( true ) {
							mr += dr[d];
							mc += dc[d];
							if ( mr < 0 || mr >= N || mc < 0 || mc >= M ) continue outter;
							if ( map[mr][mc] == map[r][c] ) continue outter;
							if ( map[mr][mc] > 0 ) break;
							++length;
						}
						if ( length <= 1 ) continue;
						bridges.add(new Bridge(map[r][c],map[mr][mc],length));
					}
				}
			}
		}
	}
	
	
	private static boolean validateIsAllSameSet() {
		int val = find(1);
		for ( int i =2 ; i <= islandCount ; ++i ) {
			if ( val != find(i) ) return false;
		}
		return true;
	}

	private static void bfs(int row, int col) {
		Queue<int []> q = new ArrayDeque<>();
		q.offer(new int[] {row,col});
		isVisited[row][col] = true;
		map[row][col] = islandCount;
		
		while ( !q.isEmpty() ) {
			int now[] = q.poll();
			for ( int d = 0 ; d < 4 ; ++d ) {
				int mr = now[0] + dr[d];
				int mc = now[1] + dc[d];
				if ( mr < 0 || mr >= N || mc < 0 || mc >= M ) continue;
				if ( map[mr][mc] != 1 ) continue;
				if ( isVisited[mr][mc] ) continue;
				isVisited[mr][mc] = true;
				map[mr][mc] = islandCount;
				q.offer(new int[] {mr,mc});
			}
		}
	}
	
	private static void parentMake() {
		for ( int i = 1 ; i <= islandCount ; ++i ) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if ( x == parent[x] ) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if ( aRoot == bRoot ) return;
		if ( aRoot < bRoot ) {
			parent[bRoot] = aRoot;
		}
		else {
			parent[aRoot] = bRoot;
		}
	}
}
