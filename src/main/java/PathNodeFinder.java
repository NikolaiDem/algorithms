/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

import java.util.*;

class PathNodeFinder {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        List<TreeNode> listOfRoot = new ArrayList<>();
        listOfRoot.add(root);
        Map<TreeNode, List<TreeNode>> listByNode = new HashMap<>();
        listByNode.put(root, listOfRoot);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode right = node.right;
            TreeNode left = node.left;

            boolean isLeaf = left == null && right == null;
            if (isLeaf) {
                checkPathSum(targetSum, result, listByNode, node);
            }
            if (right != null) {
                addNodeToStackAndUpdateMapOfNodes(stack, listByNode, node, right);
            }
            if (left != null) {
                addNodeToStackAndUpdateMapOfNodes(stack, listByNode, node, left);
            }
        }
        return result;
    }

    private void checkPathSum(int targetSum, List<List<Integer>> result, Map<TreeNode, List<TreeNode>> listByNode, TreeNode node) {
        List<TreeNode> path = listByNode.get(node);
        if (getSumOfNodeList(path) == targetSum) {
            List<Integer> pathSum = mapListOfNodesToListOfValues(path);
            result.add(pathSum);
        }
    }

    private void addNodeToStackAndUpdateMapOfNodes(Deque<TreeNode> stack, Map<TreeNode, List<TreeNode>> listByNode, TreeNode node, TreeNode right) {
        stack.push(right);
        List<TreeNode> newList = new ArrayList<>(listByNode.get(node));
        newList.add(right);
        listByNode.put(right, newList);
    }

    private double getSumOfNodeList(List<TreeNode> list) {
        double result = 0;
        for (TreeNode node : list) {
            result += node.val;
        }
        return result;
    }

    private List<Integer> mapListOfNodesToListOfValues(List<TreeNode> list) {
        List<Integer> result = new ArrayList<>();
        for (TreeNode node : list) {
            result.add(node.val);
        }
        return result;
    }

}