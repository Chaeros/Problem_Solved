import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str=br.readLine();
		
		int currentNumber=Integer.parseInt(String.valueOf(str.charAt(0)));
		int[] count=new int[2];
		count[currentNumber]=1;
		for(int i=1;i<str.length();++i) {
			int x=Integer.parseInt(String.valueOf(str.charAt(i)));
			if(currentNumber!=x) {
				++count[x];
				currentNumber=x;
			}
		}
		if((count[0]==1 && count[1]==0) || (count[0]==0 && count[1]==1)) {
			System.out.println(0);
		}
		else {
			System.out.println(Math.min(count[0], count[1]));
		}
	}
}