import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	public static int N;
	public static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for ( int i = 0 ; i < N ; ++i ) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		bw.write(merge_sort(0, N-1)+"\n");
		bw.flush();
		bw.close();
	}
	
	
	public static long merge_sort(int start, int end) {
		if ( start == end ) {
			return arr[start];
		}
		
		int mid = ( start + end ) /2;
		int left = mid - 1;
		int right = mid + 1;
		long height = arr[mid];
		long area = height * (right - left - 1);
		
		while ( start <= left || right <= end ) {
			if ( end < right ) {
				height = Math.min(height, arr[left]);
				--left;
			}
			else if ( left < start ) {
				height = Math.min(height, arr[right]);
				++right;
			}
			else if ( arr[left] < arr[right] ) {
				height = Math.min(height, arr[right]);
				++right;
			}
			else {
				height = Math.min(height, arr[left]);
				--left;
			}
			
			area = Math.max(area, height*(right-left-1));
		}
		
		return Math.max(area, Math.max(merge_sort(start, mid), merge_sort(mid+1, end)));
	}
}
