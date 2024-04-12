import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static int N,M;
	static int parents[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st; 
		
		int T = Integer.parseInt(br.readLine());
		
		for ( int t = 1 ; t <= T ; ++t ) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			makeParent();
			Set<Integer> set = new HashSet<>();
			for ( int i = 0 ; i < M ; ++i ) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			for ( int i = 1 ; i <= N ; ++i ) {
				set.add(find(i));
			}
			sb.append("#"+t+" "+set.size()+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void makeParent() {
		for ( int i = 1 ; i <= N ; ++i ) {
			parents[i] = i;
		}
	}
	
	private static int find(int x) {
		if ( x == parents[x] ) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if ( aRoot == bRoot ) return;
		if ( aRoot < bRoot ) {
			parents[bRoot] = aRoot;
		}
		else {
			parents[aRoot] = bRoot;
		}
	}
	
	
}