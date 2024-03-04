import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,K;
	static long arr[];
	static long tree[];
	static long lazy[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N];
		tree = new long[N*4];
		lazy = new long[N*4];
		
		for ( int n = 0 ; n < N ; ++n ) {
			arr[n] = Long.parseLong(br.readLine());
		}
		initTree(0,N-1,1);
		for ( int n = 0 ; n < M + K ; ++n ) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if ( command == 1 ) {
				int start = Integer.parseInt(st.nextToken())-1;
				int end = Integer.parseInt(st.nextToken())-1;
				long value = Long.parseLong(st.nextToken());
				updateTree(0, N-1, 1, start, end, value);
			} else if ( command == 2 ) {
				int start = Integer.parseInt(st.nextToken())-1;
				int end = Integer.parseInt(st.nextToken())-1;
				sb.append(getSumValue(0, N-1, 1, start, end)+"\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void initTree(int start, int end, int node) {
		if ( start == end ) {
			tree[node] = arr[start];
			return;
		}
		
		int mid = ( start + end ) / 2;
		initTree(start, mid, node*2);
		initTree(mid+1, end, node*2+1);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	private static void updateLazy(int start, int end, int node) {
		tree[node] += (end-start+1)*lazy[node];
		if ( start != end ) {
			lazy[node*2] += lazy[node];
			lazy[node*2+1] += lazy[node];
		}
		lazy[node] = 0;
	}
	
	private static void updateTree(int start, int end, int node, int nodeLeft, int nodeRight, long value) {
		updateLazy(start,end,node);
		if ( nodeRight < start || end < nodeLeft ) {
			return;
		}
		if ( nodeLeft <= start && end <= nodeRight ) {
			tree[node] += (end-start+1)*value;
			if ( start != end ) {
				lazy[node*2] += value;
				lazy[node*2+1] += value;
			}
			return;
		}
		
		int mid = ( start + end ) / 2;
		updateTree(start, mid, node*2, nodeLeft, nodeRight, value);
		updateTree(mid+1, end, node*2+1, nodeLeft, nodeRight, value);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	private static long getSumValue(int start, int end, int node, int leftNode, int rightNode) {
		updateLazy(start,end,node);
		if ( rightNode < start || end < leftNode ) {
			return 0;
		}
		if ( leftNode <= start && end <= rightNode ) {
			return tree[node];
		}
		
		int mid = ( start + end ) /2;
		long left = getSumValue(start, mid, node*2, leftNode, rightNode);
		long right = getSumValue(mid+1, end, node*2+1, leftNode, rightNode);
		return left + right;
	}
}
