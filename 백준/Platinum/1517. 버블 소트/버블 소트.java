import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	public static int arr[];
	public static long answer = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		merge_sort(0,N-1);
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();		
	}
	
	public static void merge_sort(int start, int end) {
		if ( start == end ) {
			return;
		}
		
		int mid = ( start + end ) / 2;
		
		merge_sort(start,mid);
		merge_sort(mid+1,end);
		
		int left = start;
		int right = mid + 1;
		int temp[] = new int[end - start + 1];
		int tempIndex = 0;
		
		while ( left <= mid && right <= end ) {
			if ( arr[left] <= arr[right] ) {
				temp[tempIndex++] = arr[left++];
			}
			else {
				temp[tempIndex++] = arr[right++];
				answer += mid - left + 1;
			}
		}
		
		while ( left <= mid ) {
			temp[tempIndex++] = arr[left++];
		}
		
		while ( right <= end ) {
			temp[tempIndex++] = arr[right++];
		}
		
		for ( int i = start ; i <= end ; ++i ) {
			arr[i] = temp[i-start];
		}		
	}
}
