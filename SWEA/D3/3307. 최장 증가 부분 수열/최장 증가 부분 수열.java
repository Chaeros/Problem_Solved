import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	static int N;
	static int arr[];
	static int resultArr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for ( int t = 1 ; t <= T ; ++t ) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for ( int i = 0 ; i < N ; ++i ) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			bw.write("#"+t+" "+lis()+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static int binaryLowerBoundSearch(int start, int end, int val) {
		
		while( start < end ) {
			
			int mid = ( start + end ) / 2;
			if ( resultArr[mid] < val) {
				start = mid + 1;
			}
			else {
				end = mid;
			}
		}
		return end;
	}
	
	public static int lis() {
		resultArr = new int[N+1];
		resultArr[0] = arr[0];
		int index = 1;
		for ( int i = 1 ; i < N ; ++i ) {
			int returnIndex = binaryLowerBoundSearch(0, index, arr[i]);
			resultArr[returnIndex]=arr[i];
			if ( returnIndex >= index ) {
				++index;
			}
		}
		return index;
	}
}
