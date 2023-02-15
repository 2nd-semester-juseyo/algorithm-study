#include <bits/stdc++.h>

using namespace std;

const int MAX = 101;
int r, c, k, ans, row, col;
int a[MAX][MAX];

bool compare(pair<int, int> &a, pair<int, int> &b) {
    if (a.second == b.second) {
        return a.first < b.first;
    }

    return a.second < b.second;
}

void simulate() {
    if (row >= col) {
        int tmp = 0;

        for (int i = 1; i <= row; i++) {
            unordered_map<int, int> um;

            for (int j = 1; j <= 100; j++) {
                if (a[i][j]) {
                    um[a[i][j]]++;
                    a[i][j] = 0;
                }
            }

            vector<pair<int, int>> v;

            for (auto &p: um) {
                v.emplace_back(p);
            }

            sort(v.begin(), v.end(), compare);
            tmp = max(tmp, min((int) v.size() * 2, 100));

            for (int j = 1, sz = min(50, (int) v.size()); j <= sz; j++) {
                a[i][j * 2 - 1] = v[j - 1].first;
                a[i][j * 2] = v[j - 1].second;
            }
        }

        col = tmp;
    } else {
        int tmp = 0;

        for (int i = 1; i <= col; i++) {
            unordered_map<int, int> um;

            for (int j = 1; j <= 100; j++) {
                if (a[j][i]) {
                    um[a[j][i]]++;
                    a[j][i] = 0;
                }
            }

            vector<pair<int, int>> v;

            for (auto &p: um) {
                v.emplace_back(p);
            }

            sort(v.begin(), v.end(), compare);
            tmp = max(tmp, min((int) v.size() * 2, 100));

            for (int j = 1, sz = min(50, (int) v.size()); j <= sz; j++) {
                a[j * 2 - 1][i] = v[j - 1].first;
                a[j * 2][i] = v[j - 1].second;
            }
        }

        row = tmp;
    }
}

void func() {
    while (ans <= 100) {
        if (a[r][c] == k) {
            break;
        }

        simulate();
        ans++;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> r >> c >> k;
    row = col = 3;

    for (int i = 1; i <= 3; i++) {
        for (int j = 1; j <= 3; j++) {
            cin >> a[i][j];
        }
    }

    func();
    cout << (ans != 101 ? ans : -1) << '\n';
    return 0;
}