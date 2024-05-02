class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        final int INF = 98764321;
        int distances[][] = new int[n+1][n+1];
        for ( int i = 1 ; i <= n ; ++i ){
            for ( int j =1 ; j <= n ; ++j ){
                if ( i != j ){
                    distances[i][j] = INF;
                }
            }
        }
        
        for ( int r = 0 ; r < fares.length ; ++r ){
            int start = fares[r][0];
            int end = fares[r][1];
            int distance = fares[r][2];
            distances[start][end] = distance;
            distances[end][start] = distance;
        }
        
        for ( int k = 1 ; k <= n ; ++k ){
            for ( int i = 1 ; i <= n ; ++i ){
                for ( int j = 1 ; j <= n ; ++j ){
                    distances[i][j] = Math.min(distances[i][j],distances[i][k]+distances[k][j]);
                }
            }
        }
        
        int resultSum = 987654321;
        for ( int i = 1 ; i <= n ; ++i ){
            int tempSum = distances[i][s] + distances[i][a] + distances[i][b];
            resultSum = Math.min(resultSum,tempSum);
        }
        
        return resultSum;
    }
}