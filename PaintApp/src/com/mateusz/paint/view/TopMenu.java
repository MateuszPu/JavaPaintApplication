package com.mateusz.paint.view;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopMenu extends JMenuBar
{

	private JMenuBar menuBar;
	private JMenu menu, submenu;
	private JMenuItem menuItem;

	public TopMenu()
	{
	}

	public void setTopMenu()
	{
		menuBar = new JMenuBar();

		// menu file
		menu = new JMenu("File");
		menuBar.add(menu);

		// add items to menu "File"
		menuItem = new JMenuItem("New...", new ImageIcon("images/menu/new.png"));
		menu.add(menuItem);
		menuItem = new JMenuItem("Open image...", new ImageIcon("images/menu/open.png"));
		menu.add(menuItem);
		menuItem = new JMenuItem("Save image...", new ImageIcon("images/menu/save.png"));
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Exit...", new ImageIcon("images/menu/exit.png"));
		menu.add(menuItem);

		// menu edit
		menu = new JMenu("Edit");
		menuBar.add(menu);

		// add items to menu "Edit"
		menuItem = new JMenuItem("Undo", new ImageIcon("images/menu/undo.png"));
		menu.add(menuItem);
		menuItem = new JMenuItem("Redo", new ImageIcon("images/menu/redo.png"));
		menu.add(menuItem);

		// menu rotate
		menu = new JMenu("Rotate");
		menuBar.add(menu);

		// add items to menu "Rotate"
		menuItem = new JMenuItem("Rotate 90° right", new ImageIcon("images/menu/rotate90right.png"));
		menu.add(menuItem);
		menuItem = new JMenuItem("Rotate 90° left", new ImageIcon("images/menu/rotate90left.png"));
		menu.add(menuItem);
		menuItem = new JMenuItem("Rotate 180°", new ImageIcon("images/menu/rotate180.png"));
		menu.add(menuItem);
		menuItem = new JMenuItem("Flip Vertical", new ImageIcon("images/menu/flipvertical.png"));
		menu.add(menuItem);
		menuItem = new JMenuItem("Flip Horizontal", new ImageIcon("images/menu/fliphorizontal.png"));
		menu.add(menuItem);

	}

	public JMenuBar getMenuBar()
	{
		return menuBar;
	}

}