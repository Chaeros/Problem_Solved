import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		
		int result[]=new int[N];
		int leftCount[]=new int[N];
		
		for(int i=0;i<N;++i) {
			leftCount[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<N;++i) {
			int spaceCount=leftCount[i];
			for(int j=0;j<N;++j) {
				if(result[j]==0) {
					if(spaceCount==0) {
						result[j]=i+1;
						break;
					}
					--spaceCount;
				}
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int n=0;n<N;++n) {
			sb.append(result[n]).append(" ");
		}
		System.out.println(sb.toString());
	}
}