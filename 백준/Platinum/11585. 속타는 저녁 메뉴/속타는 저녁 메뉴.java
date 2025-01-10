import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String text="";
		String keyword="";
		
		StringBuilder textSB=new StringBuilder();
		StringBuilder keywordSB=new StringBuilder();
		
		N=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		for(int n=0;n<N;++n) {
			keywordSB.append(st.nextToken());
		}
		keyword=keywordSB.toString();
		st=new StringTokenizer(br.readLine());
		for(int n=0;n<N;++n) {
			textSB.append(st.nextToken());
		}
		text=textSB.toString();
		
		int kmpResult=kmp(text+text,keyword);
		int r=gcd(N,kmpResult);
		System.out.println(kmpResult/r+"/"+N/r);
	}
	
	public static int[] failTable(String str) {
		int[] table=new int [str.length()];
		
		int j=0;
		for(int i=1;i<str.length();++i) {
			while(j>0 && str.charAt(i)!=str.charAt(j)) {
				j=table[j-1];
			}
			if(str.charAt(i)==str.charAt(j)) {
				table[i]=++j;
			}
		}
		return table;
	}
	
	public static int kmp(String text, String keyword) {
		int result=0;
		
		int textLen=text.length();
		int keywordLen=keyword.length();
		int[] pi=failTable(keyword);
		
		int j=0;
		for(int i=0;i<textLen-1;++i) {
			while(j>0 && text.charAt(i)!=keyword.charAt(j)) {
				j=pi[j-1];
			}
			if(text.charAt(i)==keyword.charAt(j)) {
				if(j==keywordLen-1) {
					++result;
					j=pi[j];
				}
				else {
					++j;
				}
			}
		}
		return result;
	}
	
	public static int gcd(int a, int b) {
		while(b!=0) {
			int r=a%b;
			a=b;
			b=r;
		}
		return a;
	}
}
