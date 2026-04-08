#include <stdio.h>
#include <stdlib.h>
int main() {
    
    int size, x;
    int *arr;
    scanf("%d %d", &size,&x);
    arr = (int *)malloc(sizeof(int) * size);
    for (int i = 0; i < size; i++) {
        scanf("%d", &arr[i]);
    }
    for (int i = 0; i < size; i++) {
        if (arr[i] < x) {
            printf("%d ", arr[i]);
        }
    }
    free(arr);

    return 0;
}