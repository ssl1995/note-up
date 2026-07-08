# 路径之和系列面试解题思路对比
## 系列概览
| 题号 | 题目 | 路径起点 | 路径终点 | 返回值 | 核心解法 | 时间复杂度 | 空间复杂度 |
| ---- | ---- | -------- | -------- | ------ | -------- | ---------- | ---------- |
| LC112 | 路径之和 | 根节点 | 叶子节点 | boolean | DFS减target | O(n) | O(n) |
| LC113 | 路径之和II | 根节点 | 叶子节点 | List<List<Integer>> | DFS + 回溯 | O(n) | O(n) |
| LC437 | 路径之和III | 任意节点 | 任意后代节点 | int | 前缀和 + HashMap | O(n) | O(n) |
## 记忆总口诀
根到叶子用DFS，减到零时判胜负；
收集所有加回溯，一条路径一个List；
任意起点用前缀，哈希表里查差值。
## LC112：路径之和
### 题目特点
判断二叉树中是否存在一条从根节点到叶子节点的路径，使得路径上所有节点值之和等于 targetSum。
### 核心思路
递归过程中不断用 targetSum 减去当前节点值。当到达叶子节点且剩余值恰好为0时，说明找到了这样一条路径。
### 代码示例
```java
public boolean hasPathSum(TreeNode root, int targetSum) {
  // 越过叶子节点还没有找到，失败
  if (root == null) {
    return false;
  }
  targetSum -= root.val;
  // 到达叶子节点且剩余和为0，成功
  if (root.left == null && root.right == null && targetSum == 0) {
    return true;
  }
  // 递归左右子树，只要有一边存在即可
  return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
}
```
### 面试记忆点
- 叶子节点判定：`root.left == null && root.right == null`。
- 返回值用 `||`：左子树或右子树找到任意一条即可。
- 空节点返回 false，表示此路不通。
## LC113：路径之和II
### 题目特点
在 LC112 基础上，要求返回所有满足条件的路径，而不仅仅是判断是否存在。
### 核心思路
DFS 遍历过程中维护一个 `path` 列表，到达叶子节点且路径和满足条件时，把当前路径加入结果。由于 Java 中 List 是引用类型，必须复制一份新的 ArrayList 再添加。回溯时移除当前节点，保证路径状态正确。
### 代码示例
```java
private List<List<Integer>> res;
private List<Integer> path;

public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
  res = new ArrayList<>();
  path = new ArrayList<>();
  if (root == null) {
    return res;
  }
  process(root, targetSum);
  return res;
}

private void process(TreeNode root, int targetSum) {
  if (root == null) {
    return;
  }
  path.add(root.val);
  targetSum -= root.val;
  if (root.left == null && root.right == null && targetSum == 0) {
    // 必须new ArrayList，否则后续回溯会清空path
    res.add(new ArrayList<>(path));
  }
  process(root.left, targetSum);
  process(root.right, targetSum);
  // 回溯，撤销当前节点的选择
  path.remove(path.size() - 1);
}
```
### 面试记忆点
- 与 LC112 相比，只是多了一步收集路径。
- 加节点在进入递归前，减节点在递归返回后，这是回溯模板。
- `res.add(new ArrayList<>(path))` 是高频易错点，必须复制。
## LC437：路径之和III
### 题目特点
路径不需要从根节点开始，也不需要在叶子节点结束，但必须是向下的（父节点到子节点）。
### 核心思路
最优解是前缀和加哈希表。从根到当前节点的路径和为 curSum，如果之前某个祖先节点到根的路径和为 curSum - targetSum，那么从该祖先的下一个节点到当前节点的路径和就是 targetSum。用 HashMap 记录路径上每个前缀和出现的次数，即可 O(1) 查询。
### 代码示例
```java
private int res = 0;

public int pathSum(TreeNode root, int targetSum) {
  if (root == null) {
    return 0;
  }
  Map<Long, Integer> map = new HashMap<>();
  // 处理从根节点开始的路径
  map.put(0L, 1);
  dfs(root, (long) root.val, targetSum, map);
  return res;
}

private void dfs(TreeNode node, long preSum, int target, Map<Long, Integer> map) {
  // 如果preSum - target存在，说明有对应路径
  if (map.containsKey(preSum - target)) {
    res += map.get(preSum - target);
  }
  // 当前前缀和入表
  map.put(preSum, map.getOrDefault(preSum, 0) + 1);
  if (node.left != null) {
    dfs(node.left, preSum + node.left.val, target, map);
  }
  if (node.right != null) {
    dfs(node.right, preSum + node.right.val, target, map);
  }
  // 回溯，撤销当前节点前缀和的影响
  map.put(preSum, map.getOrDefault(preSum, 0) - 1);
}
```
### 面试记忆点
- 本质是 LC560（和为K的子数组）的二叉树版本。
- 初始化 `map.put(0L, 1)`：处理从根节点开始的路径。
- 先查询再加表：避免把当前节点自己算进去。
- 递归返回前要回溯：左子树的前缀和不能影响右子树。
- 用 long 防止溢出。
## 三题演化关系
| 阶段 | 题目 | 变化点 | 技巧升级 |
| ---- | ---- | ------ | -------- |
| 阶段一 | LC112 | 只判断根到叶子是否存在 | 基础DFS |
| 阶段二 | LC113 | 收集根到叶子的所有路径 | DFS + 回溯 |
| 阶段三 | LC437 | 路径起点终点不限 | 前缀和 + HashMap |
这三道题是一个循序渐进的系列：从简单的存在性判断，到收集所有路径，最后到任意路径的计数。掌握这个演化关系，面试时可以根据题目条件快速定位解法。
## 统一解题框架
拿到"二叉树路径和"相关题目时，按下面三步判断：
- 第一步：路径是否必须从根节点开始？是否必须到叶子节点结束？
- 第二步：如果是根到叶子，用 DFS 直接做。如果只问是否存在，返回 boolean；如果要收集所有路径，加回溯。
- 第三步：如果路径起点和终点都不限，用前缀和加 HashMap 优化到 O(n)。
## 易错点提醒
- 易错点1：LC112 中空节点返回 false，但 LC113 中空节点直接 return，不要往 path 里加 null。
- 易错点2：LC113 中 `res.add(new ArrayList<>(path))` 必须复制 path，否则回溯后结果会被清空。
- 易错点3：LC437 中 `map.put(0L, 1)` 不能忘，否则漏掉从根节点开始的路径。
- 易错点4：LC437 中一定要先 `res += map.get(preSum - target)`，再 `map.put(preSum, ...)`，顺序不能反。
- 易错点5：LC437 中节点值可能为负数，不能提前终止递归；回溯也要做彻底。
## 面试回答模板
### LC112回答模板
面试官好，这道题判断是否存在根到叶子的路径和等于 target。我的思路是 DFS：从根节点开始，每经过一个节点就用 targetSum 减去当前节点值。当到达叶子节点且剩余值为0时返回 true。左右子树只要有一条满足就返回 true。时间复杂度 O(n)，空间复杂度 O(n)。
### LC113回答模板
面试官好，这道题是 LC112 的扩展，要求收集所有满足条件的路径。我用 DFS 加回溯：维护一个 path 列表记录当前路径，到达叶子节点且路径和满足条件时，把 path 复制一份加入结果。递归返回前移除当前节点，保证状态回退。时间复杂度 O(n)，空间复杂度 O(n)。
### LC437回答模板
面试官好，这道题路径不需要从根开始，也不需要到叶子结束。暴力做法是双重 DFS，以每个节点为起点向下枚举，时间 O(n²)。更优的做法是前缀和加哈希表：从根到当前节点的路径和为 curSum，如果 `curSum - target` 在哈希表中出现过 k 次，说明有 k 条路径满足条件。初始化 `map.put(0, 1)` 处理从根开始的路径，递归返回前要回溯撤销当前前缀和。时间复杂度 O(n)，空间复杂度 O(n)。
## 相关题目
- LC112 路径之和
- LC113 路径之和II
- LC437 路径之和III
- LC560 和为K的子数组
- LC124 二叉树中的最大路径和
- LC129 求根节点到叶节点数字之和
