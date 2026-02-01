#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
	long long sum;
	int left;
	int right;
	Node() :sum(0), left(0), right(0) {}
	Node(long long sum, int left, int right)
		:sum(sum), left(left), right(right) {}
};

const int MAX_N = 100005;
const int MAX_VAL = 1000000000;
Node tree[MAX_N * 40];
int root[MAX_N];
int nodeCount = 0;
int N;

int updateTree(int prev, int start, int end, int val) {
	int current = ++nodeCount;
	tree[current] = tree[prev];
	tree[current].sum += val;

	if (start == end) return current;
	int mid = start + (end - start) / 2;
	if (val <= mid) {
		tree[current].left = updateTree(tree[prev].left, start, mid, val);
	}
	else {
		tree[current].right = updateTree(tree[prev].right, mid + 1, end, val);
	}
	return current;
}

long long query(int left, int right, int start, int end, int val) {
	if (val <= 0) return 0;
	if (end <= val) return tree[right].sum - tree[left].sum;

	long long ans = 0;
	int mid = start + (end - start) / 2;
	ans = query(tree[left].left, tree[right].left, start, mid, val);
	if (mid < val) {
		ans += query(tree[left].right, tree[right].right, mid + 1, end, val);
	}
	return ans;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	cin >> N;
	for (int n = 1;n <= N;++n) {
		int x;
		cin >> x;
		root[n] = updateTree(root[n - 1], 1, MAX_VAL, x);
	}

	int M;
	int left, right;
	cin >> M;
	for (int m = 0;m < M;++m) {
		cin >> left >> right;

		long long ans = 0;
		while (true) {
			int next = query(root[left-1], root[right], 1, MAX_VAL, ans + 1);
			if (ans < next) {
				ans = next;
			}
			else {
				break;
			}
		}
		cout << ans+1 << "\n";
	}

	return 0;
}