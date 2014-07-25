package com.mateusz.paint.model;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape
{

	public Rectangle(int x, int y, int x2, int y2, Color c)
	{
		super(x, y, x2, y2, c);

	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(getColor());
		int w = calcWidth();
		int h = calcHeight();
		if (w <= 0 && h <= 0)
		{
			w = Math.abs(w);
			h = Math.abs(h);
			g.drawRect(getX2(), getY2(), w, h);
		}
		else if (w < 0 && h >= 0)
		{
			w = Math.abs(w);
			h = Math.abs(h);
			g.drawRect(getX2(), getY(), w, h);
		}
		else if (w >= 0 && h < 0)
		{
			w = Math.abs(w);
			h = Math.abs(h);
			g.drawRect(getX(), getY2(), w, h);
		}
		else
		{
			g.drawRect(getX(), getY(), w, h);
		}
	}
}
