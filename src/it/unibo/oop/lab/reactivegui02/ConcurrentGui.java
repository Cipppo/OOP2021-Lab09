package it.unibo.oop.lab.reactivegui02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;


public class ConcurrentGui extends JFrame {
    

    private static final long serialVersionUID = 1L;
    
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    
    private final JLabel display = new JLabel();
    final JButton up = new JButton("up");
    final JButton down = new JButton("down");
    final JButton stop = new JButton("stop");
    
    
    
    public ConcurrentGui() {
        
        super();
        final Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screensize.getWidth() * WIDTH_PERC ), (int) (screensize.getHeight() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final JPanel canvas = new JPanel();
        
        canvas.add(display);
        canvas.add(up);
        canvas.add(down);
        canvas.add(stop);
        
        
        this.getContentPane().add(canvas);
        
        this.setVisible(true);
        
        
        final Agent agent = new Agent();
        new Thread(agent).start();
        
        
        stop.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                agent.stopCounting();
                up.setEnabled(false);
                down.setEnabled(false);
            }
        });
        
        up.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                agent.setForward();
            }
        });
       
        down.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                agent.setBackward();
            }
        });
    }
    
    private class Agent implements Runnable{
        
        private volatile boolean stop;
        private volatile int counter;
        private boolean forward = true;
        
        @Override
        public void run() {
            while(!this.stop) {
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        
                        @Override
                        public void run() {
                            ConcurrentGui.this.display.setText(Integer.toString(Agent.this.counter));
                            
                        }
                    });
                    
                    if(this.forward) {
                        this.counter++;                        
                    }else {
                        this.counter--;
                    }
                    
                    Thread.sleep(100);
                }catch(InvocationTargetException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        
    
    
        public void stopCounting() {
            this.stop = true;
        }
        
        public void setForward() {
            this.forward = true;
        }
        
        public void setBackward() {
            this.forward = false;
        }
    }
    

    
}
