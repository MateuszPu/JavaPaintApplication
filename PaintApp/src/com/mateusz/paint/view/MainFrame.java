package com.mateusz.paint.view;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import com.mateusz.paint.model.Model;

public class MainFrame extends JFrame
{

	final TopMenu menuBar;
	private ToolsMenu toolsMenu;
	private Model model;

	public MainFrame(Model model) throws HeadlessException
	{
		super("Paint Application");
		this.model = model;

		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		// add tools menu
		ToolsMenu toolsMenu = new ToolsMenu(this);
		add(toolsMenu);

		// add menu bar
		menuBar = new TopMenu();
		menuBar.setTopMenu();
		setJMenuBar(menuBar.getMenuBar());

	}

}
