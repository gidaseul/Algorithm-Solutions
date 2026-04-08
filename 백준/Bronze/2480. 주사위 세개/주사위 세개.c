#include <stdio.h>

int main(void) {
	
	int a, b, c;
	scanf("%d %d %d", &a, &b, &c);
	int max1, max2;
	max1 = (a > b) ? a : b;
	max2 = (max1 > c) ? max1 : c;
	
	if ((a == b) && (a == c)) {
		printf("%d", 10000 + (a * 1000));
	}
	else if ((a == b) || (a == c)) {
		printf("%d", 1000 + (a * 100));
	}
	else if (b == c) {
		printf("%d", 1000 + (b * 100));
	}
	else printf("%d", max2 * 100);
	return 0;
}