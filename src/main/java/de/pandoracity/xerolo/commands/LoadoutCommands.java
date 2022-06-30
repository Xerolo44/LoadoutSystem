package de.pandoracity.xerolo.commands;

import de.pandoracity.xerolo.Main;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class LoadoutCommands implements CommandExecutor {

    private Main plugin;

    public LoadoutCommands(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            FileConfiguration config = plugin.getConfig();

            plugin.saveConfig();

            PlayerInventory pI = ((Player) sender).getInventory();
            //evtl. in case umwandeln
            if (args.length == 0){
                p.sendMessage("§7[§2Loadout§7] Bitte verwende §6/loadout create §7| §6/loadout load §7| §6/loadout delete");

            }else {

                if (args[0].equalsIgnoreCase("create")) {
                    //Wenn der Player ein Loadout erstellen will


                    if (args.length == 2) {

                        String loadoutName = args[1];

                        if (!config.contains("Loadout." + p.getName() + loadoutName)) {

                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Helmet", pI.getHelmet());
                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Chestplate", pI.getChestplate());
                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Leggings", pI.getLeggings());
                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Boots", pI.getBoots());

                            //falls net geht for schleife mit itemstack array
                            config.set("Loadout." + p.getName() + loadoutName + ".Inventory", pI.getStorageContents());

                            plugin.saveConfig();

                            p.sendMessage("§7[§2Loadout§7] Das Loadout mit dem Namen §a" + loadoutName + "§7 wurde erstellt");

                        } else {
                            p.sendMessage("§7[§2Loadout§7] Dieses Loadout existiert bereits");
                        }
                    } else {
                        p.sendMessage("§7[§2Loadout§7] Bitte verwende §6/loadout create <name>");
                    }


                } else if (args[0].equalsIgnoreCase("load")) {

                    if (args.length == 2) {

                        String loadoutName = args[1];

                        if (config.contains("Loadout." + p.getName() + loadoutName)) {

                            pI.setHelmet((ItemStack) config.get("Loadout." + p.getName() + loadoutName + ".Armor" + ".Helmet"));
                            pI.setChestplate((ItemStack) config.get("Loadout." + p.getName() + loadoutName + ".Armor" + ".Chestplate"));
                            pI.setLeggings((ItemStack) config.get("Loadout." + p.getName() + loadoutName + ".Armor" + ".Leggings"));
                            pI.setBoots((ItemStack) config.get("Loadout." + p.getName() + loadoutName + ".Armor" + ".Boots"));
                            pI.setStorageContents((ItemStack[]) config.get("Loadout." + p.getName() + loadoutName + ".Inventory"));

                        } else {
                            p.sendMessage("§7[§2Loadout§7] Dieses Loadout existiert nicht");
                        }

                    } else {
                        p.sendMessage("§7[§2Loadout§7] Bitte verwende §6/loadout load <name>");


                    }


                } else if (args[0].equalsIgnoreCase("delete")) {

                    if (args.length == 2) {

                        String loadoutName = args[1];

                        if (config.contains("Loadout." + p.getName() + loadoutName)) {

                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Helmet", null);
                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Chestplate", null);
                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Leggings", null);
                            config.set("Loadout." + p.getName() + loadoutName + ".Armor" + ".Boots", null);
                            config.set("Loadout." + p.getName() + loadoutName + ".Inventory", null);
                            config.set("loadout." + p.getName() + loadoutName, null);


                            plugin.saveConfig();

                        } else {
                            p.sendMessage("§7[§2Loadout§7] Dieses Loadout existiert nicht");
                        }

                    } else {
                        p.sendMessage("§7[§2Loadout§7] Bitte verwende §6/loadout delete <name>");


                    }


                } else {
                    p.sendMessage("§7[§2Loadout§7] Bitte verwende §6/loadout create §7| §6/loadout load §7| §6/loadout delete");
                }
            }
        }

        return false;
    }
}
