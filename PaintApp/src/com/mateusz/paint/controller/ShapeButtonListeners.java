package com.mateusz.paint.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import com.mateusz.paint.enums.ShapeEnum;
import com.mateusz.paint.model.StaticStuffColorAndShape;
import com.mateusz.paint.view.MainFrame;
import com.mateusz.paint.view.ToolsMenu;

public class ShapeButtonListeners
{
	private MainFrame view;
	private ToolsMenu toolsMenuButtons;

	public ShapeButtonListeners(MainFrame view)
	{
		this.view = view;
		toolsMenuButtons = view.getToolsMenu();

		toolsMenuButtons.addRectangleListener(new RectangleListener());
		toolsMenuButtons.addCircleListener(new CircleListener());
		toolsMenuButtons.addLineListener(new LineListener());
		toolsMenuButtons.addPencilListener(new PencilListener());
		toolsMenuButtons.addRubberListener(new RubberListener());
		toolsMenuButtons.addColorListener(new ColorListener());
		toolsMenuButtons.addFillClosedShapeListener(new FillClosedShapeListener());
	}

	class ColorListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			Color selectedColor = JColorChooser.showDialog(view, "Chose color:",
					StaticStuffColorAndShape.getShapeColor());
			StaticStuffColorAndShape.setShapeColor(selectedColor);
			view.getToolsMenu().setColor(selectedColor);
		}
	}

	class RectangleListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StaticStuffColorAndShape.setShapeType(ShapeEnum.RECTANGLE);
		}
	}

	class CircleListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StaticStuffColorAndShape.setShapeType(ShapeEnum.CIRCLE);
		}
	}

	class LineListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StaticStuffColorAndShape.setShapeType(ShapeEnum.LINE);
		}
	}

	class PencilListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StaticStuffColorAndShape.setShapeType(ShapeEnum.PENCIL);
		}
	}

	class RubberListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StaticStuffColorAndShape.setShapeType(ShapeEnum.RUBBER);
		}
	}

	class FillClosedShapeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			StaticStuffColorAndShape.setShapeType(ShapeEnum.FILLCLOSEDSHAPE);
		}

	}
}
