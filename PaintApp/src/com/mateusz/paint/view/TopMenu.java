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
	private JMenuItem newItem, openItem, saveItem, exitItem, undoItem, rotate90RightItem, rotate90RLeftItem,
			rotate180Item, flipVerticalItem, flipHorizontalItem;

	public TopMenu()
	{
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
		// undoItem.setEnabled(false);
		menuEdit.add(undoItem);

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

	public void setEnabledUndoItem()
	{
		undoItem.setEnabled(false);
	}

	public JMenuBar getMenuBar()
	{
		return menuBar;
	}

	public void addNewListener(ActionListener listenerForNewItem)
	{
		newItem.addActionListener(listenerForNewItem);
	}

	public void addOpenListener(ActionListener listenerForOpenItem)
	{
		openItem.addActionListener(listenerForOpenItem);
	}

	public void addSaveImageListener(ActionListener listenerForSaveImage)
	{
		saveItem.addActionListener(listenerForSaveImage);
	}

	public void addExitListener(ActionListener listenerForExit)
	{
		exitItem.addActionListener(listenerForExit);
	}

	public void addUndoListener(ActionListener listenerForUndo)
	{
		undoItem.addActionListener(listenerForUndo);
	}

	public void addRotate90RightListener(ActionListener listenerForRotate90Right)
	{
		rotate90RightItem.addActionListener(listenerForRotate90Right);
	}

	public void addRotate90RLeftListener(ActionListener listenerForRotate90RLeftItem)
	{
		rotate90RLeftItem.addActionListener(listenerForRotate90RLeftItem);
	}

	public void addRotate180Listener(ActionListener listenerForRotate180Item)
	{
		rotate180Item.addActionListener(listenerForRotate180Item);
	}

	public void addflipVerticaListener(ActionListener listenerForFlipVertical)
	{
		flipVerticalItem.addActionListener(listenerForFlipVertical);
	}

	public void addFlipHorizontalListener(ActionListener listenerForFlipHorizontal)
	{
		flipHorizontalItem.addActionListener(listenerForFlipHorizontal);
	}

}