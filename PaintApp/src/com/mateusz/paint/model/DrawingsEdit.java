package com.mateusz.paint.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawingsEdit extends JComponent
{
	private BufferedImage bufferedImage;
	private Graphics2D graphics2D;
	private List<BufferedImage> undoImageList = new ArrayList<>();

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

	public List<BufferedImage> getUndoImageList()
	{
		return undoImageList;
	}

	public void setUndoImageList(List<BufferedImage> undoImageList)
	{
		this.undoImageList = undoImageList;
	}

	public void floodFill(Point pkt)
	{
		Color c = new Color(bufferedImage.getRGB((int) pkt.getX(), (int) pkt.getY()));
		int red = c.getRed();
		int green = c.getGreen();
		int blue = c.getBlue();
		Color backgroundColor = new Color(red, green, blue);

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		int target = backgroundColor.getRGB();
		int replacement = StaticStuff.getShapeColor().getRGB();

		if (target != replacement)
		{
			Deque<Point> queue = new LinkedList<Point>();
			do
			{
				int x = pkt.x;
				int y = pkt.y;
				while (x > 0 && bufferedImage.getRGB(x - 1, y) == target)
				{
					x--;
				}
				boolean pixelUp = false;
				boolean pixelDown = false;
				while (x < width && bufferedImage.getRGB(x, y) == target)
				{
					bufferedImage.setRGB(x, y, replacement);
					if (!pixelUp && y > 0 && bufferedImage.getRGB(x, y - 1) == target)
					{
						queue.add(new Point(x, y - 1));
						pixelUp = true;
					}
					else if (pixelUp && y > 0 && bufferedImage.getRGB(x, y - 1) != target)
					{
						pixelUp = false;
					}
					if (!pixelDown && y < height - 1 && bufferedImage.getRGB(x, y + 1) == target)
					{
						queue.add(new Point(x, y + 1));
						pixelDown = true;
					}
					else if (pixelDown && y < height - 1 && bufferedImage.getRGB(x, y + 1) != target)
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
			int defaultFileType = 1; // 0==all files, 1==jpg, 2==png, 3==bmp
										// 4==jpeg
			JFileChooser saveAs = new JFileChooser();
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("JPG (.jpg)", "jpg"));
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("PNG (.png)", "png"));
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("BMP (.bmp)", "bmp"));
			saveAs.addChoosableFileFilter(new FileNameExtensionFilter("JPEG (.jpeg)", "jpeg"));
			saveAs.setFileFilter(saveAs.getChoosableFileFilters()[defaultFileType]);

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
					catch (IOException | NullPointerException e)
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

	public void flipHorizontal()
	{
		int sx = -1; // sx - factor by which coordinates are scaled along the X
						// axis direction
		int sy = 1; // sy - factor by which coordinates are scaled along the Y
					// axis direction
		int ty = 0; // ty - the distance by which coordinates are translated in
					// the Y axis direction

		AffineTransform tx = AffineTransform.getScaleInstance(sx, sy);
		tx.translate(-bufferedImage.getWidth(null), ty);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		this.bufferedImage = op.filter(bufferedImage, null);
	}

	public void flipVertical()
	{
		int sx = 1; // sx - factor by which coordinates are scaled along the X
					// axis direction
		int sy = -1; // sy - factor by which coordinates are scaled along the Y
						// axis direction
		int ty = 0; // ty - the distance by which coordinates are translated in
					// the Y axis direction

		AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);
		transform.translate(ty, -bufferedImage.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		this.bufferedImage = op.filter(bufferedImage, null);
	}

	public void rotateImage180Degrees()
	{
		int sx = -1; // sx - factor by which coordinates are scaled along the X
						// axis direction
		int sy = -1; // sy - factor by which coordinates are scaled along the Y
						// axis direction

		AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);
		transform.translate(-bufferedImage.getWidth(null), -bufferedImage.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		this.bufferedImage = op.filter(bufferedImage, null);
	}

	public void rotateRightLeft(int degrees)
	{

		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.toRadians(degrees), bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		this.bufferedImage = op.filter(bufferedImage, null);// (source,destination)
	}

	public void clearUndoImageList()
	{
		undoImageList.clear();
	}

	public void getGraphicsAndImageFromDrawings(int width, int height)
	{
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bufferedImage.createGraphics();
		this.bufferedImage = bufferedImage;
		this.graphics2D = g2d;
		undoImageList.add(bufferedImage);
	}
}
