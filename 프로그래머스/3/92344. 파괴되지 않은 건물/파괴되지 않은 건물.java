class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int[][] map = new int[N + 1][M + 1];

        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
            int diff = (type == 1 ? -degree : degree);

            map[r1][c1] += diff;
            map[r1][c2 + 1] -= diff;
            map[r2 + 1][c1] -= diff;
            map[r2 + 1][c2 + 1] += diff;
        }

        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                map[i][j] += map[i][j - 1];
            }
        }

        for (int j = 0; j < M + 1; j++) {
            for (int i = 1; i < N + 1; i++) {
                map[i][j] += map[i - 1][j];
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + map[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}
