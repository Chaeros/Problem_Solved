#include <iostream>

int A[1000001];
int main() 
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int T;
	std::cin >> T;
	for (int t = 0;t < T;++t) {
		int N;
		std::cin >> N;
		for(int i=0;i<N;++i){
			std::cin >> A[i];
		}

		int max = A[N - 1];
		long long result = 0;
		for (int i = N - 1;i >= 0;--i) {
			if (max < A[i]) max = A[i];
			else {
				result += max - A[i];
			}
		}
		std::cout << result << "\n";
	}

	return 0;
}