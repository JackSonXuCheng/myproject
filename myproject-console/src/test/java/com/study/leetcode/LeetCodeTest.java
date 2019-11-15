package com.study.leetcode;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/12 12:15
 * @comment:
 */

public class LeetCodeTest {

    @Test
    public void test() {
        int[] numb = {1, 2, 1};
        int[] ints = Arrays.stream(numb).distinct().toArray();
        System.out.println(ints.length);


    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    @Test
    public void test1() {
        int[] num = {1, 2, 5, 4, 3};
        int number = 0;
        for (int i = num.length - 1; i > 0; i--) {
            int lr = num[i] - num[i - 1];
            if (lr > 0) {
                number += lr;
            }
        }
        System.out.println(number);
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     */
    @Test
    public void test2() {
        int[] num = {1, 2, 3, 4, 5};
        int k = 2;
        for (int i = 0; i < k; i++) {
            for (int j = num.length - 1; j > 0; j--) {
                int nu = num[j];
                num[j] = num[j - 1];
                num[j - 1] = nu;

            }
        }

        System.out.println(Arrays.toString(num)); //[4, 5, 1, 2, 3]
    }

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     */
    @Test
    public void test3() {
        int[] nums = {1, 2, 3, 6, 5};
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    System.out.println(true);
                }


            }


        }


    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明： ^ 2^3 将2 3 转成二级制 0010 0011  0001  1*2的零次方
     * <p>
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     */
    @Test
    public void test4() {
        int[] nums = {1, 1, 2, 2, 3};
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        System.out.println(result);

    }

    /**
     * 两个集合的交集
     */
    @Test
    public void test5() {
        int[] nums1 = {1, 2, 2};
        int[] nums2 = {2, 2};
        List nums3 = CollectionUtils.arrayToList(nums1);
        List nums4 = CollectionUtils.arrayToList(nums2);
        System.out.println(nums4);
        List collect = (List) nums3.stream().filter(result -> nums4.contains(result)).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 两个数组的交集 执行用时 6ms
     */
    @Test
    public void test6() {
        long start = System.currentTimeMillis();

        int[] nums1 = {1, 2, 2, 4};
        int[] nums2 = {2, 2, 2, 3, 4};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int key = nums1[i];
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }

        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            int key = nums2[i];
            if (map.containsKey(key) && map.get(key) > 0) {
                map.put(key, map.get(key) - 1);
                list.add(key);
            }
        }
        System.out.println(list);
        System.out.println(System.currentTimeMillis() - start);

    }

    /**
     * 两个数组的交集 执行用时 2ms 前提两个数组必须排好顺序
     */
    @Test
    public void test7() {
        long start = System.currentTimeMillis();
        int[] num1 = {1, 2, 2, 4};
        int[] num2 = {2, 2, 2, 3, 4};
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < num1.length && j < num2.length; ) {
            if (num1[i] == num2[j]) {
                list.add(num1[i]);
                i++;
                j++;
            } else if (num1[i] > num2[j]) {
                j++;
            } else if (num1[i] < num2[j]) {
                i++;
            }
        }

        System.out.println(list);
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void test8() {
        int[] digits = {4, 3, 2, 1};
        //假设第一个为最大值
        int arr = digits[0];
        String str = arr + "," + 0;
        for (int i = 0; i < digits.length; i++) {
            if (arr < digits[i]) {
                arr = digits[i];
                str = arr + "," + i;
            }

        }
        String[] split = str.split(",");
        String strs = split[1];
        digits[Integer.valueOf(strs)] = 1 + Integer.valueOf(split[0]);
        //Arrays.sort(digits);
        System.out.println(Arrays.toString(digits));
    }


    @Test
    public void test9() {
        int[] digits = {9, 9};
        int len = digits.length;
        int i = len - 1;
        int th = 9;
        digits[i] += 1;
        if (digits[i] > th) {   //大于9  产生进位
            while (i > 0 && digits[i] > th) {
                digits[i] = 0;
                digits[--i] += 1;
            }
            if (i == 0 && digits[i] > th) {   //如果首位大于9， 将数组扩大一位，首位变为1
                digits = new int[len + 1];
                digits[0] = 1;
            }
        }

        System.out.println(Arrays.toString(digits));
    }

    @Test
    public void test10() {
        int[] digits = {8, 9, 9};
        int length = digits.length - 1;


        for (int i = length; i >= 0; i--) {


            if (digits[i] < 9) {
                digits[length] = digits[length] + 1;
                break;
            } else {
                if (length == 0) {
                    if (digits[i] > 9) {
                        digits = new int[digits.length + 1];
                        digits[0] = 1;
                    }
                } else {
                    if (digits[i] >= 9) {

                        if (i == 0) {
                            if (digits[i] > 9) {
                                digits = new int[digits.length + 1];
                                digits[0] = 1;
                            }
                        } else {
                            digits[i] = 0;
                            digits[i - 1] += 1;
                        }
                    } else {
                        break;
                    }
                }


            }


        }

        System.out.println(Arrays.toString(digits));


    }
}
