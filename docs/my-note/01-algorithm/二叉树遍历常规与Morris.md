# 二叉树遍历常规写法与Morris遍历
## 一、非递归常规写法总结
### 1.前序遍历（LC144）
核心思路：根节点先入栈；每次弹出节点就记录答案；由于栈是后进先出，先压右孩子、再压左孩子，这样出栈顺序就是左右。
```java
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    if (root == null) return res;
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        res.add(node.val);
        // 先右后左，保证出栈顺序为左、右
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
    }
    return res;
}
```
### 2.中序遍历（LC94）
核心思路：一路把左子节点压栈，直到左子树为空；弹出栈顶记录答案，再转向右子树。
```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    while (!stack.isEmpty() || root != null) {
        if (root != null) {
            stack.push(root);
            root = root.left;
        } else {
            TreeNode node = stack.pop();
            res.add(node.val);
            root = node.right;
        }
    }
    return res;
}
```
### 3.后序遍历（LC145 单栈 + prev）
核心思路：先一路向左压栈；栈顶节点若右子树为空或已经访问过，就弹出记录；否则转向右子树继续。
```java
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    if (root == null) return res;
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode prev = null;
    while (!stack.isEmpty() || root != null) {
        if (root != null) {
            stack.push(root);
            root = root.left;
            continue;
        }
        TreeNode peek = stack.peek();
        if (peek.right != null && peek.right != prev) {
            root = peek.right;
        } else {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            prev = pop;
        }
    }
    return res;
}
```
### 4.三种写法的共同规律
- 都用栈模拟递归过程。
- 前序：访问顺序与入栈顺序一致，处理完当前节点再处理子树。
- 中序：先把左子树全部压栈，再访问节点，再处理右子树。
- 后序：最复杂，需要`prev`记录右子树是否已经遍历过，避免重复访问。
## 二、Morris遍历
### 1.核心思想
Morris遍历利用叶子节点的空闲右指针，临时建立一条"线索"指向中序遍历的后继节点，从而不需要栈，空间复杂度为O(1)。遍历结束后会把线索恢复，保证树结构不变。
### 2.Morris中序遍历
算法步骤：
1. `cur`指向根节点。
2. 如果`cur.left == null`，访问`cur`，然后`cur = cur.right`。
3. 否则找到`cur`左子树的最右节点`mostRight`：
   - 若`mostRight.right == null`：令`mostRight.right = cur`，建立线索，然后`cur = cur.left`。
   - 若`mostRight.right == cur`：说明左子树已经遍历完毕，恢复`mostRight.right = null`，访问`cur`，然后`cur = cur.right`。
```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    TreeNode cur = root;
    while (cur != null) {
        if (cur.left == null) {
            res.add(cur.val);
            cur = cur.right;
        } else {
            TreeNode mostRight = cur.left;
            while (mostRight.right != null && mostRight.right != cur) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == null) {
                mostRight.right = cur;
                cur = cur.left;
            } else {
                mostRight.right = null;
                res.add(cur.val);
                cur = cur.right;
            }
        }
    }
    return res;
}
```
### 3.Morris前序遍历
前序遍历只需要在第一次到达某个节点时（建立线索时）访问它，其余逻辑与中序相同。
```java
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    TreeNode cur = root;
    while (cur != null) {
        if (cur.left == null) {
            res.add(cur.val);
            cur = cur.right;
        } else {
            TreeNode mostRight = cur.left;
            while (mostRight.right != null && mostRight.right != cur) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == null) {
                res.add(cur.val); // 第一次到达cur时访问
                mostRight.right = cur;
                cur = cur.left;
            } else {
                mostRight.right = null;
                cur = cur.right;
            }
        }
    }
    return res;
}
```
### 4.Morris后序遍历
后序遍历的Morris写法最复杂：当第二次回到某个节点（即线索已存在）时，把该节点左子树到最右节点的路径逆序输出，输出完再恢复。
核心步骤：
1. 中序Morris遍历，当遇到`mostRight.right == cur`时。
2. 将`cur.left`到`mostRight`的路径上的节点逆序，并输出。
3. 输出完再逆序恢复。
```java
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    TreeNode cur = root;
    while (cur != null) {
        if (cur.left == null) {
            cur = cur.right;
        } else {
            TreeNode mostRight = cur.left;
            while (mostRight.right != null && mostRight.right != cur) {
                mostRight = mostRight.right;
            }
            if (mostRight.right == null) {
                mostRight.right = cur;
                cur = cur.left;
            } else {
                mostRight.right = null;
                // 输出cur.left到mostRight的逆序路径
                reverseAndAdd(cur.left, res);
                cur = cur.right;
            }
        }
    }
    return res;
}
// 辅助函数：将以root为根的树的最右链逆序后输出，再恢复
private void reverseAndAdd(TreeNode root, List<Integer> res) {
    TreeNode tail = reverse(root);
    TreeNode cur = tail;
    while (cur != null) {
        res.add(cur.val);
        cur = cur.right;
    }
    reverse(tail);
}
private TreeNode reverse(TreeNode root) {
    TreeNode prev = null, cur = root;
    while (cur != null) {
        TreeNode next = cur.right;
        cur.right = prev;
        prev = cur;
        cur = next;
    }
    return prev;
}
```
### 5.复杂度分析
- 时间复杂度：O(N)，每个节点最多被访问常数次，寻找最右节点过程中每条边最多走两次。
- 空间复杂度：O(1)，只使用几个临时指针。
### 6.面试高频考点
- 为什么Morris遍历能做到O(1)空间？因为它复用了叶子节点的空右指针作为线索。
- 线索的建立与恢复是成对出现的，不会破坏原树结构。
- 重点掌握Morris中序和前序，后序了解"逆序输出左子树最右链"的思路即可。
