import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int V, E;
	static int parent[];
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if ( this.weight < o.weight ) return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for ( int t = 1 ; t <= T ; ++t ) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			parent = new int[V+1];
			make();
			
			Edge[] edgeList = new Edge[E];
			for ( int i = 0 ; i < E ; ++i ) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from,to,weight);
			}
			Arrays.sort(edgeList);
			
			long sum = 0;
			int count = 0;
			for ( int i = 0 ; i < E ; ++i ) {
				Edge now = edgeList[i];
				if ( union(now.from,now.to) ) {
					sum += now.weight;
					if ( ++count == V-1 ) break;
				}
			}
			bw.write("#"+t+" "+sum+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void make() {
		for ( int i = 1 ; i <= V ; ++i ) {
			parent[i] = i;
		}
	}
	
	private static int find(int x) {
		if ( x == parent[x] ) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if ( aRoot == bRoot ) return false;
		if ( aRoot < bRoot ) {
			parent[bRoot] = aRoot;
		}
		else {
			parent[aRoot] = bRoot;
		}
		return true;
	}
}
