package SSA.tasks;

import java.util.*;
import cache.MyQueueImplementation;

class Solution {
      static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    static List<List<Integer>> l = new ArrayList<>();
    static Set<Character> digits = new HashSet<>();
    static String res;
    static int cnt = 0;
    static StringBuilder builder = new StringBuilder(" ");
    static int maxDepthL = 1;
    static int maxDepthR = 1;
    static TreeNode newRoot = null;
    static TreeNode currNewRoot = null;
    static private int ld = 0;
    static private int rd = 0;
    static private int[] prefixSum;
    static private int sum = 0;

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        System.out.println(searchMatrix(matrix, 13));
    }
    public String customSortString(String order, String s) {
        StringBuilder bul = new StringBuilder();
        int[] charsS = new int[26];
        for (char c : s.toCharArray()) {
            charsS[c - 'a']++;
        }
        for (char c : order.toCharArray()) {
            while (charsS[c - 'a'] != 0) {
                bul.append(c);
                charsS[c - 'a']--;
            }
        }
        for (int i = 0; i < 26; i++) {
            while (charsS[i] != 0) {
                bul.append((char) (i + 'a'));
                charsS[i]--;
            }
        }
        return bul.toString();
    }
    public int minCostClimbingStairs(int[] cost) {
        int j;
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length + 1; i++) {
            if (i == cost.length) {
                j = 0;
            } else {
                j = cost[i];
            }
            dp[i] = Math.min(j + dp[i - 1], j + dp[i - 2]);
        }
        return dp[cost.length];
    }
    public TreeNode bstToGst(TreeNode root) {
        rightSubTree1(root);
        return root;
    }
    private void bstToGstSum(TreeNode root) {
        if (root == null) {
            return;
        }
        bstToGstSum(root.right);
        sum += root.val;
        root.val = sum;
        bstToGstSum(root.left);
    }
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder s = new StringBuilder();
        if (num < 0) {
            num = Math.abs(num);
            s.append('-');
        }
        s = base7(s, num);
        return s.toString();
    }
    private StringBuilder base7(StringBuilder s, int num) {
        if (num == 0) {
            return s;
        }
        int rem = num % 7;
        s = base7(s, num / 7);
        s.append(rem);
        return s;
    }
    public boolean winnerOfGame(String colors) {
        if (colors.length() < 3) {
            return false;
        }
        int a = possibleMoves('A', colors);
        int b = possibleMoves('B', colors);

        return a > b;
    }
    private int possibleMoves(char c, String col) {
        int cnt = 0;
        int moves = 0;
        for (int i = 0; i < col.length(); i++) {
            if (col.charAt(i) == c) {
                cnt++;
            } else {
                if (cnt > 2) {
                    moves += cnt - 2;
                }
                cnt = 0;
            }
        }
        if (cnt > 2) {
            moves += cnt - 2;
        }
        return moves;
    }
    public ListNode mergeNodes(ListNode head) {
        ListNode newHead = null;
        ListNode curr = null;
        head = head.next;
        int sum = 0;
        while (head != null) {
            if (head.val != 0) {
                sum += head.val;
            } else {
                if (newHead == null) {
                    newHead = new ListNode(sum);
                    curr = newHead;
                } else {
                    curr.next = new ListNode(sum);
                    curr = curr.next;
                }
                sum = 0;
            }
            head = head.next;
        }
        return newHead;
    }
    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n - 2; i++) {
            if (!isPalindromicInBaseB(n, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean isPalindromicInBaseB(int n, int base) {
        StringBuilder st = new StringBuilder();
        int d;
        while (n != 0) {
            d = n % base;
            n /= base;
            st.append(d);
        }
        return st.toString().equals(st.reverse().toString());
    } 
    public boolean isUnivalTree(TreeNode root) {
        return m(-1, root);
    }
    private boolean m(int prevVal, TreeNode root) {
        if (prevVal != -1) {
            if (root != null) {
                if (prevVal != root.val) {
                    return false;   
                }
            } else {
                return true;
            }
        }
        return m(root.val, root.left) && m(root.val, root.right);
    }
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode currHead = head;
        while (currHead.next != null) {
            ListNode newNode = gcd(currHead.val, currHead.next.val);
            ListNode nextNode = currHead.next;
            currHead.next = newNode;
            newNode.next = nextNode;
            currHead = currHead.next.next;
        }
        return head;
    }
    private ListNode gcd(int f, int s) {
        if (s == 0) {
            return new ListNode(f);
        }
        return gcd(s, f % s);
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode currRoot = root;
        TreeNode del = null;
        TreeNode succ = null;
        TreeNode prev = null;
        while (currRoot != null) {
            if (currRoot.val == key) {
                del = currRoot;
                break;
            } else if (currRoot.val > key) {
                prev = currRoot;
                currRoot = currRoot.left;
            } else {
                prev = currRoot;
                currRoot = currRoot.right;
            }
        }
        if (del != null) {
            if (del.left == null && del.right == null) {
                if (prev == null) {
                    return null;
                }
                if (prev.left == del) {
                    prev.left = null;
                } else {
                    prev.right = null;
                }
            } else if (del.left == null || del.right == null) {
                if (prev == null) {
                    if (root.left != null) {
                        return root.left;
                    } else {
                        return root.right;
                    }
                } else if (prev.left == del) {
                    if (del.left != null) {
                        prev.left = del.left;
                    } else {
                        prev.left = del.right;
                    }
                } else {
                    if (del.left != null) {
                        prev.right = del.left;
                    } else {
                        prev.right = del.right;
                    }
                }
            } else {
                currRoot = del.right;
                while (currRoot != null) {
                    prev = succ;
                    succ = currRoot;
                    currRoot = currRoot.left;
                }
                del.val = succ.val;
                if (prev == null) {
                    del.right = succ.right;
                } else {
                    prev.left = succ.right;
                }
            }
        }
        return root;    
    }
    public int distributeCandies(int[] candyType) {
        int numberOfCandies = candyType.length / 2;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < candyType.length; i++) {
            set.add(candyType[i]);
            if (numberOfCandies == set.size()) {
                return set.size();
            }
        }
        return set.size();
    }
    public ListNode[] splitListToParts(ListNode head, int k) {
        int size = sizeOfList(head);
        int parts = size / k;
        int remainder = size % k;
        ListNode[] arrayOfNodes = new ListNode[k];
        ListNode currHead = head;
        int cnt;
        int index = 0;
        while (currHead != null) {
            cnt = 0;
            ListNode currIndexHead = currHead;
            if (parts != 0 && remainder >= 1) {
                cnt = 1;
                remainder--;
            }
            for (int i = 1; i < parts + cnt; i++) {
                currHead = currHead.next;
            }
            ListNode nextNode = currHead.next;
            currHead.next = null;
            arrayOfNodes[index++] = currIndexHead;
            currHead = nextNode;
        }
        return arrayOfNodes;
    }
    private int sizeOfList(ListNode head) {
        int cnt = 0;
        while (head != null) {
            cnt++;
            head = head.next;
        }
        return cnt;
    }
    static class NumArray {
    	public NumArray(int[] nums) {
        	this.prefixSum = new int[nums.length + 1];

        	for (int i = 1; i <= nums.length; i++) {
            		prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        	}
    	}
    
    	public int sumRange(int left, int right) {
        	return prefixSum[right + 1] - prefixSum[left];
    	}
    }	
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode currNext = head;
        while (currNext.next != null) {
            if (currNext.val == currNext.next.val) {
                currNext.next = currNext.next.next;
            } else {
                currNext = currNext.next;
            }
        }
        return head;
    }
    public void reorderList(ListNode head) {
        Stack<ListNode> s = new Stack<>();
        ListNode currHead = head;
        while (currHead != null) {
            s.push(currHead);
            currHead = currHead.next;
        }
        currHead = head;
        ListNode currNext;
        ListNode lastNode = null;
        while (true) {
            currNext = currHead.next;
            if (currNext == lastNode) {
                currHead.next = null;
                return;
            }
            lastNode = s.pop();
            if (currHead.next == lastNode) {
                currHead.next.next = null;
                return;
            }
            currHead.next = lastNode;
            lastNode.next = currNext;
            currHead = currNext;
        }
    }
    public int removeDuplicates(int[] nums) {
        int cnt = 1;
        int i = 0;
        int begin = 0;
        int len = nums.length - 1;
        while (i < len) {
            if (nums[i] == nums[i + 1]) {
                cnt++;
                if (cnt == 3) {
                    begin = i + 1;
                }
            } else {
                if (cnt > 2) {
                    nums = overrideNums(nums, begin, i + 1);
                    len -= (i + 1 - begin);
                    i = begin - 1;
                }
                cnt = 1;
            }
            i++;
        }
        if (cnt >= 3) {
            return begin;
        } else {
            return i + 1;
        }
    }
    private int[] overrideNums(int[] nums, int begin, int diff) {
        while (diff < nums.length) {
            nums[begin++] = nums[diff++];
        }
        return nums;
    }
    public ListNode removeElements(ListNode head, int val) {
        while (head != null) {
            if (head.val == val) {
                head = head.next;
            } else {
                break;
            }
        }
        ListNode currHead = head;
        ListNode prev = null;
        while (currHead != null) {
            if (currHead.val == val) {
                prev.next = currHead.next;
            } else {
                prev = currHead;
            }
            currHead = currHead.next;
        }
        return head;
    }
    public int bestClosingTime(String customers) {
        int count = 0;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                count++;
            }
        }
        int currPen = count;
        int minPen = count;
        int minI = 0;
        for (int i = 1; i < customers.length() + 1; i++) {
            if (customers.charAt(i - 1) == 'Y') {
                currPen--;
                if (minPen > currPen) {
                    minPen = currPen;
                    minI = i;
                }
            } else {
                currPen++;
            }
        }
        return minI;
    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            leftSubTree(curr.left, 0);
            rightSubTree(curr.right, 0);
            if (Math.abs(ld - rd) > 1) {
                return false;
            }
            ld = 0;
            rd = 0;
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return true;
    }
    private void leftSubTree(TreeNode root, int d) {
        if (root == null) {
            if (d > ld) {
                ld = d;
            }
            return;
        }
        leftSubTree(root.left, d + 1);
        leftSubTree(root.right, d + 1);
    }
    private void rightSubTree(TreeNode root, int d) {
        if (root == null) {
            if (d > rd) {
                rd = d;
            }
            return;
        }
        rightSubTree(root.left, d + 1);
        rightSubTree(root.right, d + 1);
    }
    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()) {
            return false;
        }
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public int lengthOfLastWord(String s) {
        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != 32) {
                cnt++;
            } else {
                if (cnt != 0) {
                    break;
                }
            }
        }
        return cnt;
    }
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    public int passThePillow(int n, int time) {
        int k = time / (n - 1);
        if (k == 0) {
            return 1 + time;
        }
        if (k % 2 == 0) {
            int m = time - k * (n - 1);
            return 1 + m;
        }
        int l = time % (n - 1);
        return n - l;
    }
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int pow, resPow = 0, digit, num, currRes, res = 0;
        for (int i = num2.length() - 1; i >= 0; i--) {
            digit = Integer.parseInt(String.valueOf(num2.charAt(i)));
            pow = num1.length() - 1;
            currRes = 0;
            for (int j = 0; j < num1.length(); j++) {
                num = Integer.parseInt(String.valueOf(num1.charAt(j)));
                num = (int) (num * Math.pow(10, pow--));
                currRes += digit * num;
            }
            currRes = (int) (currRes * Math.pow(10, resPow++));
            res += currRes;
        }
        String strRes = String.valueOf(res);
        return strRes;
    }
    public boolean hasAlternatingBits(int n) {
        int prev = -1;
        while (n != 0) {
            int currBit = n % 2;
            if (prev != -1 && currBit == prev) {
                return false;
            }
            n /= 2;
            prev = currBit;
        }
        return true;
    }
    public boolean validPalindrome(String s) {
        int beg = 0, end = s.length() - 1;
        boolean ff = true;
        while (beg < end) {
            if (s.charAt(beg) != s.charAt(end)) {
                int f = beg;
                int l = end;
                beg++;
                while (beg < end) {
                    if (s.charAt(beg) != s.charAt(end)) {
                        ff = false;
                        break;
                    }
                    beg++;
                    end--;
                }
                if (ff) {
                    return true;
                }
                beg = f;
                end = l;
                end--;
                while (beg < end) {
                    if (s.charAt(beg) != s.charAt(end)) {
                        return false;
                    }
                    beg++;
                    end--;
                }
                return true;
            }
            beg++;
            end--;
        }
        return true;
    }
    public static boolean searchMatrix(int[][] matrix, int target) {
        int l = 0;
        int r = matrix.length * matrix[0].length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            int row = m / matrix[0].length;
            int col = m % matrix[0].length;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }
    public static ListNode partition(ListNode head, int x, char c) {
        if (head == null) {
            return null;
        }
        ListNode lessHead = new ListNode(-1);
        ListNode currLessHead = lessHead;
        ListNode greaterOrEqualHead = new ListNode(-1);
        ListNode currGreaterOrEqualHead = greaterOrEqualHead;
        while (head != null) {
            if (head.val < x) {
                currLessHead.next = head;
                currLessHead = head;
            } else {
                currGreaterOrEqualHead.next = head;
                currGreaterOrEqualHead = head;
            }
            head = head.next;
        }
        currLessHead.next = greaterOrEqualHead.next;
        currGreaterOrEqualHead.next = null;
        return lessHead.next;
    }
    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        List<ListNode> less = new ArrayList<>();
        List<ListNode> greaterOrEqual = new ArrayList<>();
        while (head != null) {
            if (head.val < x) {
                less.add(head);
            } else {
                greaterOrEqual.add(head);
            }
            head = head.next;
        }
        ListNode currHead = new ListNode(-1);
        head = currHead;
        for (ListNode node : less) {
            currHead.next = node;
            currHead = currHead.next;
        }
        for (ListNode listNode : greaterOrEqual) {
            currHead.next = listNode;
            currHead = currHead.next;
        }
        currHead.next = null;
        return head.next;
    }
    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;

        }
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }
    public static TreeNode sortedArrayToBST(int[] nums) {
        int m = (nums.length - 1) / 2;
        if ((nums.length - 1) % 2 != 0) {
           m++;
        }
        int l = m - 1;
        int r = m + 1;
        TreeNode root = new TreeNode(nums[m]);
        while (l >= 0 || r < nums.length) {
            recHelper(root, nums, l , r);
            l--;
            r++;
        }

        return root;
    }
    public static void recHelper(TreeNode root, int[] nums, int l, int r) {
        TreeNode currRoot = root;
        while (currRoot.left != null) {
            currRoot = currRoot.left;
        }
        if (l >= 0) {
            currRoot.left = new TreeNode(nums[l]);
        }
        currRoot = root;
        while (currRoot.right != null) {
            currRoot = currRoot.right;
        }
        if (r < nums.length) {
            currRoot.right = new TreeNode(nums[r]);
        }
    }
    public static int numIslands(char[][] grid) {
        int cnt = 0;
        Queue<Integer> iS = new LinkedList<>();
        Queue<Integer> jS = new LinkedList<>();

        for (int l = 0; l < grid.length; l++) {
            for (int k = 0; k < grid[0].length; k++) {
                if (grid[l][k] == '1') {
                    iS.add(l);jS.add(k);
                    grid[l][k] = '0';
                } else {
                    continue;
                }
                while (!iS.isEmpty()) {
                    int i = iS.poll();
                    int j = jS.poll();
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        iS.add(i - 1);
                        jS.add(j);
                        grid[i - 1][j] = '0';
                    }
                    if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
                        iS.add(i);
                        jS.add(j + 1);
                        grid[i][j + 1] = '0';
                    }
                    if (i + 1 < grid.length && grid[i + 1][j] == '1') {
                        iS.add(i + 1);
                        jS.add(j);
                        grid[i + 1][j] = '0';
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        iS.add(i);
                        jS.add(j - 1);
                        grid[i][j - 1] = '0';
                    }
                }
                cnt++;
            }
        }
        return cnt;
    }
    public static boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        String[] tokens = s.split(" ");
        if (tokens.length != pattern.length()) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(tokens[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(tokens[i])) {
                    return false;
                }
                map.put(pattern.charAt(i), tokens[i]);
            }
        }
        return true;
    }
    public static String addBinary(String a, String b) {
        StringBuilder s = new StringBuilder();
        int minLen = Math.min(a.length(), b.length());
        int maxLen = Math.max(a.length(), b.length());
        boolean f = minLen == a.length();
        s.append("0".repeat(maxLen - minLen));
        if (f) {
            s.append(a);
            a = s.toString();
        } else {
            s.append(b);
            b = s.toString();
        }
        s = new StringBuilder();
        boolean c = false;
        for (int i = maxLen - 1; i >= 0; i--) {
            if (a.charAt(i) == '0' && b.charAt(i) == '0') {
                if (c) {
                    s.append(1);
                    c = false;
                } else {
                    s.append(0);
                }
            } else if ((a.charAt(i) == '1' && b.charAt(i) == '0') ||
                    a.charAt(i) == '0' && b.charAt(i) == '1') {
                if (c) {
                    s.append(0);
                } else {
                    s.append(1);
                }
            } else {
                if (c) {
                    s.append(1);
                } else {
                    s.append(0);
                    c = true;
                }
            }
        }
        if (c) {
            s.append(1);
        }
        return s.reverse().toString();
    }
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        return hasPathSum(root.left, targetSum) ||
               hasPathSum(root.right, targetSum);

    }
    public static int minimumTotal(List<List<Integer>> l) {
        if (l.size() == 1) {
            return l.get(0).get(0);
        }
        int[][] dp = new int[l.size()][];

        for (int i = 0; i < l.size(); i++) {
            dp[i] = new int[i + 1];
        }
        dp[0][0] = l.get(0).get(0);
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < l.size(); i++) {
            int j = 0;
            while (j < i + 1) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + l.get(i).get(0);
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][i - 1] + l.get(i).get(i);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + l.get(i).get(j),
                            dp[i - 1][j] + l.get(i).get(j));
                }
                if (i == l.size() - 1 && min > dp[i][j]) {
                    min = dp[i][j];
                }
                j++;
            }
        }
        return min;
    }
    public static int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        help(root, s, list, null, null);
        return list.stream().reduce(0, Integer::sum);
    }
    public static void help(TreeNode root, StringBuilder s, List<Integer> list, TreeNode prev,
                                  TreeNode before) {
        if (root == null) {
            if (prev.left == null && prev.right == null && prev != before) {
                int num = Integer.parseInt(s.toString());
                list.add(num);
            }
            return;
        }
        s.append(root.val);
        help(root.left, s, list, root, before);
        before = root;
        help(root.right, s, list, root, before);
        s.deleteCharAt(s.length() - 1);
    }
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        int i = 0;

        while (i < intervals.length && intervals[i][1] < start) {
            res.add(intervals[i++]);
        }
        while (i < intervals.length && intervals[i][0] <= end) {
            start = Math.min(intervals[i][0], start);
            end = Math.max(intervals[i][1], end);
            i++;
        }
        newInterval[0] = start;
        newInterval[1] = end;
        res.add(newInterval);

        while (i < intervals.length) {
            res.add(intervals[i++]);
        }
        return res.toArray(new int[res.size()][]);
    }
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                int val = map.get(c);
                map.put(c, val + 1);
            }
        }
        for (char c : ransomNote.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            int val = map.get(c);
            if (val == 1) {
                map.remove(c);
            } else {
                map.put(c, val - 1);
            }
        }
        return true;
    }
    public static boolean validAnagram(String s, String s1) {
        if (s.length() != s1.length()) {
            return false;
        }
        int[] array = new int[25];
        for (int i = 0, j = 0; i < s.length() && j < s1.length(); i++, j++) {
            array[s.charAt(i) - 'a']++;
            array[s1.charAt(j) - 'a']--;
        }
        for (int m : array) {
            if (m != 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isSubsequence(String s, String t) {
        int j = 0;
        char firstPointer = s.charAt(j++);
        char secondPointer;

        for (int i = 0; i < t.length(); i++) {
            secondPointer = t.charAt(i);
            if (firstPointer == secondPointer) {
                if (j == s.length()) {
                    return true;
                }
                firstPointer = s.charAt(j++);
            }
        }
        return false;
    }
    public static String reverseWords(String s) {
        String[] tokens = s.split(" ");
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (!token.equals("")) {
                stack.push(token);
            }
        }
        StringBuilder res = new StringBuilder();
        while (stack.size() > 1) {
            res.append(stack.pop());
            res.append(" ");
        }
        res.append(stack.pop());
        return res.toString();
    }
    public static void flatten(TreeNode root) {
        preOrder(root);
        root = newRoot;
    }
    private static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (newRoot != null) {
            currNewRoot.left = null;
            currNewRoot.right = new TreeNode(root.val);
            currNewRoot = currNewRoot.right;
        } else {
            newRoot = new TreeNode(root.val);
            currNewRoot = newRoot;
        }
        preOrder(root.left);
        preOrder(root.right);

    }
    public static TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.left != null || curr.right != null) {
                TreeNode temp = curr.left;
                curr.left = curr.right;
                curr.right = temp;
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
            }
        }
        return root;
    }
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return recHelper(root, 1);
    }
    private static int recHelper(TreeNode root, int d) {
        if (root == null) {
            return d - 1;
        }
        maxDepthL = recHelper(root.left, d + 1);
        maxDepthR = recHelper(root.right, d + 1);
        return Math.max(maxDepthL, maxDepthR);
    }
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Queue<Integer> q = new LinkedList<>();
        ListNode prevHead;
        int prevVal1 = Integer.MIN_VALUE;
        prevHead = head;
        head = head.next;
        while (head != null) {
            if (prevVal1 != prevHead.val && prevHead.val != head.val) {
                q.add(prevHead.val);
            }
            prevVal1 = prevHead.val;
            prevHead = head;
            head = head.next;
        }
        if (prevVal1 != prevHead.val) {
            q.add(prevHead.val);
        }
        if (q.isEmpty()) {
            return new ListNode();
        }
        ListNode res = new ListNode(q.poll());
        ListNode curr = res;
        while (!q.isEmpty()) {
            curr.next = new ListNode(q.poll());
            curr = curr.next;
        }
        return res;
    }
    public static String intToRoman(int num) {
        List<String> numsRoman = new ArrayList<>();
        Map<Integer, String> roman = initMap();
        int mul = 0;
        int n;
        StringBuilder s = new StringBuilder();
        while (num != 0) {
            n = (int) ((num % 10) * Math.pow(10, mul++));
            if (roman.containsKey(n)) {
                numsRoman.add(roman.get(n));
            } else {
                int j = (int) Math.pow(10, mul);
                while (n != 0) {
                    if (n >= j && roman.containsKey(j)) {
                        s.append(roman.get(j));
                        n -= j;
                    } else {
                        j -= Math.pow(10, mul - 1);
                    }

                }
                numsRoman.add(s.toString());
                s.delete(0, s.length());
            }
            num /= 10;
        }
        for (int i = numsRoman.size() - 1; i >= 0; i--) {
            s.append(numsRoman.get(i));
        }
        return s.toString();
    }
    private static Map<Integer, String> initMap() {
        Map<Integer, String> roman = new HashMap<>();
        roman.put(1, "I");roman.put(5, "V");roman.put(10, "X");roman.put(50, "L");
        roman.put(100, "C");roman.put(500, "D");roman.put(1000, "M");roman.put(4, "IV");
        roman.put(9, "IX");roman.put(40, "XL");roman.put(90, "XC");roman.put(400, "CD");
        roman.put(900, "CM");
        return roman;
    }
    public static void stack(String path) {
        Stack<String> stack = new Stack<>();
        String[] p = path.split("/");
        for (String value : p) {
            if (!stack.empty() && value.equals(".."))
                stack.pop();
            else if (!value.equals(".") && !value.equals("") && !value.equals(".."))
                stack.push(value);
        }
        List<String> res = new ArrayList<>(stack);
        System.out.println("/" + String.join("/", res));
    }
    public static int singleNumber(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            num ^= nums[i];
        }

        return num;
    }
    public static void rotate(int[] nums, int k) {
        int l = k % nums.length;
        int[] prefix = new int[nums.length - l];
        for (int j = 0; j < nums.length - l; j++) {
            prefix[j] = nums[j];
        }
        int index = 0;
        for (int j = nums.length - l; j < nums.length ;j++) {
            nums[index++] = nums[j];
        }
        for (int j = 0; j < nums.length - l; j++) {
            nums[index++] = prefix[j];
        }
        System.out.println(Arrays.toString(nums));
    }
    private static int factorial(int n) {
        int res = 1;
        while (n != 0) {
            res *= n;
            n--;
        }

        return res;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(preorder[0]);
        int inorderIndex = 0;
        stack.push(root);

        for(int i = 1; i < preorder.length; i++){
            TreeNode prev = null;
            while(!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                prev = stack.pop();
                inorderIndex++;
            }
            TreeNode toInsert = new TreeNode(preorder[i]);
            if(prev == null){
                prev = stack.peek();
                prev.left = toInsert;
            }
            else{
                prev.right = toInsert;
            }
            stack.push(toInsert);
        }
        return root;
    }
    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            if (i % 3 == 0) {
                builder.append("Fizz");
            } if (i % 5 == 0) {
                builder.append("Buzz");
            } if (builder.isEmpty()) {
                builder.append(i);
            }
            res.add(builder.toString());
        }
        return res;
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        recursive(root, 0, map);
        List<List<Integer>> res = new ArrayList<>(map.values());
        for(int i=0; i<res.size(); i+=2){
            Collections.reverse(res.get(i));
        }
        return res;
    }
    private static void recursive(TreeNode root, int depth, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return;
        }
        List<Integer> li = map.getOrDefault(depth, new ArrayList<>());
        li.add(root.val);
        map.put(depth, li);
        recursive(root.left, depth + 1, map);
        recursive(root.right,depth + 1, map);
    }
    public static int missingNumber(int[] nums) { //xor
        int res = nums.length;
        for(int i=0; i<nums.length; i++){
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }
    public static int longestConsecutive(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int cnt = 1;
        int max_cnt = 1;
        for (int num : nums) {
            q.add(num);
        }

        int first;
        int second = q.poll();
        while (!q.isEmpty()) {
            first = second;
            second = q.poll();
            if (second - first == 1) {
                cnt++;
            } else if (second != first) {
                if (cnt > max_cnt) {
                    max_cnt = cnt;
                }
                cnt = 1;
            }
        }

        return Math.max(cnt, max_cnt);
    }
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int n1, n2;
        for (String s : tokens) {
            switch (s) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 - n2);
                }
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "/" -> {
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 / n2);
                }
                default -> stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
    public static String largestNumber(int[] nums) {
        StringBuilder str = new StringBuilder();
        String[] s = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, (i, j) -> {
            String s1 = i + j;
            String s2 = j + i;
            return s1.compareTo(s2);
        });

        if (s[s.length - 1].charAt(0) == '0') {
            return "0";
        }
        for (int i = s.length - 1; i >= 0; i--) {
            str.append(s[i]);
        }

        return str.toString();
    }
    public static ListNode sortList(ListNode head) {
        ListNode curr_head = head;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        while (curr_head != null) {
            q.add(curr_head.val);
            curr_head = curr_head.next;
        }
        curr_head = head;
        while (curr_head != null) {
            curr_head.val = q.remove();
            curr_head = curr_head.next;
        }
        return head;
    }
    public static void sortColors(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);  //add
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = pq.remove();  //poll
        }
        System.out.println(Arrays.toString(nums));
    }
    public static void setZeroes(int[][] matrix) {
        boolean[] arr_i = new boolean[matrix.length]; // m
        boolean[] arr_j = new boolean[matrix[0].length]; // n

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    arr_i[i] = true;
                    arr_j[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0 && arr_i[i] && arr_j[j]) {
                    int curr_i = i;
                    i = 0;
                    while (i < matrix.length) {
                        if (matrix[i][j] != 0) {
                            matrix[i][j] = 0;
                        }
                        i++;
                    }
                    i = curr_i;
                    int curr_j = j;
                    j = 0;
                    while (j < matrix[0].length) {
                        if (matrix[i][j] != 0) {
                            matrix[i][j] = 0;
                        }
                        j++;
                    }
                    j = curr_j;
                }
            }
        }
    }
    public static int uniquePaths(int m, int n) {
        int cnt=0;
        int[][] dp=new int[m][n];
        return helper(0,0,m,n,dp);
    }
    public static int helper(int r,int c,int m,int n,int [][]dp){
        if(c>=n||r>=m){
            return 0;
        }
        if(dp[r][c]!=0){
            return dp[r][c];
        }
        if(r==m-1&&c==n-1){
            return 1;
        }
        int cnt1=helper(r+1,c,m,n,dp);
        int cnt2=helper(r,c+1,m,n,dp);
        return dp[r][c]=cnt1+cnt2;
    }
    public static int[][] merge(int[][] intervals) {
        int cnt = 0;
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        int[] curr_arr1 = intervals[0];
        int i = 1;
        int[][] res = new int[intervals.length][2];
        int res_index = 0;
        boolean f = true;

        while (i < intervals.length) {
            int[] curr_arr2 = intervals[i];
            while (curr_arr1[1] >= curr_arr2[0]) {
                int[] curr_res = new int[2];
                curr_res[0] = curr_arr1[0];
                curr_res[1] = Math.max(curr_arr2[1], curr_arr1[1]);
                curr_arr1 = curr_res;
                cnt++;
                if (i < intervals.length - 1) {
                    curr_arr2 = intervals[++i];
                } else {
                    f = false;
                    break;
                }
            }
            res[res_index++] = curr_arr1;
            curr_arr1 = curr_arr2;
            i++;
        }
        if (f && i == intervals.length) {
            res[res_index] = curr_arr1;
        }
        System.out.println(cnt);
        int len = intervals.length - cnt;
        int[][] res1 = new int[len][2];
        System.arraycopy(res, 0, res1, 0, len);
        return res1;
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> l = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = 0, q = 0, p = 0, w = 0, t = 0, r = 0;
        int down = n - 1;
        int down_i = 1;
        int left_j = n - 2;
        int left = m - 1;
        int up_i = m - 2;
        int up = 0;

        while (true) {
            if (l.size() == m*n) {
                break;
            }
            for (int k = j; k < n - t; k++) {
                l.add(matrix[i][k]);
            }
            i++;t++;j++;
            if (l.size() == m*n) {
                break;
            }
            for (int k = down_i; k < m - q; k++) {
                l.add(matrix[k][down - p]);
            }
            down_i++;q++;p++;
            if (l.size() == m*n) {
                break;
            }
            for (int k = left_j; k >= w; k--) {
                l.add(matrix[left][k]);
            }
            left_j--;w++;left--;
            if (l.size() == m*n) {
                break;
            }
            for (int k = up_i; k >= 1 + r; k--) {
                l.add(matrix[k][up]);
            }
            r++;up_i--;up++;
        }

        return l;
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        String[] s_sorted = new String[strs.length];
        List<Boolean> tf = new ArrayList<>();

        for (int i = 0; i < strs.length;i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            s_sorted[i] = new String(c);
            tf.add(false);
        }
//        System.out.println(Arrays.toString(s_sorted));
        List<List<String>> res = new ArrayList<>();
        List<String> ss;
        int index = 0;

        while (tf.contains(false)) {
            ss = new ArrayList<>();
            String curr;
            if (!tf.get(index)) {
                curr = s_sorted[index];
                ss.add(strs[index]);
                tf.remove(index);
                tf.add(index, true);
                for (int i = 0; i < s_sorted.length; i++) {
                    if (!tf.get(i)) {
                        if (curr.equals(s_sorted[i])) {
                            ss.add(strs[i]);
                            tf.remove(i);
                            tf.add(i, true);
                        }
                    }
                }
                res.add(ss);
            }
            index++;
        }

        return res;
    }
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0) {
            return permutations;
        }

        collectPermutations(nums, 0, new ArrayList<>(), permutations);
        return permutations;
    }

    private static void collectPermutations(int[] nums, int start, List<Integer> permutation,
                                     List<List<Integer>>  permutations) {

        if (permutation.size() == nums.length) {
            permutations.add(permutation);
            return;
        }

        for (int i = 0; i <= permutation.size(); i++) {
            List<Integer> newPermutation = new ArrayList<>(permutation);
            newPermutation.add(i, nums[start]);
            collectPermutations(nums, start + 1, newPermutation, permutations);
        }
    }
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int[] cols = new int[n*n];
        int index = 0;
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                cols[index++] = matrix[i][j];
            }
        }
        index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = cols[index++];
            }
        }
    }
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        res = countAndSay(n - 1);
        builder.delete(0, builder.length());
        int cnt = 0;
        char d;
        char cmp = res.charAt(0);
        for (int i = 0; i < res.length(); i++) {
            d = res.charAt(i);
            if (cmp == d) { // 2 == 1
                cnt++;
            } else {
                builder.append(cnt);
                builder.append(cmp);
                cnt = 1;
            }
            cmp = d;
        }

        builder.append(cnt);
        builder.append(cmp);

        res = builder.toString();
        return res; // 1211
    }
    public static boolean isValidSudoku(char[][] board) {
        boolean res;
        int i = -1, j = -1;
        while (i < 6 && j < 6) {
            res = isValidSquare(i + 1, j + 1, i + 3, j + 3, board);// 0,0   2,2
            if (!res) {
                return false;
            }
            digits = new HashSet<>();
            j+=3; // 8
            if (j == 8) {
                i+=3;
                j = -1;
            }
        }
        i = 0;j = 0;
        for (i = 0,j = 0; i < 9 && j < 9; i++, j++) {
            res = isValidRow(i,board);
            if (!res) {
                return false;
            }
            digits = new HashSet<>();
            res = isValidCol(j,board);
            if (!res) {
                return false;
            }
            digits = new HashSet<>();
        }

        return true;
    }
    private static boolean isValidSquare(int i_s, int j_s, int i_e, int j_e, char[][] board) {
        for (int i = i_s; i <= i_e; i++) {
            for (int j = j_s; j <= j_e; j++) {
                if (board[i][j] != '.') {
                    if (!digits.add(board[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private static boolean isValidRow(int i_s, char[][] board) {
        for (int j = 0; j < 9; j++) {
            if (board[i_s][j] != '.') {
                if (!digits.add(board[i_s][j])) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean isValidCol(int j_s, char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i][j_s] != '.') {
                if (!digits.add(board[i][j_s])) {
                    return false;
                }
            }
        }
        return true;
    }
    public static int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }

            if (nums[m] > nums[l]) {
                if (target <= nums[m] && target >= nums[l]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }

            else if (nums[m] < nums[r]) {
                if (target <= nums[m] && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }
        return -1;
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        curr = head;
        int i = len - n; // 3
        if (i == 0) {
            return head.next;
        }
        for (int j = 1; j < i; j++) {
            curr = curr.next;
        }
        if (curr.next.next == null) {
            curr.next = null;
        } else {
            curr.next = curr.next.next;
        }

        return head;
    }
    public static void m1(MyQueueImplementation Q1,MyQueueImplementation Q2,MyQueueImplementation Q3) {
        int n1 = 0, n2 = 0, n3 = 0;
        while (n1 != -1 && n2 != -1 && n3 != -1) {
            n1 = Q1.pop();
            if (n1 != -1) {
                System.out.print((char)n1);
            }
            n2 = Q2.pop();
            if (n2 != -1) {
                System.out.print((char)n2);
            }
            n3 = Q3.pop();
            if (n3 != -1) {
                System.out.print((char)n3);
            }
        }
    }
    public static int m(char[] text) {
        int cnt = 1;
        int max_cnt = 1;
        for (int i = 0 ; i < text.length - 1; i++) {
            if (text[i] == text[i + 1]) {
                cnt++;
            } else {
                if (max_cnt < cnt) {
                    max_cnt = cnt;
                }
                cnt = 1;
            }
        }

        System.out.println(Arrays.toString(text));
        return max_cnt;
    }
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; //Cornor case when -2^31 is divided by -1 will give 2^31 which doesnt exist so overflow

        boolean negative = dividend < 0 ^ divisor < 0; //Logical XOR will help in deciding if the results is negative only if any one of them is negative

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int quotient = 0, subQuot = 0;

        while (dividend - divisor >= 0) {
            for (subQuot = 0; dividend - (divisor << subQuot << 1) >= 0; subQuot++);
            quotient += 1 << subQuot; //Add to the quotient
            dividend -= divisor << subQuot; //Substract from dividend to start over with the remaining
        }
//        However multiplication is not allowed to use let see what else we can do for replacing it.
//
//        Programmatic Rule:
//        Left Shift (<<) shall be considered as multiplication by 2^N
//        Similarly, Right Shift (>>) shall be considered as division by 2^N
        return negative ? -quotient : quotient;
    }
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        recurse(res, 0, 0, "", n);
        return res;
    }

    public static void recurse(List<String> res, int left, int right, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }

        if (left < n) {
            recurse(res, left + 1, right, s + "(", n);
        }

        if (right < left) {
            recurse(res, left, right + 1, s + ")", n);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                helperTriplets(i, j, j + 1, nums);
            }
        }
        return l;
    }
    private static void helperTriplets(int i, int j, int k, int[] nums) {
        if (k >= nums.length) {
            return;
        }
        if (nums[i] + nums[j] + nums[k] == 0) {
            List<Integer> curr = new ArrayList<>();
            curr.add(nums[i]);curr.add(nums[j]);curr.add(nums[k]);
            curr.sort(Comparator.comparingInt(x -> x));
            if (!l.contains(curr)) {
                l.add(curr);
            }
        }
        helperTriplets(i, j, k + 1, nums);
    }
    private static void addUniqueList(List<List<Integer>> listOfLists, List<Integer> newList) {
        Set<List<Integer>> setOfLists = new HashSet<>(listOfLists);

        if (!setOfLists.contains(newList)) {
            listOfLists.add(newList);
        }
    }
    public static List<String> letterCombinations_queue(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        int len = digits.length();
        String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> queue = new LinkedList<>();
        int[] digitsArr = new int[len];
        for (int i = 0; i < len; i++) {
            digitsArr[i] = digits.charAt(i) - '0';
        }

        queue.offer("");
        for (int i = 0; i < len; i++) {
            String letter = letters[digitsArr[i] - 2];
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                for (char ch : letter.toCharArray()) {
                    queue.offer(temp + ch);
                }
            }
        }

        return new ArrayList<>(queue);
    }
    public static int myAtoi(String s) {
        return  0;
    }
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int max = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            while(!set.add(s.charAt(right))) {
                set.remove(s.charAt(left++));
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int plus) {
        ListNode res = new ListNode();
        if (l1 == null && l2 == null) {
            if (plus == 1) {
                res.val = 1;
                return res;
            }
            return null;
        }

        int val1,val2;

        if (l1 == null) {
            val1 = 0;
        } else {
            val1 = l1.val;
        }if (l2 == null) {
            val2 = 0;
        } else {
            val2 = l2.val;
        }

        res.val = (val1 + val2 + plus) % 10;
        int plusOne = (val1 + val2 + plus) / 10;
        if (l1 == null) {
            res.next = addTwoNumbers(null, l2.next, plusOne);
        } else if (l2 == null) {
            res.next = addTwoNumbers(l1.next, null, plusOne);
        } else {
            res.next = addTwoNumbers(l1.next, l2.next, plusOne);
        }

        return res;
    }
    public static int[] twoSum(int[] nums, int target) {
        int[] asn = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    asn[0] = i;
                    asn[1] = j;
                    return asn;
                }
            }
        }

        return null;
    }
    public static int romanToInteger(String s) {
        int answer = 0, number = 0, prev = 0;

        for (int j = s.length() - 1; j >= 0; j--) {
            switch (s.charAt(j)) {
                case 'M' -> number = 1000;
                case 'D' -> number = 500;
                case 'C' -> number = 100;
                case 'L' -> number = 50;
                case 'X' -> number = 10;
                case 'V' -> number = 5;
                case 'I' -> number = 1;
            }
            if (number < prev) {
                answer -= number;
            }
            else {
                answer += number;
            }
            prev = number;
        }
        return answer;
    }
    public static String longestCommonPrefix(String[] strs) {
        String s1 = strs[0];
        String s2 = strs[strs.length - 1];
        int i = 0;

        while (i < s1.length() && i < s2.length()) {
            if (s1.charAt(i) == s2.charAt(i)) {
                i++;
            } else {
                break;
            }
        }

        return s1.substring(0, i);
    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode n1 = new ListNode(-2);
        ListNode curr = n1;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null ? list2 : list1;
        return n1.next;
    }
    public static int removeDuplicates(int[] nums) {
        int index = 1, cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index - 1] != nums[i]) {  // {-3,-1,0,0,0,3,3};
                nums[index++] = nums[i];
                cnt++;
            }
        }
        return cnt;
    }
    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
    public static int[] plusOne(int[] digits) {
//        int cnt = 0;
//        for (int n : digits) {
//            if (n == 9) {
//                cnt++;
//            } else {
//                break;
//            }
//        }
//        int[] newDigits;
//        if (cnt == digits.length) {
//            newDigits = new int[digits.length + 1];
//        } else {
//            newDigits = new int[digits.length];
//        }
//
//        int index = newDigits.length - 1;
//        int i = digits.length - 1;
//        if (digits[i] == 9) {
//            while (i >= 0 && digits[i] == 9) { // 7,7,9,9
//                newDigits[index--] = 0;
//                i--;
//            }
//            if (i == -1) {
//                newDigits[index--] = 1;
//            } else {
//                newDigits[index--] = digits[i--] + 1;
//            }
//
//        } else {
//            newDigits[index--] = digits[i--] + 1;
//        }
//
//        for (int j = i; j >= 0; j--) {
//            newDigits[index--] = digits[i--];
//        }
//        return newDigits;

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[]dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        return dp[n];
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, index = 0;
        int[] nums3 = new int[m + n];
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                nums3[index++] = nums1[i];
                i++;
            } else {
                nums3[index++] = nums2[j];
                j++;
            }
        }

        if (i != m) {
            for (int k = i; k < m; k++) {
                nums3[index++] = nums1[k];
            }
        } else {
            for (int k = j; k < n; k++) {
                nums3[index++] = nums2[k];
            }
        }

        System.arraycopy(nums3, 0, nums1, 0, nums3.length);
        System.out.println(Arrays.toString(nums1));
    }
//    public boolean isSymmetric(TreeNode root) {
//        if(root==null)  return true;
//
//        Stack<TreeNode> stack = new Stack<TreeNode>();
//        TreeNode left, right;
//        if(root.left!=null){
//            if(root.right==null) return false;
//            stack.push(root.left);
//            stack.push(root.right);
//        }
//        else if(root.right!=null){
//            return false;
//        }
//
//        while(!stack.empty()){
//            if(stack.size()%2!=0)   return false;
//            right = stack.pop();
//            left = stack.pop();
//            if(right.val!=left.val) return false;
//
//            if(left.left!=null){
//                if(right.right==null)   return false;
//                stack.push(left.left);
//                stack.push(right.right);
//            }
//            else if(right.right!=null){
//                return false;
//            }
//
//            if(left.right!=null){
//                if(right.left==null)   return false;
//                stack.push(left.right);
//                stack.push(right.left);
//            }
//            else if(right.left!=null){
//                return false;
//            }
//        }
//
//        return true;
//    }

    public static boolean isPalindrome(String s) {
        StringBuilder str = new StringBuilder(); // бялхляб
        for (char c : s.toCharArray()) {
            if ((c > 47 && c < 58) || (c > 64 && c < 91) || (c > 96 && c < 123)) {
                if (c > 64 && c < 91) {
                    c += 32;
                }
                str.append(c);
            }
        }
        String palindrome = str.toString();
        int len = palindrome.length();
        for (int i = 0; i < len / 2; i++) {
            if (palindrome.charAt(i) != palindrome.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            triangle.add(new ArrayList<>());
        }

        int f,s,index;
        triangle.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            index = 0;
            triangle.get(i).add(1);
            while (index < triangle.get(i - 1).size() - 1) {
                f = triangle.get(i - 1).get(index++);
                s = triangle.get(i - 1).get(index);
                triangle.get(i).add(f + s);
            }
            triangle.get(i).add(1);
        }

        return triangle;
    }
}
