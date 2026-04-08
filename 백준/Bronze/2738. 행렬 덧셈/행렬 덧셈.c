#include <stdio.h>
int main(void) {
    
    int arr_A[100][100];
    int arr_B[100][100];

    int n, m;
    scanf("%d %d", &n, &m);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            scanf("%d", &arr_A[i][j]);
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            scanf("%d", &arr_B[i][j]);
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            printf("%d ", arr_A[i][j] + arr_B[i][j]);
        }
        printf("\n");
    }
    
    return 0;
}