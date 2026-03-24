#include <iostream>
#include <string>

std::string str;

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> str;

	int index = 0;
	int num = 1;

	while (true) {
		std::string target = std::to_string(num);
		
		for (int i = 0;i < target.size();++i) {
			if (index < str.size() && str[index] == target[i])
			{
				++index;
			}
		}

		if (index == str.size()) {
			std::cout << num << "\n";
			break;
		}
		++num;
	}

	return 0;
}