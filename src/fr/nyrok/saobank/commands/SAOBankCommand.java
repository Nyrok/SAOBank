package fr.nyrok.saobank.commands;

import com.sun.istack.internal.Nullable;
import fr.nyrok.saobank.SAOBank;
import fr.nyrok.saobank.objects.Bank;
import fr.nyrok.saobank.utils.InventorySerializer;
import fr.nyrok.saobank.utils.StorageBank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

final public class SAOBankCommand implements CommandExecutor {
    SAOBank plugin;

    public SAOBankCommand(SAOBank plugin) {
        this.plugin = plugin;
    }

    public String getHelpMessage() {
        return ChatColor.AQUA + "SAO Bank Help:" + "\n" + ChatColor.DARK_AQUA + "- /saobank: Ouvrir sa banque" + "\n" + ChatColor.DARK_AQUA + "- /saobank open [player]: Ouvrir la banque d'un joueur (*)" + "\n" + ChatColor.DARK_AQUA + "- /saobank clear <player>: Clear la banque d'un joueur (*)" + "\n" + ChatColor.DARK_AQUA + "- /saobank add <player> <amount>: Ajouter des slots à un joueur (*)";
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        File playerBank = new File(this.plugin.getDirectory(), ((Player) commandSender).getUniqueId() + ".json");
        Bank bank = this.plugin.getBankSerializer().deserialize(StorageBank.loadContent(playerBank));
        if (args.length == 0) this.plugin.openBank(commandSender, ((Player) commandSender).getUniqueId().toString());
        else {
            List<String> argsList = Arrays.asList(args);
            switch (argsList.get(0)) {
                case "help":
                    commandSender.sendMessage(this.getHelpMessage());
                    break;
                case "open":
                    if (argsList.get(1).isEmpty())
                        this.plugin.openBank(commandSender, ((Player) commandSender).getUniqueId().toString());
                    else {
                        if (!commandSender.isOp()) {
                            commandSender.sendMessage(ChatColor.RED + "Vous devez être opérateur pour ouvrir la bank d'un autre joueur.");
                            return false;
                        }
                        @Nullable OfflinePlayer target = getOfflinePlayer(argsList.get(1));
                        if (target != null) {
                            UUID uuid = target.getUniqueId();
                        } else {
                            commandSender.sendMessage(ChatColor.RED + "Je n'ai pas trouvé le joueur: " + argsList.get(1));
                        }
                    }
                    break;
                case "clear":
                    if (!commandSender.isOp()) {
                        commandSender.sendMessage(ChatColor.RED + "Vous devez être opérateur pour clear la bank d'un autre joueur.");
                        return false;
                    }
                    if (argsList.get(1).isEmpty()){
                        commandSender.sendMessage(ChatColor.RED + "Vous devez mentionner un joueur.");
                        return false;
                    }
                    String clearInvContents = InventorySerializer.inventoryToBase64(Bukkit.createInventory(null, 27, "SAO Bank"));
                    @Nullable OfflinePlayer target = getOfflinePlayer(argsList.get(1));
                    if(target != null){
                        File targetBank = new File(this.plugin.getDirectory(), target.getUniqueId() + ".json");
                    }
                    else {
                        commandSender.sendMessage(ChatColor.RED + "Je n'ai pas trouvé le joueur: " + argsList.get(1));
                    }
                    break;
                default:
                    commandSender.sendMessage(ChatColor.RED + "Argument invalide: " + argsList.get(0));
                    break;
            }
        }
        return true;
    }

    public @Nullable OfflinePlayer getOfflinePlayer(String username){
        @Nullable OfflinePlayer target = null;
        for (OfflinePlayer offlinePlayer : this.plugin.getServer().getOfflinePlayers()) {
            if (!Objects.equals(offlinePlayer.getName(), username)) continue;
            target = offlinePlayer;
            break;
        }
        return target;
    }
}
