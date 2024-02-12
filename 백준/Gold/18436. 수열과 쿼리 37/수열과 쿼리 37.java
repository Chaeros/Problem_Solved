import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int arr[];
	public static int tree[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		tree = new int[4*N];
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		initTree(0, N-1, 1);
		int M = Integer.parseInt(br.readLine());
		for ( int i = 0 ; i < M ; ++i ) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			if ( command == 1 ) {
				updateTree(0, N-1, 1, left-1, right);
			} else if ( command == 2 ) {
				sb.append(getNumber(0, N-1, 1, left-1, right-1)).append("\n");
			} else if ( command == 3 ) {
				sb.append((right-left+1)-getNumber(0, N-1, 1, left-1, right-1)).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();		
	}
	
	public static void initTree(int start, int end, int node) {
		if ( start == end ) {
			if ( arr[start] % 2 == 0 ) {
				tree[node] = 1;
			}
			return;
		}
		int mid = ( start + end ) / 2;
		initTree(start,mid,node*2);
		initTree(mid+1, end, node*2+1);
		tree[node] =  tree[node*2] + tree[node*2+1]; 
	}
	
	public static void updateTree(int start, int end, int node, int index, int value) {
		if ( index < start || end < index ) {
			return;
		}
		
		if ( start == index && end == index ) {
			if ( value % 2 == 0 ) {
				tree[node] = 1;
			}
			else {
				tree[node] = 0;
			}
			return;
		}
		
		int mid = ( start + end ) / 2;
		updateTree(start,mid,node*2,index,value);
		updateTree(mid+1,end,node*2+1,index,value);
		tree[node] = tree[node*2] + tree[node*2+1];
	}
	
	public static int getNumber(int start, int end, int node, int nodeLeft, int nodeRight) {
		if ( nodeLeft <= start && end <= nodeRight) {
			return tree[node];
		}
		
		if ( nodeRight < start || end < nodeLeft ) {
			return 0;
		}
		
		int mid = ( start + end ) / 2;
		return getNumber(start, mid, node*2, nodeLeft, nodeRight) + getNumber(mid+1, end, node*2+1, nodeLeft, nodeRight);
	}
	
}
