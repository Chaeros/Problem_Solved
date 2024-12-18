import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		int distances[][]=new int[N+1][N+1];
		int result[][]=new int[N+1][N+1];
		for(int a=1;a<=N;++a) {
			for(int b=1;b<=N;++b) {
				if(a==b) distances[a][b]=0;
				else {
					distances[a][b]=200000;
					result[a][b]=b;
				}
			}
		}
		
		for(int m=0;m<M;++m) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int cost=Integer.parseInt(st.nextToken());
			distances[a][b]=cost;
			distances[b][a]=cost;
			result[a][b]=b;
			result[b][a]=a;
		}
		
		for(int k=1;k<=N;++k) {
			for(int a=1;a<=N;++a) {
				int minRoute=50000;
				for(int b=1;b<=N;++b) {
					if(distances[a][b]>distances[a][k]+distances[k][b]) {
						distances[a][b]=distances[a][k]+distances[k][b];
						result[a][b]=result[a][k];
					}
				}
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for(int a=1;a<=N;++a) {
			for(int b=1;b<=N;++b) {
				if(result[a][b]==0) sb.append("-");
				else sb.append(result[a][b]);
				
				if(b!=N) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
