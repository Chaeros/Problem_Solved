#include <iostream>

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

long long lcm(long long a, long long b) {
	return a / gcd(a, b) * b;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	long long A, B;
	scanf("%lld %lld", &A, &B);

	std::cout << lcm(A, B);
	return 0;
}