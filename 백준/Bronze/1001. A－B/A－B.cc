#include <stdio.h>

int main() {
	
	int a = 0;
	int b = 0;
	scanf("%d %d", &a, &b);
	if (0<a && b < 10) {
		printf("%d", a - b);
	}


	return 0; 
}