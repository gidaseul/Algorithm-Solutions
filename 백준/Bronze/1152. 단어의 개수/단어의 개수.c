#include <stdio.h>
#include <string.h>
int main(void) {
	char str[1000001];
	int a = 0;
	scanf("%[^\n]s", &str);
	if (str[0] != ' ') {
		a++;
	}
	for (int i = 1; i < strlen(str); i++) {
		if (str[i - 1] == ' ' && str[i] != ' ')
            a++;
	}
	printf("%d",a);
	
	return 0;
}