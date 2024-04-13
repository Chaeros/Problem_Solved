import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N,M;
	public static ArrayList<Integer>[] list;
	public static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		for ( int i = 0 ; i <= N ; ++i ) {
			list[i] = new ArrayList<Integer>();
		}
		for ( int i = 0 ; i < N ; ++i ) {
			st = new StringTokenizer(br.readLine());
			for ( int j = 0 ; j < N ; ++j ) {
				int val = Integer.parseInt(st.nextToken());
				if ( val == 1 ) {
					list[i+1].add(j+1);
					list[j+1].add(i+1);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int startPoint = Integer.parseInt(st.nextToken());
		prim(startPoint);
		boolean isSuccess = true;
		for ( int m = 1 ; m < M ; ++m ) {
			int destination = Integer.parseInt(st.nextToken());
			if ( !visited[destination] ) {
				isSuccess = false;
				break;
			}
		}
		if ( isSuccess ) bw.write("YES\n");
		else bw.write("NO\n");
		bw.flush();
		bw.close();
	}
	
	public static void prim(int start) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(start);
		while ( !q.isEmpty() ) {
			int now = q.poll();
			if ( visited[now] ) continue;
			visited[now] = true;
			
			for ( int city : list[now] ) {
				if ( !visited[city] ) {
					q.offer(city);
				}
			}
		}
	}
}
