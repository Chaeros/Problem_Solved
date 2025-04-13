#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(vector<int> schedules, vector<vector<int>> timelogs, int startday) {
    int answer = 0;
    
    int n=schedules.size();
    for(int i=0;i<n;++i){
        bool isPass=true;
        for(int d=0;d<7;++d){
            int currentday=(startday+d-1)%7;
            if(currentday==5 || currentday==6) continue;
            int time=schedules[i];
            if(((schedules[i]%100)+10)>=60){
                time+=50;
            }
            else{
                time+=10;
            }
            if(timelogs[i][d]>time){
                isPass=false;
                break;
            }
        }
        if(isPass){
            ++answer;
        }
    }
    return answer;
}