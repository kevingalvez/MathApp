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
    private int n = 100;
    private boolean signo;
    private double[] coeficientes;
    
    public InfixToPostfix(String expr)
    {
        this.expr = expr;
        simbolos = new Stack(n);
        numeros = new Stack(n);         
        coeficientes = new double[n];
    }
    
    private void init(){
        simbolos.init();
        numeros.init();
        signo = false; 
        for (int i = 0; i < n; i++)
            coeficientes[i] = 0.0;
    }
    
    private int precedencia(char c)
    {
        int result = 0;
        switch (c) {
            case '(': result = 0; break;
            case '+': result = 1; break;
            case '-': result = 1; break;
            case '*': result = 2; break;
            case '/': result = 2; break;
            case '^': result = 3; break;
            case '~': result = 2; break;
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
    
    public String ConvertToPostfix() throws Exception
    {
        String postfix = "";
        init();
        int i = 0;
        while (i < expr.length())
        {                   
            switch (expr.charAt(i)) {
                case '(': 
                            simbolos.Push(expr.charAt(i));
                            i++;
                        break;
                case ')': 
                        while ((!simbolos.isEmpty()) && ((char)simbolos.Top() != '('))
                        {
                            postfix += simbolos.Pop();
                            simbolos.Pop();
                            i++;
                        }
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
                    if (signo) 
                    {
                        postfix +=verifica_signo('*');
                    }
                    
                    postfix += expr.charAt(i);
                    i++;
                    signo = true;
                break;
            }
        }
        while (!simbolos.isEmpty())
        {
            postfix += (char)simbolos.Pop();
        }
        return postfix;
    }
    
    public double evaluar(double value) throws Exception
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
            }
            
        }
        return (double)numeros.Pop();
    }
}
