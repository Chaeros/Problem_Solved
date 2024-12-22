import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		
		PriorityQueue<String> pQ=new PriorityQueue<>((o1,o2)-> (o2+o1).compareTo(o1+o2));
		boolean isZero=true;
		for(int n=0;n<N;++n) {
			String str=st.nextToken();
			pQ.offer(str);
			if(!str.equals("0")) {
				isZero=false;
			}
		}
		
		if(isZero) {
			System.out.println(0);
		}
		else {
			StringBuilder sb=new StringBuilder();
			for(int n=0;n<N;++n) {
				sb.append(pQ.poll());
			}
			
			System.out.println(sb.toString());
		}
	}
}
