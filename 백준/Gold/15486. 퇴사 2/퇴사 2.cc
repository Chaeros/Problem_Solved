#include <iostream>
#include <cmath>

int N;
int T[1500001];
int P[1500001];
long long dp[1500001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N;
	for (int n = 0;n < N;++n) {
		std::cin >> T[n] >> P[n];
	}

	for (int i = 0;i < N;++i) {
		dp[i+1] = std::max(dp[i + 1], dp[i]);
		
		int next = i + T[i];
		if (next <= N) {
			dp[next] = std::max(dp[next], dp[i] + P[i]);
		}
	}

	std::cout << dp[N] << "\n";
	return 0;
}