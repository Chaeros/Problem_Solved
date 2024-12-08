import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String N=br.readLine();
		
		int sum=0;
		for(int i=0;i<N.length();++i) {
			sum+=Integer.parseInt(String.valueOf(N.charAt(i)));
		}
		
		if(!N.contains("0") || sum%3!=0) {
			System.out.println("-1");
			return;
		}
		
		char[] array=N.toCharArray();
		Arrays.sort(array);
		
		String result="";
		for(int i=N.length()-1;i>=0;--i) {
			result+=array[i];
		}
		System.out.println(result);
	}
}
