import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static int cityCount, busCount;
	public static List<List<Node>> list=new ArrayList<>();
	public static int distance[];
	public static class Node implements Comparable<Node>{
		int start;
		int end;
		int cost;
		List<Integer> route=new ArrayList<>();
		public Node(int start, int end, int cost, List<Integer> route) {
			this.start = start;
			this.end = end;
			this.cost = cost;
			this.route = route;
		}
		@Override
		public int compareTo(Node o) {
			if(this.cost<o.cost) return -1;
			return 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		cityCount = Integer.parseInt(br.readLine());
		busCount = Integer.parseInt(br.readLine());
		distance=new int[cityCount+1];
		for(int cc=0;cc<=cityCount;++cc) {
			list.add(new ArrayList<>());
			distance[cc]=Integer.MAX_VALUE;
		}
		for(int bc=0;bc<busCount;++bc) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			int cost=Integer.parseInt(st.nextToken());
			Node node=new Node(start,end,cost,new ArrayList<>());
			list.get(start).add(node);
		}
		st=new StringTokenizer(br.readLine());
		int start=Integer.parseInt(st.nextToken());
		int end=Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq=new PriorityQueue<Node>();
		distance[start]=0;
		List<Integer> startList=new ArrayList<Integer>();
		startList.add(start);
		pq.add(new Node(start,-1,0,startList));
		
		List<Integer> result=new ArrayList<Integer>();
		boolean visit[]=new boolean[cityCount+1];
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			if(visit[now.start]) continue;
			visit[now.start]=true;
			for(Node node:list.get(now.start)) {
				int dist=node.cost+now.cost;
				if(dist<distance[node.end]) {
					distance[node.end]=dist;
					List<Integer> cRoute=new ArrayList<Integer>();
					cRoute.addAll(now.route);
					cRoute.add(node.end);
					pq.offer(new Node(node.end,-1,distance[node.end],cRoute));
					if(node.end==end) {
						result=cRoute;
					}
				}
			}
		}
		
		StringBuilder sb=new StringBuilder();
		sb.append(distance[end]).append("\n");
		sb.append(result.size()).append("\n");
		for(int x:result) {
			sb.append(x).append(" ");
		}
		System.out.println(sb.toString());
	}
}
