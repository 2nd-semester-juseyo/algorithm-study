#include <bits/stdc++.h>

using namespace std;

const int MAX = 21;
int n, ans = INT_MAX;
int board[MAX][MAX], b[MAX][MAX];

void cal(int x, int y, int d1, int d2) {
    memset(b, false, sizeof(b));

    for (int i = 0; i <= d1; i++) {
        b[x + i][y - i] = 5;
    }

    for (int i = 0; i <= d2; i++) {
        b[x + i][y + i] = 5;
    }

    for (int i = 0; i <= d2; i++) {
        b[x + d1 + i][y - d1 + i] = 5;
    }

    for (int i = 0; i <= d1; i++) {
        b[x + d2 + i][y + d2 - i] = 5;
    }

    for (int i = 1; i <= n; i++) {
        vector<int> v;

        for (int j = 1; j <= n; j++) {
            if (b[i][j] == 5) {
                v.push_back(j);
            }
        }

        if (v.size() >= 2) {
            for (int j = v[0]; j <= v[1]; j++) {
                b[i][j] = 5;
            }
        }
    }

    for (int r = 1; r <= n; r++) {
        bool flag = false;

        for (int c = 1; c <= n; c++) {
            if (b[r][c]) {
                flag = true;
                continue;
            }

            if (1 <= r && r < x + d1 && 1 <= c && c <= y && !flag) {
                b[r][c] = 1;
            } else if (1 <= r && r <= x + d2 && y < c && c <= n) {
                b[r][c] = 2;
            } else if (x + d1 <= r && r <= n && 1 <= c && c < y - d1 + d2 && !flag) {
                b[r][c] = 3;
            } else if (x + d2 < r && r <= n && y - d1 + d2 <= c && c <= n) {
                b[r][c] = 4;
            }
        }
    }

    int arr[6] = {0,};

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            arr[b[i][j]] += board[i][j];
        }
    }

    int val1 = INT_MAX, val2 = INT_MIN;

    for (int i = 1; i <= 5; i++) {
        val1 = min(arr[i], val1);
        val2 = max(arr[i], val2);
    }

    ans = min(ans, abs(val1 - val2));
}

void func() {
    for (int x = 1; x <= n; x++) {
        for (int y = 1; y <= n; y++) {
            for (int d1 = 1; d1 <= y - 1; d1++) {
                for (int d2 = 1; x + d1 + d2 <= n; d2++) {
                    cal(x, y, d1, d2);
                }
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> n;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> board[i][j];
        }
    }

    func();
    cout << ans << '\n';
    return 0;
}