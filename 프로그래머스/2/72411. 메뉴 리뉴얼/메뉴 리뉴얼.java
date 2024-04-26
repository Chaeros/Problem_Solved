import java.util.*;

class Solution {
    
    public char temp[];
    public int objective;
    public String tempStr;
    public Map<String,Integer>[] map = new HashMap[11];
    public int maxVal[] = new int[11];
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        for ( int i = 0 ; i < 11 ; ++i ){
            map[i] = new HashMap<>();   
        }
        
        for (String str : orders){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            tempStr = String.valueOf(arr);
            for (int x:course){
                objective = x;
                temp = new char[objective];
                dfs(0,0);
            }
        }
        
        List<String> list = new ArrayList<>();
        for ( int x : course ){
            for (Map.Entry<String, Integer> entry : map[x].entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                if ( value >= 2 && value == maxVal[x] ){
                    list.add(key);
                }
            }
        }
        Collections.sort(list);
        answer = new String[list.size()];
        for ( int i = 0 ; i < list.size() ; ++i ){
            answer[i] = list.get(i);   
        }
        
        for ( int i = 1 ; i < 11 ; ++i ){
            System.out.println(i+":"+maxVal[i]);   
        }
        return answer;
    }
    
    public void dfs(int depth, int index){
        if (depth == objective){
            int strLength = objective;
            if ( map[strLength].containsKey(String.valueOf(temp)) ){
                map[strLength].put(String.valueOf(temp),map[strLength].get(String.valueOf(temp))+1);
                maxVal[strLength] = Math.max(maxVal[strLength],map[strLength].get(String.valueOf(temp)));
                System.out.println("이미 있는거-[" + strLength +"]"+String.valueOf(temp)+","+map[strLength].get(String.valueOf(temp)));
            }
            else{
                map[strLength].put(String.valueOf(temp),1);
                maxVal[strLength] = Math.max(maxVal[strLength],map[strLength].get(String.valueOf(temp)));
                System.out.println("없음-[" + strLength +"]"+String.valueOf(temp)+","+map[strLength].get(String.valueOf(temp)));
            }
            
           return;
        }
        
        for (int i = index ; i < tempStr.length() ; ++i ){
            temp[depth] = tempStr.charAt(i);
            dfs(depth+1,i+1);
        }
        
    }
}