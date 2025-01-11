import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		
		int[] time1=new int[N];
		int[] time2=new int[N];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;++i) {
			time1[i]=Integer.parseInt(st.nextToken());
		}
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<N;++i) {
			time2[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(time1);
		Arrays.sort(time2);
		
		int[] diff1=new int[N*2-1];
		int[] diff2=new int[N];
		
		for(int i=0;i<N-1;++i) {
			diff1[i]=time1[i+1]-time1[i];
			diff1[i+N]=diff1[i];
			diff2[i]=time2[i+1]-time2[i];
		}
		diff1[N-1]=time1[0]+360000-time1[N-1];
		diff2[N-1]=time2[0]+360000-time2[N-1];
		
		if(kmp(diff1,diff2)) {
			System.out.println("possible");
		}
		else {
			System.out.println("impossible");
		}
	}
	
	
	public static int[] getPi(int[] text) {
		int textLen=text.length;
		int[] table=new int[textLen];
		int j=0;
		for(int i=1;i<textLen;++i) {
			while(j>0 && text[i]!=text[j]) {
				j=table[j-1];
			}
			if(text[i]==text[j]) {
				table[i]=++j;
			}
		}
		return table;
	}
	
	public static boolean kmp(int[] text, int[] pattern) {
		int textLen=text.length;
		int patternLen=pattern.length;
		int[] pi=getPi(pattern);
		
		int j=0;
		for(int i=0;i<textLen;++i) {
			while(j>0 && text[i]!=pattern[j]) {
				j=pi[j-1];
			}
			if(text[i]==pattern[j]) {
				++j;
				if(j==patternLen) {
					return true;
				}
			}
		}
		return false;
	}
}