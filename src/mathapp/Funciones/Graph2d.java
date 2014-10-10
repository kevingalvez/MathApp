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
/**
 *
 * @author KevinAlfonso
 */
public class Graph2d extends Canvas {
    
    private int scalaX,scalaY, longitudX, longitudY;
    private double limiX, limsX, limiY, limsY;
    private boolean dibujar;
    
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
        return (int)((scalaY/longitudY)*Math.abs(puntoY-limiY)+0);
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
    }
     
    @Override
    public void paint(Graphics g) {
        if (this.dibujar)
            dibujar_plano(g);
    }
    
    
}
