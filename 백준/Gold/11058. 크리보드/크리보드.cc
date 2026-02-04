#include <iostream>
#include <cmath>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	long long dp[101];

	for (int i = 1;i < 101;++i) {
		dp[i] = dp[i - 1] + 1;

		if (i < 4) continue;
		for (int j = i-3;j >0;--j) {
			dp[i] = std::max(dp[i], dp[j] * (i - j - 1));
		}
	}

	std::cout << dp[N] << std::endl;

	return 0;
}