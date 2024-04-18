import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.InputMap;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for ( int t = 1 ; t <= 10 ; ++t ) {
			int n = Integer.parseInt(br.readLine());
			int arr[] = new int[100];
			
			st = new StringTokenizer(br.readLine());
			for ( int i = 0 ; i < 100 ; ++i ) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			for ( int i = 0 ; i < n ; ++i ) {
				int frontPointer = 0;
				int rearPointer = arr.length-1;
				
				if ( arr[arr.length-1] - arr[0] <= 1 ) {
					break;
				}
				
				while ( arr[frontPointer] >= arr[frontPointer+1] ) {
					++frontPointer;
				}
				while ( arr[rearPointer-1] >= arr[rearPointer] ) {
					--rearPointer;
				}
				++arr[frontPointer];
				--arr[rearPointer];
				
			}
			
			bw.write("#"+t+" "+(arr[arr.length-1]-arr[0])+"\n");
		}
		bw.flush();
		bw.close();
	}
}
