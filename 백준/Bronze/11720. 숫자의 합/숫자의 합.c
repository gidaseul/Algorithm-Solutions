#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>

int main() {

	int input, sum = 0;
	char arr[100] = ""; // 수의 범위 : 1<=N<=100

	scanf("%d", &input);
	scanf("%s", arr); // ex)54321 입력

	for (int i = 0; i < input; i++) {
		//printf("%d\n", arr[i]); 53 52 51 50 49가 한 줄 씩 출력
		//printf("%c\n", arr[i]); 5 4 3 2 1 한 줄 씩 출력
		sum += arr[i] - '0'; //아스키 코드 값을 빼 줘야 값이 같음
	}
	printf("%d", sum);
    return 0;
}