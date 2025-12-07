#include <iostream>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	
	int N;
	scanf("%d", &N);

	long long result = 0;
	for (int n = 2;n <= N;++n) {
		int tResult = n;
		int temp = n;

		for (int p = 2;p * p <= temp;++p) {
			if (temp % p == 0) {
				while (temp % p == 0) temp /= p;
				tResult -= tResult / p;
			}
		}

		if (temp > 1) {
			tResult -= tResult / temp;
		}
		result += tResult;
	}

	std::cout << result << std::endl;
	return 0;
}