#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>

using namespace std;

const int MAX = 200001;
const int MAXLOG = 20;
int M;
int ac[MAX][MAXLOG];

void makeTable() {
	for (int i = 1; i < MAXLOG; ++i) {
		for (int j = 1; j <= M; ++j) {
			int temp = ac[j][i - 1];
			if (temp != 0) {
				ac[j][i] = ac[temp][i - 1];
			}
		}
	}
}

int getResult(int n, int x) {
	int remainMoveCount = n;
	for (int i = MAXLOG - 1; i >= 0; --i) {
		if (remainMoveCount < (1<<i)) continue;
		remainMoveCount -= (1<<i);
		x = ac[x][i];
	}
	return x;
}

int main() {
	scanf("%d", &M);
	for (int m = 1; m <= M; ++m) {
		scanf("%d", &ac[m][0]);
	}

	makeTable();

	ostringstream output;

	int Q;
	scanf("%d", &Q);
	for (int q = 0; q < Q; ++q) {
		int n, x;
		scanf("%d %d", &n, &x);
		output << getResult(n, x) << "\n";
	}
	cout << output.str();

	return 0;
}