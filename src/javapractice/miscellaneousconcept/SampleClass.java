package javapractice.miscellaneousconcept;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleClass {

    public static void main(String[] args) {

        System.out.println(isSubsequence("aaaaaa","bbaaaa"));
        /*int stR=4;
        int stC=4;
        int endR=2;
        int endC=1;
        int [][] mat = {{1,1,1,1,1},
                       {1,1,1,1,1},
                       {1,1,0,1,1},
                       {1,0,0,0,1},
                       {1,1,1,1,1}};
        //7 step
        int n = mat.length;
        countMinStepINMatrix(mat,n,stR,stC,endR,endC);*/


    }

    public static boolean isSubsequence(String s, String t) {
        if(t == null|| t.isEmpty()){
            return false;
        }
        if(s == null){
            return true;
        }
        int i=0,j=0;
        int n=s.length();
        int m = t.length();
        while(i<n){
            for(;j<m;j++){
                 if(s.charAt(i) == t.charAt(j)){
                     j++;
                     break;
                 }
            }
            if(j>=m && i<n){
                return false;
            }
            i++;
        }
        System.out.printf("outer loop");
        if(i>=n){
            return true;
        }
        return false;
    }


    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        int c=0;
        int l = nums.length;
        int s=0;
        int e=l-1;
        List<List<Integer>> result = new ArrayList<>();
        int tempSum = 0;
        for(int i=0;i<l-2;i++){
            c=nums[i];
            s=i+1;
            e=l-1;
            List<Integer> tempList = new ArrayList<Integer>();
            while(s<e){
                tempSum=nums[s]+nums[e];
                if(c==tempSum){
                    tempList.add(nums[s]);
                    tempList.add(nums[e]);
                    tempList.add(c);
                    break;
                }
                s++;
                e--;
            }

        }
        return result;

    }

    private static int countMinStepINMatrix(int[][] mat, int n, int stR, int stC, int endR, int endC) {

        boolean [][]visited = new boolean[n][n];

        if(!isValidpoint(mat,n,stR,stC,endR,endC,visited)){
            return 0;
        }
        int a = countMinStepINMatrix(mat,n,stR,stC+1,endR,endC);
        int b = countMinStepINMatrix(mat,n,stR+1,stC,endR,endC);
        int c = countMinStepINMatrix(mat,n,stR-1,stC,endR,endC);
        int d = countMinStepINMatrix(mat,n,stR,stC-1,endR,endC);
        return Math.min(Math.min(a,b),Math.min(c,d));

    }

    private static boolean isValidpoint(int[][] mat, int n, int stR, int stC, int endR, int endC, boolean[][] visited) {
        return true;
    }
}
