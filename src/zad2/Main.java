/**
 *
 *  @author Opieka Adrian S11656
 *
 */

package zad2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {
	  JFrame frame = new JFrame("BUTT1");
	  JButton button = new JButton("Button");
	  button.setPreferredSize(new Dimension(500, 200));
	  button.setFont(new Font("Dialog", Font.BOLD, 36));
	  Color[] colors = {Color.CYAN, Color.GRAY, Color.ORANGE, Color.DARK_GRAY};
	  
	  button.addActionListener(new ActionListener(){
		int i = 0;
		 
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(i == colors.length) i = 0;
			button.setBackground(colors[i]);
			i++;
		}
	  });
	  
	  frame.add(button);
	  frame.pack();
	  frame.setVisible(true);
	  frame.setLocationRelativeTo(null);
	  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
}
