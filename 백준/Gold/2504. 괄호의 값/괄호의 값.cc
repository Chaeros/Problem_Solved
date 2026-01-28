#include <iostream>
#include <stack>

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::string str;
	std::cin >> str;

	std::stack<char> stack;
	int result = 0;
	int temp = 1;
	for (int i = 0;i < str.length();++i) {
		if (str[i] == '(') {
			temp *= 2;
			stack.push('(');
		}
		else if (str[i] == ')') {
			if (stack.empty() || stack.top() == '[') {
				result = 0;
				break;
			}
			else if (str[i - 1] == '(') {
				result += temp;
			}
			temp /= 2;
			stack.pop();
		}
		else if (str[i] == '[') {
			temp *= 3;
			stack.push('[');
		}
		else if(str[i]==']') {
			if (stack.empty() || stack.top() == '(') {
				result = 0;
				break;
			}
			else if (str[i - 1] == '[') {
				result += temp;
			}
			temp /= 3;
			stack.pop();
		}
	}
	if (!stack.empty()) std::cout << 0 << "\n";
	else std::cout << result << "\n";
	return 0;
}