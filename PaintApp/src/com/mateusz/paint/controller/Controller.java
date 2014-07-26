package com.mateusz.paint.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.Logger;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.StaticStuff;
import com.mateusz.paint.view.MainFrame;
import com.mateusz.paint.view.ToolsMenu;

public class Controller
{

	final static Logger logger = Logger.getLogger(ToolsMenu.class);
	private MainFrame view;
	private Model model;

	public Controller(MainFrame view, Model model)
	{
		this.view = view;
		this.model = model;

		this.view.getToolsMenu().addRectangleListener(new RectangleListener());
		this.view.getToolsMenu().addCircleListener(new CircleListener());
		this.view.getToolsMenu().addLineListener(new LineListener());
		this.view.getToolsMenu().addPencilListener(new PencilListener());
		this.view.getToolsMenu().addRubberListener(new RubberListener());
	}

	class RectangleListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent event)
		{
			logger.debug("rectangle");
		}
	}

	class CircleListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			logger.debug("circle");
		}
	}

	class LineListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			logger.debug("line");
		}
	}

	class PencilListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			logger.debug("pencil");
		}
	}

	class RubberListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			logger.debug("rubber");
		}
	}

}
