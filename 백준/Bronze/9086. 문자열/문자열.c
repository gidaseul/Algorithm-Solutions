#include <stdio.h>
#include <string.h>

int main(void) {

    int testnum = 0;
    scanf("%d", &testnum);
    for (int i = 0; i < testnum; i++) {
        char str[1001];
        scanf("%s", str);
        int d = 0;
        d = strlen(str)-1;
        printf("%c%c\n", str[0],str[d]);
    }
   
   
    return 0;
}