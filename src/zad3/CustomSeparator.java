package zad3;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSeparator;

public class CustomSeparator extends JSeparator
{

	private static final long serialVersionUID = 1L;

	public CustomSeparator()
	{
		super();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.RED);
		g.drawRect(getParent().getX() + 10, 1, getParent().getWidth() - 20, 20);
	}
}
