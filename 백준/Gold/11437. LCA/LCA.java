import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M;
	static List<List<Integer>> list = new ArrayList<>();
	static int parents[];
	static int depths[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		depths = new int[N+1];
		visited = new boolean[N+1];
		for ( int i = 0 ; i <= N ; ++i ) {
			list.add(new ArrayList<Integer>());
		}
		
		for ( int i = 0 ; i < N-1 ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		parents[1] = 1;
		dfs(1,0);
		
		M = Integer.parseInt(br.readLine());
		for ( int i = 0 ; i < M ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(getLCA(a,b)+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int val, int depth) {
		depths[val] = depth;
		visited[val] = true;
		for ( int i = 0 ; i < list.get(val).size() ; ++i ) {
			int nextVal = list.get(val).get(i);
			if ( !visited[nextVal] ) {
				parents[nextVal] = val;
				dfs(nextVal,depth+1);
			}
		}
	}
	
	private static int getLCA(int a, int b) {
		while ( depths[a] != depths[b] ) {
			if ( depths[a] < depths[b] ) {
				b = parents[b];
			}
			else if ( depths[a] > depths[b] ){
				a = parents[a];
			}
		}
		while ( a != b ) {
			a = parents[a];
			b = parents[b];
		}
		return a;
	}
}
