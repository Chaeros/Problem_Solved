import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,M;
	public static int tree[];
	public static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		tree = new int[4*N+1];
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for ( int i =1 ; i <= N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		initTree(1, N, 1);
		
		M = Integer.parseInt(br.readLine());
		
		for ( int i = 0 ; i < M ; ++i ) {
			st = new StringTokenizer(br.readLine());
			
			int command = Integer.parseInt(st.nextToken());
			
			if ( command ==1 ) {
				int index = Integer.parseInt(st.nextToken());
				int newValue = Integer.parseInt(st.nextToken());
				
				updateTree(1,N,1,index,newValue);
			}
			else if ( command ==2 ) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				
				bw.write(getMinValue(1,N,1,left,right)+"\n");
			}
			
//			printTree();
		}
		bw.flush();
		bw.close();
	}
	
	public static int merge(int left, int right) {
		if ( left < right ) {
			return left;
		}
		return right;
	}
	
	public static int initTree(int start, int end, int node) {
		if ( start == end ) {
			return tree[node] = arr[start];
		}
		
		int mid = ( start + end ) / 2;
		int left = initTree(start, mid, node*2);
		int right = initTree(mid+1,end,node*2+1);
		return tree[node] = merge(left,right);
	}
	
	public static int updateTree(int start, int end, int node, int index, int newValue) {
		if ( index < start || end < index ) {
			return tree[node];
		}
		else if ( start == end ) {
			return tree[node] = newValue;
		}
		
		int mid = ( start + end ) / 2;
		int left = updateTree(start, mid, node*2, index, newValue);
		int right = updateTree(mid+1, end, node*2+1, index, newValue);
		return tree[node] = merge(left,right);
	}
	
	public static int getMinValue(int start, int end, int node, int nodeLeft, int nodeRight) {
		if ( nodeRight < start || end < nodeLeft ) {
			return Integer.MAX_VALUE;
		}
		else if ( nodeLeft <= start && end <= nodeRight ) {
			return tree[node];
		}
		
		int mid = ( start + end ) / 2;
		int left = getMinValue(start, mid, node*2, nodeLeft, nodeRight);
		int right = getMinValue(mid+1, end, node*2+1, nodeLeft, nodeRight);
		return merge(left,right);
	}
	
}
