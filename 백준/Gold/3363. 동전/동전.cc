#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int data[3][8];
	char sign[3];
	for (int i = 0;i < 3;++i) {
		scanf("%d %d %d %d %c %d %d %d %d", 
			&data[i][0], &data[i][1], &data[i][2], &data[i][3], &sign[i], &data[i][4], &data[i][5], &data[i][6], &data[i][7]);
	}

	std::vector<int> arr(12,10);
	std::vector<std::pair<int,char>> result;

	char resultSign = '.';
	int resultVal = 0;
	for (int i = 0;i < 24;++i) {
		arr[i / 2] += (i % 2==1) ? 1 : -1;

		bool isEnd = true;
		for (int d = 0;d < 3;++d) {
			int sumA = 0, sumB = 0;;

			for (int j = 0;j < 4;++j) {
				sumA += arr[data[d][j]-1];
				sumB += arr[data[d][j+4]-1];
			}

			if (sign[d] == '<') {
				if (!(sumA < sumB)) {
					isEnd = false;
				}
			}
			else if (sign[d] == '>') {
				if (!(sumA > sumB)) {
					isEnd = false;
				}
			}
			else if (sign[d] == '=') {
				if (!(sumA == sumB)) {
					isEnd = false;
				}
			}
		}
		if (isEnd == true) {
			resultSign = (i % 2 == 1) ? '+' : '-';
			resultVal = (i / 2);
			result.push_back({ resultVal+1,resultSign });
		}
		arr[i / 2] -= (i % 2 == 1) ? 1 : -1;
	}

	if (result.size() == 0) std::cout << "impossible" << std::endl;
	else if (result.size() == 1) std::cout << result[0].first << result[0].second << std::endl;
	else if (result.size() > 1) std::cout << "indefinite" << std::endl;

	return 0;
}