package com.mateusz.paint.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.mateusz.paint.model.shapes.Shape;

public class DrawPanel extends JPanel
{
	private List<Shape> shapes = new ArrayList<>();
	// private List<BufferedImage> undoImage = new LinkedList<>();
	private Shape temmplateShape;
	private BufferedImage imageDrawOnPanel;

	public DrawPanel()
	{
		setOpaque(true);
		int width = 400;
		int height = 400;
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.WHITE);
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

	public void currentDrawingsToImage(Graphics2D g2d, BufferedImage bufferedImage)
	{
		paint(g2d);
		setImageToDraw(bufferedImage);
	}

	public void clear()
	{
		imageDrawOnPanel = null;
		shapes.clear();
	}
	// public void rotateLeftRight(int degrees)
	// {
	// BufferedImage oldImage = undoImage.get(undoImage.size() - 1);
	// BufferedImage newImage = new BufferedImage(oldImage.getHeight(),
	// oldImage.getWidth(), oldImage.getType());
	// Graphics2D graphics = (Graphics2D) newImage.getGraphics();
	// graphics.rotate(Math.toRadians(degrees), newImage.getWidth() / 2,
	// newImage.getHeight() / 2);
	// graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2,
	// (newImage.getHeight() - oldImage.getHeight()) / 2);
	// graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(),
	// oldImage.getHeight(), null);
	// setImageToDraw(newImage);
	// clearDrawingsShapes();
	// undoOperation();
	// repaint();
	//
	// public void undoOperation()
	// {
	// int w = getWidth();
	// int h = getHeight();
	// BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	// Graphics2D g2d = bi.createGraphics();
	// paint(g2d);
	// undoImage.add(bi);
	// }
	//

}
