#include <stdio.h>
#include<math.h>
int main() {

    long a, b;
    scanf("%ld %ld", &a, &b);
    printf("%ld", labs(a-b));
    return 0;
}