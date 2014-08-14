package com.mateusz.paint.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import com.mateusz.paint.enums.ShapeEnum;
import com.mateusz.paint.model.DrawingsEdit;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.StaticStuff;
import com.mateusz.paint.model.shapes.Circle;
import com.mateusz.paint.model.shapes.Line;
import com.mateusz.paint.model.shapes.Pencil;
import com.mateusz.paint.model.shapes.Rectangle;
import com.mateusz.paint.model.shapes.Rubber;
import com.mateusz.paint.model.shapes.Shape;
import com.mateusz.paint.view.DrawPanel;
import com.mateusz.paint.view.MainFrame;

public class DrawPanelListeners
{
	private MainFrame view;
	private Model model;
	private DrawPanel drawPanel;
	private Shape drawShape;

	public DrawPanelListeners(MainFrame view, Model model)
	{
		this.view = view;
		this.model = model;
		drawPanel = view.getDrawPanel();

		drawPanel.addDrawPanelMouseListener(new MouseListenerForDrawPanel());
		drawPanel.addDrawPanelMouseMotionListener(new MouseMotionListenerForDrawPanel());
	}

	class MouseMotionListenerForDrawPanel implements MouseMotionListener
	{

		@Override
		public void mouseDragged(MouseEvent event)
		{
			if (!(drawShape == null))
			{
				if (drawShape instanceof Pencil)
				{
					((Pencil) drawShape).addPoint(new Point(event.getX(), event.getY()));
				}
				else if (drawShape instanceof Rubber)
				{
					((Rubber) drawShape).addPoint(new Point(event.getX(), event.getY()));
				}
				else
				{
					drawShape.setX2(event.getX());
					drawShape.setY2(event.getY());
				}
				view.getDrawPanel().setTmpShape(drawShape);
				view.getDrawPanel().repaint();
			}
		}

		// required to implement from MouseListener but unneeded
		@Override
		public void mouseMoved(MouseEvent event)
		{
		}

	}

	class MouseListenerForDrawPanel implements MouseListener
	{
		public void mouseClicked(MouseEvent event)
		{
			if (StaticStuff.getShapeType() == ShapeEnum.FILLCLOSEDSHAPE)
			{
				DrawPanel panelView = view.getDrawPanel();
				DrawingsEdit drawingsEdit = model.getDrawingsEdit();

				int width = panelView.getWidth();
				int height = panelView.getHeight();

				drawingsEdit.setBufferedImageAndGraphicsFromCurrentDrawings(width, height);
				BufferedImage bufferedImage = drawingsEdit.getBufferedImage();
				Graphics2D g2d = drawingsEdit.getGraphics2D();
				panelView.currentDrawingsToImage(g2d, bufferedImage);

				Color c = new Color(panelView.getImageDrawOnPanel().getRGB(event.getX(), event.getY()));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				Color backgroundColor = new Color(red, green, blue);

				drawingsEdit.floodFill(panelView.getImageDrawOnPanel(), event.getPoint(), backgroundColor,
						StaticStuff.getShapeColor());
				panelView.repaint();
			}
		}

		// required to implement from MouseListener but unneeded
		public void mouseEntered(MouseEvent event)
		{
		}

		// required to implement from MouseListener but unneeded
		public void mouseExited(MouseEvent event)
		{
		}

		public void mousePressed(MouseEvent event)
		{
			drawShape = getTmpShape(event.getX(), event.getY(), 2, 2);
			view.getDrawPanel().setTmpShape(drawShape);
		}

		public void mouseReleased(MouseEvent event)
		{
			if (!(drawShape == null))
			{
				drawShape.setX2(event.getX());
				drawShape.setY2(event.getY());

				List<Shape> shapes = view.getDrawPanel().getShapes();
				shapes.add(drawShape);
				view.getDrawPanel().setTmpShape(null);
				view.getDrawPanel().setShapes(shapes);
				drawShape = null;
				view.getDrawPanel().repaint();

				// view.getDrawPanel().undoOperation();
			}
		}
	}

	private Shape getTmpShape(int x, int y, int x2, int y2)
	{
		switch (StaticStuff.getShapeType())
		{
		case RECTANGLE:
			return new Rectangle(x, y, x2, y2, StaticStuff.getShapeColor());
		case CIRCLE:
			return new Circle(x, y, x2, y2, StaticStuff.getShapeColor());
		case PENCIL:
			return new Pencil(x, y, x2, y2, StaticStuff.getShapeColor());
		case LINE:
			return new Line(x, y, x2, y2, StaticStuff.getShapeColor());
		case RUBBER:
			return new Rubber(x, y, x2, y2, Color.WHITE);
		default:
			break;
		}
		return null;
	}

}
