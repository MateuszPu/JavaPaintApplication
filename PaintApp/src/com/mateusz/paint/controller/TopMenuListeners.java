package com.mateusz.paint.controller;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import com.mateusz.paint.model.DrawingsEdit;
import com.mateusz.paint.model.Model;
import com.mateusz.paint.model.shapes.Shape;
import com.mateusz.paint.view.DrawPanel;
import com.mateusz.paint.view.MainFrame;
import com.mateusz.paint.view.TopMenu;

public class TopMenuListeners
{
	private MainFrame view;
	private Model model;
	private TopMenu topMenuButtons;

	public TopMenuListeners(MainFrame view, Model model)
	{
		this.view = view;
		this.model = model;
		topMenuButtons = view.getTopMenu();

		topMenuButtons.addNewItemListener(new newItemListener());
	}

	class newItemListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			DrawPanel panelView = view.getDrawPanel();
			DrawingsEdit drawingsEdit = model.getDrawingsEdit();

			List<Shape> currentDrawings = panelView.getShapes();
			int width = panelView.getWidth();
			int height = panelView.getHeight();

			drawingsEdit.setBufferedImageAndGraphicsFromCurrentDrawings(width, height);
			BufferedImage bufferedImagetoSave = drawingsEdit.getBufferedImage();
			Graphics2D g2d = drawingsEdit.getGraphics2D();
			panelView.currentDrawingsToImage(g2d, bufferedImagetoSave);

			int result = JOptionPane.showConfirmDialog(null, "Do you want to save your progress?", "Save progress?",
					JOptionPane.YES_NO_CANCEL_OPTION);

			switch (result)
			{
			case JOptionPane.YES_OPTION:
				model.getDrawingsEdit().saveImageToFile(bufferedImagetoSave);
				model.getDrawingsEdit().clearDrawings(currentDrawings);
				view.getDrawPanel().repaint();
				break;
			case JOptionPane.NO_OPTION:
				model.getDrawingsEdit().clearDrawings(currentDrawings);
				view.getDrawPanel().repaint();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
	}
}
