import java.util.*;

class Solution {
    class Position{
        int row;
        int col;
        Position(int row,int col){
            this.row=row;
            this.col=col;
        }
    }
    
    char[][] map;
    List<Position> list;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0;i<5;++i){
            map=new char[5][5];
            list=new ArrayList<>();
            for(int j=0;j<5;++j){
                String str=places[i][j];
                for(int k=0;k<5;++k){
                    char place=str.charAt(k);
                    if(place=='P'){
                        list.add(new Position(j,k));
                    }
                    map[j][k]=place;
                }
            }
            if(bfs()==true) answer[i]=1;
            else answer[i]=0;
        }
        return answer;
    }
    
    public boolean bfs(){
        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};
        boolean isSuccess=true;
        
        for(Position p:list){
            int[][] isVisit=new int[5][5];
            for (int[] row : isVisit) Arrays.fill(row, -1);
            
            Queue<Position> q = new ArrayDeque<>();
            q.offer(new Position(p.row,p.col));
            isVisit[p.row][p.col]=0;
            
            while(!q.isEmpty()){
                Position now = q.poll();
                for(int d=0;d<4;++d){
                    int mr=now.row+dr[d];
                    int mc=now.col+dc[d];
                    if(mr<0 || mr>=5) continue;
                    if(mc<0 || mc>=5) continue;
                    if(isVisit[mr][mc]!=-1) continue;
                    if(map[mr][mc]=='X') continue;
                    if(map[mr][mc]=='P' && isVisit[now.row][now.col]+1 <=2 ){
                        isSuccess=false;
                        break;
                    }
                    isVisit[mr][mc]=isVisit[now.row][now.col]+1;
                    if (isVisit[mr][mc] < 2) q.offer(new Position(mr, mc));
                }
                if(!isSuccess) break;
            }
        }
        return isSuccess;
    }
}