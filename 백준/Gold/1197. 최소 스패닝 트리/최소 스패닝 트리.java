import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static int V,E;
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			if ( this.weight < o.weight ) return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		ArrayList<Node>[] list = new ArrayList[V+1];
		for ( int i = 0 ; i <= V; ++i ) {
			list[i] = new ArrayList<Node>();
		}
		for ( int i = 0 ; i < E; ++i ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
		}
		
		boolean visited[] = new boolean [V+1];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(1,0));
		
		int sum = 0;
		while ( !pq.isEmpty() ) {
			Node now = pq.poll();
			
			if ( visited[now.vertex] ) continue;
			
			visited[now.vertex] = true;
			sum += now.weight;
			
			for ( Node node : list[now.vertex] ) {
				if ( !visited[node.vertex] ) {
					pq.offer(node);
				}
			}
		}
		bw.write(sum+"\n");
		bw.flush();
		bw.close();
	}
}
