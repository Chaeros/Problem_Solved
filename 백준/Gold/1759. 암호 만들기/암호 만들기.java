import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int L, C;
	static char arr[];
	static char result[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C];
		result = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < C ; ++i ) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(arr);
		dfs(0,0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static void dfs( int index, int depth ) {
		if ( depth == L ) {
			if ( isCorretCountString() ) {
				addResultString();
			}
			return;
		}
		
		for ( int i = index ; i < C ; ++i ) {
			result[depth] = arr[i];
			dfs(i+1, depth+1);
		}
	}
	
	private static boolean isCorretCountString() {
		int size = 0;
		for ( int i = 0 ; i < L ; ++i ) {
			if ( result[i] == 'a' || result[i] == 'e' || result[i] == 'i' ||
				 result[i] == 'o' || result[i] == 'u') {
				++size;
			}
		}
		if ( size < 1 || L - size < 2 ) return false;
		return true;
	}
	
	private static void addResultString() {
		for ( int i = 0 ; i < L ; ++i ) {
			sb.append(result[i]);
		}
		sb.append("\n");
	}
	
}
