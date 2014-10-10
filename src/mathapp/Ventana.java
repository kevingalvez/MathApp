/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapp;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mathapp.Funciones.Graph2d;

/**
 *
 * @author KevinAlfonso
 */
public class Ventana extends JFrame{
       
    public Ventana()
    {
        setSize(1024,768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Configuracion Panel
        panel.setLayout(null);
        panel.setBounds(20,20,250,140);
        
        // Configuracion del boton del graficador
        btn_graficador.setText("Graficar");
        btn_graficador.setSize(100,30);
        btn_graficador.setLocation(100,210);
        
        // Configuracion del JTextfield
        txt_funcion.setSize(300,30);
        txt_funcion.setLocation(100,170);
        
        
        //grafica
        //grafica.setLayout(null);
        //grafica.repaint();
        
        panel.add(btn_graficador);
        panel.add(txt_funcion);
        //panel.add(grafica);
        //panel.add(new Graph2d(10,10,30,50));
       
        
        add(panel);
        //add(new Graph2d(10,10,30,50));
        setVisible(true);
    }
    
    private JPanel panel = new JPanel(); 
    private JButton btn_graficador = new JButton();
    private JTextField txt_funcion = new JTextField();
    //private Graph2d grafica = new Graph2d(10,10,30,50);
}
