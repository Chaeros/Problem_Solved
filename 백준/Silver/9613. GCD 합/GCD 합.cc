#include <iostream>
#include <vector>

std::vector<int> list[100];
long total;

int gcd(int a, int b) {
	if (a < b) {
		int temp = a;
		a = b;
		b = temp;
	}
	while (b!=0) {
		int r = a % b;
		a = b;
		b = r;
	}
	return a;
}

void dfs(int n,int T,int depth, int start, int result[2]) {
	if (depth == 2) {
		total += gcd(result[0], result[1]);
		return;
	}
	
	for (int t = start;t < T;++t) {
		result[depth] = list[n][t];
		dfs(n, T, depth + 1, t+1, result);
		result[depth] = 0;
	}
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	scanf("%d", &N);
	for (int n = 0;n < N;++n) {
		int T;
		scanf("%d", &T);
		for (int t = 0;t < T;++t) {
			int x;
			scanf("%d", &x);
			list[n].push_back(x);
		}

		total = 0;
		int val[2] = { 0 };
		dfs(n, T, 0, 0, val);
		std::cout << total << std::endl;
	}
	return 0;
}