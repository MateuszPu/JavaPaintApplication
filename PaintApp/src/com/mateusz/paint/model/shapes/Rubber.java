package com.mateusz.paint.model.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Rubber extends Shape
{
	private List<Point> points = new ArrayList<>();

	public Rubber(int x, int y, int x2, int y2, Color c)
	{
		super(x, y, x2, y2, c);
		points.add(new Point(x, y));
	}

	@Override
	public void render(Graphics g)
	{
		int width = 20;
		int height = 20;
		g.setColor(Color.WHITE);

		for (Point p : points)
		{
			g.fillRect((int) p.getX(), (int) p.getY(), width, height);
		}
	}

	public void addPoint(Point p)
	{
		points.add(p);
	}
}