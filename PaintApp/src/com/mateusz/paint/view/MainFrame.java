package com.mateusz.paint.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.mateusz.paint.model.Model;

public class MainFrame extends JFrame
{

	private TopMenu menuBar;
	private DrawPanel drawPanel;
	private ToolsMenu toolsMenu;

	public MainFrame(Model model) throws HeadlessException
	{
		super("Paint Application");
		prepareFrame();
		// setLayout(new GridBagLayout());
		// GridBagConstraints constraints = new GridBagConstraints();

		initializeToolsMenuComponents();
		initializeTopMenuComponents();
		initializeDrawPanel();

		JScrollPane scrollPane = new JScrollPane(drawPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel mainLayoutforApplication = new JPanel(new BorderLayout());
		mainLayoutforApplication.add(toolsMenu, BorderLayout.WEST);
		mainLayoutforApplication.add(scrollPane, BorderLayout.CENTER);
		add(mainLayoutforApplication);

		// // constraints.gridheight = 1;
		// // constraints.gridwidth = 1;
		// constraints.gridx = 0;
		// constraints.gridy = 0;
		// // constraints.weightx = 1.0;
		// // constraints.weighty = 1.0;
		// constraints.fill = GridBagConstraints.BOTH;
		//
		// add(toolsMenu, constraints);
		// constraints.gridx = 1;
		// add(scrollPane, constraints);
	}

	public void prepareFrame()
	{
		int width = 500;
		int height = 500;

		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setResizable(false);
		setVisible(true);
	}

	public void initializeTopMenuComponents()
	{
		menuBar = new TopMenu();
		menuBar.setTopMenu();
		setJMenuBar(menuBar.getMenuBar());
	}

	public void initializeToolsMenuComponents()
	{
		toolsMenu = new ToolsMenu();
		toolsMenu.setTools();
	}

	public void initializeDrawPanel()
	{
		drawPanel = new DrawPanel();
	}

	public ToolsMenu getToolsMenu()
	{
		return toolsMenu;
	}

	public TopMenu getTopMenu()
	{
		return menuBar;
	}

	public DrawPanel getDrawPanel()
	{
		return drawPanel;
	}
}
