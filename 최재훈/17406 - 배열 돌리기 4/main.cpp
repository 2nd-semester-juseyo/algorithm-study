#include <bits/stdc++.h>

using namespace std;

const int MAX = 51, K_MAX = 6;
int n, m, k, ans = INT_MAX;
int board[MAX][MAX], b[MAX][MAX];
int dir[4][2] = {{0,  1},
                 {1,  0},
                 {0,  -1},
                 {-1, 0}};
bool visited[K_MAX];
vector<int> v;

struct Operator {
    int r, c, s;
} op[K_MAX];

void rotate(int row, int col, int s) {
    if (!s) {
        return;
    }

    int d = 0, r = row - s, c = col - s, buffer = b[r][c];
    unordered_set<int> rl = {row - s, row + s}, cl = {col - s, col + s};

    while (1) {
        r += dir[d][0];
        c += dir[d][1];

        swap(buffer, b[r][c]);

        if (r == row - s && c == col - s) {
            break;
        }

        if (rl.find(r) != rl.end() && cl.find(c) != cl.end()) {
            d++;
        }
    }

    rotate(row, col, s - 1);
}

void simulate() {
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            b[i][j] = board[i][j];
        }
    }

    for (auto &i: v) {
        rotate(op[i].r, op[i].c, op[i].s);
    }

    int res = INT_MAX;

    for (int i = 1; i <= n; i++) {
        int sum = 0;

        for (int j = 1; j <= m; j++) {
            sum += b[i][j];
        }

        res = min(res, sum);
    }


    ans = min(ans, res);
}

void func() {
    if (v.size() == k) {
        simulate();
    }

    for (int i = 0; i < k; i++) {
        if (!visited[i]) {
            v.push_back(i);
            visited[i] = true;
            func();
            v.pop_back();
            visited[i] = false;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n >> m >> k;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            cin >> board[i][j];
        }
    }

    for (int i = 0; i < k; i++) {
        int r, c, s;
        cin >> r >> c >> s;
        op[i] = {r, c, s};
    }

    func();
    cout << ans << '\n';
    return 0;
}