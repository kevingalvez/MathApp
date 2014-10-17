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

import java.math.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int margenX,margenY;
    private double step;

    public Graph2d(int x, int y, int margenX, int margenY){
        this.scalaX = x-2*margenX;
        this.scalaY = y-2*margenY;
        this.margenX = margenX;
        this.margenY = margenY;
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
    
    public void setStep(int presicion)
    {
        step = (double)1/presicion;
    }    
    
    public int ConvertToPixelX(double puntoX)
    {
        return (int)(scalaX*(puntoX-limiX)/longitudX+margenX);
    }
    
    public int ConvertToPixelY(double puntoY)
    {
        return (scalaY - (int)(scalaY*(puntoY-limiY)/longitudY+margenY));
    }

    public void dibujar(boolean bol)
    {
        this.dibujar = bol;
        repaint();
    }
    
    private void dibujar_plano(Graphics g) throws Exception
    {
        g.setColor(Color.black);
        int lineaY = ConvertToPixelY(0);
        int lineaX = ConvertToPixelX(0);
        g.drawLine(margenX*2, lineaY, scalaX, lineaY);
        g.drawLine(lineaX, margenY*2, lineaX, scalaY);
        
        graficar(g);
    }
    
    public void setExpr(String expr)
    {
        this.expr = expr;
    }
    
    private void graficar(Graphics g) throws Exception{
        try 
        {
            if (this.expr != null)
            {
                InfixToPostfix a = new InfixToPostfix(this.expr);
                double puntoX = this.limiX;
                boolean iter = true;
                int actX = 0,actY = 0;
                while (puntoX < this.limsX)
                {
                    if (puntoX>0)
                        puntoX = puntoX+0;
                    double puntoY = a.evaluar(puntoX);
                    g.setColor(Color.red);
                    int lineaY = ConvertToPixelY(puntoY);
                    int lineaX = ConvertToPixelX(puntoX);
                    if (iter)
                        g.drawLine(lineaX, lineaY, lineaX, lineaY);
                    else
                        g.drawLine(actX, actY, lineaX, lineaY);
                    actX = lineaX;
                    actY = lineaY;

                    puntoX +=step;
                }
            }
        } catch (Exception e) 
        {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    @Override
    public void paint(Graphics g) {
        if (this.dibujar)
            try {
                dibujar_plano(g);
        } catch (Exception ex) {
            Logger.getLogger(Graph2d.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
