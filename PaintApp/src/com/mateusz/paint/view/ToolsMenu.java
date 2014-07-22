package com.mateusz.paint.view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.mateusz.paint.model.StaticStuff;

public class ToolsMenu extends JPanel
{

	private JButton rectangle;
	private JButton circle;
	private JButton pencil;
	private JButton line;
	private JButton rubber;
	private JButton fillClosedShape;

	private JButton colorButton;
	private Color c = StaticStuff.shapeColor;

	public ToolsMenu(MainFrame mf)
	{

		// GroupLayout layout = new GroupLayout(toolPanel);
		// toolPanel.setLayout(layout);

		colorButton = new JButton("   ");
		rectangle = new JButton(new ImageIcon("images/tools/rectangle.png"));
		circle = new JButton(new ImageIcon("images/tools/circle.png"));
		pencil = new JButton(new ImageIcon("images/tools/pencil.png"));
		line = new JButton(new ImageIcon("images/tools/line.png"));
		rubber = new JButton(new ImageIcon("images/tools/rubber.png"));
		fillClosedShape = new JButton(new ImageIcon("images/tools/fill.png"));

		colorButton.setForeground(c);
		colorButton.setBackground(c);
		this.add(colorButton);
		this.add(circle);
		this.add(rectangle);
		this.add(pencil);
		this.add(line);
		this.add(rubber);
		this.add(fillClosedShape);

	}

}
