import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int n = 0;
        for(int x[] : commands){
            int i = x[0]-1; // 배열 시작
            int j = x[1]; // 배열 끝
            int k = x[2]-1; // 그 중에서 k번째
            
            int[] arr = Arrays.copyOfRange(array,i,j);
            Arrays.sort(arr);
            answer[n++] = arr[k];
        }
        return answer;
    }
}
