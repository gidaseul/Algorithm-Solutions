import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        int[] pattern1 = {1, 2, 3, 4, 5}; // 길이 5
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5}; // 길이 8
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 길이 10
        
        int[] scores = {0,0,0};
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == pattern1[i % pattern1.length]) scores[0]++;
            if (answers[i] == pattern2[i % pattern2.length]) scores[1]++;
            if (answers[i] == pattern3[i % pattern3.length]) scores[2]++;
        }
        
        int maxScore =Math.max(scores[0], Math.max(scores[1],scores[2]));
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i < scores.length; i++){
            if (scores[i] == maxScore){
                list.add(i+1);
            }
        }
        int[] result = new int[list.size()];
        for(int i= 0; i<list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
}