#include <iostream>
#include <climits>
#include <cmath>
#include <algorithm>

int dp[2][1000001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int a, b;
	std::cin >> a >> b;
	std::fill(&dp[0][0], &dp[0][0] + 2 * 1000001, INT_MAX-100);
	dp[0][a] = 0;

	for (int i = a+1;i <= b;++i) {
		if (i - 1 > 0) dp[0][i] = std::min(dp[0][i], dp[0][i - 1] + 1);
		if (i % 2 == 0 && i > 1) dp[0][i] = std::min(dp[0][i], dp[0][i/2] + 1);

		if (i - 1 > 0) dp[1][i] = std::min(dp[1][i], dp[1][i - 1] + 1);
		if (i % 2 == 0 && i > 1) dp[1][i] = std::min(dp[1][i], dp[1][i/2] + 1);
		if (i >= 10 && i % 10 == 0) dp[1][i] = std::min(dp[1][i], dp[0][i/10] + 1);
	}

	int result = std::min(dp[0][b], dp[1][b]);
	std::cout << result << "\n";

	return 0;
}