#include <bits/stdc++.h>

using namespace std;

const int MAX = 21;
const int dir[4][2] = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
int n, m, ans;
int b[MAX][MAX], temp[MAX][MAX];
bool visited[MAX][MAX];

class Data {
public:
	int rainbow_cnt = 0;
	vector<pair<int, int>> group;

	bool operator<(const Data& data);
};

bool Data::operator<(const Data& data) {
	if (group.size() == data.group.size()) {
		if (group.empty()) {
			return false;
		}

		if (rainbow_cnt == data.rainbow_cnt) {
			if (group[0].first == data.group[0].first) {
				return group[0].second < data.group[0].second;
			}

			return group[0].first < data.group[0].first;
		}

		return rainbow_cnt < data.rainbow_cnt;
	}

	return group.size() < data.group.size();
}

Data bfs(int r, int c, int color) {
	Data data;

	queue<pair<int, int>> q;
	vector<pair<int, int>> rainbow;

	q.push({ r, c });
	visited[r][c] = true;

	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();

		data.group.push_back({ r, c });

		if (!b[r][c]) {
			rainbow.push_back({ r, c });
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (nr >= 1 && nr <= n && nc >= 1 && nc <= n 
				&& !visited[nr][nc] && (b[nr][nc] == color || !b[nr][nc])) {
				visited[nr][nc] = true;
				q.push({ nr, nc });
			}
		}
	}

	for (auto& i : rainbow) {
		visited[i.first][i.second] = false;
	}

	data.rainbow_cnt = rainbow.size();

	return data;
}

void drop() {
	for (int i = 1; i <= n; i++) {
		for (int j = n; j >= 1; j--) {
			if (b[j][i] >= 0) {
				int pos = j;

				while (1) {
					if (pos + 1 <= n && b[pos + 1][i] == -2) {
						pos++;
					}
					else {
						break;
					}
				}

				swap(b[j][i], b[pos][i]);
			}
		}
	}
}

void rotate() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			temp[n - j + 1][i] = b[i][j];
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			b[i][j] = temp[i][j];
		}
	}
}

bool simulate() {
	memset(visited, false, sizeof(visited));
	Data data;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (b[i][j] >= 1 && !visited[i][j]) {
				auto res = bfs(i, j, b[i][j]);

				if (data < res) {
					data = res;
				}
			}
		}
	}

	if (data.group.size() <= 1) {
		return false;
	}

	ans += pow(data.group.size(), 2);

	for (auto& i : data.group) {
		b[i.first][i.second] = -2;
	}

	drop();
	rotate();
	drop();

	return true;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> b[i][j];
		}
	}

	while (simulate());

	cout << ans << '\n';
	return 0;
}