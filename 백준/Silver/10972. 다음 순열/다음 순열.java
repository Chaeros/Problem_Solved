import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

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
		
		if ( nextPermutation() ) {
			for ( int x : arr ) {
				bw.write(x+" ");
			}
		}
		else {
			bw.write("-1\n");
		}
		bw.flush();
		bw.close();
	}
	
	public static void swap(int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static boolean nextPermutation() {
		int i = N - 1;
		while ( i > 0 && arr[i-1] >= arr[i] ) {
			--i;
		}
		if ( i == 0 ) return false;
		
		int j = N - 1;
		while ( arr[j] <= arr[i-1] ) {
			--j;
		}
		swap(i-1,j);
		
		int k = N - 1;
		while ( i < k ) {
			swap(i++,k--);
		}
		
		return true;
	}
}
