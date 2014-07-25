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
	private Color selectedColor = StaticStuff.shapeColor;
	private JLabel tipText;

	final static Logger logger = Logger.getLogger(ToolsMenu.class);

	public ToolsMenu()
	{
	}

	public void setTools()
	{
		int numberOfColumns = 1; // = 0 many columns as necessary
		int numberOfRows = 0; // 0 = many rows as necessary
		this.setLayout(new GridLayout(numberOfRows, numberOfColumns));

		tipText = new JLabel("Color Choser: ");
		rectangleButton = new JButton(new ImageIcon("images/tools/rectangle.png"));
		circleButton = new JButton(new ImageIcon("images/tools/circle.png"));
		pencilButton = new JButton(new ImageIcon("images/tools/pencil.png"));
		lineButton = new JButton(new ImageIcon("images/tools/line.png"));
		rubberButton = new JButton(new ImageIcon("images/tools/rubber.png"));
		fillClosedShapeButton = new JButton(new ImageIcon("images/tools/fill.png"));

		colorButton = new JButton();
		colorButton.setForeground(selectedColor);
		colorButton.setBackground(selectedColor);

		initColorButtonListener();
		initCircleButtonListener();
		initFillClosedShapeButtonListener();
		initLineButtonListener();
		initPencilButtonListener();
		initRectangleButtonListener();
		initRubberButtonListener();

		this.add(circleButton);
		this.add(rectangleButton);
		this.add(pencilButton);
		this.add(lineButton);
		this.add(rubberButton);
		this.add(fillClosedShapeButton);
		this.add(tipText);
		this.add(colorButton);
	}

	public void initColorButtonListener()
	{
		colorButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				selectedColor = JColorChooser.showDialog(ToolsMenu.this, "Chose color:", selectedColor);
				StaticStuff.shapeColor = selectedColor;
				colorButton.setForeground(selectedColor);
				colorButton.setBackground(selectedColor);
			}
		});
	}

	public void initRectangleButtonListener()
	{
		rectangleButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				logger.debug("rectangle");
			}
		});
	}

	public void initCircleButtonListener()
	{

		circleButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				logger.debug("circle");
			}
		});
	}

	public void initPencilButtonListener()
	{
		pencilButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				logger.debug("pencil");
			}
		});
	}

	public void initLineButtonListener()
	{
		lineButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				logger.debug("line");
			}
		});
	}

	public void initRubberButtonListener()
	{
		rubberButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				logger.debug("rubber");
			}
		});
	}

	public void initFillClosedShapeButtonListener()
	{

		fillClosedShapeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent event)
			{
				logger.debug("fill closed shape");
			}
		});
	}
}
