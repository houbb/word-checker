package com.github.houbb.word.checker.util;

import com.github.houbb.word.checker.constant.EditOperateEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 编辑距离工具类
 *
 * @since 1.2.0
 */
public final class EditDistanceHelper {

    /**
     * 基于 DP + 内存优化的最佳实现
     * 时间复杂度：`O(m * n)`
     * 空间复杂度：`O(n)`
     * @param source 单词1
     * @param target 单词2
     * @return 结果长度
     * @since 1.2.0
     */
    public static int minDistance(String source, String target) {
        int m = source.length();
        int n = target.length();

        // 用两个一维数组代替二维数组
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];

        // 初始化第一行
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        // 填充动态规划表
        for (int i = 1; i <= m; i++) {
            curr[0] = i;  // 第i行的第0列，表示将word1[0..i-1]转换为空字符串，需要i次删除
            for (int j = 1; j <= n; j++) {
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    curr[j] = prev[j - 1];  // 字符相等，无需操作
                } else {
                    curr[j] = Math.min(Math.min(prev[j], curr[j - 1]), prev[j - 1]) + 1;  // 三种操作的最小值
                }
            }
            // 更新prev和curr
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];  // 返回最后的结果
    }

    /**
     * 返回最短的编辑距离的处理过程
     * @param source 原始
     * @param target 目标
     * @return 结果列表
     * @since 1.2.0
     */
    public static List<String> minDistanceList(String source, String target) {
        int m = source.length();
        int n = target.length();

        // DP数组，记录最小操作数
        int[][] dp = new int[m + 1][n + 1];
        // 操作类型数组，记录操作类型："" = no-op, "insert", "delete", "replace"
        String[][] operation = new String[m + 1][n + 1];

        // 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
            operation[i][0] = EditOperateEnum.DELETE.getCode();
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
            operation[0][j] = EditOperateEnum.INSERT.getCode();
        }

        // 填充DP表和操作类型表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (source.charAt(i - 1) == target.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    operation[i][j] = ""; // 字符相等，不需要操作
                } else {
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;

                    // 选择最小的操作
                    dp[i][j] = Math.min(Math.min(insert, delete), replace);

                    // 记录操作类型
                    if (dp[i][j] == insert) {
                        operation[i][j] = EditOperateEnum.INSERT.getCode();
                    } else if (dp[i][j] == delete) {
                        operation[i][j] = EditOperateEnum.DELETE.getCode();
                    } else {
                        operation[i][j] = EditOperateEnum.REPLACE.getCode();
                    }
                }
            }
        }

        // 回溯操作路径，生成变换过程
        List<String> result = new ArrayList<>();
        int i = m, j = n;
        StringBuilder currentWord = new StringBuilder(source);

        // 回溯路径
        while (i > 0 || j > 0) {
            addNewWord(result, currentWord.toString());
            if (operation[i][j].equals("")) {
                i--;
                j--;
            } else if (operation[i][j].equals(EditOperateEnum.INSERT.getCode())) {
                currentWord.insert(i, target.charAt(j - 1));
                j--;
            } else if (operation[i][j].equals(EditOperateEnum.DELETE.getCode())) {
                currentWord.deleteCharAt(i - 1);
                i--;
            } else if (operation[i][j].equals(EditOperateEnum.REPLACE.getCode())) {
                currentWord.setCharAt(i - 1, target.charAt(j - 1));
                i--;
                j--;
            }
        }

        // 添加初始单词到变换过程
        addNewWord(result, currentWord.toString());

        // 由于回溯是从后往前的，因此需要反转结果
//        Collections.reverse(result);
        return result;
    }

    // 避免数据重复
    private static void addNewWord(List<String> list, String word) {
        if(list.size() > 0 && word.equals(list.get(list.size()-1))) {
            return;
        }

        list.add(word);
    }

}
