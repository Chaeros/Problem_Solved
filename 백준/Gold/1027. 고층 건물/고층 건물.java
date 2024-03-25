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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int count[] = new int[N];
		Arrays.fill(count, 2);
		count[0] -=1;
		count[N-1] -= 1;
		for ( int i = 0 ; i < N ; ++i ) {
			for ( int j = i + 1 ; j < N ; ++ j ) {
				double m = (double)(arr[j]-arr[i]) / (j - i);
				for ( int k = i+1 ; k <= j-1 ; ++k ) {
					if ( m*k - m*i + arr[i] <= arr[k] ) {
						break;
					}
					if ( k == j-1 ) {
						++count[i];
						++count[j];
					}
				}
			}
		}
		Arrays.sort(count);
		bw.write(count[N-1]+"\n");
		bw.flush();
		bw.close();
	}
}
