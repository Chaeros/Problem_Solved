#include <iostream>
#include <vector>
#include <cmath>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	long long N;
	scanf("%lld", &N);

	std::vector<bool> isPrime(N + 1, true);
	std::vector<long long> list;
	isPrime[0] = isPrime[1] = false;
	unsigned long long result = 1;


	for (long long i = 2;i * i <= N;++i) {
		if (isPrime[i]) {
			for (long long j = i * i;j <= N;j+=i) {
				isPrime[j] = false;
			}
		}
	}

	for (long long i = 2;i <= N;++i) {
		if (isPrime[i]) list.push_back(i);
	}

	for (long long x : list) {
		long long temp = x;
		while (temp * x <= N) temp *= x;
		result *= temp;
	}

	std::cout << (uint32_t)result << std::endl;


	return 0;
}