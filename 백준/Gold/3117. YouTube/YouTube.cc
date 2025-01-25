#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>

using namespace std;

const int MAX = 100001;
const int MAXLOG = 30;
int studentNumber;
int videoNumber;
int remainingTime;
int recommend[MAX][MAXLOG];

void memorization() {
    for (int i = 1; i < MAXLOG; ++i) {
        for (int j = 1; j <= videoNumber; ++j) {
            if (recommend[j][i - 1] != 0) {
                int temp = recommend[j][i - 1];
                recommend[j][i] = recommend[temp][i - 1];
            }
        }
    }
}

int getResult(int x) {
    int remainTime = remainingTime-1;
    int result = x;
    for (int i = MAXLOG-1; i >= 0; --i) {
        if (remainTime >= (1 << i) && recommend[result][i] != 0) {
            remainTime -= (1 << i);
            result = recommend[result][i];
        }
    }
    return result;
}

int main() {
    scanf("%d %d %d", &studentNumber, &videoNumber, &remainingTime);
    int* userVideo=new int[studentNumber + 1];
    for (int i = 1; i <= studentNumber; ++i) {
        scanf("%d", &userVideo[i]);
    }
    for (int i = 1; i <= videoNumber; ++i) {
        scanf("%d", &recommend[i][0]);
    }

    memorization();

    ostringstream output;
    for (int i = 1; i <= studentNumber; ++i) {
        output << getResult(userVideo[i]) << " ";
    }
    cout << output.str();
    delete[] userVideo;
    return 0;
}