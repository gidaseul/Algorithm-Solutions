import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br;
    static int[] photoBox;
    static int[] photoBoxCount;
    static int[] photoBoxOrder;
    static int count;


    public static void main(String args[]) throws IOException
    {
        br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        photoBox = new int[N];
        photoBoxCount = new int[N];
        photoBoxOrder = new int[N];

        int T = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        count = 0;

        for(int t=0; t<T; t++) {
            int cmd = Integer.parseInt(st.nextToken());
            int targetIdx = -1;
            boolean isExist = false;

            // 동일한 값이 있다면 추천수 늘리기
            for (int i =0; i<N; i++){
                if(photoBox[i] == cmd){
                    photoBoxCount[i]++;
                    isExist = true;
                    break;
                }
            }

            if(isExist){
                continue;
            }
            // 빈 자리가 있는지 확인 (값을 넣어야 함)
            for (int i = 0; i < N; i++) {
                if (photoBox[i] == 0) {
                    targetIdx = i;
                    break;
                }
            }
            // 사진틀이 꽉 찼다면 추천수와 시간에 따라 교체 대상을 선정
            if (targetIdx == -1) {
                targetIdx = 0; // 우선 0번을 후보로 설정
                for (int i = 1; i < N; i++) {
                    // 추천수가 더 적은 게 우선적으로 나감
                    if (photoBoxCount[i] < photoBoxCount[targetIdx]) {
                        targetIdx = i;
                    }
                    // 추천수가 동일하다면 가장 오래된(Order가 작은) 것 나감
                    else if (photoBoxCount[i] == photoBoxCount[targetIdx]) {
                        if (photoBoxOrder[i] < photoBoxOrder[targetIdx]) {
                            targetIdx = i;
                        }
                    }
                }
            }
            // 결정된 위치(targetIdx)에 값 삽입 및 초기화
            photoBox[targetIdx] = cmd;
            photoBoxCount[targetIdx] = 1;
            photoBoxOrder[targetIdx] = count++;
        }

        // 추천순에 상관없이 최종 후보들을 학생 번호순으로 재배열해서 출력
        List<Integer> finalCandidates = new ArrayList<>();
        for (int id : photoBox) {
            if (id > 0) finalCandidates.add(id);
        }
        Collections.sort(finalCandidates);

        for (int id : finalCandidates) {
            System.out.print(id + " ");
        }
    }
}