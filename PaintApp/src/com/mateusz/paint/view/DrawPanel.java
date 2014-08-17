package com.mateusz.paint.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import com.mateusz.paint.model.shapes.Shape;

public class DrawPanel extends JPanel
{
	private List<Shape> shapes = new ArrayList<>();
	private Shape temmplateShape;
	private BufferedImage imageDrawOnPanel;

	public DrawPanel()
	{
		setOpaque(true);
		setPreferredSizeSizeOfPanel();
		setBackground(Color.WHITE);
	}

	public void setPreferredSizeSizeOfPanel()
	{
		int width = 400;
		int height = 400;
		setPreferredSize(new Dimension(width, height));
	}

	public void addDrawPanelMouseListener(MouseListener listenForMouse)
	{
		this.addMouseListener(listenForMouse);
	}

	public void addDrawPanelMouseMotionListener(MouseMotionListener listenForMouse)
	{
		this.addMouseMotionListener(listenForMouse);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// The image is drawn with its top-left corner at (x, y)
		int xCordinate = 0;
		int yCordinate = 0;
		g.drawImage(imageDrawOnPanel, xCordinate, yCordinate, null);

		for (Shape s : shapes)
		{
			if (s != null)
				s.render(g);
		}

		if (temmplateShape != null)
		{
			temmplateShape.render(g);
		}
	}

	public List<Shape> getShapes()
	{
		return shapes;
	}

	public void setShapes(List<Shape> shapes)
	{
		this.shapes = shapes;
	}

	public Shape getTmpShape()
	{
		return temmplateShape;
	}

	public void setTmpShape(Shape tmpShape)
	{
		this.temmplateShape = tmpShape;
	}

	public void setImageToDraw(BufferedImage img)
	{
		imageDrawOnPanel = img;
	}

	public BufferedImage getImageDrawOnPanel()
	{
		return imageDrawOnPanel;
	}
}
