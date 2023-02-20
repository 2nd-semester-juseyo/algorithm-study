#include <bits/stdc++.h>

using namespace std;

const int MAX = 6;
const int dir[4][2] = { {0, 1}, {0, -1}, {1, 0}, {-1, 0} };
int n, q, sz;
int b[1 << MAX + 1][1 << MAX + 1];
int temp[1 << MAX + 1][1 << MAX + 1];
bool visited[1 << MAX + 1][1 << MAX + 1];
vector<int> queries;

int bfs(int sr, int sc) {
	queue<pair<int, int>> q;
	q.push({ sr, sc });
	visited[sr][sc] = true;

	int ret = 0;

	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();
		ret++;

		for (int i = 0; i < 4; i++) {
			int nr = r + dir[i][0];
			int nc = c + dir[i][1];

			if (nr >= 1 && nr <= sz && nc >= 1 && nc <= sz && !visited[nr][nc] && b[nr][nc]) {
				visited[nr][nc] = true;
				q.push({ nr, nc });
			}
		}
	}

	return ret;
}

void simulate(int l) {
	memset(temp, false, sizeof(temp));

	int len = 1 << l;

	for (int r = 1; r <= sz; r += len) {
		for (int c = 1; c <= sz; c += len) {
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					temp[r + j][c + len - i - 1] = b[r + i][c + j];
				}
			}
		}
	}

	for (int i = 1; i <= sz; i++) {
		for (int j = 1; j <= sz; j++) {
			if (!temp[i][j]) {
				b[i][j] = 0;
				continue;
			}

			int cnt = 0;

			for (int k = 0; k < 4; k++) {
				int nr = i + dir[k][0];
				int nc = j + dir[k][1];

				if (nr >= 1 && nr <= sz && nc >= 1 && nc <= sz && temp[nr][nc]) {
					cnt++;
				}
			}

			b[i][j] = temp[i][j] - !(cnt >= 3);
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> q;
	sz = 1 << n;

	for (int i = 1; i <= sz; i++) {
		for (int j = 1; j <= sz; j++) {
			cin >> b[i][j];
		}
	}

	while (q--) {
		int query;
		cin >> query;
		queries.push_back(query);
	}

	for (auto& i : queries) {
		simulate(i);
	}

	int sum = 0;
	for (int i = 1; i <= sz; i++) {
		for (int j = 1; j <= sz; j++) {
			sum += b[i][j];
		}
	}

	int ans = 0;
	for (int i = 1; i <= sz; i++) {
		for (int j = 1; j <= sz; j++) {
			if (!visited[i][j] && b[i][j]) {
				ans = max(ans, bfs(i, j));
			}
		}
	}

	cout << sum << '\n' << ans << '\n';
	return 0;
}