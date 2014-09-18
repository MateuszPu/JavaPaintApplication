package com.mateusz.paint.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import com.mateusz.paint.enums.ShapeEnum;
import com.mateusz.paint.model.DrawingsEdit;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.StaticStuffColorAndShape;
import com.mateusz.paint.model.shapes.Circle;
import com.mateusz.paint.model.shapes.Line;
import com.mateusz.paint.model.shapes.Pencil;
import com.mateusz.paint.model.shapes.Rectangle;
import com.mateusz.paint.model.shapes.Rubber;
import com.mateusz.paint.model.shapes.Shape;
import com.mateusz.paint.view.DrawPanel;
import com.mateusz.paint.view.MainFrame;

public class DrawPanelListeners extends SuperControllerForPanel
{
	private DrawPanel drawPanel;
	private DrawingsEdit drawingsEdit;

	private Shape drawShape;

	public DrawPanelListeners(MainFrame view, Model model)
	{
		super(view, model);

		drawPanel = super.getDrawPanel();
		drawingsEdit = super.getDrawingsEdit();

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
				drawPanel.setTmpShape(drawShape);
				drawPanel.repaint();
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
			if (StaticStuffColorAndShape.getShapeType() == ShapeEnum.FILLCLOSEDSHAPE)
			{
				currentDrawingsToImage();
				clearCurrentDrawings();

				drawingsEdit.floodFill(event.getPoint());
				drawPanel.setImageToDraw(drawingsEdit.getBufferedImage());
				drawPanel.repaint();
				drawingsEdit.addImageToUndoList();
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
			drawPanel.setTmpShape(drawShape);
		}

		public void mouseReleased(MouseEvent event)
		{
			if (!(drawShape == null))
			{
				currentDrawingsToImage();
				drawShape.setX2(event.getX());
				drawShape.setY2(event.getY());

				List<Shape> shapes = drawPanel.getShapes();
				shapes.add(drawShape);
				drawPanel.setTmpShape(null);
				drawPanel.setShapes(shapes);
				drawShape = null;

				drawPanel.repaint();
				drawingsEdit.addImageToUndoList();
			}
		}
	}

	private Shape getTmpShape(int x, int y, int x2, int y2)
	{
		switch (StaticStuffColorAndShape.getShapeType())
		{
		case RECTANGLE:
			return new Rectangle(x, y, x2, y2, StaticStuffColorAndShape.getShapeColor());
		case CIRCLE:
			return new Circle(x, y, x2, y2, StaticStuffColorAndShape.getShapeColor());
		case PENCIL:
			return new Pencil(x, y, x2, y2, StaticStuffColorAndShape.getShapeColor());
		case LINE:
			return new Line(x, y, x2, y2, StaticStuffColorAndShape.getShapeColor());
		case RUBBER:
			return new Rubber(x, y, x2, y2, Color.WHITE);
		default:
			break;
		}
		return null;
	}
}
