package com.gmail.lynx7478.yanpclib;

import org.bukkit.Bukkit;
import org.bukkit.Location;


public class YANPC {
	
	private static YANPCHandler handler;
	
	public YANPC()
	{
		handler = null;
        try
        {
            String version = VersionUtils.getVersion();
            String className = "com.gmail.lynx7478.yanpclib.versions.NPC"+version;
            Class<?> cl = Class.forName(className);
            Class<? extends YANPCHandler> pack = cl.asSubclass(YANPCHandler.class);
            YANPCHandler p = pack.newInstance();
            handler = p;
        }
        catch(Throwable t)
        {
        	//TODO: Post a message.
        }
	}
	
	public void spawn(Location loc, String name)
	{
		handler.spawn(loc, name);
	}
	
	public void destroy()
	{
		handler.destroy();
	}
	
	
}

class VersionUtils
{
    public static String getVersion()
    {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf(".") + 1);
    }
}
