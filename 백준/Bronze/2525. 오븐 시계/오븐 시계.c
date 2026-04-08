#include <stdio.h>

int main(void) {
	int h,m,time;
	scanf("%d %d\n", &h, &m);
	scanf("%d",&time);
	int sum = (h * 60) + m + time;
	int sum_h = sum / 60;
	int sum_m = sum % 60;
	if ((h <= 23) && (m <= 59) && (time <= 1000)) {
		if (sum >= 1440) {
			printf("%d %d", sum_h - 24, sum_m);
		}else printf("%d %d", sum_h, sum_m);
	}

	return 0;
}