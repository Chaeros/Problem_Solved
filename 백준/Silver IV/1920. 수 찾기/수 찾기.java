import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
	
	public static int N;
	public static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < M ; ++i ) {
			int val = Integer.parseInt(st.nextToken());
			bw.write(binarySearch(val,0,N-1)+"\n");
		}
		bw.flush();
		bw.close();
	}

	private static int binarySearch(int val, int start, int end) {
		if ( start <= end ) {
			int mid = ( start + end ) / 2;
			if ( arr[mid] == val ) return 1;
			if ( arr[mid] < val ) return binarySearch(val, mid+1, end);
			else return binarySearch(val, start, mid-1);
		}
		return 0;
	}
}
