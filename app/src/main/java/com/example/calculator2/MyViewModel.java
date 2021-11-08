package com.example.calculator2;

import androidx.lifecycle.ViewModel;

import java.util.Stack;
import java.util.Vector;

public class MyViewModel extends ViewModel {
    private  String input="";
    boolean isRadical=false;//根号判断；
    boolean hasOP=false; //是否有符号判断
    public  String getInput(){
        if(input.isEmpty()){
            return "";
        }
        return  input;
    }
    public void setInput(String s){
        input=s;
    }
    public void Reverse() {  //取反函数
        if(isOperator(input.charAt(input.length()-1))&&input.charAt(input.length()-1)!=')')//当输入最后一个字符是操作符时不操作
            return;
        String Num="";
        char c;
        if(input.charAt(input.length()-1)==')') { //为负数时
            for(int i=input.length()-1;i>=0;i--) {
                c=input.charAt(i);
                if(!isOperator(c)||c=='.')//将数字、小数点保留,其他字符一律不保存
                    Num+=c;
                else {
                    if(c=='('){//当遍历到左括号时结束
                        input = input.substring(0, i);
                        break;
                    }
                }
            }
            Num=reverse(Num);//将数据逆转回来，一开始是从后往前保存的；

        }
        else {//正数
                for(int i=input.length()-1;i>=0;i--) {
                c=input.charAt(i);
                if(isOperator(c)&&c!='.') //如果是除小数点以外的操作符,则停止遍历
                    break;
                Num+=c;//否则将该数字加到Num中
                input = input.substring(0, i);
            }
            Num="(-"+reverse(Num)+")";

        }
        input+=Num;
    }

    public String reverse(String s)//逆转函数
    {
        String rs="";
        for(int i=s.length()-1;i>=0;i--)
            rs+=s.charAt(i);
        return rs;
    }
    public void operatorChange() {//不能同时输入两个符号；后输入的替换前一个；
        if(input.length()==0)
            return;
        char last=input.charAt(input.length()-1);
        if(isOperator(last)&&last!=')')
            input=input.substring(0,input.length()-1);
    }

    public void numberChange() {
        if(input.length()==0)
            return;
        //当前一个操作数为负数时，将前一个负数替换为要输入的数字
        if(input.charAt(input.length()-1)==')') {
            int i;
            for( i=input.length()-1;i>=0;i--)
            {
                if(input.charAt(i)=='(')
                    break;
            }
            input=input.substring(0,i);
            return;
        }
        //当前一个字符为小数点时，验证当前数字小数点是否规范
        if(input.charAt(input.length()-1)=='.') {

            int dotCount=0;
            for(int i=input.length()-1;i>=0;i--)
            {
                char c=input.charAt(i);
                if(isOperator(c)&&c!='.')
                    break;
                if(c=='.')
                    dotCount++;
                if(dotCount>1)
                    break;
            }
            if(dotCount>1)
                input=input.substring(0,input.length()-1);//如果当前数字含有超过一个的小数点，则删除前一个小数点字符
        }
        //规范第一个字符为操作符的情况
        if(input.length()==1) {//如果先输入符号，则直接清空；
            if (isOperator(input.charAt(0)))
                input = "";
        }
    }
    public boolean isOperator(char c) //判断是否是非0-9数字
    {
        return c < '0' || c > '9';
    }
    public String calculate() {
        this.hasOP();//是否有符号判断

        if(this.isRadical){//是否是开根号
            if(hasOP){
                input="";
                return  "根号内错误";
            }else{
                    input=String.valueOf(Math.sqrt(Double.valueOf(input)));
                 return   input;

            }
        }
        if(input.isEmpty())
            return "0";
        Stack<Character> OP= new Stack<>();//运算符栈
        Stack<String> NUM=new Stack<>();//数字栈

        //中缀转后缀
        char c;
        Vector<String> postFix=new Vector<>();//存后缀表达式
        StringBuilder tmpNum= new StringBuilder(new String());//存储数字
        for(int i=0;i<input.length();i++) {
            c = input.charAt(i);
            if(c=='('){   //将负数加入tmpNum
                i++;
                while(true) {
                    if(i==input.length())
                        break;
                    c=input.charAt(i);
                    if(c==')')
                        break;
                    tmpNum.append(c);
                    i++;
                }
                postFix.add(tmpNum.toString());
                tmpNum = new StringBuilder();
                continue;
            }
            if(i==input.length()-1&&c!='%'&&hasOP)//将表达式最后一个数字加入tmpNum，以及后缀表达式
            {
                tmpNum.append(c);
                postFix.add(tmpNum.toString());
                tmpNum = new StringBuilder();
                hasOP=false;//重置
                break;
            }


            if(!isOperator(c)){
                tmpNum.append(c);
            }
            if (!hasOP) {//没符号是入栈的方法；
                postFix.add(tmpNum.toString());
                tmpNum = new StringBuilder();
            }

            if(isOperator(c)) {//每次遇到操作符说明前一个数已经存完；
                if(c=='.') {
                    tmpNum.append(c);
                    continue;
                }
                if(tmpNum.length() > 0) {
                    postFix.add(tmpNum.toString());
                    tmpNum = new StringBuilder();
                }
                if(OP.isEmpty())//优先级低于c
                    OP.push(c);
                else if((Priority(OP.peek())<Priority(c))){
                    OP.push(c);
                }
                else {//优先级不低于c
                    postFix.add(String.valueOf(OP.pop()));
                    OP.push(c);
                }

            }


        }
        while(!OP.isEmpty())//将OP内的操作符加到后缀表达式后
        {
            char peek=OP.peek();
            OP.pop();

            postFix.add(String.valueOf(peek));
        }
        for(int i=0;i<postFix.size();i++)
        {
            String s=postFix.elementAt(i);
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/"))
            {
                double num2=Double.parseDouble(NUM.pop());
                double num1=Double.parseDouble(NUM.pop());
                if(s.equals("+"))
                    NUM.push(String.valueOf(num1+num2));
                if(s.equals("-"))
                    NUM.push(String.valueOf(num1-num2));
                if(s.equals("*"))
                    NUM.push(String.valueOf(num1*num2));
                if(s.equals("/")) {
                    if(num2==0)
                        return "error";
                    NUM.push(String.valueOf(num1 / num2));
                }
            }else if(s.equals("%")){
                double num1=Double.parseDouble(NUM.pop());
                NUM.push(String.valueOf(num1*0.01));
            }
            else
                NUM.push(s);
        }
        if(Double.parseDouble(NUM.peek())>=0){
            return NUM.pop();}
        else {
            return "(" + NUM.pop() + ")";
        }
    }
    private int Priority(char c)
    {
        if(c=='+'||c=='-')
            return 0;
        else return 1;
    }
    private void hasOP() {
        if ((!input.contains("+")) && (!input.contains("-")) && (!input.contains("%")) && (!input.contains("*")) && (!input.contains("/"))) {
        } else {
            this.hasOP=true;
        }

}


}
