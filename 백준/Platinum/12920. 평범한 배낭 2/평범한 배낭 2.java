import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int weight;
		int value;
		public Node(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Node> list = new ArrayList<>();
		for ( int n = 0 ; n < N ; ++n ) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			for ( int i = number ; i > 0 ; i>>=1) {
				int x = i - (i>>1);
				list.add(new Node(weight*x,value*x));
			}
		}
		
		int d[] = new int[K+1];
		for ( Node node : list ) {
			for ( int i = K ; i > 0 ; --i ) {
				if ( i >= node.weight ) {
					d[i] = Math.max(d[i], d[i-node.weight] + node.value);
				}
			}
		}
		bw.write(d[K]+"\n");
		bw.flush();
		bw.close();
	}
}
