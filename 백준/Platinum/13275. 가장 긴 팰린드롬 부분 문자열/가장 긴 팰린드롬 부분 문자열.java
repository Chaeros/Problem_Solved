import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String text="";
		String input=br.readLine();
		StringBuilder sb=new StringBuilder();
		sb.append("#");
		for(int i=0;i<input.length();++i) {
			sb.append(input.charAt(i));
			sb.append("#");
		}
		text=sb.toString();
		
		int pLen[]=new int[200020];
		int c=-1;
		int r=-1;
		
		for(int i=0;i<text.length();++i) {
			if(i<=r) {
				pLen[i]=Math.min(r-i, pLen[c+(c-i)]);
			}
			
			while(i+pLen[i]+1<text.length() && i-pLen[i]-1>=0 && text.charAt(i+pLen[i]+1)==text.charAt(i-pLen[i]-1)) {
				++pLen[i];
			}
			
			if(i+pLen[i]>r) {
				c=i;
				r=i+pLen[i];
			}
		}
		
		int result=0;
		for(int i=0;i<text.length();++i) {
			result=Math.max(result, pLen[i]);
		}
		System.out.println(result);
	}
}
