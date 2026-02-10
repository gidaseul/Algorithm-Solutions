import java.io.*;
import java.util.*;

/**
 * SW Expert Academy 5648
 * 원자 소멸 시뮬레이션
 *
 * [풀이 개요]
 * 1. 0.5초 충돌 처리를 위해 좌표를 2배로 확장한다.
 * 2. 매 시간마다 모든 원자를 동시에 이동시킨다.
 * 3. 이동 후 같은 좌표에 모인 원자들의 에너지 합을 계산한다.
 * 4. 해당 위치의 에너지 합이 자기 자신보다 크면 충돌 -> 제거
 * 5. 최대 4000 step까지만 반복한다.
 *
 * 시간복잡도: O(T × 4000 × N)
 */
public class Solution {

    /**
     * 원자 정보를 저장하는 클래스
     */
    static class Atom {
        int x, y;      // 현재 좌표
        int dir;       // 이동 방향
        int energy;    // 보유 에너지

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }

    // 방향 배열 (0: 상, 1: 하, 2: 좌, 3: 우)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // 좌표 범위 (2배 확장 후 최대 +-4000까지 가능)
    static final int LIMIT = 4000;

    // 음수 좌표 보정을 위한 offset
    static final int OFFSET = 4000;

    /**
     * (x, y)를 하나의 정수 key로 변환
     * HashMap에서 좌표를 효율적으로 관리하기 위함
     */
    static int makeKey(int x, int y) {
        return (x + OFFSET) * 8001 + (y + OFFSET);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());
            List<Atom> atoms = new ArrayList<>();

            // 입력 및 좌표 2배 확장
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(x, y, dir, energy));
            }

            int totalEnergy = 0;

            // 위치별 에너지 합 저장용 Map
            Map<Integer, Integer> map = new HashMap<>();

            // 최대 4000번 반복 (그 이후는 모두 범위 밖)
            for (int time = 0; time < 4000; time++) {

                // 원자가 없으면 종료
                if (atoms.isEmpty()) break;

                // 1. 모든 원자 이동 + 위치별 에너지 합 계산
                for (int i = atoms.size() - 1; i >= 0; i--) {
                    Atom a = atoms.get(i);

                    // 방향에 따라 이동
                    a.x += dx[a.dir];
                    a.y += dy[a.dir];

                    // 범위 벗어나면 제거
                    if (a.x < -LIMIT || a.x > LIMIT ||
                            a.y < -LIMIT || a.y > LIMIT) {
                        atoms.remove(i);
                        continue;
                    }

                    // 해당 위치의 에너지 합 누적
                    int key = makeKey(a.x, a.y);
                    map.merge(key, a.energy, Integer::sum);
                }

                // 2. 충돌 처리
                // 같은 위치에 2개 이상 모이면
                // 위치의 에너지 합 > 자기 에너지
                for (int i = atoms.size() - 1; i >= 0; i--) {
                    Atom a = atoms.get(i);
                    int key = makeKey(a.x, a.y);

                    // 해당 위치 에너지 합이 더 크면 충돌
                    if (map.get(key) > a.energy) {
                        totalEnergy += a.energy;
                        atoms.remove(i);
                    }
                }

                // 다음 step을 위해 Map 초기화
                map.clear();
            }

            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }

        System.out.print(sb);
    }
}
