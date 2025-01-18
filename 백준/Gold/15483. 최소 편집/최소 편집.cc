#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main() {

	string text1;
	string text2;
	cin >> text1;
	cin >> text2;

	int text1Len = text1.length();
	int text2Len = text2.length();

	int** dp = new int*[text1Len+1];
	for (int i = 0; i < text1Len+1; ++i) {
		dp[i] = new int[text2Len+1];
		fill(dp[i],dp[i] + text2Len+1, 0);
	}

	for (int i = 0; i <= text1Len; ++i) {
		dp[i][0] = i;
	}
	for (int j = 0; j <= text2Len; ++j) {
		dp[0][j] = j;
	}

	for (int i = 1; i <= text1Len; ++i) {
		for (int j = 1; j <= text2Len; ++j) {

			if (text1[i-1] == text2[j-1]) {
				dp[i][j] = dp[i - 1][j - 1];
			}
			else {
				dp[i][j] = min(dp[i - 1][j], min(dp[i-1][j-1],dp[i][j - 1]))+1;
			}
		}
	}
	cout << dp[text1Len][text2Len];
	return 0;
}