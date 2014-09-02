package com.mateusz.paint.controller;

import java.util.List;
import com.mateusz.paint.model.DrawingsEdit;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.shapes.Shape;
import com.mateusz.paint.view.DrawPanel;
import com.mateusz.paint.view.MainFrame;

public class SuperControllerForPanel
{
	private MainFrame view;
	private Model model;
	private DrawPanel drawPanel;
	private DrawingsEdit drawingsEdit;

	public SuperControllerForPanel(MainFrame view, Model model)
	{
		this.setView(view);
		this.model = model;

		drawPanel = view.getDrawPanel();
		drawingsEdit = model.getDrawingsEdit();

	}

	public MainFrame getView()
	{
		return view;
	}

	public void setView(MainFrame view)
	{
		this.view = view;
	}

	public DrawPanel getDrawPanel()
	{
		return drawPanel;
	}

	public DrawingsEdit getDrawingsEdit()
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
