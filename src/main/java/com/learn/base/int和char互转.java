package com.learn.base;

/**
 * @author yeyongjun
 * @since 2025/2/8 18:13
 */
public class int和char互转 {
    public static void main(String[] args) {
        //int类型转char类型
        int number = 9;
        char cNumber= (char) (number+'0');
        System.out.println("Number "+number+" to char is:"+cNumber);

        //char类型转int类型
        char cNumber2='3';
        int number2=cNumber2-'0';
        System.out.println("Char "+cNumber2+" to number is:"+number2);
    }
}
