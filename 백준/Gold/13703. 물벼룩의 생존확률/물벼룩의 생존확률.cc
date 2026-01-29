#include <iostream>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int K, N;
	std::cin >> K >> N;

	long long dp[64][128];

	dp[0][K] = 1;

	for (int i = 1;i <= N;++i) {
		for (int j = 1;j < 127;++j) {
			dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
		}
	}

	long long answer = 0;
	for (int i = 1;i < 127;++i) {
		answer += dp[N][i];
	}

	if (K == 0) std::cout << "0\n";
	else std::cout << answer << "\n";

	return 0;
}