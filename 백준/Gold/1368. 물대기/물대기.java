import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Node implements Comparable<Node>{
		int number;
		int cost;
		public Node(int number, int cost) {
			this.number = number;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
		@Override
		public String toString() {
			return "Node [number=" + number + ", cost=" + cost + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		List<List<Node>> list=new ArrayList<>();
		PriorityQueue<Node> pq=new PriorityQueue<>();
		
		int N=Integer.parseInt(br.readLine());
		
		for(int n=0;n<=N;++n) {
			list.add(new ArrayList<>());
		}
		
		for(int n=1;n<=N;++n) {
			int x=Integer.parseInt(br.readLine());
			Node node=new Node(n,x);
			list.get(n).add(node);
			pq.offer(node);
		}
		Node startNode=pq.poll();
		list.get(startNode.number).remove(0);
		
		for(int n=1;n<=N;++n) {
			st=new StringTokenizer(br.readLine());
			for(int o=1;o<=N;++o) {
				int x=Integer.parseInt(st.nextToken());
				if(n==o) continue;
				Node node=new Node(o,x);
				list.get(n).add(node);
			}
		}
		
		pq.offer(startNode);
		boolean visit[]=new boolean[N+1];
		
		int result=0;
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			if(visit[now.number]) continue;
			visit[now.number]=true;
			result+=now.cost;

			for(Node node:list.get(now.number)) {
				pq.offer(node);
			}
		}
		System.out.println(result);
	}
}
