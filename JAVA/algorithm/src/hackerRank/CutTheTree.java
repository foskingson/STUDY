package hackerRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CutTheTree {
   public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        Map<Integer, Integer> lookup = new HashMap<>();
        int totalSum = treeSum(1, -1, data, adj, lookup);
        int[] minDiff = {Integer.MAX_VALUE};
        minPart(1, -1, totalSum, adj, lookup, minDiff);
        return minDiff[0];
    }

    private static int treeSum(int node, int parent, List<Integer> data, Map<Integer, List<Integer>> adj, Map<Integer, Integer> lookup) {
        int sum = data.get(node - 1);
        for (int child : adj.getOrDefault(node, new ArrayList<>())) {
            if (child == parent) continue;
            sum += treeSum(child, node, data, adj, lookup);
        }
        lookup.put(node, sum);
        return sum;
    }

    private static void minPart(int node, int parent, int totalSum, Map<Integer, List<Integer>> adj, Map<Integer, Integer> lookup, int[] minDiff) {
        for (int child : adj.getOrDefault(node, new ArrayList<>())) {
            if (child == parent) continue;
            int tree1 = lookup.get(child);
            int tree2 = totalSum - tree1;
            minDiff[0] = Math.min(minDiff[0], Math.abs(tree1 - tree2));
            minPart(child, node, totalSum, adj, lookup, minDiff);
        }
    }
}
