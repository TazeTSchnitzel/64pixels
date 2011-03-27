package common;

public class CraftrVersion
{
	private static final int protocolVer = 257;
	private static final int releaseVer = 0;
	private static final int majorVer = 1;
	private static final int minorVer = 1;
	private static final int patchVer = 1;
	public CraftrVersion()
	{
	}
	public static int getProtocolVersion()
	{
		return protocolVer;
	}
	public static String getVersionName()
	{
		String temp = "" + releaseVer + "." + majorVer;
		if(minorVer != 0 || patchVer != 0)
		{
			temp += "." + minorVer;
			if(patchVer!=0) temp += "." + patchVer;
		}
		return temp;
	}
}
