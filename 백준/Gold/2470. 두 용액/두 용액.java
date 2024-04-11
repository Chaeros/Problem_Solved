import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int value;
		int minus;
		public Node(int value, int minus) {
			this.value = value;
			this.minus = minus;
		}
		@Override
		public int compareTo(Node o) {
			if ( this.value < o.value ) return -1;
			return 1;
		}
		@Override
		public String toString() {
			return "Node [value=" + value + ", minus=" + minus + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<Node> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for ( int n = 0 ; n < N ; ++n ) {
			int val = Integer.parseInt(st.nextToken());
			int sign = 1;
			if ( val < 0 ) sign = -1; 
			list.add(new Node(Math.abs(val),sign));
		}
		Collections.sort(list);
	
		int minDiff = Integer.MAX_VALUE;
		int result[] = new int[2];
		for ( int i = 0 ; i < N-1 ; ++i ) {
			int pre = list.get(i).value*list.get(i).minus;
			int after = list.get(i+1).value*list.get(i+1).minus;
			int diff = Math.abs( pre + after );
			if ( minDiff > diff ) {
				minDiff = diff;
				result[0] = pre;
				result[1] = after;
			}
		}
		Arrays.sort(result);
		bw.write(result[0]+" "+result[1]+"\n");
		bw.flush();
		bw.close();
	}
}
