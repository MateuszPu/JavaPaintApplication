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
		this.view = view;
		this.model = model;

		shapeButtonsListener = new ShapeButtonListeners(view);
		drawPanelListeners = new DrawPanelListeners(view, model);
		topMenuListeners = new TopMenuListeners(view, model);
	}
}
