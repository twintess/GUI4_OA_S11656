/**
 *
 *  @author Opieka Adrian S11656
 *
 */

package zad1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

  public static void main(String[] args) {
	  
	  String borders[] = {"North", "West", "Center", "East", "South"};
	  String text[] = {"Lorem", "ipsum", "dolor", "sit", "amet"};
	  
	  JFrame f = new JFrame("GUICOMP");
	  f.setLayout(new BorderLayout());
	  
	  for(int i = 0; i < borders.length; i++)
	  {
		  Random r = new Random();
		  JLabel l = new JLabel(text[i]);
		  l.setBackground(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		  l.setForeground(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		  l.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, r.nextInt(150)));
		  l.setToolTipText("Tooltip " + i);
		  l.setOpaque(true);
		  l.setBorder(BorderFactory.createLineBorder(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), r.nextInt(10)));
		  f.add(l, borders[i]);
	  }

	  f.pack();
	  f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  f.setVisible(true);
	  f.setLocationRelativeTo(null);
  }
}
