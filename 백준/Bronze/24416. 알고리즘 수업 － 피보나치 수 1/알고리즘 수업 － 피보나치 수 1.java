import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static int cnt1 = 1;
	public static int cnt2 = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		fib(n);
		fib2(n);
		bw.write(cnt1 + " " + cnt2 +"\n");
		bw.flush();
		bw.close();
	}
	
	public static int fib(int n) {
		if ( n == 1 || n == 2 ) return 1;
		else{
			cnt1++;
			return fib(n-1) + fib(n-2);
		}
	}
	
	public static void fib2(int n) {
		int d[] = new int[41];
		d[0] = 1;
		d[1] = 1;
		for ( int i = 2 ; i <n ; ++i ) {
			d[i] = d[i-1] + d[i-2];
			cnt2++;
		}
	}
}
