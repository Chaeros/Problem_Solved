import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static final int INF=999999999;
	
	public static class Node implements Comparable<Node>{
		int index;
		int index2;
		int costSum;
		public Node(int index, int index2, int costSum) {
			this.index = index;
			this.index2 = index2;
			this.costSum = costSum;
		}
		@Override
		public int compareTo(Node o) {
			if(this.costSum==o.costSum) {
				if(this.index==o.index) {
					return this.index2-o.index2;
				}
				return this.index-o.index;
			}
			return this.costSum-o.costSum;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int buildingCount=Integer.parseInt(st.nextToken());
		int roadCount=Integer.parseInt(st.nextToken());
		int cost[][]=new int[buildingCount+1][buildingCount+1];
		
		for(int i=1;i<=buildingCount;++i) {
			Arrays.fill(cost[i], INF);
			cost[i][i]=0;
		}
		
		for(int r=0;r<roadCount;++r) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			cost[a][b]=1;
			cost[b][a]=1;
		}
		
		for(int k=1;k<=buildingCount;++k) {
			for(int a=1;a<=buildingCount;++a) {
				for(int b=1;b<=buildingCount;++b) {
					cost[a][b]=Math.min(cost[a][b], cost[a][k]+cost[k][b]);
				}
			}
		}
		
		int sum[]=new int[buildingCount+1];
		PriorityQueue<Node> pq=new PriorityQueue<>();
		for(int f=1;f<=buildingCount;++f) {
			for(int s=f+1;s<=buildingCount;++s) {
				int tempSum=0;
				for(int k=1;k<=buildingCount;++k) {
					if(k==f || k==s) continue;
					if(cost[f][k]<cost[s][k]) tempSum+=cost[f][k];
					else tempSum+=cost[s][k];
				}
				pq.offer(new Node(f,s,tempSum));
			}
		}
		
		Node result=pq.poll();
		System.out.println(result.index+" "+result.index2+" "+result.costSum*2);
		
	}
}
