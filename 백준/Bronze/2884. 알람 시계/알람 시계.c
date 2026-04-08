#include <stdio.h>

int main(void) {
	int h, m;
	scanf("%d %d", &h, &m);
	if ((0 <= h && h <= 23) && (0 <= m && m <= 59)) {
		int sum = 0;
		sum = ((h * 60) + m);
		int time = sum - 45;
		
		if (time < 0) {
			int mintime = 0;
			mintime = 1440 + time;
			printf("%d %d", mintime/60, mintime %60);
		}
		else printf("%d %d", time / 60, time % 60);
	}
	return 0;
}