#include <iostream>
#include <string>
#include <vector>

int dp[1000][1000];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::string str;
	std::cin >> str;

	int strLen = str.length();
	for (int i = 0;i < strLen;++i) {
		dp[i][i] = 1;
	}

	for(int i=2;i<=strLen;++i){
		for(int l=0;l+i-1<strLen;++l){
			int r = l + i - 1;

			if (str[l] == str[r]) {
				dp[l][r] = dp[l][r - 1] + dp[l + 1][r] + 1;
			}
			else {
				dp[l][r] = dp[l][r - 1] + dp[l + 1][r] - dp[l + 1][r - 1];
			}

			dp[l][r] %= 10007;
			if (dp[l][r] < 0) dp[l][r] += 10007;
		}
	}

	std::cout << dp[0][strLen - 1] << std::endl;
	return 0;
}