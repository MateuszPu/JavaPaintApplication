package com.mateusz.paint.model;

import java.util.List;

public class Model
{
	private DrawingsEdit drawingsEdit;
	private TopMenuEdit topMenuEdit;

	public Model()
	{
		setDrawingsEdit(new DrawingsEdit());
		setTopMenuEdit(new TopMenuEdit());
	}

	public DrawingsEdit getDrawingsEdit()
	{
		return drawingsEdit;
	}

	public void setDrawingsEdit(DrawingsEdit drawingsEdit)
	{
		this.drawingsEdit = drawingsEdit;
	}

	public TopMenuEdit getTopMenuEdit()
	{
		return topMenuEdit;
	}

	public void setTopMenuEdit(TopMenuEdit topMenuEdit)
	{
		this.topMenuEdit = topMenuEdit;
	}

}
