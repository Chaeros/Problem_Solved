import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int len=Integer.parseInt(br.readLine());
		String text=br.readLine();
		
		int failValue=failTable(text);
		System.out.println(text.length()-failValue);
	}
	
	public static int failTable(String text) {
		int textLength=text.length();
		int[] table=new int[textLength];
		
		int j=0;
		for(int i=1;i<text.length();++i) {
			if(j>0 && text.charAt(i)!=text.charAt(j)) {
				j=table[j-1];
			}
			if(text.charAt(i)==text.charAt(j)) {
				table[i]=++j;
			}
		}
		
		return table[textLength-1];
	}
}