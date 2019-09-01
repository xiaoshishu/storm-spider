package com.example.algorithm;

/**
 *
 * 求最大公约数
 */
public class Greatest {
    /**
     * 欧几里得算法
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisort2(int a, int b){
        int big = a > b ? a : b;
        int small = a < b ? a : b;

        if (big % small == 0) {
            return  small;
        }

        return getGreatestCommonDivisort2(big%small, small);
    }


    public static int getGreatestCommonDivisort3(int a, int b){

        if (a == b){
            return a;
        }
        System.out.println("a " + a  + " b " +

                b);
        int big = a > b ? a : b;
        int small = a < b ? a : b;


        return getGreatestCommonDivisort3(big-small, small);
    }

    public static int gcb(int a, int b){
        if (a == b) {
            return a;
        }
        // & 运算转换为2进制数，如果对应位置都是1 则为1 否则为0；
        // 如果 a&1 == 0 则说明式偶数， a&1 !=0 说明是奇数

        if ((a&1) == 0 && (b&1) == 0){
            return gcb(a >>1, b>>1)<<1;
        } else if ((a&1) == 0 && (b&1) != 0) {
            return gcb(a >>1, b);
        } else if ((a&1) !=0 && (b&1) == 0) {
            return gcb(a, b >>1);
        } else {
            int big = a > b ? a: b;
            int small = a < b ? a: b;
            return gcb(big-small, small);
        }


    }



    public static void main(String[] args) {

        System.out.println(getGreatestCommonDivisort2(25,5));
        System.out.println(getGreatestCommonDivisort2(100,80));
        System.out.println(getGreatestCommonDivisort2(27,14));

        System.out.println(getGreatestCommonDivisort3(25,5));
        System.out.println(getGreatestCommonDivisort3(100,80));
        System.out.println(getGreatestCommonDivisort3(27,14));

        System.out.println(gcb(25,5));
        System.out.println(gcb(100,80));
        System.out.println(gcb(27,14));


    }


}
