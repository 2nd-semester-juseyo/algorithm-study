#include <bits/stdc++.h>

using namespace std;

const int MAX = 4;
const int dir[9][2] = { {0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1} };
int ans = 0;
vector<vector<pair<int, int>>> space(4, vector<pair<int, int>>(4));

void rotate(int& d) {
	if (++d == 9) {
		d = 1;
	}
}

void move(vector<vector<pair<int, int>>>& s, pair<int, int> p) {
	for (int i = 1; i <= 16; i++) {
		bool flag = false;
		pair<int, int> pos;

		for (int j = 0; !flag && j < 4; j++) {
			for (int k = 0; !flag && k < 4; k++) {
				if (s[j][k].first == i) {
					pos = { j, k };
					flag = true;
				}
			}
		}

		if (!flag) {
			continue;
		}

		for (int j = 0; j < 8; j++) {
			int nr = pos.first + dir[s[pos.first][pos.second].second][0];
			int nc = pos.second + dir[s[pos.first][pos.second].second][1];

			if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !(p.first == nr && p.second == nc)) {
				auto tmp = s[pos.first][pos.second];
				s[pos.first][pos.second] = s[nr][nc];
				s[nr][nc] = tmp;
				break;
			}

			rotate(s[pos.first][pos.second].second);
		}
	}
}

void func(vector<vector<pair<int, int>>> s, pair<int, int> p, int score, int d) {
	ans = max(ans, score);
	move(s, p);

	for (int i = 1; i <= 3; i++) {
		int nr = p.first + dir[d][0] * i;
		int nc = p.second + dir[d][1] * i;

		if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && s[nr][nc].first) {
			auto tmp = s[nr][nc];
			s[nr][nc] = { 0, 0 };
			func(s, { nr, nc }, score + tmp.first, tmp.second);
			s[nr][nc] = tmp;
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			int a, b;
			cin >> a >> b;
			space[i][j] = { a, b };
		}
	}

	int score = space[0][0].first;
	int direction = space[0][0].second;

	space[0][0] = { 0, 0 };

	func(space, { 0, 0 }, score, direction);
	cout << ans << '\n';
	return 0;
}