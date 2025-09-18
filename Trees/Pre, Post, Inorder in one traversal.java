// Given a binary tree with root node. Return the In-order,Pre-order and Post-order traversal of the binary tree.

// Examples:
// Input : root = [1, 3, 4, 5, 2, 7, 6 ]
// Output : [ [5, 3, 2, 1, 7, 4, 6] , [1, 3, 5, 2, 4, 7, 6] , [5, 2, 3, 7, 6, 4, 1] 
// Explanation : The In-order traversal is [5, 3, 2, 1, 7, 4, 6].
// The Pre-order traversal is [1, 3, 5, 2, 4, 7, 6].
// The Post-order traversal is [5, 2, 3, 7, 6, 4, 1].

// Input : root = [1, 2, 3, null, null, null, 6 ]
// Output : [ [2, 1, 3, 6] , [1, 2, 3, 6] , [2, 6, 3, 1] ]
// Explanation : The In-order traversal is [2, 1, 3, 6].
// The Pre-order traversal is [1, 2, 3, 6].
// The Post-order traversal is [2, 6, 3, 1].

// Input : root = [5, 1, 2, 8, null, 4, 5, null, 6]
// Output:
// [ [8, 6, 1, 5, 4, 2, 5], [5, 1, 8, 6, 2, 4, 5], [6, 8, 1, 4, 5, 2, 5] ]

// Constraints:
// 1 <= Number of Nodes <= 105
// 0 <= Node.val <= 105
import java.util.*;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    TreeNode(int data) { this.data = data; this.left = null; this.right = null; }
}


class Solution {
    List<List<Integer>> treeTraversal(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        List<Integer> inor = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        res.add(inor);
        res.add(pre);
        res.add(post);
       
        if(root == null) return res;

        Stack<State> stack = new Stack<>();
        stack.push(new State(root, 1));

        while(!stack.isEmpty() ){
            State top = stack.peek();

            if(top.state == 1){
                pre.add(top.node.data);
                top.state++;
                if(top.node.left != null)
                    stack.push(new State(top.node.left, 1));
            }
            else if(top.state == 2){
                inor.add(top.node.data);
                top.state++;
                if(top.node.right != null)
                    stack.push(new State(top.node.right, 1));
            }
            else{
                post.add(top.node.data);
                stack.pop();
            }
        }

        return res;
    }

}

class State{
    TreeNode node;
    int state;

    State(TreeNode node, int s){
        this.node = node;
        this.state = s;
    }
}