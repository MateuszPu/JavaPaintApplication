package com.mateusz.paint.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.mateusz.paint.model.StaticStuff;

public class ToolsMenu extends JPanel
{

	private JButton colorButton, rectangle, circle, pencil, line, rubber, fillClosedShape;
	private Color c = StaticStuff.shapeColor;

	public ToolsMenu()
	{
	}

	public void setTools()
	{
		int numberOfColumns = 1; // = 0 many columns as necessary
		int numberOfRows = 0; // 0 = many rows as necessary

		this.setLayout(new GridLayout(numberOfRows, numberOfColumns));

		rectangle = new JButton(new ImageIcon("images/tools/rectangle.png"));
		circle = new JButton(new ImageIcon("images/tools/circle.png"));
		pencil = new JButton(new ImageIcon("images/tools/pencil.png"));
		line = new JButton(new ImageIcon("images/tools/line.png"));
		rubber = new JButton(new ImageIcon("images/tools/rubber.png"));
		fillClosedShape = new JButton(new ImageIcon("images/tools/fill.png"));
		colorButton = new JButton("   ");

		colorButton.setForeground(c);
		colorButton.setBackground(c);
		this.add(circle);
		this.add(rectangle);
		this.add(pencil);
		this.add(line);
		this.add(rubber);
		this.add(fillClosedShape);
		this.add(colorButton);
	}
}
