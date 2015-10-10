package dk.mrspring.commands.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created on 05-10-2015 for ConvenientCommands.
 */
public interface IAttributeHandler
{
    String getName();

    String getSetDescription(ICommandSender sender);

    String getGetDescription(ICommandSender sender);

    boolean setAttribute(CommandSetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments);

    Object getAttribute(CommandGetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments);
}
