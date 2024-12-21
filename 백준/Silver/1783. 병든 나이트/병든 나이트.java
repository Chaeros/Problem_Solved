import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		long M=Long.parseLong(st.nextToken());
		
		if(N==1) {
			System.out.println(1);
		} else if(N==2) {
			if(M<7) {
				System.out.println((M+1)/2);
			}
			else {
				System.out.println(4);
			}
		} else if(N>=3) {
			if(M<=4) {
				System.out.println(M);
			} else if(M==5 || M==6) {
				System.out.println(4);
			} else {
				System.out.println(M-2);
			}
		}
	}
}
