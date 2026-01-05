#include <iostream>
#include <vector>
#include <algorithm>

int A[101][10001];

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N, M;
	std::cin >> N >> M;

	for (int n = 0;n < N;++n) {
		std::vector<int> v;
		for (int m = 0;m < M;++m) {
			std::cin >> A[n][m];
			v.push_back(A[n][m]);
		}

		std::sort(v.begin(), v.end());
		v.erase(std::unique(v.begin(), v.end()),v.end());
		for (int m = 0;m < v.size();++m) {
			A[n][m] = std::lower_bound(v.begin(), v.end(), A[n][m])-v.begin();
		}
	}

	int result = 0;
	for(int n=0;n<N;++n){
		for (int j = n + 1;j < N;++j) {
			bool isSuccess = true;
			for (int m = 0;m < M;++m) {
				if (A[n][m] != A[j][m]) {
					isSuccess = false;
					break;
				}
			}
			if (isSuccess) ++result;
		}
	}

	std::cout << result << "\n";

	return 0;
}