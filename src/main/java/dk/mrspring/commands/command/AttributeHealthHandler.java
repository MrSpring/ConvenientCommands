package dk.mrspring.commands.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created on 04-10-2015 for TheKitchenMod.
 */
public class AttributeHealthHandler implements IAttributeHandler
{
    @Override
    public String getName()
    {
        return "health";
    }

    @Override
    public String getSetDescription(ICommandSender sender)
    {
        return "Set the health of the player";
    }

    @Override
    public String getGetDescription(ICommandSender sender)
    {
        return "Get the health of the player";
    }

    @Override
    public boolean setAttribute(CommandSetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments)
    {
        int setting = CommandBase.parseIntBounded(sender, arguments[0], 0, (int) player.getMaxHealth());
        player.setHealth(setting);
        return true;
    }

    @Override
    public Object getAttribute(CommandGetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments)
    {
        return player.getHealth();
    }
}
