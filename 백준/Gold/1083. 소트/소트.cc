#include <iostream>
#include <vector>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	scanf("%d", &N);
	std::vector<int> v(N, 0);

	for (int n = 0;n < N;++n) {
		scanf("%d", &v[n]);
	}

	int S;
	scanf("%d", &S);

	for (int n = 0;n < N-1;++n) {
		int maxIndex = n;

		for (int k = n+1;k < N && k - n <= S;++k) {
			if (v[maxIndex] < v[k]) {
				maxIndex = k;
			}
		}

		for (int k = maxIndex;k > n;--k) {
			std::swap(v[k],v[k-1]);
			--S;
		}
	}

	for (int n = 0;n < N;++n) {
		if (n != 0) std::cout << " ";
		std::cout << v[n];
	}

	return 0;
}