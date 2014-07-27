package com.mateusz.paint.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.JColorChooser;
import org.apache.log4j.Logger;
import com.mateusz.paint.enums.ShapeEnum;
import com.mateusz.paint.model.Circle;
import com.mateusz.paint.model.Line;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.Pencil;
import com.mateusz.paint.model.Rectangle;
import com.mateusz.paint.model.Rubber;
import com.mateusz.paint.model.Shape;
import com.mateusz.paint.model.StaticStuff;
import com.mateusz.paint.view.MainFrame;
import com.mateusz.paint.view.ToolsMenu;

public class Controller
{

	final static Logger logger = Logger.getLogger(ToolsMenu.class);
	// private Color selectedColor = StaticStuff.getShapecolor();
	private MainFrame view;
	private Model model;

	private Shape drawShape;

	public Controller(MainFrame view, Model model)
	{
		this.view = view;
		this.model = model;

		this.view.getToolsMenu().addRectangleListener(new RectangleListener());
		this.view.getToolsMenu().addCircleListener(new CircleListener());
		this.view.getToolsMenu().addLineListener(new LineListener());
		this.view.getToolsMenu().addPencilListener(new PencilListener());
		this.view.getToolsMenu().addRubberListener(new RubberListener());
		this.view.getToolsMenu().addColorListener(new ColorListener());
		this.view.getToolsMenu().addFillClosedShapeListener(new FillClosedShapeListener());
		this.view.getDrawPanel().addDrawPanelMouseListener(new MouseListenerForDrawPanel());
		this.view.getDrawPanel().addDrawPanelMouseMotionListener(new MouseMotionListenerForDrawPanel());
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

		@Override
		public void mouseMoved(MouseEvent arg0)
		{
		}

	}

	class MouseListenerForDrawPanel implements MouseListener
	{
		public void mouseClicked(MouseEvent e)
		{
			System.out.println(StaticStuff.getShapeColor());
			System.out.println(StaticStuff.getShapeType());
			if (StaticStuff.getShapeType() == ShapeEnum.FILLCLOSEDSHAEP)
			{
				view.getDrawPanel().currentDrawingsToImage();
				Color c = new Color(view.getDrawPanel().imageToDrawOnPanel.getRGB(e.getX(), e.getY()));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				Color backgroundColor = new Color(red, green, blue);

				view.getDrawPanel().floodFill(view.getDrawPanel().imageToDrawOnPanel, e.getPoint(), backgroundColor,
						StaticStuff.getShapeColor());
				view.getDrawPanel().repaint();
				view.getDrawPanel().undoOperation();
			}
		}

		public void mouseEntered(MouseEvent e)
		{

		}

		public void mouseExited(MouseEvent e)
		{

		}

		public void mousePressed(MouseEvent e)
		{
			drawShape = getTmpShape(e.getX(), e.getY(), 2, 2);
			view.getDrawPanel().setTmpShape(drawShape);
		}

		public void mouseReleased(MouseEvent e)
		{
			if (!(drawShape == null))
			{
				drawShape.setX2(e.getX());
				drawShape.setY2(e.getY());

				List<Shape> shapes = view.getDrawPanel().getShapes();
				shapes.add(drawShape);
				view.getDrawPanel().setTmpShape(null);
				view.getDrawPanel().setShapes(shapes);
				drawShape = null;
				view.getDrawPanel().repaint();

				view.getDrawPanel().undoOperation();
			}
		}
	}

	class ColorListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			Color selectedColor = JColorChooser.showDialog(view, "Chose color:", StaticStuff.getShapeColor());
			StaticStuff.setShapeColor(selectedColor);
			view.getToolsMenu().setColor(selectedColor);
		}
	}

	class RectangleListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			StaticStuff.setShapeType(ShapeEnum.RECTANGLE);
		}
	}

	class CircleListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			StaticStuff.setShapeType(ShapeEnum.CIRCLE);
		}
	}

	class LineListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			StaticStuff.setShapeType(ShapeEnum.LINE);
		}
	}

	class PencilListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			StaticStuff.setShapeType(ShapeEnum.PENICL);
		}
	}

	class RubberListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			StaticStuff.setShapeType(ShapeEnum.RUBBER);
		}
	}

	class FillClosedShapeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			StaticStuff.setShapeType(ShapeEnum.FILLCLOSEDSHAEP);
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
		case PENICL:
			return new Pencil(x, y, x2, y2, StaticStuff.getShapeColor());
		case LINE:
			return new Line(x, y, x2, y2, StaticStuff.getShapeColor());
		case RUBBER:
			return new Rubber(x, y, x2, y2, Color.WHITE);
		default:
			break;
		}
		return drawShape;
	}

}
