import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	
	static int parent[];
	static class Node implements Comparable<Node>{
		int a;
		int b;
		double distance;

		public Node(int a, int b, double distance) {
			this.a = a;
			this.b = b;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			if ( this.distance < o.distance ) return -1;
			return 1;
		}

		@Override
		public String toString() {
			return "Node [a=" + a + ", b=" + b + ", distance=" + distance + "]";
		}
		
		
	}
	
	static int find(int x) {
		if ( parent[x] == x ) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);
		
		if ( aParent != bParent ) {
			if ( aParent < bParent ) {
				parent[bParent] = aParent;
			}
			else {
				parent[aParent] = bParent;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		List<Node> nodes = new ArrayList<>();
		
		int positions[][] = new int[N][2];
		for ( int n = 0 ; n < N ; ++n ) {
			parent[n] = n;
			st = new StringTokenizer(br.readLine());
			positions[n][0] = Integer.parseInt(st.nextToken());
			positions[n][1] = Integer.parseInt(st.nextToken());
		}
		
		for ( int i = 0 ; i < N ; ++i ) {
			for ( int j = i+1 ; j < N ; ++j ) {
				double distance = Math.sqrt(Math.pow(positions[i][0]-positions[j][0],2) 
						+ Math.pow(positions[i][1]-positions[j][1],2));
				nodes.add(new Node(i,j,distance));
			}
		}
		Collections.sort(nodes);
		
		double result = 0;
		for ( int m = 0 ; m < M ; ++m ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			union(a,b);
		}
		
		int count=M;
		for ( Node node : nodes ) {
			int a = node.a;
			int b = node.b;
			if ( find(a) != find(b) ) {
				union(a,b);
				result += node.distance;
				++count;
			}
		}
		
		bw.write(String.format("%.2f", result)+"\n");
		bw.flush();
		bw.close();
	}
}
