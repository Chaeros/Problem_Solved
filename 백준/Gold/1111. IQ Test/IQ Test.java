import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int n = 0 ; n < N ; ++n ) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		if ( N == 1 ) {
			System.out.println("A");
			return;
		}
		if ( N == 2 ) {
			if ( arr[0] == arr[1] ) {
				System.out.println(arr[0]);
			}
			else System.out.println("A");
			return;
		}
		int a = 0;
		if ( arr[1]-arr[0] != 0 ) a = (arr[2]-arr[1]) / (arr[1]-arr[0]);
		int b=arr[1] - arr[0]*a;
		for ( int n = 2 ; n < N ; ++n ) {
			if ( arr[n-1]*a+b != arr[n] ) {
				System.out.println("B");
				return;
			}
		}
		System.out.println(arr[N-1]*a+b);
	}
}
