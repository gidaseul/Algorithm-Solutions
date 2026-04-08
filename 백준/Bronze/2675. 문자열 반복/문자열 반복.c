#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <string.h>
int main() {

	int T, R;
	char S[21];

	scanf("%d", &T);
	for (int i = 0; i < T; i++) {
		scanf("%d %s", &R, S);

		for (int i = 0; i < strlen(S); i++) {
			for (int j = 0; j < R; j++)
				printf("%c", S[i]);
		}
		printf("\n");
	}
	return 0;
}