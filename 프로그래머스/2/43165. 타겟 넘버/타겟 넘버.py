def solution(numbers, target):
    answer = 0
    
    def depth(cur_idx, cur_sum):
        if cur_idx == len(numbers):
            if cur_sum == target:
                return 1
            else:
                return 0
        return depth(cur_idx+1, cur_sum + numbers[cur_idx]) + depth(cur_idx+1, cur_sum-numbers[cur_idx]) 
    return depth(0,0)