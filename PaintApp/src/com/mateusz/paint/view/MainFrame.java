package com.mateusz.paint.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.MenuBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

		initializeToolsMenuComponents();
		initializeTopMenuComponents();
		drawPanel = new DrawPanel();

		JPanel mainLayoutforApplication = new JPanel(new BorderLayout());

		mainLayoutforApplication.add(toolsMenu, BorderLayout.WEST);
		mainLayoutforApplication.add(drawPanel, BorderLayout.CENTER);
		this.add(mainLayoutforApplication);

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
