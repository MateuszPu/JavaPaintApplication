package com.mateusz.paint.application;

import javax.swing.SwingUtilities;

import com.mateusz.paint.controller.Controller;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.view.MainFrame;

public class Application
{

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				runApplication();
			}
		});

	}

	public static void runApplication()
	{
		Model model = new Model();

		MainFrame view = new MainFrame(model);
		Controller controller = new Controller(view, model);

	}
}
