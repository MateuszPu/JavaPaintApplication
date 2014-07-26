package com.mateusz.paint.model;

import java.awt.Color;
import com.mateusz.paint.enums.ShapeEnum;

public class StaticStuff
{

	private static Color shapeColor = Color.GREEN;
	private static ShapeEnum shape = ShapeEnum.RECTANGLE;

	public StaticStuff()
	{
	}

	public void setColor(Color color)
	{
		StaticStuff.shapeColor = color;
	}

	public static Color getShapecolor()
	{
		return shapeColor;
	}

	public void setShape(ShapeEnum shape)
	{
		StaticStuff.shape = shape;
	}

	public static ShapeEnum getShape()
	{
		return shape;
	}

}
