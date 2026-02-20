import java.util.*;

public class SaddlebagBalancer {
    /**
     * Return the authors' names.
     * @return The names of the authors of this title.
     */
    public static String getAuthors() {
        return "Shy Daniell and Elle Spivey";
    }

    public static List<Integer> getPartition(List<Integer> packageWeights) {
        int num = packageWeights.size();
        long totalSum = 0;
        for (int weight : packageWeights) {
            totalSum += weight;
        }

        long target = totalSum / 2;

        Map<String, Boolean> memo = new HashMap<>();
        List<Integer> result = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            result.add(0);
        }

        findPartition(packageWeights, 0, 0, target, result, memo);

        return result;
    }

    private static boolean findPartition(List<Integer> weights, int index, long currentSum, long target, List<Integer> result, Map<String, Boolean> memo) {
        if (currentSum == target) {
            return true;
        }

        if (index == weights.size() || currentSum > target) {
            return false;
        }

        String state = index + "_" + currentSum;
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        result.set(index, 1);
        if (findPartition(weights, index + 1, currentSum + weights.get(index), target, result, memo)) {
            return true;
        }

        result.set(index, 0);
        if (findPartition(weights, index + 1, currentSum, target, result, memo)) {
            return true;
        }

        memo.put(state, false);
        return false;
    }
}