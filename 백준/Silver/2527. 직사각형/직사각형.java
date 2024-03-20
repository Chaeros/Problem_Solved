import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int positions[] = new int[8];

        for ( int i = 0 ; i < 4 ; ++i ) {
            st = new StringTokenizer(br.readLine());
            for ( int j = 0 ; j < 8 ; ++j ){
                positions[j] = Integer.parseInt(st.nextToken());
            }

            if(codeD(positions)){
                System.out.println("d");
            }
            else if(codeC(positions)){
                System.out.println("c");
            }
            else if(codeB(positions)){
                System.out.println("b");
            }
            else{
                System.out.println("a");
            }
        }
    }

    public static boolean codeD( int[] positions ){
        if (positions[0] > positions[6]) return true;
        if (positions[1] > positions[7]) return true;
        if (positions[2] < positions[4]) return true;
        if (positions[3] < positions[5]) return true;
        return false;
    }

    public static boolean codeC( int[] positions ){
        if ((positions[0] == positions[6]) && (positions[1] == positions[7])) return true;
        if ((positions[2] == positions[4]) && (positions[3] == positions[5])) return true;
        if ((positions[2] == positions[4]) && (positions[1] == positions[7])) return true;
        if ((positions[0] == positions[6]) && (positions[3] == positions[5])) return true;
        return false;
    }

    public static boolean codeB( int[] positions ){
        if (positions[0] == positions[6])  return true;
        if (positions[2] == positions[4])  return true;
        if (positions[1] == positions[7])  return true;
        if (positions[3] == positions[5])  return true;
        return false;
    }
}
