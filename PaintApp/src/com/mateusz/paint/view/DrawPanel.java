package com.mateusz.paint.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import com.mateusz.paint.enums.ShapeEnum;

public class DrawPanel extends JPanel
{

	public DrawPanel()
	{
		setOpaque(true);
		setBackground(Color.WHITE);
	}
}