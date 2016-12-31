package com.gmail.lynx7478.yanpclib.versions;

import java.util.UUID;

import net.minecraft.server.v1_8_R2.EntityPlayer;
import net.minecraft.server.v1_8_R2.MinecraftServer;
import net.minecraft.server.v1_8_R2.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R2.PlayerConnection;
import net.minecraft.server.v1_8_R2.PlayerInteractManager;
import net.minecraft.server.v1_8_R2.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.CraftServer;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.gmail.lynx7478.yanpclib.YANPCHandler;
import com.mojang.authlib.GameProfile;

public class NPCv1_8_R2 implements YANPCHandler {

    private EntityPlayer entity;

    // Constructor.
    public NPCv1_8_R2()
    {
    	//TODO:
    	// We should probably make the NPC here, still haven't figured out how without breaking the whole thing.
    	// Any ideas?
    }

    public EntityPlayer getEntity()
    {
        return entity;
    }

	@Override
	public void spawn(Location loc, String name) 
	{
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        entity = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(nmsWorld));
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        for(Player p : Bukkit.getOnlinePlayers())
        {
            PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entity));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(entity));
        }
		
	}

	@Override
	public void destroy() 
	{
        for(Player p : Bukkit.getOnlinePlayers())
        {
            PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entity));
            connection.sendPacket(new PacketPlayOutEntityDestroy(entity.getId()));
            entity = null;
        }
	}

}
