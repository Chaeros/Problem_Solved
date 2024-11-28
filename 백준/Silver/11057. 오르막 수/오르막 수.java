import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static int dp[][]=new int[1001][10];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		
		for(int i=0;i<=9;++i) {
			dp[1][i]=1;
		}
		
		if(N!=1) {
			for(int n=2;n<=N;++n) {
				for(int x=0;x<10;++x) {
					dp[n][x]=dpSum(n-1,x);
				}
			}
		}
		
		System.out.println(dpSum(N,0));
		
	}
	
	public static int dpSum(int index, int startNumber) {
		int result = 0;
		for(int n=startNumber;n<10;++n) {
			result=(result+dp[index][n])%10007;
		}
		return result;
	}
}
