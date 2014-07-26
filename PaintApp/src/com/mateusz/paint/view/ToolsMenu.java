package com.mateusz.paint.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import com.mateusz.paint.model.StaticStuff;

public class ToolsMenu extends JPanel
{

	private JButton colorButton, rectangleButton, circleButton, pencilButton, lineButton, rubberButton,
			fillClosedShapeButton;
	private JLabel tipText;

	public ToolsMenu()
	{
	}

	public void setTools()
	{
		int numberOfColumns = 1; // = 0 many columns as necessary
		int numberOfRows = 0; // 0 = many rows as necessary
		this.setLayout(new GridLayout(numberOfRows, numberOfColumns));

		tipText = new JLabel("Color Chooser: ");
		rectangleButton = new JButton(new ImageIcon("images/tools/rectangle.png"));
		circleButton = new JButton(new ImageIcon("images/tools/circle.png"));
		pencilButton = new JButton(new ImageIcon("images/tools/pencil.png"));
		lineButton = new JButton(new ImageIcon("images/tools/line.png"));
		rubberButton = new JButton(new ImageIcon("images/tools/rubber.png"));
		fillClosedShapeButton = new JButton(new ImageIcon("images/tools/fill.png"));

		colorButton = new JButton();
		colorButton.setForeground(StaticStuff.getShapecolor());
		colorButton.setBackground(StaticStuff.getShapecolor());

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

	public void setColor(Color color)
	{
		colorButton.setForeground(color);
		colorButton.setBackground(color);
	}

}
