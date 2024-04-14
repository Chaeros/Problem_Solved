import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<Integer>[] list = new LinkedList[N+1];
		for ( int i = 0 ; i < N+1 ; ++i ) {
			list[i] = new LinkedList<>();
		}
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		for ( int n = 0 ; n < N ; ++n ) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			list[deadline].add(count);
			set.add(deadline);
		}
		
		int resultSum = 0;
		PriorityQueue<Integer> pQ = new PriorityQueue<>((o1,o2)->o2-o1);
		for ( int t = N ; t >= 1 ; --t ) {
			if ( set.contains(t) ) {
				for ( int x : list[t] ) {
					pQ.add(x);
				}
			}
			if ( !pQ.isEmpty() ) {
				resultSum += pQ.poll();
			}
		}
		System.out.println(resultSum);
	}
}