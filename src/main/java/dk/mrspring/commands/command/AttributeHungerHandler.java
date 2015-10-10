package dk.mrspring.commands.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created on 04-10-2015 for TheKitchenMod.
 */
public class AttributeHungerHandler implements IAttributeHandler
{
    @Override
    public String getName()
    {
        return "hunger";
    }

    @Override
    public String getSetDescription(ICommandSender sender)
    {
        return "Set the hunger of a player";
    }

    @Override
    public String getGetDescription(ICommandSender sender)
    {
        return "Get the hunger of a player";
    }

    @Override
    public boolean setAttribute(CommandSetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments)
    {
        int setting = CommandBase.parseInt(sender, arguments[0]);
        player.getFoodStats().setFoodLevel(setting);
        return true;
    }

    @Override
    public Object getAttribute(CommandGetAttribute command, ICommandSender sender, EntityPlayerMP player, String[] arguments)
    {
        return player.getFoodStats().getFoodLevel();
    }
}
