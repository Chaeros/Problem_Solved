import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static class Node implements Comparable<Node>{
		int destination;
		int weight;
		public Node(int destination, int weight) {
			this.destination = destination;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			if(this.weight<o.weight) return -1;
			return 1;
		}
	}
	
	static int N,M,X;
	static List<Node>[] list, reverseList;
	static int[] dist, reverseDist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		reverseList = new ArrayList[N+1];
		dist = new int[N+1];
		reverseDist = new int[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(reverseDist, Integer.MAX_VALUE);
		
		for(int n=0;n<N+1;++n) {
			list[n]= new ArrayList<>();
			reverseList[n]= new ArrayList<>();
		}
		
		for (int m=0;m<M;++m) {
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int start = Integer.parseInt(st.nextToken());
				int destination = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				list[start].add(new Node(destination,weight));
				reverseList[destination].add(new Node(start,weight));
			}
		}
		
		dijkstra(X);
		reverseDijkstra(X);
		
		int result = 0;
		for(int n=1;n<=N;++n) {
			if(n==X) continue;
			int temp = dist[n]+reverseDist[n];
			result = Math.max(result, temp);
		}
		bw.write(result+"\n");
		bw.flush();
		bw.close();
	}
	
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start,0));
		boolean isVisited[] = new boolean[N+1];
		dist[start]=0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(isVisited[now.destination]) {
				continue;
			}
			isVisited[now.destination]=true;
			for(Node node:list[now.destination]) {
				int newDist=now.weight+node.weight;
				if(dist[node.destination]>newDist) {
					dist[node.destination]=newDist;
					pq.offer(new Node(node.destination,dist[node.destination]));
				}
			}
		}
	}
	
	public static void reverseDijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start,0));
		boolean isVisited[] = new boolean[N+1];
		reverseDist[start]=0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(isVisited[now.destination]) {
				continue;
			}
			isVisited[now.destination]=true;
			for(Node node:reverseList[now.destination]) {
				if(isVisited[node.destination]) continue;
				int newDist=now.weight+node.weight;
				if(reverseDist[node.destination]>newDist) {
					reverseDist[node.destination]=newDist;
					pq.offer(new Node(node.destination,reverseDist[node.destination]));
				}
			}
		}
	}
}
