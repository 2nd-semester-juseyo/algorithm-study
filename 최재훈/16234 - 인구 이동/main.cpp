#include <bits/stdc++.h>

using namespace std;

const int MAX = 51;
int n, le, ri;
int b[MAX][MAX], dir[4][2] = {{1,  0},
                              {-1, 0},
                              {0,  1},
                              {0,  -1}};
bool visited[MAX][MAX];

int update(int cnt, int sum, vector<pair<int, int>> &v) {
    int val = sum / cnt, check = 0;

    for (auto &i: v) {
        if (b[i.first][i.second] != val) {
            b[i.first][i.second] = val;
            check++;
        }
    }

    return check;
}

void dfs(int r, int c, int &cnt, int &sum, vector<pair<int, int>> &v) {
    v.push_back({r, c});
    cnt++;
    sum += b[r][c];
    visited[r][c] = true;

    for (int i = 0; i < 4; i++) {
        int nr = r + dir[i][0];
        int nc = c + dir[i][1];
        int diff = abs(b[r][c] - b[nr][nc]);

        if (nr >= 1 && nr <= n && nc >= 1 && nc <= n && !visited[nr][nc] && diff >= le && diff <= ri) {
            dfs(nr, nc, cnt, sum, v);
        }
    }
}

bool func(void) {
    int check = 0;
    memset(visited, false, sizeof(visited));

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (!visited[i][j]) {
                int cnt = 0, sum = 0;
                vector<pair<int, int>> v;

                dfs(i, j, cnt, sum, v);
                check += update(cnt, sum, v);
            }
        }
    }

    return check != 0;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n >> le >> ri;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> b[i][j];
        }
    }

    int cnt = 0;
    while (1) {
        if (!func()) {
            break;
        }
        cnt++;
    }

    cout << cnt << '\n';
    return 0;
}