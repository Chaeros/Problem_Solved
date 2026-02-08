#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int k, n;
	std::cin >> k >> n;

	std::string bottomString;
	std::cin >> bottomString;

	std::vector<std::string> lines(n);
	int queryLine = -1;
	for (int i = 0;i < n;++i) {
		std::cin >> lines[i];
		if (lines[i][0] == '?') {
			queryLine = i;
		}
	}

	std::string upString;
	for (int i = 0;i < k;++i) {
		upString += (char)('A' + i);
	}

	for (int i = 0;i < queryLine;++i) {
		for (int j = 0;j < k - 1;++j) {
			if (lines[i][j] == '-') {
				std::swap(upString[j], upString[j + 1]);
			}
		}
	}

	for (int i = n - 1;i > queryLine;--i) {
		for (int j = 0;j < k - 1;++j) {
			if (lines[i][j] == '-') {
				std::swap(bottomString[j], bottomString[j + 1]);
			}
		}
	}

	std::string result = "";
	for (int i = 0;i < k - 1;++i){
		if (upString[i] == bottomString[i + 1] && upString[i + 1] == bottomString[i]) {
			result += '-';
			std::swap(upString[i], upString[i + 1]);
		}
		else {
			result += '*';
		}
	}

	if (upString == bottomString) {
		std::cout << result << "\n";
	}
	else {
		std::string noAnswer = "";
		for (int i = 0;i < k - 1;++i) {
			noAnswer += 'x';
		}
		std::cout << noAnswer << "\n";
	}
	return 0;
}