package zad3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class CustomIcon implements Icon
{
	private Color color;
	
	public CustomIcon(Color color)
	{
		super();
		try
		{
			this.color = color;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public CustomIcon getCustomIcon()
	{
		return this;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	@Override
	public int getIconHeight()
	{
		return 10;
	}

	@Override
	public int getIconWidth()
	{
		return 10;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y)
	{
		g.setColor(color);
		g.fillOval(x, y, this.getIconWidth(), this.getIconHeight());
	}
	
}
