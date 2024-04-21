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
	
	static int N;
	static int d[];
	static int count;
	static int result[];
	static List<Node> list = new ArrayList<>();
	
	static class Node implements Comparable<Node>{
		int value;
		int index;
		public Node(int value, int index) {
			this.value = value;
			this.index = index;
		}
		@Override
		public int compareTo(Node o) {
			if ( this.index < o.index ) return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		d = new int[N+1];
		count = 1;
		result = new int[1001];
		st = new StringTokenizer(br.readLine());
		d[0] = Integer.parseInt(st.nextToken());
		result[0] = d[0];
		for ( int n = 1 ; n < N ; ++n ) {
			int val = Integer.parseInt(st.nextToken());
			binarySearch(val);
		}
		bw.write(count+"\n");
		
		int currentIndx = count-1;
		for ( int i = list.size()-1 ; i >= 0 ; --i ) {
			if ( currentIndx == list.get(i).index ) {
				result[currentIndx] = list.get(i).value;
				--currentIndx;
			}
		}
		for ( int i = 0 ; i < count ; ++i ) {
			bw.write(result[i]+" ");
		}
		bw.flush();
		bw.close();
	} 
	
	public static void binarySearch(int num) {
		int start = 0;
		int end = count;
		while ( start < end ) {
			int mid = (start+end)/2;
			if ( d[mid] >= num ) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		d[end] = num;
		list.add(new Node(num,end));
		count = Math.max(count, end+1);
	}
}
