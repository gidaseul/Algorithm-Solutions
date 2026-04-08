#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main() {
	int N, i;
	int num;
	
	int min = 1000000;
	int max = -1000000;
	scanf("%d", &N);
	for (i = 0; i < N; i++) {
		scanf("%d", &num);
		if (min > num)
			min = num;
		if (max < num)
			max = num;
	}
	printf("%d %d", min, max);
	return 0;
}