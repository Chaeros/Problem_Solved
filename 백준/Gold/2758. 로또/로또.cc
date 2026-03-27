#include <iostream>
#include <cmath>
#include <algorithm>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int T;
	std::cin >> T;

	long long dp[11][2001];

	std::fill(dp[1], dp[1] + 2001, 1);
	for (int i = 2;i < 11;++i) {
		std::fill(dp[i], dp[i] + 2001, 0);
	}

	for (int x = 1;x < 2001;++x) {
		for (int n = 2;n < 11;++n) {
			for (int a = 1;a <= x / 2;++a) {
				dp[n][x] += dp[n - 1][a];
			}
		}
	}

	for (int t = 0;t < T;++t) {
		int n, m;
		std::cin >> n >> m;

		long long result = 0;
		for (int i = 1;i <= m;++i) {
			result += dp[n][i];
		}

		std::cout << result << "\n";
	}

	return 0;
}