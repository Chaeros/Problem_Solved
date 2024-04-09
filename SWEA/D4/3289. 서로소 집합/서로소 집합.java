import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	static int n,m;
	static int parents[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for ( int t = 1 ; t <= T ; ++t ) {
			sb.append("#"+t+" ");
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1];
			makeParent();
			for ( int i = 0 ; i < m ; ++i ) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if ( command == 0 ) {
					union(a,b);
				}
				else if ( command == 1 ) {
					confirm(a,b);
				}
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void makeParent() {
		for ( int i = 1 ; i <= n ; ++i ) {
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
	
	private static void confirm(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if ( aRoot == bRoot ) sb.append("1");
		else sb.append("0");
	}
}
