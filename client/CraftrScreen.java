package client;
import common.*;

import java.awt.*;
import java.awt.image.*;

public abstract class CraftrScreen
{
	public CraftrCanvas c;
	public CraftrScreen(CraftrCanvas cc)
	{
		c = cc;
	}
	public CraftrScreen()
	{
	
	}
	public abstract void paint(Graphics g, int mmx, int mmy);
}