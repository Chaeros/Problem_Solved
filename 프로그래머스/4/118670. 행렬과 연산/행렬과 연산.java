import java.util.*;

class Solution {
    ArrayDeque<Integer> frontCol = new ArrayDeque<>();
    ArrayDeque<Integer> lastCol = new ArrayDeque<>();
    ArrayDeque<Integer>[] rows;
    int colCount=0;
    int rowCount=0;
    int index=0;
    
    public int[][] solution(int[][] rc, String[] operations) {
        init(rc);
        runOperations(operations);
        
        int[][] answer = new int[rowCount][colCount];
        for(int r=0;r<rowCount;++r){
            answer[r][0]=frontCol.pollFirst();
            for(int c=1;c<colCount-1;++c){
                answer[r][c]=rows[(index+r)%rowCount].pollFirst();
            }
            answer[r][colCount-1]=lastCol.pollFirst();
        }
        return answer;
    }
    
    public void runOperations(String[] operations){
        index=0;
        for(String operation:operations){
            if(operation.equals("ShiftRow")){
                frontCol.addFirst(frontCol.pollLast());
                lastCol.addFirst(lastCol.pollLast());
                --index;
                if(index==-1) index=rowCount-1;
            }
            else if(operation.equals("Rotate")){
                rows[index].addFirst(frontCol.pollFirst());
                lastCol.addFirst(rows[index].pollLast());
                rows[(index+rowCount-1)%rowCount].addLast(lastCol.pollLast());
                frontCol.addLast(rows[(index+rowCount-1)%rowCount].pollFirst());
            }
        }
    }
    
    public void init(int[][] rc){
        rowCount = rc.length;
        colCount = rc[0].length;
        rows = new ArrayDeque[rowCount];
        for(int r=0;r<rowCount;++r){
            rows[r]=new ArrayDeque<>();
        }
        
        frontCol.addLast(rc[0][0]);
        for(int r=1;r<rowCount-1;++r){
            frontCol.addLast(rc[r][0]);
        }
        frontCol.addLast(rc[rowCount-1][0]);
        
        lastCol.addLast(rc[0][colCount-1]);
        for(int r=1;r<rowCount-1;++r){
            lastCol.addLast(rc[r][colCount-1]);
        }
        lastCol.addLast(rc[rowCount-1][colCount-1]);
        
        for(int r=0;r<rowCount;++r){
            for(int c=1;c<colCount-1;++c){
                rows[r].addLast(rc[r][c]);
            }
        }
    }
}