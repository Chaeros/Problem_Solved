#define _CRT_SECURE_NO_WARNINGS
#include <iostream>

class Node {
public:
	int sum;
	int exist;
};

Node tree[4000001];
int count[1000001];

void update(int node, int start, int end, int index, int diff, bool change);
Node getSum(int node, int start, int end, int nodeLeft, int nodeRight);
Node merge(Node left, Node right);
int query(int node, int start, int end);

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	int T;
	scanf("%d", &T);

	int command, vitality;
	while (T--) {
		scanf("%d %d", &command, &vitality);
		if (command == 1) {
			++count[vitality];
			if (count[vitality] == 1) {
				update(1, 1, 1000000, vitality, 1, true);
			}
			else {
				update(1, 1, 1000000, vitality, 1, false);
			}
		}
		else if (command == 2) {
			--count[vitality];
			if (count[vitality] == 0) {
				update(1, 1, 1000000, vitality, -1, true);
			}
			else {
				update(1, 1, 1000000, vitality, -1, false);
			}
		}
		int result = query(1, 1, 1000000);
		printf("%d\n", result);
	}
	return 0;
}

void update(int node, int start, int end, int index, int diff, bool change) {
	if (start==index && end==index) {
		tree[node].sum += diff;
		if(change) tree[node].exist += diff;
		return;
	}
	if (index < start || end < index) {
		return;
	}
	int mid = (start + end) / 2;
	update(node * 2, start, mid, index, diff, change);
	update(node * 2+1, mid+1, end, index, diff, change);
	tree[node] = merge(tree[node*2], tree[node*2+1]);
}

 Node getSum(int node, int start, int end, int nodeLeft, int nodeRight) {
	if (nodeLeft <= start && end <= nodeRight) {
		return tree[node];
	}
	if (nodeRight < start || end < nodeLeft) {
		return Node{ 0,0 };
	}
	int mid = (start + end) / 2;
	Node left = getSum(node * 2, start, mid, nodeLeft, nodeRight);
	Node right = getSum(node * 2 + 1, mid + 1, end, nodeLeft, nodeRight);
	return merge(left,right);
}

 Node merge(Node left, Node right) {
	 Node newNode;
	 newNode.sum = left.sum + right.sum;
	 newNode.exist = left.exist + right.exist;
	 return newNode;
 }

 int query(int node, int start, int end) {
	 if (start == end) {
		 if (tree[node].exist == 1) {
			 return tree[node].sum;
		 }
		 else {
			 return 0;
		 }
	 }
	 int mid = (start + end) / 2;
	 int leftCount = tree[node * 2].exist;
	 int leftLength = mid - start + 1;
	 if (leftCount == leftLength) {
		 int result = query(node * 2 + 1, mid + 1, end);
		 result += tree[node * 2].sum;
		 return result;
	 }
	 else return query(node * 2, start, mid);
 }