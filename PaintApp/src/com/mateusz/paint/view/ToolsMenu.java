package com.mateusz.paint.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.mateusz.paint.model.StaticStuffColorAndShape;

public class ToolsMenu extends JPanel
{
	private JButton colorButton, rectangleButton, circleButton, pencilButton, lineButton,
			rubberButton, fillClosedShapeButton;
	private JLabel tipText;

	public ToolsMenu()
	{
		int width = 60;
		int height = 210;
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}

	public void setTools()
	{
		int numberOfColumns = 1; // = 0 many columns as necessary
		int numberOfRows = 0; // 0 = many rows as necessary
		this.setLayout(new GridLayout(numberOfRows, numberOfColumns));

		tipText = new JLabel("Color:  ");
		rectangleButton = new JButton(new ImageIcon("images/tools/rectangle.png"));
		rectangleButton.setToolTipText("Draw Rectangle");
		circleButton = new JButton(new ImageIcon("images/tools/circle.png"));
		circleButton.setToolTipText("Draw Circle");
		pencilButton = new JButton(new ImageIcon("images/tools/pencil.png"));
		pencilButton.setToolTipText("Draw Pencil");
		lineButton = new JButton(new ImageIcon("images/tools/line.png"));
		lineButton.setToolTipText("Draw Line");
		rubberButton = new JButton(new ImageIcon("images/tools/rubber.png"));
		rubberButton.setToolTipText("Erase drawings");
		fillClosedShapeButton = new JButton(new ImageIcon("images/tools/fill.png"));
		fillClosedShapeButton.setToolTipText("Fill closed shape by selected Color");

		colorButton = new JButton();
		colorButton.setToolTipText("Select color");
		colorButton.setForeground(StaticStuffColorAndShape.getShapeColor());
		colorButton.setBackground(StaticStuffColorAndShape.getShapeColor());

		this.add(circleButton);
		this.add(rectangleButton);
		this.add(pencilButton);
		this.add(lineButton);
		this.add(rubberButton);
		this.add(fillClosedShapeButton);
		this.add(tipText);
		this.add(colorButton);
	}

	public void addColorListener(ActionListener listenForColorButton)
	{
		colorButton.addActionListener(listenForColorButton);
	}

	public void addRectangleListener(ActionListener listenForRectangleButton)
	{
		rectangleButton.addActionListener(listenForRectangleButton);
	}

	public void addCircleListener(ActionListener listenForCircleButton)
	{
		circleButton.addActionListener(listenForCircleButton);
	}

	public void addLineListener(ActionListener listenForLineButton)
	{
		lineButton.addActionListener(listenForLineButton);
	}

	public void addPencilListener(ActionListener listenForPencilButton)
	{
		pencilButton.addActionListener(listenForPencilButton);
	}

	public void addRubberListener(ActionListener listenForRubberButton)
	{
		rubberButton.addActionListener(listenForRubberButton);
	}

	public void addFillClosedShapeListener(ActionListener listenForFillClosedShapeButton)
	{
		fillClosedShapeButton.addActionListener(listenForFillClosedShapeButton);
	}

	public void setColor(Color color)
	{
		colorButton.setForeground(color);
		colorButton.setBackground(color);
	}
}
