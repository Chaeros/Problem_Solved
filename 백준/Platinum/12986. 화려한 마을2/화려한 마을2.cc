#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int A[100001];
int count[200005];
int answer[100001];
int frequency[100001];
int currentMaxCountNumber = 0;
int N, Q;
int rootN;
class Node {
public:
	int left;
	int right;
	int index;
	Node(int left,int right, int index)
		:left(left), right(right), index(index) {
	}
	bool operator<(const Node& other) const {
		if (this->left / rootN != other.left / rootN) return this->left / rootN < other.left / rootN;
		return this->right < other.right;
	}
};

int getMaxCount() {
	int result = 0;
	for (int i = 1;i <= 200000;++i) {
		result = std::max(result, count[i]);
	}
	return result;
}

void add(int x) {
	if (count[x] != 0) --frequency[count[x]];
	++count[x];
	++frequency[count[x]];
	currentMaxCountNumber = std::max(currentMaxCountNumber, count[x]);
}

void remove(int x) {
	if (count[x] == currentMaxCountNumber) {
		if (frequency[count[x]] == 1) {
			--currentMaxCountNumber;
		}
	}
	--frequency[count[x]];
	--count[x];
	++frequency[count[x]];
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> N >> Q;
	for (int n = 1;n <= N;++n) {
		int x;
		std::cin >> x;
		A[n] = x + 100001;
	}
	rootN = std::sqrt(N);

	std::vector<Node> queries;
	for (int q = 0;q < Q;++q) {
		int left, right;
		std::cin >> left >> right;
		queries.push_back(Node(left, right, q));
	}
	std::sort(queries.begin(), queries.end());

	int leftIndex = 1;
	int rightIndex = 0;
	
	for (Node query : queries) {
		while (leftIndex < query.left) remove(A[leftIndex++]);
		while (query.left < leftIndex) add(A[--leftIndex]);
		while (rightIndex < query.right) add(A[++rightIndex]);
		while (query.right < rightIndex) remove(A[rightIndex--]);

		answer[query.index] = currentMaxCountNumber;
	}

	for (int q = 0;q < Q;++q) {
		std::cout << answer[q] << "\n";
	}

	return 0;
}