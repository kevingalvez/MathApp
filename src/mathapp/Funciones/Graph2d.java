/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapp.Funciones;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;


import java.math.*;
import mathapp.InfixToPostfix;
/**
 *
 * @author KevinAlfonso
 */
public class Graph2d extends Canvas {
    
    private int scalaX,scalaY, longitudX, longitudY;
    private double limiX, limsX, limiY, limsY;
    private boolean dibujar;
    private String expr;
    
    public Graph2d(int x, int y){
        this.scalaX = x;
        this.scalaY = y;
        setBackground(Color.white);
        setSize(new Dimension(x,y));
    }
    
    public void setLimitesX(double xi, double xs)
    {
        this.limiX = xi;
        this.limsX = xs;
        longitudX = (int)(xs-xi);
    }
    
    public void setLimitesY(double yi, double ys)
    {
        this.limiY = yi;
        this.limsY = ys;
        longitudY = (int)(ys-yi);
    }
    
    public int ConvertToPixelX(double puntoX)
    {
        return (int)((scalaX/longitudX)*Math.abs(puntoX-limiX)+0);
    }
    
    public int ConvertToPixelY(double puntoY)
    {
     //   return (int)((scalaY/longitudY)*Math.abs(puntoY-limiY)+0);
        //return (int)(ypf + 1 - dyup * |yu - yui|
        return (int)(scalaY + 1 - (scalaY/longitudY)*Math.abs(puntoY-limiY));
    }

    public void dibujar(boolean bol)
    {
        this.dibujar = bol;
        repaint();
    }
    
    private void dibujar_plano(Graphics g)
    {
        g.setColor(Color.black);
        int lineaY = ConvertToPixelY(0);
        int lineaX = ConvertToPixelX(0);
        g.drawLine(0, lineaY, scalaX, lineaY);
        g.drawLine(lineaX, 0, lineaX, scalaY);
        
        graficar(g);
    }
    
    public void setExpr(String expr)
    {
        this.expr = expr;
    }
    
    private void graficar(Graphics g){
        if (this.expr != null)
        {
            InfixToPostfix a = new InfixToPostfix(this.expr);
            double step = 0.1, puntoX = this.limiX;

            while (puntoX < this.limsX)
            {
                double puntoY = a.evaluar(puntoX);
                g.setColor(Color.red);
                int lineaY = ConvertToPixelY(puntoY);
                int lineaX = ConvertToPixelX(puntoX);
                g.drawOval(lineaX, lineaY, 5, 5);
                puntoX +=step;
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        if (this.dibujar)
            dibujar_plano(g);
    }
    
    
}
