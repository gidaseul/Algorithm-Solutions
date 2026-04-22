class Solution {
    public int[] solution(int start_num, int end_num) {
        int range = end_num - start_num+1;
        int[] answer = new int[range];

        for(int i=0;i<range; i++){
            answer[i] = start_num; 
            start_num++;
        }
        return answer;
    }
}