/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapp;

/**
 *
 * @author kgalvez
 */
public class InfixToPostfix {
    private String expr;
    private Stack simbolos;
    private Stack numeros;
    
    public InfixToPostfix(String expr)
    {
        this.expr = expr;
        simbolos = new Stack(100);
        numeros = new Stack(100);
    }
    
    public int precedencia(char c)
    {
        int result = 0;
        switch (c) {
            case '+': result = 1; break;
            case '-': result = 1; break;
            case '*': result = 2; break;
            case '/': result = 2; break;
        }
        return result;
    }
    
    public String ConvertToPostfix()
    {
        String temp = "";
        boolean num = false;
        String postfix = "";
        for (int i = 0;i < expr.length();i++)
        {                   
            switch (expr.charAt(i)) {
                case '0': case '1': case '2': case '3': case '4': case '5':
                case '6': case '7': case '8': case '9': case '.':
                    temp += expr.charAt(i); num = true; 
                    break;
                case '+': case '-': case '*': case '/':
                        if (num) {
                            num = false;
                            numeros.Push(temp);
                            postfix += "#";
                            temp = "";
                        }
                        if (simbolos.isEmpty())
                        {
                            simbolos.Push(expr.charAt(i));
                        } 
                        else 
                        {
                            while (precedencia(expr.charAt(i)) <= precedencia((char)simbolos.Top()))
                            {
                                postfix += simbolos.Pop();
                            }
                            simbolos.Push(expr.charAt(i));
                        }

                    break;
                default:postfix += expr.charAt(i); break;
            }
        }
        if (num) {
            num = false;
            numeros.Push(temp);
            postfix += "#";
            temp = "";
        }        
        while (!simbolos.isEmpty())
        {
            postfix += (char)simbolos.Pop();
        }
        return postfix;
    }
}
