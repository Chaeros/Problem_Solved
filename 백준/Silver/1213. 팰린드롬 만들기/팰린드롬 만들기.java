import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str=br.readLine();
		char[] strArray=str.toCharArray();
		Arrays.sort(strArray);
		int strCount=str.length();
		int oddCount=0;
		
		String frontStr="";
		String rearStr="";
		String middleStr="";
		if(strCount%2==1) {
			for(int i=0;i<strCount;) {
				if(i==strCount-1 && oddCount==0) {
					middleStr+=strArray[i];
					break;
				}
				if(strArray[i]==strArray[i+1]) {
					frontStr+=strArray[i];
					rearStr=strArray[i]+rearStr;
					i+=2;
				}
				else if(strArray[i]!=strArray[i+1]) {
					if(oddCount==1) {
						frontStr="I'm Sorry Hansoo";
						middleStr="";
						rearStr="";
						break;
					}
					middleStr+=strArray[i];
					oddCount=1;
					++i;
				}				
				
			}
		}
		else if(strCount%2==0) {
			for(int i=0;i<strCount;) {
				if(strArray[i]==strArray[i+1]) {
					frontStr+=strArray[i];
					rearStr=strArray[i]+rearStr;
					i+=2;
				}
				else if(strArray[i]!=strArray[i+1]) {
					frontStr="I'm Sorry Hansoo";
					rearStr="";
					break;
				}				
				
			}
		}
		String result=frontStr+middleStr+rearStr;
		System.out.println(result);
	}
}
