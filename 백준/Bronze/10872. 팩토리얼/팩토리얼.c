#include <stdio.h>
int main() {
    int N;
    scanf("%d", &N);
    printf("%d\n", fact(N));
    return 0;
}
int fact(int n) {
    if (n > 1) {
        return n * fact(n - 1);
    }
    else return 1;
}