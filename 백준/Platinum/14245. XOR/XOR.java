import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
	static int n,m;
	static int arr[];
	static long tree[];
	static long lazy[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		tree = new long[4*n+1];
		lazy = new long[4*n+1];
		
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < n ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		initTree(1,0,n-1);
		m = Integer.parseInt(br.readLine());
		for ( int i = 0 ; i < m ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			
			if ( command == 1 ) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				update(1,0,n-1,start,end,diff);
			}
			else if ( command == 2 ) {
				int index = Integer.parseInt(st.nextToken());
				sb.append(getNodeValue(1,0,n-1,index)+"\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	public static void updateLazy(int node, int start, int end) {
		if ( lazy[node] != 0 ) {
			if ( end - start == 0 ) {
				tree[node] ^= lazy[node];
			}
			else if( start != end ) {
				lazy[node*2] ^= lazy[node];
				lazy[node*2+1] ^= lazy[node];
			}
			lazy[node]=0;
		}
	}
	
	public static void initTree(int node,int start,int end) {
		if ( start == end ) {
			tree[node] = arr[start];
			return;
		}
		
		int mid = ( start + end ) / 2;
		initTree(node*2, start, mid);
		initTree(node*2+1, mid+1, end);
	}
	
	public static void update(int node, int start, int end, int nodeLeft, int nodeRight, int diff) {
		updateLazy(node,start,end);
		if ( nodeRight < start || end < nodeLeft ) {
			return;
		}
		else if ( nodeLeft <= start && end <= nodeRight ) {
			if ( end - start + 1 == 1 ) {
				tree[node] ^= diff;
			}
			else if ( start != end ) {
				lazy[node*2] ^= diff;
				lazy[node*2+1] ^= diff;
			}
			return;
		}
		
		int mid = ( start + end ) / 2;
		update(node*2,start,mid,nodeLeft,nodeRight,diff);
		update(node*2+1,mid+1,end,nodeLeft,nodeRight,diff);
	}
	
	public static long getNodeValue(int node, int start, int end, int index) {
		updateLazy(node,start,end);
		if ( start == index && end == index ) {
			return tree[node];
		}
		else if( index < start || end < index ) {
			return 0;
		}
		
		int mid = ( start + end ) / 2;
		long left = getNodeValue(node*2, start, mid, index);
		long right = getNodeValue(node*2+1, mid+1, end, index);
		return left ^ right;
	}
}
