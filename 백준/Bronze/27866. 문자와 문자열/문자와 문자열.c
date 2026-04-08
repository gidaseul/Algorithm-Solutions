#include <stdio.h>

int main(void) {

    char str[1001];
    int i = 0;
    scanf("%s\n", str);
    scanf("%d\n", &i);
    printf("%c", str[i-1]);
   
    return 0;
}