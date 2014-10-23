package com.mateusz.paint.view;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopMenu extends JMenuBar
{
	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar;
	private JMenu menuFile, menuRotate, menuEdit;
	private JMenuItem newItem, openItem, saveItem, exitItem, undoItem, rotate90RightItem,
			rotate90RLeftItem, rotate180Item, flipVerticalItem, flipHorizontalItem;
	private ImageIcon exitIcon, newIcon, openIcon, saveIcon, undoIcon, rotate90RightIcon,
			rotate90RLeftIcon, rotate180Icon, flipVerticalIcon, flipHorizontalIcon;

	public TopMenu()
	{
	}

	public void setTopMenu()
	{
		menuBar = new JMenuBar();

		exitIcon = new ImageIcon(getClass().getResource("/menu/exit.png"));
		newIcon = new ImageIcon(getClass().getResource("/menu/new.png"));
		openIcon = new ImageIcon(getClass().getResource("/menu/open.png"));
		saveIcon = new ImageIcon(getClass().getResource("/menu/save.png"));
		undoIcon = new ImageIcon(getClass().getResource("/menu/undo.png"));

		rotate90RightIcon = new ImageIcon(getClass().getResource("/menu/rotate90right.png"));
		rotate90RLeftIcon = new ImageIcon(getClass().getResource("/menu/rotate90left.png"));
		rotate180Icon = new ImageIcon(getClass().getResource("/menu/rotate180.png"));
		flipVerticalIcon = new ImageIcon(getClass().getResource("/menu/flipvertical.png"));
		flipHorizontalIcon = new ImageIcon(getClass().getResource("/menu/fliphorizontal.png"));

		// menu file
		menuFile = new JMenu("File");
		menuBar.add(menuFile);

		// add items to menu "File"
		newItem = new JMenuItem("New...", newIcon);
		menuFile.add(newItem);
		openItem = new JMenuItem("Open image...", openIcon);
		menuFile.add(openItem);
		saveItem = new JMenuItem("Save image...", saveIcon);
		menuFile.add(saveItem);
		menuFile.addSeparator();
		exitItem = new JMenuItem("Exit...", exitIcon);
		menuFile.add(exitItem);

		// menu edit
		menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		// add items to menu "Edit"
		undoItem = new JMenuItem("Undo", undoIcon);
		// undoItem.setEnabled(false);
		menuEdit.add(undoItem);

		// menu rotate
		menuRotate = new JMenu("Rotate");
		menuBar.add(menuRotate);

		// add items to menu "Rotate"
		rotate90RightItem = new JMenuItem("Rotate 90° right", rotate90RightIcon);
		menuRotate.add(rotate90RightItem);
		rotate90RLeftItem = new JMenuItem("Rotate 90° left", rotate90RLeftIcon);
		menuRotate.add(rotate90RLeftItem);
		rotate180Item = new JMenuItem("Rotate 180°", rotate180Icon);
		menuRotate.add(rotate180Item);
		flipVerticalItem = new JMenuItem("Flip Vertical", flipVerticalIcon);
		menuRotate.add(flipVerticalItem);
		flipHorizontalItem = new JMenuItem("Flip Horizontal", flipHorizontalIcon);
		menuRotate.add(flipHorizontalItem);

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