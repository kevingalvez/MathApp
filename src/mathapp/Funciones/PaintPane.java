/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapp.Funciones;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author KevinAlfonso
 */
public class PaintPane extends JPanel{
    
    public PaintPane () {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(10, 10);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g2d.dispose();
    }    
}
