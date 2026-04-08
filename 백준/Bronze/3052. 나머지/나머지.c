#include <stdio.h>
#include<string.h>

int main(void) {
	int arry[42];
	memset(arry, 0, sizeof arry);
	
	for (int i = 0; i < 10; i++) {
		int num;
		scanf("%d", &num);
		arry[num % 42] = arry[num % 42] + 1;
	}

	int count = 0;
	for (int j = 0; j < 42; j++) {
		if (arry[j] != 0)
			count++;
	}
	printf("%d\n", count);
	return 0;
}