package com.ssl.note.common.utils;

public class ArrayUtil {

  public static boolean isEquals(int[] nums, int[] right) {
    int i = 0;
    while (i < nums.length) {
      if (nums[i] != right[i]) {
        return false;
      }
      i++;
    }
    return true;
  }
}
