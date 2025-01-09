import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		String text=st.nextToken();
		long count=Integer.parseInt(st.nextToken());
		
		int failValue=failTable(text);
		
		long result=(long)text.length()*count-((long)failValue*(count-1));
		
		System.out.println(result);
	}
	
	public static int failTable(String text) {
		int textLength=text.length();
		int[] table=new int[textLength];
		
		int j=0;
		for(int i=1;i<textLength;++i) {
			while(j>0 && text.charAt(i)!=text.charAt(j)) {
				j=table[j-1];
			}
			if(text.charAt(i)==text.charAt(j)) {
				table[i]=++j;
			}
		}
		return table[textLength-1];
	}
}