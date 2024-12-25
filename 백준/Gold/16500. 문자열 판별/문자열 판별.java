import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String plainText=br.readLine();
		int N=Integer.parseInt(br.readLine());
		Set<String> set=new HashSet<String>();

		for(int n=0;n<N;++n) {
			String temp=br.readLine();
			set.add(temp);
		}
		
		int dp[]=new int[101];
		
		for(int i=plainText.length()-1;i>=0;--i) {
			for(int j=i+1;j<plainText.length();++j) {
				if(dp[j]==1 && set.contains(plainText.substring(i, j))){
					dp[i]=1;
				}
			}
			if(set.contains(plainText.substring(i))) {
				dp[i]=1;
			}
		}
		
		System.out.println(dp[0]);
	}
}
