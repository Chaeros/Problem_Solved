import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int inBound[] = new int[N+1];
		List<Integer>[] list = new ArrayList[N+1];
		for (int i = 1; i <= N; ++i) {
		    list[i] = new ArrayList<>();
		}
		
		for ( int i = 0 ; i < M ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			++inBound[b];
		}
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		for ( int i = 1 ; i <= N ; ++i ) {
			if ( inBound[i] == 0 ) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now+" ");
			
			for ( int val : list[now] ) {
				--inBound[val];
				if ( inBound[val] == 0 ) {
					q.offer(val);
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();		
	}
}
