package com.mateusz.paint.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.mateusz.paint.model.Model;

public class MainFrame extends JFrame
{

	private TopMenu menuBar;
	private DrawPanel panel;
	private ToolsMenu toolsMenu;

	public MainFrame(Model model) throws HeadlessException
	{
		super("Paint Application");
		prepareFrame();

		initializeToolsMenuComponents();
		initializeTopMenuComponents();
		panel = new DrawPanel();

		JPanel mainLayoutforApplication = new JPanel(new BorderLayout());

		mainLayoutforApplication.add(toolsMenu, BorderLayout.WEST);
		mainLayoutforApplication.add(panel, BorderLayout.CENTER);
		this.add(mainLayoutforApplication);

	}

	public void prepareFrame()
	{
		setSize(500, 500);
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

}
