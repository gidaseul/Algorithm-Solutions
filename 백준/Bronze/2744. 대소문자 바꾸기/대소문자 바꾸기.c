#include <stdio.h>
#include <string.h>

int main(void) {
    
    char a[100];
    int length, i;
    scanf("%s", a);
    length = strlen(a);
    for (i = 0; i < length; i++) {
        if (a[i] >= 97 && a[i] <= 122) {
            a[i] -= 32;
        }
        else if (a[i] >= 65 && a[i] <= 90) {
            a[i] += 32;
        }
    }
    printf("%s", a);
    return 0;
}