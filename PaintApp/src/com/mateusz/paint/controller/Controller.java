package com.mateusz.paint.controller;

import com.mateusz.paint.model.Model;
import com.mateusz.paint.view.MainFrame;

public class Controller
{
	private MainFrame view;
	private Model model;

	public Controller(MainFrame view, Model model)
	{
		this.view = view;
		this.model = model;
	}

}
