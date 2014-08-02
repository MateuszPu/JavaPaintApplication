package com.mateusz.paint.view;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopMenu extends JMenuBar
{

	private JMenuBar menuBar;
	private JMenu menuFile, menuRotate, menuEdit;
	private JMenuItem newItem, openItem, saveItem, exitItem, undoItem, redoItem, rotate90RightItem, rotate90RLeftItem,
			rotate180Item, flipVerticalItem, flipHorizontalItem;

	public TopMenu()
	{
	}

	public void addNewItemListener(ActionListener listenForNewItemMenu)
	{
		newItem.addActionListener(listenForNewItemMenu);
	}

	public void addOpenItemListener(ActionListener listenForOpenItemMenu)
	{
		newItem.addActionListener(listenForOpenItemMenu);
	}

	public void setTopMenu()
	{
		menuBar = new JMenuBar();

		// menu file
		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		// add items to menu "File"
		newItem = new JMenuItem("New...", new ImageIcon("images/menu/new.png"));
		menuFile.add(newItem);
		openItem = new JMenuItem("Open image...", new ImageIcon("images/menu/open.png"));
		menuFile.add(openItem);
		saveItem = new JMenuItem("Save image...", new ImageIcon("images/menu/save.png"));
		menuFile.add(saveItem);
		menuFile.addSeparator();
		exitItem = new JMenuItem("Exit...", new ImageIcon("images/menu/exit.png"));
		menuFile.add(exitItem);

		// menu edit
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		// add items to menu "Edit"
		undoItem = new JMenuItem("Undo", new ImageIcon("images/menu/undo.png"));
		menuEdit.add(undoItem);
		redoItem = new JMenuItem("Redo", new ImageIcon("images/menu/redo.png"));
		menuEdit.add(redoItem);

		// menu rotate
		menuRotate = new JMenu("Rotate");
		menuBar.add(menuRotate);

		// add items to menu "Rotate"
		rotate90RightItem = new JMenuItem("Rotate 90° right", new ImageIcon("images/menu/rotate90right.png"));
		menuRotate.add(rotate90RightItem);
		rotate90RLeftItem = new JMenuItem("Rotate 90° left", new ImageIcon("images/menu/rotate90left.png"));
		menuRotate.add(rotate90RLeftItem);
		rotate180Item = new JMenuItem("Rotate 180°", new ImageIcon("images/menu/rotate180.png"));
		menuRotate.add(rotate180Item);
		flipVerticalItem = new JMenuItem("Flip Vertical", new ImageIcon("images/menu/flipvertical.png"));
		menuRotate.add(flipVerticalItem);
		flipHorizontalItem = new JMenuItem("Flip Horizontal", new ImageIcon("images/menu/fliphorizontal.png"));
		menuRotate.add(flipHorizontalItem);

	}

	public JMenuBar getMenuBar()
	{
		return menuBar;
	}

}