package dk.mrspring.commands.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * Created on 05-10-2015 for ConvenientCommands.
 */
public abstract class CommandAttributeBase extends CommandBase
{
    private Map<String, IAttributeHandler> handlers;

    public CommandAttributeBase(IAttributeHandler... handlers)
    {
        this.handlers = new HashMap<String, IAttributeHandler>();
        for (IAttributeHandler handler : handlers)
            if (handler != null) this.handlers.put(handler.getName(), handler);
    }

    public abstract String getCommandFailure(ICommandSender sender);

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] arguments)
    {
        Collection<String> attributeCollection = handlers.keySet();
        String[] attributes = attributeCollection.toArray(new String[attributeCollection.size()]);
        System.out.println(arguments.length + ", " + Arrays.toString(arguments));
        if (arguments.length <= 1)
            return Arrays.asList(ArrayUtils.add(attributes, "-p"));
        else if (arguments.length == 2)
        {
            if (arguments[0].equals("-p"))
                return getListOfStringsMatchingLastWord(arguments, MinecraftServer.getServer().getAllUsernames());
        } else if (arguments.length == 3) return Arrays.asList(attributes);
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] arguments)
    {
        if (arguments.length > 0)
        {
            EntityPlayerMP player;
            int sub = 0;
            if ("-p".equalsIgnoreCase(arguments[0]))
            {
                if (arguments.length <= 2)
                    throw new WrongUsageException(getCommandUsage(sender));
                player = getPlayer(sender, arguments[1]);
                sub += 2;
            } else player = getCommandSenderAsPlayer(sender);
            String attribute = arguments[sub];
            IAttributeHandler handler = handlers.get(attribute);
            if (handler == null)
                throw new CommandException(getCommandFailure(sender), attribute);
            this.doFromCommand(sender, player, handler, ArrayUtils.subarray(arguments, sub + 1, arguments.length));
        } else throw new WrongUsageException(getCommandUsage(sender));
    }

    public Collection<IAttributeHandler> listHandlers()
    {
        return handlers.values();
    }

    public abstract void doFromCommand(ICommandSender sender, EntityPlayerMP player, IAttributeHandler handler, String[] arguments);
}
