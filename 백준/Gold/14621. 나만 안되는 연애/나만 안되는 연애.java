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
	static char[] gender;
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int distance;
		public Node(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
		@Override
		public int compareTo(Node o) {
			if ( this.distance < o.distance ) return -1;
			return 1;
		}
		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + ", distance=" + distance + "]";
		}
	}
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		gender = new char[N];
		parent = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int n = 0 ; n < N ; ++n ) {
			gender[n] = st.nextToken().charAt(0);
			parent[n] = n;
		}
		
		List<Node> list = new ArrayList<>();
		for ( int m = 0 ; m < M ; ++m ) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			int distance = Integer.parseInt(st.nextToken());
			list.add(new Node(start,end,distance));
		}
		Collections.sort(list);
		
		int resultSum = 0;
		for ( Node node : list ) {
			if ( (findParent(node.start) != findParent(node.end)) && (gender[node.start] != gender[node.end]) ) {
				union(node.start, node.end);
				resultSum += node.distance;
			}
		}
		if ( isConnected() ) System.out.println(resultSum);
		else System.out.println("-1");
	}
	
	public static int findParent(int x) {
		if ( x == parent[x] ) return x;
		return parent[x] = findParent(parent[x]);
	}
	
	public static void union(int a, int b) {
		int aParent = findParent(a);
		int bParent = findParent(b);
		
		if ( aParent < bParent ) {
			parent[bParent] = aParent;
		}
		else {
			parent[aParent] = bParent;
		}
	}
	
	public static boolean isConnected() {
		int standard = findParent(parent[0]);
		for ( int x : parent ) {
			if ( standard != findParent(x) ) return false;
		}
		return true;
	}
}
