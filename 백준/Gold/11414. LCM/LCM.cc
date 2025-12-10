#include <iostream>
#include <vector>
#include <cmath>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	long long A, B;
	scanf("%lld %lld", &A, &B);

	if (A == B) {
		std::cout << 1 << std::endl;
		return 0;
	}

	if (A < B) {
		long long temp = A;
		A = B;
		B = temp;
	}

	long long limit = A - B;
	std::vector<long long> list;
	for (int p = 1;p * p <= limit;++p) {
		if (limit % p == 0) {
			list.push_back(p);
			list.push_back(limit / p);
		}
	}

	long long resultLcm = INT64_MAX;
	long long resultN = -1;
	for (int x : list) {
		long long modResult = B % x;
		long long N = x - modResult;
		long long lcm = (A + N) * (B + N) / x;
		if (resultLcm >= lcm) {
			if (resultLcm == lcm && resultN > N) {
				resultLcm = lcm;
				resultN = N;
			}
			else if (resultLcm > lcm) {
				resultLcm = lcm;
				resultN = N;
			}
		}
	}
	std::cout << resultN << std::endl;
	return 0;
}