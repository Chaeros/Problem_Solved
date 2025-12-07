#include <iostream>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	while (true) {
		long long input;
		scanf("%lld", &input);
		if (input == 0) break;
		if (input == 1) {
			std::cout << 0 << std::endl; 
			continue;
		}

		long long temp = input;
		long long result = input;

		for (long long p = 2;p * p <= temp;++p) {
			if (temp % p == 0) {
				while (temp % p == 0) temp /= p;
				result -= result / p;
			}
		}

		if (temp > 1) result -= result / temp;

		std::cout << result << std::endl;
	}

	return 0;
}