package com.mateusz.paint.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import org.apache.log4j.Logger;
import com.mateusz.paint.enums.ShapeEnum;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.StaticStuff;
import com.mateusz.paint.view.MainFrame;
import com.mateusz.paint.view.ToolsMenu;

public class Controller
{

	final static Logger logger = Logger.getLogger(ToolsMenu.class);
	private Color selectedColor = StaticStuff.getShapecolor();
	private MainFrame view;
	private Model model;

	private StaticStuff staticStaff = new StaticStuff();

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
	}

	class ColorListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			selectedColor = JColorChooser.showDialog(view, "Chose color:", StaticStuff.getShapecolor());
			staticStaff.setColor(selectedColor);
			view.getToolsMenu().setColor(selectedColor);
		}
	}

	class RectangleListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			staticStaff.setShape(ShapeEnum.RECTANGLE);
		}
	}

	class CircleListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			staticStaff.setShape(ShapeEnum.CIRCLE);
		}
	}

	class LineListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			staticStaff.setShape(ShapeEnum.LINE);
		}
	}

	class PencilListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			staticStaff.setShape(ShapeEnum.PENICL);
		}
	}

	class RubberListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			staticStaff.setShape(ShapeEnum.RUBBER);
		}
	}

}
