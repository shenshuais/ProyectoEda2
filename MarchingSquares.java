/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marchingsquares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.concurrent.ExecutionException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 *
 * @author Patch
 */
public class MarchingSquares {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        double[][] data_mW = {{0,0,0,0,},{0,1,0,0,},{0,0,1,0,},{0,0,0,0}};
        double[] levels_mW = {0, 0, 0, 0};
        double[] isos = {0,2, 4, 6};
        Algorithm al = new Algorithm();
        al.isovalues=isos;
        GeneralPath[] isolines = al.buildContours(data_mW, levels_mW);
        AffineTransform xf = new AffineTransform();
        xf.translate(1, 1);
        xf.scale(35,35);
        xf.translate(-1,-1); // Because MxN data was padded to (M+2)x(N+2).
        for (int i = 0; i < isolines.length; i++) {
            isolines[i].transform(xf); // Permanent mapping to world coords.
        }
        //for(int i=0; i<gp.length;i++){
        //    gp[i]
        //}
       //PathGenerator.generate(Algorithm.contour());
         JFrame frame = new JFrame("Draw GeneralPath Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MyCanvas(isolines[0]));
        frame.pack();
        frame.setSize(new Dimension(500, 500));
        frame.setVisible(true);
        
    }
}

class MyCanvas extends JComponent {
  GeneralPath shape;

  public MyCanvas(GeneralPath iso) {
    shape = iso;
  }


  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(new Color(10));
    g2.setColor(Color.gray);
    g2.draw(shape);
  }
}