#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>

using namespace std;

int N;
const int MAX = 100001;
const int MAXLOG = 17;
int ac[MAX][MAXLOG];
int depth[MAX];
int length[MAX][MAXLOG];
int energy[MAX];
bool visit[MAX];
vector<pair<int,int>> graph[MAX];

void makeTree(int current, int depthSize) {
	depth[current] = depthSize;
	visit[current] = true;

	for (int i = 0; i < graph[current].size(); ++i) {
		pair<int,int> now= graph[current][i];
		if (visit[now.first]) continue;
		visit[now.first] = true;
		ac[now.first][0] = current;
		length[now.first][0] = now.second;
		makeTree(now.first, depthSize + 1);
	}
}

void makeTable() {
	for (int i = 1; i < MAXLOG; ++i) {
		for (int j = 1; j <= N; ++j) {
			if (ac[j][i - 1] != 0) {
				int temp = ac[j][i - 1];
				ac[j][i] = ac[temp][i - 1];
				length[j][i] = length[j][i - 1] + length[temp][i - 1];
			}
		}
	}
}

int getResult(int x) {
	int remainEnergy = energy[x];
	int resultNode = x;
	for (int i = MAXLOG-1; i >= 0; --i) {
		if (ac[x][i] == 0 || remainEnergy < length[x][i]) continue;
		remainEnergy -= length[x][i];
		resultNode = ac[x][i];
		//cout<<"("<< x << "," << i << ") remainEnegy=" << remainEnergy << "length[x][i]"<< length[x][i]<<endl;
		x = ac[x][i];
	}
	return resultNode;
}

int main() {
	scanf("%d", &N);

	for (int n = 1; n <= N; ++n) {
		scanf("%d", &energy[n]);
	}

	for (int n = 0; n < N - 1; ++n) {
		int start, end, distance;
		scanf("%d %d %d", &start, &end, &distance);
		graph[start].push_back({ end,distance });
		graph[end].push_back({ start,distance });
	}

	makeTree(1, 0);
	makeTable();

	ostringstream output;
	for (int n = 1; n <= N; ++n) {
		output << getResult(n) << "\n";
	}
	cout << output.str();
	return 0;
}