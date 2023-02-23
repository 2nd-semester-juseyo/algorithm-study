#include <bits/stdc++.h>

using namespace std;

const int MAX = 21;
const int dir[5][2] = { {0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
int n, m, ex, ans;
vector<int> b[MAX][MAX], temp[MAX][MAX];
unordered_map<int, int> sm[MAX][MAX];

struct Shark {
	int d;
	vector<int> prior[5];
};

unordered_map<int, Shark> um;

void init() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			temp[i][j].clear();
		}
	}
}

bool simulate() {
	init();

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			for (auto& k : b[i][j]) {
				auto& shark = um[k];

				unordered_map<int, pair<int, int>> cand;

				for (int l = 1; l <= 4; l++) {
					int nr = i + dir[l][0];
					int nc = j + dir[l][1];

					if (nr >= 1 && nr <= n && nc >= 1 && nc <= n && sm[nr][nc].empty()) {
						cand.insert({ l, {nr, nc} });
					}
				}

				if (cand.empty()) {
					for (int l = 1; l <= 4; l++) {
						int nr = i + dir[l][0];
						int nc = j + dir[l][1];

						if (nr >= 1 && nr <= n && nc >= 1 && nc <= n && sm[nr][nc].find(k) != sm[nr][nc].end()) {
							cand.insert({ l, {nr, nc} });
						}
					}
				}

				for (auto& l : shark.prior[shark.d]) {
					if (cand.find(l) != cand.end()) {
						shark.d = l;
						temp[cand[l].first][cand[l].second].push_back(k);
						break;
					}
				}
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (temp[i][j].size() > 1) {
				sort(temp[i][j].begin(), temp[i][j].end());
				
				while (temp[i][j].size() != 1) {
					temp[i][j].pop_back();
				}
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			b[i][j] = temp[i][j];

			if (!b[i][j].empty()) {
				sm[i][j][b[i][j][0]] = ans;
			}
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			vector<int> tmp;

			for (auto& k : sm[i][j]) {
				if (k.second == ans - ex) {
					tmp.push_back(k.first);
				}
			}

			for (auto& k : tmp) {
				sm[i][j].erase(k);
			}
		}
	}

	int cnt = 0;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cnt += b[i][j].size();
		}
	}

	return cnt == 1;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m >> ex;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			int num;
			cin >> num;
			
			if (num) {
				b[i][j].push_back(num);
				sm[i][j][num] = 0;
			}
		}
	}

	for (int i = 1; i <= m; i++) {
		int dir;
		cin >> dir;
		um[i].d = dir;
	}

	for (int i = 1; i <= m; i++) {
		auto& shark = um[i];

		for (int j = 1; j <= 4; j++) {
			for (int k = 0; k < 4; k++) {
				int dir;
				cin >> dir;
				shark.prior[j].push_back(dir);
			}
		}
	}

	while (1) {
		ans++;

		if (simulate() || ans == 1001) {
			break;
		}
	}

	cout << (ans > 1000 ? -1 : ans) << '\n';
	return 0;
}