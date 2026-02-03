#include <iostream>
#include <cstring>

using namespace std;

const string str1 = "moo";
long long dp[500];

void solve(int n, long long m) {
	if (n == 0) {
		std::cout << str1[m-1] << "\n";
		return;
	}

	if (m <= dp[n - 1] ) {
		solve(n - 1, m);
	}
	else if (dp[n - 1] < m && m <= dp[n - 1] + n + 3) {
		if (dp[n - 1] + 1 == m) {
			std::cout << "m\n";
		}
		else {
			std::cout << "o\n";
		}
		return;
	}
	else if (m > dp[n - 1] + n + 3) {
		solve(n - 1, m - (dp[n - 1] + n + 3));
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	dp[0] = 3;

	int i = 1;
	while (true) {
		dp[i] = dp[i - 1] * 2 + i + 3;
		if (dp[i] > N) break;
		++i;
	}
	solve(i, N);

	return 0;
}