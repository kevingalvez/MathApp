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
    private Stack parentesis;
    private int n = 100;
    private boolean signo;
    private double[] coeficientes;
    
    public InfixToPostfix(String expr)
    {
        this.expr = expr;
        simbolos = new Stack(n);
        numeros = new Stack(n);
        parentesis = new Stack(n);
        coeficientes = new double[n];
    }
    
    private void init(){
        simbolos.init();
        numeros.init();
        parentesis.init();
        signo = false; 
        for (int i = 0; i < n; i++)
            coeficientes[i] = 0.0;
    }
    
    private int precedencia(char c)
    {
        int result = 0;
        switch (c) {
            case '(': result = 0; break;
            case '+': case '-': result = 1; break;
            case '*': case '/': case '~': result = 2; break;             
            case '^': result = 3; break;
            case 's': case 'c': case 't': case 'l': case 'e': result = 4; break;
        }
        return result;
    }

    private String verifica_signo(char c)
    {
        String postfix = "";
        if (!signo)
            if (c =='-')
              c ='~';
            else {
                //error = true;
            }

        while (!simbolos.isEmpty()&&(precedencia(c)<=precedencia((char)simbolos.Top()))) 
            postfix += simbolos.Pop();
       simbolos.Push(c);
       signo = false;
       return postfix;
    }
    
    private boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }        
    
    private boolean parseo(String expr)
    {
        for (int i = 0; i < expr.length(); i++) {
            switch (expr.charAt(i)) {
                case '(': parentesis.Push("("); break;
                case ')':
                    if (!parentesis.isEmpty() && parentesis.Top().equals("("))
                        parentesis.Pop();
                    else
                        parentesis.Push(")");
                    break;
            }
        }
        if (!parentesis.isEmpty())
            return true;
        else
            return false;
    }
    
    private String parseoFunciones(String expr)
    {
        String result = expr;
        result = result.replaceAll("sen", "s");
        result = result.replaceAll("cos", "c");
        result = result.replaceAll("tan", "t");
        result = result.replaceAll("ln", "l");
        return result;
    }
    
    public String ConvertToPostfix() throws Exception
    {
        String postfix = "";
        init();
        int i = 0;
        if (!parseo(expr)){
            expr = parseoFunciones(expr);
            while (i < expr.length())
            {   
                switch (expr.charAt(i)) {
                    case '(': 
                                simbolos.Push(expr.charAt(i));
                                i++;
                            break;
                    case ')': 
                            while ((!simbolos.isEmpty()) && (!simbolos.Top().equals('(')))
                                postfix += simbolos.Pop();
                            simbolos.Pop();
                            i++;
                        break;
                    case '0': case '1': case '2': case '3': case '4': case '5':
                    case '6': case '7': case '8': case '9': case '.':
                        int j = i;
                        while (j < expr.length() && isNumeric(expr.substring(j, j+1)))  j++;
                        if (j < expr.length() && expr.charAt(j) == '.') {
                            j++;
                           while (j < expr.length() && isNumeric(expr.substring(j, j+1)))  j++;
                        }
                        postfix += '#';
                        coeficientes[postfix.length()-1] = Double.parseDouble(expr.substring(i, j));              
                        signo = true;
                        i = j;
                        break;
                    case '+': case '-': case '*': case '/': case '^':
                            postfix += verifica_signo(expr.charAt(i));
                            i++;
                        break;
                    case 'x': case 'y':
                        if (signo) postfix +=verifica_signo('*');

                        postfix += expr.charAt(i);
                        i++;
                        signo = true;
                    break;
                    case 's': case 'c': case 't': case 'l': case 'e':
                        if (signo) postfix +=verifica_signo('*');
                        signo = true;
                        postfix +=verifica_signo(expr.charAt(i));
                        i++;
                    break;                        
                }
            }
            while (!simbolos.isEmpty())
            {
                postfix += (char)simbolos.Pop();
            }
        }
        else
        {
            throw new Exception("Parentesis mal balanceados");
        }
        return postfix;
    }
    
    public double evaluar(double value) throws Exception
    {
        try
        {
            String postfix  = ConvertToPostfix();
            double a = 0.0, b = 0.0;

            for (int i = 0; i < postfix.length(); i++)
            {
                switch (postfix.charAt(i)) {
                    case '#': 
                        numeros.Push((double)coeficientes[i]);
                        break;

                    case 'x': case 'y':
                        numeros.Push(value);
                        break;
                    case '+':
                        a = (double)numeros.Pop();
                        b = (double)numeros.Pop();
                        numeros.Push(b + a);
                        break;
                    case '-': 
                        a = (double)numeros.Pop();
                        b = (double)numeros.Pop();
                        numeros.Push(b - a);                    
                        break;                    
                    case '*': 
                        a = (double)numeros.Pop();
                        b = (double)numeros.Pop();
                        numeros.Push(b * a);                    
                        break;                                        
                    case '/':
                        a = (double)numeros.Pop();
                        b = (double)numeros.Pop();
                        numeros.Push(b / a);                    
                        break;
                    case '~':
                        a = (double)numeros.Pop();
                        numeros.Push(-a);                    
                        break;
                    case '^':
                        a = (double)numeros.Pop();
                        b = (double)numeros.Pop();
                        numeros.Push(Math.pow(b, a));                    
                        break;                    
                    case 's':
                        a = (double)numeros.Pop();
                        numeros.Push(Math.sin(a));
                        break;                         
                    case 'c':
                        a = (double)numeros.Pop();
                        numeros.Push(Math.cos(a));
                        break;                         
                    case 't':
                        a = (double)numeros.Pop();
                        numeros.Push(Math.tan(a));
                        break;                         
                    case 'l':
                        a = (double)numeros.Pop();
                        numeros.Push(Math.log(a));
                        break;                                                 
                    case 'e':
                        a = (double)numeros.Pop();
                        numeros.Push(Math.exp(a));
                        break;
                }

            }
            return (double)numeros.Pop();
        
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }
}
