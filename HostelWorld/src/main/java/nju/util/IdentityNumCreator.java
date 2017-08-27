package nju.util;

import java.util.List;

/**
 * Created by Srf on 2017/3/12
 */
public class IdentityNumCreator {

    public static String createNextIdentityNum(List<String> nums) {
        if(nums==null||nums.size()==0){
            return "1000000";
        }
        nums.sort(String::compareTo);
        String maxNum = nums.get(nums.size()-1);
        int max = Integer.parseInt(maxNum) + 1;
        return max+"";
    }

}
