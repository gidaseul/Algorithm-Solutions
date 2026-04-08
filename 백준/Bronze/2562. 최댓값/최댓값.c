#include <stdio.h>

int main() {
	int num[10], max = 0, sum;
	for (int i = 0; i <9; i++) {
		scanf("%d", &num[i]);
			if (max < num[i]) {
				max = num[i];
				sum = i+1;
			}
	}
	printf("%d\n", max);
	printf("%d",sum);

	return 0;
}