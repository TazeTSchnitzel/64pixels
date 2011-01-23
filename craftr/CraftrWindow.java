import java.lang.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class CraftrWindow
{
	public int type;
	public int w,h;
	public String title;
	public static int[] linechr; 
	public int x,y;
	public int charChosen, colorChosen;
	public byte[] recBlockChr;
	public byte[] recBlockCol;
	public byte[] recBlockType;
	
	public CraftrWindow(int _type)
	{
		type = _type;
		recBlockChr = new byte[16];
		recBlockCol = new byte[16];
		recBlockType = new byte[16];
	}
	
	public void resize()
	{
		switch(type)
		{
			case 1: // char screen
				w = 34;
				h = 10;
				title = "Choose char:";
				break;
			case 2: // color screen
				w = 18;
				h = 18;
				title = "Color:";
				break;
			case 3: // recent block screen
				w = 12;
				h = 12;
				title = "History";
				break;
			default:
				w = 8;
				h = 3;
				title = "ERROR!";
				break;
		}
		x = 32-(w>>1);
		y = 25-(h>>1);
	}
	
	static
	{
		linechr = new int[] { 218, 191, 192, 217, 196, 179 }; // border NW, NE, SW, SE, horiz, very
	}
	
	public void addRecBlock(byte t, byte ch, byte co)
	{
		int len=15;
		if(recBlockChr[0]==ch&&recBlockCol[0]==co&&recBlockType[0]==t)return;
		for(int i=1;i<15;i++)
		{
			if(recBlockChr[i]==ch&&recBlockCol[i]==co&&recBlockType[i]==t)
			{
				len=i;
				break;
			}
		}
		System.arraycopy(recBlockChr,0,recBlockChr,1,len);
		System.arraycopy(recBlockCol,0,recBlockCol,1,len);
		System.arraycopy(recBlockType,0,recBlockType,1,len);
		recBlockChr[0]=ch;
		recBlockCol[0]=co;
		recBlockType[0]=t;
	}
	
	public void makeWindow(CraftrCanvas cc, Graphics g)
	{
		for(int i=(x+1)<<3;i<=(x+w-2)<<3;i+=8)
		{
			cc.DrawChar1x(i,y<<3,(byte)linechr[4],(byte)143,g);
			cc.DrawChar1x(i,(y+h-1)<<3,(byte)linechr[4],(byte)143,g);
		}
		for(int i=(y+1)<<3;i<=(y+h-2)<<3;i+=8)
		{
			cc.DrawChar1x(x<<3,i,(byte)linechr[5],(byte)143,g);
			cc.DrawChar1x((x+w-1)<<3,i,(byte)linechr[5],(byte)143,g);
		}
		cc.DrawChar1x(x<<3,y<<3,(byte)linechr[0],(byte)143,g);
		cc.DrawChar1x((x+w-1)<<3,y<<3,(byte)linechr[1],(byte)143,g);
		cc.DrawChar1x(x<<3,(y+h-1)<<3,(byte)linechr[2],(byte)143,g);
		cc.DrawChar1x((x+w-1)<<3,(y+h-1)<<3,(byte)linechr[3],(byte)143,g);
		cc.DrawChar1x((x+w-1)<<3,y<<3,(byte)'X',(byte)143,g);
		cc.DrawString1x((x+1)<<3,y<<3,title,(byte)143,g);
		g.setColor(new Color(cc.palette[8]));
		g.fillRect((x+1)<<3,(y+1<<3),(w-2)<<3,(h-2)<<3);
	}
	
	public boolean insideRect(int mx, int my, int x, int y, int w, int h)
	{
		if(mx >= x && my >= y && mx < x+w && my < y+h)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public void render(CraftrCanvas cc, Graphics g)
	{
		resize();
		makeWindow(cc,g);
		int fx = (x+1)<<3;
		int fy = (y+1)<<3;
		switch(type)
		{
			case 1: // char screen
				for(int i=0;i<256;i++)
				{
					cc.DrawChar1x(fx+((i&31)<<3),fy+((i>>5)<<3),(byte)i,(byte)143,g);
					if(i==charChosen)
					{
						g.setColor(new Color(0xAAAAAA));
						g.drawRect(fx+((i&31)<<3),fy+((i>>5)<<3),7,7);
					}
					String t = "" + (charChosen&0xFF);
					cc.DrawString1x((x+w-1-t.length())<<3,(y+h-1)<<3,t,142,g);
				}
				break;
			case 2: // color screen
				for(int i=0;i<256;i++)
				{
					cc.DrawChar1x(fx+((i&15)<<3),fy+((i>>4)<<3),(byte)254,(byte)i,g);
					if(i==colorChosen)
					{
						g.setColor(new Color(0xAAAAAA));
						g.drawRect(fx+((i&15)<<3),fy+((i>>4)<<3),7,7);
					}
					String t = "" + (colorChosen&0xFF);
					cc.DrawString1x((x+w-1-t.length())<<3,(y+h-1)<<3,t,142,g);
				}
				break;
			case 3: // recent blocks screen
				for(int i=0;i<16;i++)
				{
					int tmx = fx+8+((i&3)<<4);
					int tmy = fy+8+((i>>2)<<4);
					cc.DrawChar(tmx,tmy,recBlockChr[i],recBlockCol[i],g);
					if(insideRect(cc.mx,cc.my,tmx,tmy,16,16))
					{
						g.setColor(new Color(0xAAAAAA));
						g.drawRect(tmx,tmy,15,15);
					}
				}
				break;
			default:
				break;
		}
	}
	
}