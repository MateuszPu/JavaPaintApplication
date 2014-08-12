package com.mateusz.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawingsEdit extends JComponent
{
	private Graphics2D graphics2D;
	private BufferedImage bufferedImage;

	public BufferedImage getBufferedImage()
	{
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage)
	{
		this.bufferedImage = bufferedImage;
	}

	public Graphics2D getGraphics2D()
	{
		return graphics2D;
	}

	public void setGraphics2D(Graphics2D graphics2d)
	{
		graphics2D = graphics2d;
	}

	public void setBufferedImageAndGraphicsFromCurrentDrawings(int width, int height)
	{
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = bufferedImage.createGraphics();
		this.setGraphics2D(graphics2D);
		this.bufferedImage = bufferedImage;
	}

	public void floodFill(BufferedImage image, Point pkt, Color targetColor, Color replacementColor)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int target = targetColor.getRGB();
		int replacement = replacementColor.getRGB();
		if (target != replacement)
		{
			Deque<Point> queue = new LinkedList<Point>();
			do
			{
				int x = pkt.x;
				int y = pkt.y;
				while (x > 0 && image.getRGB(x - 1, y) == target)
				{
					x--;
				}
				boolean pixelUp = false;
				boolean pixelDown = false;
				while (x < width && image.getRGB(x, y) == target)
				{
					image.setRGB(x, y, replacement);
					if (!pixelUp && y > 0 && image.getRGB(x, y - 1) == target)
					{
						queue.add(new Point(x, y - 1));
						pixelUp = true;
					}
					else if (pixelUp && y > 0 && image.getRGB(x, y - 1) != target)
					{
						pixelUp = false;
					}
					if (!pixelDown && y < height - 1 && image.getRGB(x, y + 1) == target)
					{
						queue.add(new Point(x, y + 1));
						pixelDown = true;
					}
					else if (pixelDown && y < height - 1 && image.getRGB(x, y + 1) != target)
					{
						pixelDown = false;
					}
					x++;
				}
			}
			while ((pkt = queue.pollFirst()) != null);
		}
	}

	public void saveImageToFile(BufferedImage imageReaded)
	{
		boolean isCorrectExtension = false;

		while (!isCorrectExtension)
		{
			int defaultFilesType = 1; // 0==all files, 1==jpg, 2==png, 3==bmp
										// 4==jpeg
			JFileChooser saveAs = new JFileChooser();
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("JPG (.jpg)", "jpg"));
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("PNG (.png)", "png"));
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("BMP (.bmp)", "bmp"));
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("JPEG (.jpeg)", "jpeg"));
			saveAs.setFileFilter(saveAs.getChoosableFileFilters()[defaultFilesType]);

			int optionChosenByUser = saveAs.showSaveDialog(this);

			if (optionChosenByUser == JFileChooser.APPROVE_OPTION)
			{
				File outPath = saveAs.getSelectedFile();
				String saveType = outPath.getName().substring(outPath.getName().lastIndexOf(".") + 1);

				if (saveType.equalsIgnoreCase("png") || saveType.equalsIgnoreCase("jpg")
						|| saveType.equalsIgnoreCase("bmp") || saveType.equalsIgnoreCase("jpeg"))
				{
					try
					{
						ImageIO.write(imageReaded, saveType, outPath);
						isCorrectExtension = true;
					}
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(this,
								"Write error for " + outPath.getName() + ": " + e.getMessage(), "Unable to save file",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Wrong extension :( " + "\n Please provide correct extension "
							+ "\n Example: \"yourPicuteName.extension\" "
							+ "\n Avaliable extension: png, jpg, bmp, jpeg", "Error !!!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				isCorrectExtension = true;
			}
		}
	}

	public void flipHorizontal(BufferedImage imageToFlipHorizontal)
	{
		int sx = -1; // sx - factor by which coordinates are scaled along the X
						// axis direction
		int sy = 1; // sy - factor by which coordinates are scaled along the Y
					// axis direction
		int ty = 0; // ty - the distance by which coordinates are translated in
					// the Y axis direction
		AffineTransform tx = AffineTransform.getScaleInstance(sx, sy);
		tx.translate(-imageToFlipHorizontal.getWidth(null), ty);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		bufferedImage = op.filter(imageToFlipHorizontal, null);
	}

	public void flipVertical(BufferedImage imageToFlipHorizontal)
	{
		int sx = 1; // sx - factor by which coordinates are scaled along the X
					// axis direction
		int sy = -1; // sy - factor by which coordinates are scaled along the Y
						// axis direction
		int ty = 0; // ty - the distance by which coordinates are translated in
					// the Y axis direction
		AffineTransform tx = AffineTransform.getScaleInstance(sx, sy);
		tx.translate(ty, -imageToFlipHorizontal.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		bufferedImage = op.filter(imageToFlipHorizontal, null);
	}

	public void rotateImage180Degrees()
	{
		int degrees = 180;
		int x = bufferedImage.getWidth(); // x - the x coordinate of the origin
											// of the rotation
		int y = bufferedImage.getHeight(); // y - the y coordinate of the origin
											// of the rotation
		graphics2D.rotate(Math.toRadians(degrees), x / 2, y / 2);
	}

	public void rotateLeftRight(int degrees)
	{
		BufferedImage oldImage = bufferedImage;
		BufferedImage newImage = new BufferedImage(oldImage.getHeight(), oldImage.getWidth(), oldImage.getType());
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.rotate(Math.toRadians(degrees), newImage.getWidth() / 2, newImage.getHeight() / 2);
		graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2,
				(newImage.getHeight() - oldImage.getHeight()) / 2);
		graphics.drawImage(oldImage, 0, 0, oldImage.getWidth(), oldImage.getHeight(), null);
		bufferedImage = newImage;
	}
}
