#include <stdio.h>
int main() {
    
    int arr[31] = { 0 };
    int n;
    for (int i = 0; i < 28; i++) {
        scanf("%d\n", &n);
        arr[n] = n;
    }
    for (int i = 1; i < 31; i++) {
        if (arr[i] == 0) {
            printf("%d\n", i);
        }
    }
    return 0;
}