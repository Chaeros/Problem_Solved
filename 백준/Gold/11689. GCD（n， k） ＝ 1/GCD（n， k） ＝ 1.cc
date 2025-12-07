#include <iostream>

int main() {

	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	long long N;
	scanf("%lld", &N);

	long long result = N;
	long long count = N;
	for (long long p = 2;p * p <= count;++p) {
		if (N % p == 0) {
			while (N % p == 0) {
				N /= p;
			}
			result -= result / p;
		}
	}

	if (N > 1) {
		result -= result / N;
	}

	std::cout << result << std::endl;

	return 0;
}