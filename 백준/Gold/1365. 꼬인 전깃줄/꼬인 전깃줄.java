import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int arr[];
	public static int resultArr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int n = 0 ; n < N ; ++n ) {
			int index = Integer.parseInt(st.nextToken())-1;
			arr[index] = n+1;
		}
		
		int size = 1;
		resultArr = new int[N];
		resultArr[0] = arr[0];
		for ( int i = 1 ; i < N ; ++i ) {
			int index = binarySearch(0, size, arr[i]);
			if ( index == size ) {
				resultArr[size++] = arr[i];
			}
			else {
				resultArr[index] = arr[i];
			}
		}
		System.out.println(N-size);
	}
	
	public static int binarySearch(int start, int end, int target) {
		while ( start < end ) {
			int mid = (start+end)/2;
			if ( resultArr[mid] >= target ) {
				end = mid;
			}
			else {
				start = mid+1;
			}
		}
		return end;
	}
}
