#include <stdio.h>
int main() {
    
    int arr[100];
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    int v, count = 0;;
    scanf("%d", &v);
    for (int i = 0; i < n; i++) {
        if (arr[i] == v) {
            count++;
        }
    }printf("%d", count);
    return 0;
}