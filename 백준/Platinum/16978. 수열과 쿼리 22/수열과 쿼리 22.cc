#include <iostream>
#include <vector>

class Node {
public:
	long long sum;
	Node* left;
	Node* right;
	Node() : sum(0), left(nullptr), right(nullptr) {}
	Node(long long sum, Node* left, Node* right) 
		:sum(sum),left(left),right(right) {}
};
std::vector<int> A;
std::vector<Node*> root;
Node pool[4000000];
int poolIndex = 0;

Node* newNode(long long sum, Node* left, Node* right) {
	pool[poolIndex] = Node(sum, left, right);
	return &pool[poolIndex++];
}

Node* makeTree(int start, int end) {
	if (start == end) {
		return newNode(A[start], nullptr, nullptr);
	}
	int mid = (start + end) / 2;
	Node* left = makeTree(start, mid);
	Node* right = makeTree(mid + 1, end);
	return newNode(left->sum + right->sum, left, right);
}

Node* updateTree(Node* node, int start, int end, int index, int diff) {
	if (start == end) {
		return newNode(diff, nullptr, nullptr);
	}
	int mid = (start + end) / 2;
	if (index <= mid) {
		Node* left = updateTree(node->left, start, mid, index, diff);
		return newNode(left->sum + node->right->sum, left, node->right);
	}
	else {
		Node* right = updateTree(node->right, mid + 1, end, index, diff);
		return newNode(node->left->sum + right->sum, node->left, right);
	}
}

long long getQuery(Node* node, int start, int end, int left, int right) {
	if (right < start || end < left) return 0;
	if (left <= start && end <= right) {
		return node->sum;
	}
	int mid = (start + end) / 2;
	long long leftSum = getQuery(node->left, start, mid, left, right);
	long long rightSum = getQuery(node->right, mid + 1, end, left, right);
	return leftSum + rightSum;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	int N;
	std::cin >> N;
	A.resize(N + 1);
	for (int n = 1;n <= N;++n) std::cin >> A[n];

	Node* rootNode = makeTree(1, N);
	root.push_back(rootNode);

	int M;
	std::cin >> M;

	int updateCount = 0;
	while (M--) {
		int command;
		std::cin >> command;

		if (command == 1) {
			int index;
			long long diff;
			std::cin >> index >> diff;

			Node* updateNode = updateTree(root[updateCount], 1, N, index, diff);
			root.push_back(updateNode);
			++updateCount;
		}
		else if (command == 2) {
			int index;
			int start;
			int end;
			std::cin >> index >> start >> end;

			long long result = getQuery(root[index], 1, N, start, end);
			//std::cout << result << '\n';
			printf("%lld\n", result);
		}
	}

	return 0;
}