package de.pandoracity.xerolo;

import de.pandoracity.xerolo.commands.LoadoutCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
    getCommand("loadout").setExecutor(new LoadoutCommands(this));
    }

    @Override
    public void onDisable()
    {

    }
}
