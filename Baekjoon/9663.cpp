#include <iostream>

using namespace std;

int arr[15][15];
int N;
int ans;

bool chk(int x, int cnt) {
    for (int i = 0; i < cnt; ++i) {
        if (arr[i][x] == 1) return false;
    }
    for (int i = x - 1, j = cnt - 1; i >= 0 && j >= 0; i--, j--) {
        if (arr[j][i] == 1) return false;
    }
    for (int i = x + 1, j = cnt - 1; i < N && j >= 0; i++, j--) {
        if (arr[j][i] == 1) return false;
    }
    return true;
}

void recur(int cnt) {
    if (cnt == N) {
        ans++;
        return;
    }
    for (int i = 0; i < N; ++i) {
        if (arr[cnt][i] == 0 && chk(i, cnt)) {
            arr[cnt][i] = 1;
            recur(cnt + 1);
            arr[cnt][i] = 0;
        }
    }
}

int main() {
    cin >> N;
    recur(0);
    cout << ans << '\n';
    return 0;
}
