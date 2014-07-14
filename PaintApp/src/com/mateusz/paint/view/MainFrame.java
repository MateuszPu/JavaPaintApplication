package com.mateusz.paint.view;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import com.mateusz.paint.model.Model;

public class MainFrame extends JFrame
{
	private Model model;

	public MainFrame(Model model) throws HeadlessException
	{
		super("Paint Application");
		this.model = model;
	}

}
