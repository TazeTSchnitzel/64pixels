public class CraftrBlock
{
	public int x = 0;
	public int y = 0;
	private byte[] block = new byte[6];
	
	public CraftrBlock()
	{
	}
	public CraftrBlock(int ax, int ay)
	{
		this(ax,ay,0,0,0,0);
	}
	public CraftrBlock(int ax, int ay, int at, int ach, int aco)
	{
		this(ax,ay,at,0,ach,aco);
	}
	public CraftrBlock(int ax, int ay, int at, int ap, int ach, int aco)
	{
		x=ax;
		y=ay;
		block[0]=(byte)at;
		block[1]=(byte)ap;
		if(block[0]==(byte)-1)
		{
			block[4]=(byte)ach;
			block[5]=(byte)aco;
		}
		else
		{
			block[2]=(byte)ach;
			block[3]=(byte)aco;
		}
	}
	public CraftrBlock(int ax, int ay, byte[] bd)
	{
		x=ax;
		y=ay;
		block=bd;
	}

	public boolean isEmpty()
	{
		if(isPushable()) return false;
		if(block[0]==0 || block[0]==2 || block[0]==5 || (block[0]==6 && (0x80&(int)block[1])!=0) || block[0]==8) return true;
		return false;
	}

	public boolean isWiriumNeighbour()
	{
		if((block[0]>=2 && block[0]<=7) || (block[0]>=9 && block[0]<=11)) return true;
		return false;
	}

	public boolean isPushable()
	{
		if(block[5]!=0) return true;
		return false;
	}

	public int getType()
	{
		return 0xFF&(int)block[0];
	}

	public int getTypeWithVirtual()
	{
		if(isPushable()) return -1;
		return 0xFF&(int)block[0];
	}

	public int getParam()
	{
		return 0xFF&(int)block[1];
	}

	public int getChar()
	{
		if(isPushable()) return 0xFF&(int)block[4];
		return 0xFF&(int)block[2];
	}

	public int getColor()
	{
		if(isPushable()) return 0xFF&(int)block[5];
		return 0xFF&(int)block[3];
	}

	public int getBlockChar()
	{
		return 0xFF&(int)block[2];
	}

	public int getBlockColor()
	{
		return 0xFF&(int)block[3];
	}

	public void setType(int type)
	{
		block[0]=(byte)type;
	}

	public void setParam(int param)
	{
		block[1]=(byte)param;
	}
	public void setChar(int ch)
	{
		if(isPushable()) block[4]=(byte)ch;
		else block[2]=(byte)ch;
	}
	public void setColor(int co)
	{
		if(isPushable()) block[5]=(byte)co;
		else block[3]=(byte)co;
	}

	public int getDrawnChar()
	{
		if(getType()==8) return 0xFF&(int)block[2];
		else return getChar();
	}
	public int getDrawnColor()
	{
		if(getType()==8) return 0xFF&(int)block[3];
		else return getColor();
	}

	public byte[] getBlockData()
	{
		return block;
	}
}
