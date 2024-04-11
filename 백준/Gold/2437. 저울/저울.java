import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int n = 0 ; n < N ; ++n ) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int count = 1;
		int sum = 0;
		int index = 0;
		while(true) {
			if ( sum < count ) {
				if ( index == N ) break;
				if ( arr[index] > count ) {
					break;
				}
				sum += arr[index++];
			}
			++count;
		}
		System.out.println(count);
	}
}
