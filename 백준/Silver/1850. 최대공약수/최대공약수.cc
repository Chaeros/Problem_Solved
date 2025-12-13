#include <iostream>
#include <cmath>

long long gcd(long long a, long long b) {
	if (a < b) {
		long long temp = a;
		a = b;
		b = temp;
	}
	while (b != 0) {
		long long r = a % b;
		a = b;
		b = r;
	}
	return a;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	long long A, B;

	scanf("%lld %lld", &A, &B);

	long long val = gcd(A, B);

	std::string result = "";
	for (int i = 0;i < val;++i) {
		result += '1';
	}

	std::cout << result << std::endl;
	return 0;
}