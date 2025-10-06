class Solution {
    
    int R,C;
    int[][] board;
    
    class Point{
        int row;
        int col;
        Point(int row,int col){
            this.row=row;
            this.col=col;
        }
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        R=board.length;
        C=board[0].length;
        this.board=board;
        Point me=new Point(aloc[0],aloc[1]);
        Point you=new Point(bloc[0],bloc[1]);
        int answer=dfs(me,you);
        return answer;
    }
    
    public int dfs(Point me,Point you){
        if(board[me.row][me.col]==0) return 0;
        
        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};
        
        int result=0;
        for(int d=0;d<4;++d){
            int nr=me.row+dr[d];
            int nc=me.col+dc[d];
            if(nr<0 || nc<0 || nr>=R || nc>=C || board[nr][nc]==0) continue;
            board[me.row][me.col]=0;
            int val=dfs(you,new Point(nr,nc))+1;
            board[me.row][me.col]=1;
            
            if(val%2==1 && result%2==0) result=val;
            else if(val%2==1 && result%2==1) result=Math.min(val,result);
            else if(val%2==0 && result%2==0) result=Math.max(val,result);
        }
        return result;
    }
}