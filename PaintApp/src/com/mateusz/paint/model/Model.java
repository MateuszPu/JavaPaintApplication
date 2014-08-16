package com.mateusz.paint.model;

import java.util.List;

public class Model
{
	private DrawingsEdit drawingsEdit;

	public Model()
	{
		setDrawingsEdit(new DrawingsEdit());
	}

	public DrawingsEdit getDrawingsEdit()
	{
		return drawingsEdit;
	}

	public void setDrawingsEdit(DrawingsEdit drawingsEdit)
	{
		this.drawingsEdit = drawingsEdit;
	}
}
