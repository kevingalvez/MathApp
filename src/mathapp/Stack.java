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
public class Stack {
    
    private int size;
    private Object[] data;
    private int index;
    
    public Stack(int size)
    {
        this.index = 0;
        this.size = size;
        this.data = new Object[size];
    }
    
    public void Push(Object data)
    {
        if (this.index < size)
        {
            this.data[this.index++] = data;
        } 
        else
        {
            // Error
        }
    }
    
    public Object Pop()
    {
        if (!isEmpty()) 
        {
            Object d = Top();
            this.index--;
            return d;
        } 
        else
        {
            return null;
        }       
    }
    
    public Object Top()
    {
        if (!isEmpty()) 
            return this.data[this.index-1];
        else
        {
            Object d = ' ';
            return d;
        }
    }
    
    public boolean isEmpty()
    {
        if (this.index > 0)
            return false;
        else
            return true;
    }
}
