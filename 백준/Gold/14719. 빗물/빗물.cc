#include <iostream>
#include <vector>

int H, W;
bool map[501][501] = { false };

void drawMap(int h, int w) {
	for (int i = 0;i < h;++i) {
		map[H-i][w] = true;
	}
}

int getWaterCount(int height) {
	int result = 0;

	int wall = -1;
	int temp = 0;
	for (int w = 0;w < W;++w) {
		if (map[H - height][w] == true) {
			if (wall != -1 || wall != w - 1) {
				result += temp;
			}
			wall = w;
			temp = 0;
		}
		else {
			if (wall != -1) {
				++temp;
			}
		}
	}
	return result;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);

	std::cin >> H >> W;

	for (int w = 0;w < W;++w) {
		int height;
		std::cin >> height;
		drawMap(height, w);
	}

	int result = 0;
	for (int h = 0;h < H;++h) {
		result += getWaterCount(h);
	}
	std::cout << result << "\n";

	return 0;
}