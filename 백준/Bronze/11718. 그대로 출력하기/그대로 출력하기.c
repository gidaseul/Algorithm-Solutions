#include <stdio.h>
int main() {

	char c;
	//scnaf는 정수형을 반환해주는 반환함수인데 평소에는 포맷 형식 갯수를 반환하지만 파일 끝에 도달하면 EOF라는 값을 반환한다.
	while(scanf("%c", &c) != EOF) {
		printf("%c", c);
	}

	return 0;
}