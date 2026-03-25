#include <iostream>
#include <vector>

using namespace std;

int C, P;
vector<int> heights;

vector<vector<int>> rec[7] = { 
	{{0}, {0,0,0,0}},
	{{0,0}},
	{{0,0,1}, {1,0}},
	{{1,0,0}, {0,1}},
	{{0,0,0}, {0,1}, {1,0,1}, {1,0}},
	{{0,0,0}, {0,0}, {0,1,1}, {2,0}},
	{{0,0,0}, {0,2}, {1,1,0}, {0,0}}
};

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	cin >> C >> P;
	heights.resize(C);
	for (int c = 0;c < C;++c) {
		cin >> heights[c];
	}

	int result = 0;
	int i = P - 1;
	for (int j = 0;j < rec[i].size();++j) {
		for (int d = 0;d <= C - rec[i][j].size();++d)
		{
			int pre = heights[d] - rec[i][j][0];
			bool pass = true;
			for (int c = 0;c < rec[i][j].size();++c) {
				if (heights[d+c] - rec[i][j][c] != pre) {
					pass = false;
					break;
				}
			}
			if (pass == true) ++result;
		}
	}

	std::cout << result << "\n";
	return 0;
}