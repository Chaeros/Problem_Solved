class Solution {
    
    public int dr[] = {1,0,0,-1};
    public int dc[] = {0,-1,1,0};
    public char direction[] = {'d','l','r','u'};
    public String result = "";
    public StringBuilder sb = new StringBuilder();
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        if ( getMinDistance(x,y,r,c) > k ||
           ((getMinDistance(x,y,r,c) % 2) != (k % 2)) ){
            return "impossible";
        }
        
        while ( getMinDistance(x,y,r,c) < k ){
            --k;
            for ( int d = 0 ; d < 4 ; ++d ){
                int mr = x + dr[d];
                int mc = y + dc[d];
                if ( mr < 1 || mr > n || mc < 1 || mc > m ) continue;
                x = mr;
                y = mc;
                sb.append(direction[d]);
                break;
            }
        }
        
        while ( (x != r) || (y != c) ){
            --k;
            for ( int d = 0 ; d < 4 ; ++d ){
                int mr = x + dr[d];
                int mc = y + dc[d];
                if ( mr < 1 || mr > n || mc < 1 || mc > m ) continue;
                if ( getMinDistance(mr,mc,r,c) != k ) continue;
                x = mr;
                y = mc;
                sb.append(direction[d]);
                break;
            }
        }
        return sb.toString();
    }
    
    public int getMinDistance(int row, int col, int endR, int endC){
        return Math.abs(endR-row)+Math.abs(endC-col);
    }
}