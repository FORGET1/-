package com.pingdu;

import java.util.Scanner;

public class ReverseNum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int num1=sc.nextInt();
        int num2=sc.nextInt();
        
        int n1 = num1;
        int n2 = num2;
        
        int result1=0;
        int result2=0;
        while(true)
        {  
            int n=num1%10;  
            result1=result1*10+n;
            num1=num1/10;
            if(num1==0)
            { 
                break;  
            }
        }
        while(true)
        {  
            int n=num2%10;  
            result2=result2*10+n;
            num2=num2/10;
            if(num2==0)
            { 
                break;  
            }
        }
        if(n1 < 1 || n1 > 70000 || n2 < 1 || n2 > 70000){
            System.out.println(-1);
        }
        else{
        	System.out.println(result1+result2);
        }
	}

}
