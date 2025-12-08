#include <iostream>

int gcd(int a, int b) {
	if (a < b) {
		int temp = a;
		a = b;
		b = temp;
	}
	while (b != 0) {
		int r = a % b;
		a = b;
		b = r;
	}
	return a;
}

int main() {
	int N;
	scanf("%d", &N);
	
	for (int n = 0;n < N;++n) {
		int x, y;
		scanf("%d %d", &x, &y);
		long long gcdVal = gcd(x, y);
		
		long long lcmVal = x / gcdVal * y;
		std::cout << lcmVal << std::endl;
	}
	return 0;
}