package com.mateusz.paint.controller;

import java.util.List;
import com.mateusz.paint.model.DrawingsEdit;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.shapes.Shape;
import com.mateusz.paint.view.DrawPanel;
import com.mateusz.paint.view.MainFrame;

public abstract class SuperControllerForPanel
{
	private MainFrame view;
	private Model model;
	private DrawPanel drawPanel;
	private DrawingsEdit drawingsEdit;

	protected SuperControllerForPanel(MainFrame view, Model model)
	{
		this.setView(view);
		this.model = model;

		drawPanel = view.getDrawPanel();
		drawingsEdit = model.getDrawingsEdit();
	}

	protected MainFrame getView()
	{
		return view;
	}

	protected void setView(MainFrame view)
	{
		this.view = view;
	}

	protected DrawPanel getDrawPanel()
	{
		return drawPanel;
	}

	protected DrawingsEdit getDrawingsEdit()
	{
		return drawingsEdit;
	}

	protected void currentDrawingsToImage()
	{
		int width = drawPanel.getWidth();
		int height = drawPanel.getHeight();
		drawingsEdit.getGraphicsAndImageFromDrawings(width, height);
		drawPanel.paint(drawingsEdit.getGraphics2D());
	}

	protected void clearCurrentDrawings()
	{
		List<Shape> currentShapesDrawings = drawPanel.getShapes();
		currentShapesDrawings.clear();
		drawPanel.setImageToDraw(null);
	}
}
