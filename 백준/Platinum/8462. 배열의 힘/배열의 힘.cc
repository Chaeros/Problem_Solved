#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

int n, t;
int A[1000001];
int count[1000001];
long long answer[100001];
int rootN;
long long currentResult = 0;
class Node {
public:
	int left;
	int right;
	int index;
	Node(int left, int right, int index) {
		this->left = left;
		this->right = right;
		this->index = index;
	}
	bool operator<(const Node& other) const {
		if (this->left / rootN != other.left / rootN) {
			return this->left / rootN < other.left / rootN;
		}
		return this->right < other.right;
	}
};

void add(int x) {
	currentResult -= 1LL * count[x] * count[x] * x;
	++count[x];
	currentResult += 1LL * count[x] * count[x] * x;
}

void remove(int x) {
	currentResult -= 1LL * count[x] * count[x] * x;
	--count[x];
	currentResult += 1LL * count[x] * count[x] * x;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> n >> t;
	rootN = std::sqrt(n);
	for (int i = 1;i <= n;++i) {
		std::cin >> A[i];
	}

	std::vector<Node> queries;
	for (int i = 0;i < t;++i) {
		int left, right;
		std::cin >> left >> right;
		queries.push_back(Node(left, right, i));
	}

	std::sort(queries.begin(), queries.end());
	
	int leftIndex = 1;
	int rightIndex = 0;

	for (Node query : queries) {
		while (leftIndex < query.left) remove(A[leftIndex++]);
		while (query.left < leftIndex) add(A[--leftIndex]);
		while (rightIndex < query.right) add(A[++rightIndex]);
		while (query.right < rightIndex) remove(A[rightIndex--]);
	
		answer[query.index] = currentResult;
	}

	for (int i = 0;i < t;++i) {
		std::cout << answer[i] << "\n";
	}

	return 0;
}