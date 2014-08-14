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
	private DrawPanel panelView;

	public TopMenuListeners(MainFrame view, Model model)
	{
		this.view = view;
		this.model = model;
		topMenuButtons = view.getTopMenu();
		panelView = view.getDrawPanel();

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
			List<Shape> currentShapesDrawings = panelView.getShapes();
			BufferedImage currentDrawingsToImage = getCurrentDrawingsToImage();

			int result = JOptionPane.showConfirmDialog(null, "Do you want to save your progress?", "Save progress?",
					JOptionPane.YES_NO_CANCEL_OPTION);

			switch (result)
			{
			case JOptionPane.YES_OPTION:
				model.getDrawingsEdit().saveImageToFile(currentDrawingsToImage);
				clearCurrentDrawings();
				panelView.repaint();
				break;
			case JOptionPane.NO_OPTION:
				clearCurrentDrawings();
				panelView.repaint();
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
			List<Shape> currentShapesDrawings = panelView.getShapes();

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
					panelView.setImageToDraw(image);
					panelView.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
					panelView.revalidate();
					panelView.repaint();
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
			BufferedImage currentDrawingsToImage = getCurrentDrawingsToImage();
			model.getDrawingsEdit().saveImageToFile(currentDrawingsToImage);
		}
	}

	class ExitMenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int result = JOptionPane.showConfirmDialog(null, "Do you want to save your progress?", "Save progress?",
					JOptionPane.YES_NO_CANCEL_OPTION);
			BufferedImage currentDrawingsToImage = getCurrentDrawingsToImage();

			switch (result)
			{
			case JOptionPane.YES_OPTION:
				model.getDrawingsEdit().saveImageToFile(currentDrawingsToImage);
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
			System.out.println(model.getDrawingsEdit().undoImageList.size());

			for (BufferedImage image : model.getDrawingsEdit().undoImageList)
			{
				System.out.println(image.toString());
			}

			// System.out.println(model.getDrawingsEdit().getBufferedImage().toString());
			// panelView.setImageToDraw(model.getDrawingsEdit().undoImageList.get(1));
			// panelView.repaint();
		}
	}

	class Rotate90RightListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			BufferedImage imageToFlip = getCurrentDrawingsToImage();
			DrawingsEdit drawingsEdit = model.getDrawingsEdit();
			drawingsEdit.rotateRight(imageToFlip, 90);

			clearCurrentDrawings();
			setImageToDrawOnPanel();
			panelView.repaint();
		}
	}

	class Rotate90LeftListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			BufferedImage imageToFlip = getCurrentDrawingsToImage();
			DrawingsEdit drawingsEdit = model.getDrawingsEdit();
			drawingsEdit.rotateRight(imageToFlip, -90);

			clearCurrentDrawings();
			setImageToDrawOnPanel();
			panelView.repaint();
		}
	}

	class Rotate180Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			BufferedImage imageToFlip = getCurrentDrawingsToImage();
			DrawingsEdit drawingsEdit = model.getDrawingsEdit();
			drawingsEdit.rotateImage180Degrees(imageToFlip);

			clearCurrentDrawings();
			setImageToDrawOnPanel();
			panelView.repaint();
		}
	}

	class FlipVerticalListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			BufferedImage imageToFlip = getCurrentDrawingsToImage();
			clearCurrentDrawings();

			model.getDrawingsEdit().flipVertical(imageToFlip);

			panelView.setImageToDraw(model.getDrawingsEdit().getBufferedImage());
			panelView.repaint();
		}
	}

	class FlipHorizontalListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			BufferedImage imageToFlip = getCurrentDrawingsToImage();
			clearCurrentDrawings();
			model.getDrawingsEdit().flipHorizontal(imageToFlip);

			panelView.setImageToDraw(model.getDrawingsEdit().getBufferedImage());
			panelView.repaint();
		}

	}

	private BufferedImage getCurrentDrawingsToImage()
	{
		DrawingsEdit drawingsEdit = model.getDrawingsEdit();

		int width = panelView.getWidth();
		int height = panelView.getHeight();

		drawingsEdit.setBufferedImageAndGraphicsFromCurrentDrawings(width, height);
		BufferedImage bufferedImage = drawingsEdit.getBufferedImage();
		Graphics2D g2d = drawingsEdit.getGraphics2D();
		panelView.currentDrawingsToImage(g2d, bufferedImage);

		return bufferedImage;
	}

	private void setImageToDrawOnPanel()
	{
		DrawingsEdit drawingsEdit = model.getDrawingsEdit();
		BufferedImage bufferedImage = drawingsEdit.getBufferedImage();
		Graphics2D g2d = drawingsEdit.getGraphics2D();
		panelView.currentDrawingsToImage(g2d, bufferedImage);
	}

	private void clearCurrentDrawings()
	{
		List<Shape> currentShapesDrawings = panelView.getShapes();
		currentShapesDrawings.clear();
		panelView.setImageToDraw(null);
	}
}
