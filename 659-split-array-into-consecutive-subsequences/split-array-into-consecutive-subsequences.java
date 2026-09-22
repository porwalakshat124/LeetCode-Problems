class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> appendFreq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            }

            if (appendFreq.getOrDefault(num, 0) > 0) {
                // Append this num to an existing subsequence ending at num - 1
                appendFreq.put(num, appendFreq.get(num) - 1);
                appendFreq.put(num + 1, appendFreq.getOrDefault(num + 1, 0) + 1);
                freq.put(num, freq.get(num) - 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
                // Start a new subsequence of length 3: num, num+1, num+2
                freq.put(num, freq.get(num) - 1);
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                appendFreq.put(num + 3, appendFreq.getOrDefault(num + 3, 0) + 1);
            } else {
                // Can't place this num anywhere validly
                return false;
            }
        }

        return true;
    }
}