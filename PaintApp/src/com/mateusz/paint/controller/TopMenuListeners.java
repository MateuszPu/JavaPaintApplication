package com.mateusz.paint.controller;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	private DrawPanel drawPanel;
	private DrawingsEdit drawingsEdit;

	public TopMenuListeners(MainFrame view, Model model)
	{
		this.view = view;
		this.model = model;
		topMenuButtons = view.getTopMenu();
		drawPanel = view.getDrawPanel();
		drawingsEdit = model.getDrawingsEdit();

		topMenuButtons.addNewListener(new NewMenuListener());
		topMenuButtons.addOpenListener(new OpenMenuListener());
		topMenuButtons.addSaveImageListener(new SaveMenuListener());
		topMenuButtons.addExitListener(new ExitMenuListener());

		topMenuButtons.addUndoListener(new UndoListener());

		topMenuButtons.addRotate90RightListener(new Rotate90RightListener());
		topMenuButtons.addRotate90RLeftListener(new Rotate90LeftListener());
		topMenuButtons.addRotate180Listener(new Rotate180Listener());
		topMenuButtons.addflipVerticaListener(new FlipVerticalListener());
		topMenuButtons.addFlipHorizontalListener(new FlipHorizontalListener());
	}

	class NewMenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			getCurrentDrawingsToImage();
			BufferedImage currentDrawingsToImage = drawingsEdit.getBufferedImage();
			int result = JOptionPane.showConfirmDialog(null, "Do you want to save your progress?", "Save progress?",
					JOptionPane.YES_NO_CANCEL_OPTION);

			switch (result)
			{
			case JOptionPane.YES_OPTION:
				drawingsEdit.saveImageToFile(currentDrawingsToImage);
				clearCurrentDrawings();
				drawPanel.repaint();
				break;
			case JOptionPane.NO_OPTION:
				clearCurrentDrawings();
				drawPanel.repaint();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
	}

	class OpenMenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			List<Shape> currentShapesDrawings = drawPanel.getShapes();

			JFileChooser openFile = new JFileChooser();
			openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			openFile.setAcceptAllFileFilterUsed(false);
			openFile.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "bmp", "jpeg"));

			int result = openFile.showOpenDialog(openFile);

			if (result == JFileChooser.APPROVE_OPTION)
			{
				File file = openFile.getSelectedFile();
				String pathName = file.getAbsolutePath();
				File imageFile = new File(pathName);

				try
				{
					BufferedImage image = ImageIO.read(imageFile);
					currentShapesDrawings.clear();
					drawPanel.setImageToDraw(image);
					drawPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
					drawPanel.revalidate();
					drawPanel.repaint();
				}
				catch (IOException ex)
				{
					JOptionPane.showMessageDialog(view,
							"Open error for \"" + file.getPath() + "\" : " + ex.getMessage(), "Unable to Open file",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	class SaveMenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			getCurrentDrawingsToImage();
			BufferedImage currentDrawingsToImage = drawingsEdit.getBufferedImage();
			drawingsEdit.saveImageToFile(currentDrawingsToImage);
		}
	}

	class ExitMenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int result = JOptionPane.showConfirmDialog(null, "Do you want to save your progress?", "Save progress?",
					JOptionPane.YES_NO_CANCEL_OPTION);
			getCurrentDrawingsToImage();
			BufferedImage currentDrawingsToImage = drawingsEdit.getBufferedImage();

			switch (result)
			{
			case JOptionPane.YES_OPTION:
				drawingsEdit.saveImageToFile(currentDrawingsToImage);
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
	}

	class UndoListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			List<BufferedImage> undoImageList = drawingsEdit.getUndoImageList();
			int lastImage = undoImageList.size() - 1;
			drawPanel.setImageToDraw(undoImageList.get(lastImage));
			undoImageList.remove(lastImage);
			drawPanel.repaint();

			if (undoImageList.isEmpty())
			{
				topMenuButtons.setEnabledUndoItem();
			}
		}
	}

	class Rotate90RightListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			final int degrees = 90; // positive amount is responsible for rotate
									// right
			getCurrentDrawingsToImage();
			clearCurrentDrawings();

			BufferedImage imageToRotate = drawingsEdit.getBufferedImage();
			drawingsEdit.rotateRightLeft(imageToRotate, degrees);

			drawPanel.setImageToDraw(drawingsEdit.getBufferedImage());
			drawPanel.repaint();
		}
	}

	class Rotate90LeftListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			final int degrees = -90; // negative amount is responsible for
										// rotate left

			getCurrentDrawingsToImage();
			clearCurrentDrawings();

			BufferedImage imageToRotate = drawingsEdit.getBufferedImage();
			drawingsEdit.rotateRightLeft(imageToRotate, degrees);

			drawPanel.setImageToDraw(drawingsEdit.getBufferedImage());
			drawPanel.repaint();
		}
	}

	class Rotate180Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			getCurrentDrawingsToImage();
			clearCurrentDrawings();

			BufferedImage imageToRotate = drawingsEdit.getBufferedImage();
			drawingsEdit.rotateImage180Degrees(imageToRotate);

			drawPanel.setImageToDraw(drawingsEdit.getBufferedImage());
			drawPanel.repaint();
		}
	}

	class FlipVerticalListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			getCurrentDrawingsToImage();
			clearCurrentDrawings();

			BufferedImage imageToFlip = drawingsEdit.getBufferedImage();
			drawingsEdit.flipVertical(imageToFlip);

			drawPanel.setImageToDraw(drawingsEdit.getBufferedImage());
			drawPanel.repaint();
		}
	}

	class FlipHorizontalListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			getCurrentDrawingsToImage();
			clearCurrentDrawings();

			BufferedImage imageToFlip = drawingsEdit.getBufferedImage();
			drawingsEdit.flipHorizontal(imageToFlip);

			drawPanel.setImageToDraw(drawingsEdit.getBufferedImage());
			drawPanel.repaint();
		}
	}

	private void getCurrentDrawingsToImage()
	{
		int width = drawPanel.getWidth();
		int height = drawPanel.getHeight();

		drawingsEdit.setBufferedImageAndGraphicsFromCurrentDrawings(width, height);
		BufferedImage bufferedImage = drawingsEdit.getBufferedImage();
		Graphics2D g2d = drawingsEdit.getGraphics2D();
		drawPanel.currentDrawingsToImage(g2d, bufferedImage);
	}

	private void clearCurrentDrawings()
	{
		List<Shape> currentShapesDrawings = drawPanel.getShapes();
		currentShapesDrawings.clear();
		drawPanel.setImageToDraw(null);
	}
}
