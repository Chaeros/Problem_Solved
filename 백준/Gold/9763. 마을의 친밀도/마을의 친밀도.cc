#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cmath>
#include <climits>

int familiarity(int d1, int d2);

int N;
int arr[10001][3];
int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	scanf("%d", &N);
	int x, y, z;
	for (int n = 1;n <= N;++n) {
		scanf("%d %d %d", &x, &y, &z);
		arr[n][0] = x;
		arr[n][1] = y;
		arr[n][2] = z;
	}

	int result = INT_MAX;
	for (int i = 1;i <= N;++i) {
		int minValue1 = INT_MAX;
		int minValue2 = INT_MAX;
		for (int j = 1;j <= N;++j) {
			if (i == j) continue;

			int distance = familiarity(i, j);

			if (minValue1 > distance) {
				minValue2 = minValue1;
				minValue1 = distance;
			}
			else if (minValue2 > distance) {
				minValue2 = distance;
			}
		}
		result = std::min(minValue1 + minValue2, result);
	}
	std::cout << result;
}

int familiarity(int d1,int d2) {
	return abs(arr[d1][0] - arr[d2][0])
		+ abs(arr[d1][1] - arr[d2][1])
		+ abs(arr[d1][2] - arr[d2][2]);
}