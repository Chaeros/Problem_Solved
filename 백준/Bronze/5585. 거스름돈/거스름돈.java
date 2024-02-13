import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	public static int unit[] = {500,100,50,10,5,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int money = 1000 - Integer.parseInt(br.readLine());
		int billCount = 0;
		for ( int i = 0 ; i < unit.length ; ++i ) {
			if ( money / unit[i] >0 ) {
				billCount += money / unit[i];
				money = money % unit[i];
			}
		}
		bw.write(billCount+"\n");
		bw.flush();
		bw.close();
	}
}
