#include <iostream>
#include <cmath>

long long dp[1000001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, a, b;
	std::cin >> N >> a >> b;

	std::fill(dp, dp+1000001,987654321);
	dp[0] = 0;
	for (int i = 1;i < 1000001;++i) {
		dp[i] = std::min(dp[i], dp[i - 1] + 1);
		if (i > a) {
			dp[i] = std::min(dp[i], dp[i-a-1]+1);
		}
		if (i > b) {
			dp[i] = std::min(dp[i], dp[i-b-1]+1);
		}
	}
	std::cout << dp[N] << "\n";

	return 0;
}