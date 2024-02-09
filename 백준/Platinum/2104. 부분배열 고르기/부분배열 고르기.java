import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static long arr[];
	public static long sum[];
	public static long tree[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new long[N];
		sum = new long[N];
		tree = new long[4*N+1];
		Arrays.fill(tree, Integer.MAX_VALUE);
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		sum[0] = arr[0];
		for ( int i = 1 ; i < N ; ++i ) {
			sum[i] = sum[i-1] + arr[i];
		}
		
		initMinTree(0, N-1, 1);
		bw.write(String.valueOf(merge_sort(0,N-1)));
		bw.flush();
		bw.close();
	}
	
	private static long getPrefixSum(int start, int end) {
		if ( start == 0 ) return sum[end];
		return sum[end]-sum[start-1];
	}
	
	private static long merge(long left, long right) {
		return Math.min(left, right);
	}
	
	public static long initMinTree(int start, int end, int node) {
		if ( start == end ) {
			return tree[node] = arr[start];
		}
		int mid = ( start + end ) / 2;
		long left = initMinTree(start, mid, node*2);
		long right = initMinTree(mid+1, end, node*2+1);
		return tree[node] = merge(left,right);
	}
	
	public static long getMinVal(int start, int end, int node, int nodeLeft, int nodeRight) {
		if ( nodeLeft <= start && end <= nodeRight ) {
			return tree[node];
		}
		
		if ( nodeRight < start || end < nodeLeft ) {
			return Integer.MAX_VALUE;
		}
		
		int mid = ( start + end ) /2;
		long left = getMinVal(start, mid, node*2, nodeLeft, nodeRight);
		long right = getMinVal(mid+1, end, node*2+1, nodeLeft, nodeRight);
		return merge(left,right);
	}
	
	public static long merge_sort(int start, int end) {
		if ( start == end ) {
			return arr[start]*arr[start];
		}
		
		int mid = ( start + end ) / 2;
		int left = mid - 1;
		int right = mid + 1;
		long sum = arr[mid];
		long outcome = sum * arr[mid];
		
		while ( start <= left || right <= end ) {
			if ( left < start ) {
				sum = getPrefixSum(left+1, right);
				++right;
			}
			else if ( end < right ) {
				sum = getPrefixSum(left, right-1);
				--left;
			}
			else if( arr[left] < arr[right] ) {
				sum = getPrefixSum(left+1, right);
				++right;
			}
			else {
				sum = getPrefixSum(left, right-1);
				--left;
			}
			outcome = Math.max(outcome, sum*getMinVal(0, N-1, 1, left+1, right-1));
		}
		return Math.max(outcome, Math.max(merge_sort(start, mid), merge_sort(mid+1, end)));
	}
}
