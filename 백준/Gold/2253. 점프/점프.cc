#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

int N, M;
const int INF = 2000000000;
std::vector<bool> exception(N + 1, false);
std::vector<std::vector<int>> dp;

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N >> M;

	exception.assign(N + 1, false);
	dp.assign(N + 1, std::vector<int>(151, INF));

	for (int m = 0;m < M;++m) {
		int x;
		std::cin >> x;
		exception[x] = true;
	}

	if (!exception[2]) {
		dp[2][1] = 1;
	}

	for (int n = 2;n <= N;++n) {
		if (exception[n] == true) continue;

		for (int v = 1;v <= 150;++v) {
			if (dp[n][v] == INF) continue;

			for (int k = v - 1;k <= v + 1;++k) {
				if (k < 1) continue;

				int next = n + k;
				if (next > N) continue;
				if (exception[next] == true) continue;

				dp[next][k] = std::min(dp[next][k], dp[n][v]+1);
			}
		}
	}

	int answer = INF;
	for (int i = 0; i <= 150;++i) {
		answer = std::min(answer, dp[N][i]);
	}

	if (answer == INF) {
		std::cout << "-1\n";
	}
	else {
		std::cout << answer << "\n";
	}

	return 0;
}