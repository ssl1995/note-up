# LC1306 跳跃游戏 III
## 题目信息
- 题目链接：[1306. 跳跃游戏 III](https://leetcode.cn/problems/jump-game-iii/)
- 难度：中等
- 标签：BFS、图、数组
## 题目描述
给定一个非负整数数组 `arr`，你最开始位于该数组的起始下标 `start` 处。当你位于下标 `i` 处时，你可以跳到：
- `i + arr[i]`
- `i - arr[i]`
请你判断自己是否能够跳到对应元素值为 `0` 的任一 下标处。
注意：不管是什么情况下，你都无法跳到数组之外。
## 示例
**示例 1：**
输入：`arr = [4,2,3,0,3,1,2]`, `start = 5`
输出：`true`
解释：到达值为 0 的下标 3 有以下可能方案：
- 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
- 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
**示例 2：**
输入：`arr = [4,2,3,0,3,1,2]`, `start = 0`
输出：`false`
**示例 3：**
输入：`arr = [3,0,2,1,2]`, `start = 2`
输出：`false`
## 解题思路
这道题的本质是：**从起点开始，每次可以向左或向右跳跃，问能否到达值为 0 的位置。**
因为每个下标最多有两种跳法（`i + arr[i]` 和 `i - arr[i]`），所以可以把数组看作一个图，下标是节点，跳跃关系是边。
### 为什么用 BFS？
- 问题问的是"能否到达"，而不是"最少步数"，所以只要找到一条可行路径即可。
- BFS 适合逐层探索所有可能到达的位置，一旦找到值为 0 的下标就可以立刻返回 `true`。
- 如果没有找到，说明所有可达位置都访问过了，返回 `false`。
### 如何避免死循环？
因为数组中可能存在非零值，比如 `arr[i] = 1`，那么从 `i` 可以跳到 `i+1`，从 `i+1` 又可以跳回 `i`，造成无限循环。
所以需要用 `visited[]` 数组记录访问过的下标，避免重复访问。
## 代码实现
```java
import java.util.LinkedList;
import java.util.Queue;
public class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        // 记录访问过的下标，防止在两个位置之间来回跳动造成死循环
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            // 只要到达任意值为0的下标，就返回true
            if (arr[i] == 0) {
                return true;
            }
            int step = arr[i];
            // 向右跳
            int right = i + step;
            if (right < n && !visited[right]) {
                visited[right] = true;
                queue.offer(right);
            }
            // 向左跳
            int left = i - step;
            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.offer(left);
            }
        }
        // 队列已空，说明所有可达位置都访问过，仍未找到值为0的下标
        return false;
    }
}
```
## 复杂度分析
- **时间复杂度：** O(n)
  - 每个下标最多入队出队一次。
- **空间复杂度：** O(n)
  - `visited` 数组占用 O(n)，队列最多存储 O(n) 个下标。
## 关键点
1. **双向跳跃：** 每个位置可以向左跳 `i - arr[i]`，也可以向右跳 `i + arr[i]`，但必须在数组范围内。
2. **visited 数组：** 避免死循环，保证每个下标只访问一次。
3. **提前返回：** 一旦遇到值为 0 的下标，立即返回 `true`，不需要继续搜索。
4. **BFS 自然适合"是否可达"类问题**，代码简洁直观。
