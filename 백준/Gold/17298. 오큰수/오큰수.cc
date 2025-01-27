#include <iostream>
#include <algorithm>
#include <stack>
#include <sstream>

using namespace std;

int main() {
	int N;
	scanf("%d", &N);
	int* arr = new int[N];
	for (int n = 0; n < N; ++n) {
		scanf("%d", &arr[n]);
	}

	stack<int> tempStack;
	stack<int> resultStack;
	for (int n = N - 1; n >= 0; --n) {
		while (!tempStack.empty() && arr[n] >= tempStack.top()) {
			tempStack.pop();
		}
		if (tempStack.empty()) {
			resultStack.push(-1);
		}
		else {
			resultStack.push(tempStack.top());
		}
		tempStack.push(arr[n]);
	}

	ostringstream output;
	while (!resultStack.empty()) {
		output << resultStack.top() << " ";
		resultStack.pop();
	}
	cout << output.str();

	delete[] arr;
	return 0;
}