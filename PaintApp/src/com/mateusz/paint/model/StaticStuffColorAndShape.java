package com.mateusz.paint.model;

import java.awt.Color;
import com.mateusz.paint.enums.ShapeEnum;

public class StaticStuffColorAndShape
{
	private static ShapeEnum shapeType = ShapeEnum.PENCIL;
	private static Color shapeColor = Color.BLACK;

	public static ShapeEnum getShapeType()
	{
		return shapeType;
	}

	public static void setShapeType(ShapeEnum shapeType)
	{
		StaticStuffColorAndShape.shapeType = shapeType;
	}

	public static Color getShapeColor()
	{
		return shapeColor;
	}

	public static void setShapeColor(Color shapeColor)
	{
		StaticStuffColorAndShape.shapeColor = shapeColor;
	}
}
