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
	
	static class Vertex implements Comparable<Vertex>{
		int no;
		int weight;
		
		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			if ( this.weight < o.weight ) return -1;
			return 1;
		}
	}
	
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
		generateBridge();
		
		boolean[] visited = new boolean[islandCount];
		int[] minEdge = new int[islandCount];
		Arrays.fill(minEdge,1,islandCount,Integer.MAX_VALUE);
		int count = 0;
		int resultSum = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0,0));
		
		while ( !pq.isEmpty() ) {
			Vertex current = pq.poll();
			if ( visited[current.no] ) continue;
			int minVertex = current.no;
			int minValue = current.weight;
			resultSum += minValue;
			visited[minVertex] = true;
			++count;
			if ( count == islandCount ) break;
			
			for ( int i = 0 ; i < bridges.get(minVertex).size() ; ++i ) {
				if ( !visited[bridges.get(minVertex).get(i).no] && bridges.get(minVertex).get(i).weight < minEdge[bridges.get(minVertex).get(i).no] ) {
					minEdge[bridges.get(minVertex).get(i).no] = bridges.get(minVertex).get(i).weight;
					pq.offer(new Vertex(bridges.get(minVertex).get(i).no,minEdge[bridges.get(minVertex).get(i).no]));
				}
			}
		}
		
		if ( isMST(minEdge) ) {
			bw.write(resultSum+"\n");
		}
		else{
			bw.write("-1\n");
		}
		bw.flush();
		bw.close();
	}
	
	
	private static boolean isMST(int[] minEdge) {
		for ( int i = 0 ; i < islandCount ; ++i ) {
			if ( minEdge[i] == Integer.MAX_VALUE ) return false;
		}
		return true;
	}


	static List<List<Vertex>> bridges = new ArrayList<>();
	private static void generateBridge() {
		for ( int r = 0 ; r <= islandCount ; ++r ) {
			bridges.add(new ArrayList<>());
		}
		
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
						bridges.get(map[r][c]-1).add(new Vertex(map[mr][mc]-1, length));
					}
				}
			}
		}
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
	
}
