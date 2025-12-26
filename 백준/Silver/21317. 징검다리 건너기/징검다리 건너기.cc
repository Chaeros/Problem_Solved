#include <iostream>
#include <vector>
#include <cmath>

const int INF = 1e9;

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	int jumpEnergy[20][2];
	std::vector<std::vector<int>> dp(N+1,std::vector<int>(2,INF) );
	for (int n = 0;n < N-1;++n) {
		int smallJump, bigJump;
		std::cin >> smallJump >> bigJump;
		jumpEnergy[n][0] = smallJump;
		jumpEnergy[n][1] = bigJump;
	}
	int K;
	std::cin >> K;

	dp[0][0] = 0;
	for (int n = 1;n < N;++n) {
		dp[n][0] = std::min(dp[n][0],dp[n -1][0] + jumpEnergy[n - 1][0]);
		dp[n][1] = std::min(dp[n][1], dp[n - 1][1] + jumpEnergy[n - 1][0]);

		if (n >= 2) {
			dp[n][0] = std::min(dp[n][0], dp[n - 2][0] + jumpEnergy[n - 2][1]);
			dp[n][1] = std::min(dp[n][1], dp[n - 2][1] + jumpEnergy[n - 2][1]);
		}

		if (n >= 3) {
			dp[n][1] = std::min(dp[n][1], dp[n - 3][0] + K);
		}
	}

	int result = std::min(dp[N - 1][0], dp[N - 1][1]);
	std::cout << result;

	return 0;
}