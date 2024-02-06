import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int N,K;
	public static int arr[];
	public static int count = 0;
	public static boolean isEnd = false;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		merge_sort(0,N-1);
		if ( count < K ) System.out.println("-1");
	}

	private static void merge_sort(int start, int end) {
		if ( isEnd ) return;
		if ( start == end ) return;
		int mid = ( start + end ) / 2;
		
		merge_sort(start,mid);
		merge_sort(mid+1,end);
		
		int temp[] = new int[end-start+1];
		int left = start;
		int right = mid + 1;
		int index = 0;
		while ( left <= mid && right <= end) {
			temp[index++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
		}
		while ( left <= mid ) {
			temp[index++] = arr[left++];
		}
		while(right <= end ) {
			temp[index++] = arr[right++];
		}
		for ( int i = start ; i <= end ; ++i ) {
			arr[i] = temp[i-start];
			count++;
			if ( count == K ) {
				for ( int x : arr ) {
					sb.append(x+" ");
				}
				isEnd = true;
				System.out.println(sb);
			}
		}
	}
}
