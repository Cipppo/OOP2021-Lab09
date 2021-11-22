package it.unibo.oop.lab.reactivegui02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class ConcurrentGui extends JFrame {
    

    private static final long serialVersionUID = 1L;
    
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    
    private final JLabel display = new JLabel("ciao");
    final JButton up = new JButton("up");
    final JButton down = new JButton("down");
    final JButton stop = new JButton("stop");
    
    
    
    public ConcurrentGui() {
        
        super();
        final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screensize.getWidth() * WIDTH_PERC), (int) (screensize.getHeight() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final JPanel canvas = new JPanel();
        
        canvas.add(display);
        canvas.add(up);
        canvas.add(down);
        canvas.add(stop);
        
        
        this.getContentPane().add(canvas);
        this.pack();
        this.setVisible(true);
        
        
        
        
        
        
        
        
        
        
    }
    
}
