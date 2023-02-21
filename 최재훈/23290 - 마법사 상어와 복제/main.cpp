#include <bits/stdc++.h>

using namespace std;

const int MAX = 5;
const int sdir[5][2] = { {0, 0}, {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
const int dir[9][2] = { {0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1} };
int m, s;
int b[MAX][MAX][9], copying[MAX][MAX][9], moving[MAX][MAX][9];
pair<int, int> shark;
unordered_set<int> sm[MAX][MAX];

void rotate(int& d) {
	if (--d == 0) {
		d = 8;
	}
}

void func(int r, int c, string& path, int& max_score, int score, string& selected_path) {
	if (path.size() == 3) {
		if (score > max_score) {
			selected_path = path;
			max_score = score;
		}

		return;
	}

	for (int i = 1; i <= 4; i++) {
		int nr = r + sdir[i][0];
		int nc = c + sdir[i][1];

		if (nr >= 1 && nr <= 4 && nc >= 1 && nc <= 4) {
			int cnt = 0;
			int arr[9] = { 0, };

			for (int j = 1; j <= 8; j++) {
				cnt += b[nr][nc][j];
				arr[j] += b[nr][nc][j];
				b[nr][nc][j] = 0;
			}

			path.push_back(i + '0');
			func(nr, nc, path, max_score, score + cnt, selected_path);
			path.pop_back();

			for (int j = 1; j <= 8; j++) {
				b[nr][nc][j] += arr[j];
			}
		}
	}
}

void print_array() {
	for (int i = 1; i <= 4; i++) {
		for (int j = 1; j <= 4; j++) {
			int cnt = 0;
			for (int k = 1; k <= 8; k++) {
				cnt += b[i][j][k];
			}
			cout << cnt << ' ';
		}
		cout << '\n';
	}
	cout << '\n';
}

void simulate(int turn) {
	memset(moving, 0, sizeof(moving));

	for (int i = 1; i <= 4; i++) {
		for (int j = 1; j <= 4; j++) {
			for (int k = 1; k <= 8; k++) {
				copying[i][j][k] = b[i][j][k];
			}
		}
	}

	for (int i = 1; i <= 4; i++) {
		for (int j = 1; j <= 4; j++) {
			for (int k = 1; k <= 8; k++) {
				if (!b[i][j][k]) {
					continue;
				}

				int d = k;
				bool flag = false;

				for (int l = 0; l < 8; l++) {
					int nr = i + dir[d][0];
					int nc = j + dir[d][1];

					if (nr >= 1 && nr <= 4 && nc >= 1 && nc <= 4
						&& !(shark.first == nr && shark.second == nc)
						&& sm[nr][nc].empty()) {
						moving[nr][nc][d] += b[i][j][k];
						flag = true;
						break;
					}

					rotate(d);
				}

				if (!flag) {
					moving[i][j][k] += b[i][j][k];
				}
			}
		}
	}

	for (int i = 1; i <= 4; i++) {
		for (int j = 1; j <= 4; j++) {
			for (int k = 1; k <= 8; k++) {
				b[i][j][k] = moving[i][j][k];
			}
		}
	}

	int max_score = -1;
	string path = "";
	string selected_path;

	func(shark.first, shark.second, path, max_score, 0, selected_path);

	for (auto& i : selected_path) {
		pair<int, int> nshark = { shark.first + sdir[i - '0'][0], shark.second + sdir[i - '0'][1] };

		for (int j = 1; j <= 8; j++) {
			if (b[nshark.first][nshark.second][j]) {
				b[nshark.first][nshark.second][j] = 0;
				sm[nshark.first][nshark.second].insert(turn);
			}
		}

		shark = nshark;
	}

	for (int i = 1; i <= 4; i++) {
		for (int j = 1; j <= 4; j++) {
			if (sm[i][j].find(turn - 2) != sm[i][j].end()) {
				sm[i][j].erase(turn - 2);
			}
		}
	}

	for (int i = 1; i <= 4; i++) {
		for (int j = 1; j <= 4; j++) {
			for (int k = 1; k <= 8; k++) {
				b[i][j][k] += copying[i][j][k];
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> m >> s;

	for (int i = 0; i < m; i++) {
		int x, y, d;
		cin >> x >> y >> d;
		b[x][y][d]++;
	}

	cin >> shark.first >> shark.second;

	for (int i = 1; i <= s; i++) {
		simulate(i);
	}

	int ans = 0;
	for (int i = 1; i <= 4; i++) {
		for (int j = 1; j <= 4; j++) {
			for (int k = 1; k <= 8; k++) {
				ans += b[i][j][k];
			}
		}
	}

	cout << ans << '\n';
	return 0;
}