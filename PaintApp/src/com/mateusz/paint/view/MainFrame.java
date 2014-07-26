package com.mateusz.paint.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.StaticStuff;

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
		panel.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// System.out.println("clicked");

			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.out.println(StaticStuff.getShape());

			}
		});

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
