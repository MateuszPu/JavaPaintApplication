package com.mateusz.paint.controller;

import com.mateusz.paint.model.Model;
import com.mateusz.paint.view.MainFrame;

public class Controller
{
	private MainFrame view;
	private Model model;
	private ShapeButtonListeners shapeButtonsListener;
	private DrawPanelListeners drawPanelListeners;
	private TopMenuListeners topMenuListeners;

	public Controller(MainFrame view, Model model)
	{
		this.setView(view);
		this.setModel(model);

		setShapeButtonsListener(new ShapeButtonListeners(view));
		setDrawPanelListeners(new DrawPanelListeners(view, model));
		setTopMenuListeners(new TopMenuListeners(view, model));
	}

	public TopMenuListeners getTopMenuListeners()
	{
		return topMenuListeners;
	}

	public void setTopMenuListeners(TopMenuListeners topMenuListeners)
	{
		this.topMenuListeners = topMenuListeners;
	}

	public DrawPanelListeners getDrawPanelListeners()
	{
		return drawPanelListeners;
	}

	public void setDrawPanelListeners(DrawPanelListeners drawPanelListeners)
	{
		this.drawPanelListeners = drawPanelListeners;
	}

	public ShapeButtonListeners getShapeButtonsListener()
	{
		return shapeButtonsListener;
	}

	public void setShapeButtonsListener(ShapeButtonListeners shapeButtonsListener)
	{
		this.shapeButtonsListener = shapeButtonsListener;
	}

	public Model getModel()
	{
		return model;
	}

	public void setModel(Model model)
	{
		this.model = model;
	}

	public MainFrame getView()
	{
		return view;
	}

	public void setView(MainFrame view)
	{
		this.view = view;
	}
}
