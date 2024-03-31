import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		String pattern = br.readLine();
		
		kmp(text,pattern);
	}
	
	public static void kmp(String text, String pattern) {
		int textLen = text.length();
		int patternLen = pattern.length();
		int count = 0;
		StringBuilder sb = new StringBuilder();
		
		int table[] = makePatternTable(pattern);
		
		int index = 0;
		for ( int i = 0 ; i < text.length() ; ++i ) {
			while ( index > 0 && text.charAt(i) != pattern.charAt(index) ) {
				index = table[index-1];
			}
			
			if ( text.charAt(i) == pattern.charAt(index) ) {
				if ( index == patternLen - 1 ) {
					count++;
					sb.append((i-index+1)+" ");
					index = table[index];
				}
				else {
					index++;
				}
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
	}
	
	public static int[] makePatternTable(String pattern) {
		int n = pattern.length();
		int table[] = new int[n];
		
		int index = 0;
		for ( int i = 1 ; i < n ; ++i ) {
			while ( index > 0 && pattern.charAt(index) != pattern.charAt(i) ) {
				index = table[index-1];
			}
			if ( pattern.charAt(index) == pattern.charAt(i) ) {
				table[i] = ++index;
			}
		}
		return table;
	}
}
