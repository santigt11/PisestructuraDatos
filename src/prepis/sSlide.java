/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prepis;

import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class sSlide {

    public void JPanelXDerecha(int start, int stop, int delay, int increment, final JPanel jPanel) {
        if (jPanel.getX() == start) {
            new Thread(() -> {
                try {
                    for (int i = start; i <= stop; i += increment) {
                        Thread.sleep(delay);
                        jPanel.setLocation(i, jPanel.getY());
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }).start();
        }
    }

    public void JPanelXIzquierda(int start, int stop, int delay, int increment, final JPanel jPanel) {

        if (jPanel.getX() == start) {
            new Thread(() -> {
                try {
                    for (int i = start; i >= stop; i -= increment) {
                        Thread.sleep(delay);
                        jPanel.setLocation(i, jPanel.getY());
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }).start();
        }
    }


/**
 * @param args the command line arguments
 */
public static void main(String[] args) {
        // TODO code application logic here
    }

}
