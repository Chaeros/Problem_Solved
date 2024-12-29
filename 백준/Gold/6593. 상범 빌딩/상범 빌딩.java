import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static int L,R,C;
	public static char map[][][];
	public static int sl,sr,sc;
	public static int el,er,ec;
	public static final int INF=9999999;
	
	public static class Node implements Comparable<Node>{
		int l;
		int r;
		int c;
		int cost;
		public Node(int l, int r, int c, int cost) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb=new StringBuilder();
		while(true) {
			st=new StringTokenizer(br.readLine());
			L=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			
			map=new char[L][R][C];
			if(L==0 || R==0 || C==0) break;
			
			for(int l=0;l<L;++l) {
				for(int r=0;r<R;++r) {
					String inputString=br.readLine();
					for(int c=0;c<C;++c) {
						map[l][r][c]=inputString.charAt(c);
						if(map[l][r][c]=='S') {
							sl=l;
							sr=r;
							sc=c;
						} else if(map[l][r][c]=='E') {
							el=l;
							er=r;
							ec=c;
						}
					}
				}
				br.readLine();
			}
			int result=bfs(sl,sr,sc);
			if(result==INF) {
				sb.append("Trapped!").append("\n");
			} else {
				sb.append("Escaped in "+result+" minute(s).").append("\n");
			}
			
		}
		System.out.println(sb.toString());
	}
	
	public static int bfs(int l, int r, int c) {
		int dl[]= {0,0,0,0,-1,1};
		int dr[]= {-1,0,1,0,0,0};
		int dc[]= {0,1,0,-1,0,0};
		
		PriorityQueue<Node> pQ=new PriorityQueue<>();
		pQ.offer(new Node(l,r,c,0));
		
		boolean visit[][][]=new boolean[L][R][C];
		
		int distance[][][]=new int[L][R][C];
		
		for(int i=0;i<L;++i) {
			for(int j=0;j<R;++j) {
				for(int k=0;k<C;++k) {
					distance[i][j][k]=INF;
				}
			}
		}
		
		distance[l][r][c]=0;
		while(!pQ.isEmpty()) {
			Node now=pQ.poll();
			
			if(visit[now.l][now.r][now.c]) continue;
			visit[now.l][now.r][now.c]=true;
			
			for(int d=0;d<6;++d) {
				int ml=now.l+dl[d];
				int mr=now.r+dr[d];
				int mc=now.c+dc[d];
				
				if(ml<0 || ml>=L || mr<0 || mr>=R || mc<0 || mc>=C ) continue;
				if(map[ml][mr][mc]=='#') continue;
				if(distance[ml][mr][mc]>now.cost+1) {
					distance[ml][mr][mc]=now.cost+1;
					pQ.offer(new Node(ml,mr,mc,now.cost+1));
				}
			}
		}
		
		return distance[el][er][ec];
	}
}
