#include <iostream>
#include <vector>
#include <string>

int dp[30][30];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::string str;
	std::cin >> str;

	int strLen = str.length();
	for (int i = 0;i < strLen;++i) {
		dp[i][i] = 1;
	}

	for (int len = 2;len <= strLen;++len) {
		for (int left = 0;left + len-1 < strLen;++left) {
			int right = left + len - 1;

			if (str[left] == str[right]) {
				dp[left][right] = dp[left + 1][right] + dp[left][right - 1] + 1;
			}
			else {
				dp[left][right] = dp[left + 1][right] + dp[left][right - 1] - dp[left + 1][right - 1];
			}
		}
	}

	std::cout << dp[0][strLen - 1] << std::endl;

	return 0;
}