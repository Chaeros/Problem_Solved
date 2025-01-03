import java.util.*;

class Solution {
    public class Node{
        int row;
        int col;
        Node(int row, int col){
            this.row=row;
            this.col=col;
        }
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        char[][] map=new char[maps.length][maps[0].length()];
        
        int sr=0;
        int sc=0;
        int lr=0;
        int lc=0;
        int er=0;
        int ec=0;
        
        for(int i=0;i<maps.length;++i){
            String line=maps[i];
            for(int j=0;j<maps[i].length();++j){
                map[i][j]=line.charAt(j);
                if(map[i][j]=='S'){
                    sr=i;
                    sc=j;
                } else if(map[i][j]=='L'){
                    lr=i;
                    lc=j;
                } else if(map[i][j]=='E'){
                    er=i;
                    ec=j;
                }
            }
        }
        
        int result1=getDistance(map,sr,sc,lr,lc);
        int result2=getDistance(map,lr,lc,er,ec);
        
        if(result1==0 || result2==0) return -1;
        return result1+result2;
    }
    
    public int getDistance(char[][] map, int startR, int startC, int endR, int endC){
        int rowLen=map.length;
        int colLen=map[0].length;
        
        int[][] distance=new int[rowLen][colLen];
        
        Queue<Node> q=new ArrayDeque<>();
        boolean visit[][]=new boolean[rowLen][colLen];
        q.offer(new Node(startR,startC));
        
        int[] dr={-1,0,1,0};
        int[] dc={0,1,0,-1};
        
        while(!q.isEmpty()){
            Node now=q.poll();
            
            for(int d=0;d<4;++d){
                int mr=now.row+dr[d];
                int mc=now.col+dc[d];
                
                if(mr<0 || mr>=rowLen || mc<0 || mc>=colLen) continue;
                if(map[mr][mc]=='X') continue;
                if(visit[mr][mc]) continue;
                visit[mr][mc]=true;
                distance[mr][mc]=distance[now.row][now.col]+1;
                q.offer(new Node(mr,mc));
            }
        }
        
        return distance[endR][endC];
    }
}