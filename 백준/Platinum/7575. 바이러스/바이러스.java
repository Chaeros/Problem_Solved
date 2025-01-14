import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int[] pattern;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] array = new int[N][];

        int minSizeIndex = 0;
        int minSize = Integer.MAX_VALUE;

        for (int n = 0; n < N; ++n) {
            int count = Integer.parseInt(br.readLine());
            array[n] = new int[count];
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < count; ++c) {
                array[n][c] = Integer.parseInt(st.nextToken());
            }
            if (count < minSize) {
                minSize = count;
                minSizeIndex = n;
            }
        }

        boolean exist = false;
        pattern = array[minSizeIndex];
        int patternLen = pattern.length;

        int[] currentPi=new int[1001];
        outer:
        for (int i = 0; i <= patternLen - K; ++i) {
            for (int k = K; k <= patternLen - i; ++k) {
                exist = true;
                
                int start=i;
                int end=i+k;
                int j = 0;
                for (int a = 1; a < end - start; ++a) {
                    while (j > 0 && pattern[start + a] != pattern[start + j]) {
                        j = currentPi[j - 1];
                    }
                    if (pattern[start + a] == pattern[start + j]) {
                    	currentPi[a] = ++j;
                    }
                }

                for (int n = 0; n < N; ++n) {
                    if (n == minSizeIndex) continue;
                    if (!kmp(array[n], i, i + k, currentPi) && !reverseKmp(array[n], i, i + k, currentPi)) {
                        exist = false;
                        break;
                    }
                }
                if (exist) break outer;
            }
        }

        System.out.println(exist ? "YES" : "NO");
    }

    public static int[] getPi(int start, int end) {
        int[] table = new int[end - start];
        int j = 0;
        for (int i = 1; i < end - start; ++i) {
            while (j > 0 && pattern[start + i] != pattern[start + j]) {
                j = table[j - 1];
            }
            if (pattern[start + i] == pattern[start + j]) {
                table[i] = ++j;
            }
        }
        return table;
    }

    public static boolean kmp(int[] text, int start, int end, int[] pi) {
        int textLen = text.length;
        int patternLen = end - start;
        int j = 0;

        for (int i = 0; i < textLen; ++i) {
            while (j > 0 && text[i] != pattern[start + j]) {
                j = pi[j - 1];
            }
            if (text[i] == pattern[start + j]) {
                ++j;
                if (j == patternLen) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean reverseKmp(int[] text, int start, int end, int[] pi) {
        int textLen = text.length;
        int patternLen = end - start;
        int j = 0;

        for (int i = textLen - 1; i >= 0; --i) {
            while (j > 0 && text[i] != pattern[start + j]) {
                j = pi[j - 1];
            }
            if (text[i] == pattern[start + j]) {
                ++j;
                if (j == patternLen) {
                    return true;
                }
            }
        }
        return false;
    }
}
