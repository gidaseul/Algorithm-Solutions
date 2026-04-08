#include <stdio.h>

int main(void) {
	int n;
	scanf("%d", &n);
	if (n % 4 == 0) {
		for (int i = 0; i < n/4; i++) {
			printf("long ");
		}
		printf("int");
	}
	return 0;
}