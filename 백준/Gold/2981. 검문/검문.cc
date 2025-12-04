#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>

int gcd(int a, int b) {
	while (b != 0) {
		int r = a % b;
		a = b;
		b = r;
	}
	return a;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::vector<int> arr;
	scanf("%d", &N);
	for (int n = 0;n < N;++n) {
		int x;
		scanf("%d", &x);
		arr.push_back(x);
	}
	std::sort(arr.begin(), arr.end());

	int rgcd = arr[1] - arr[0];
	for (int i = 2;i < N;++i) {
		rgcd = gcd(rgcd, arr[i] - arr[i - 1]);
	}

	std::vector<int> result;
	for (int i = 2; i <= sqrt(rgcd);++i) {
		if (rgcd % i == 0) {
			result.push_back(i);
			if(i != (rgcd/i)) result.push_back(rgcd / i);
		}
	}
	result.push_back(rgcd);

	std::sort(result.begin(), result.end());
	for (int i = 0;i < result.size();++i) {
		std::cout << result[i] << " ";
	}

	return 0;
}