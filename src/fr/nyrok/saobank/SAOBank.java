package fr.nyrok.saobank;

import com.sun.istack.internal.Nullable;
import fr.nyrok.saobank.commands.SAOBankCommand;
import fr.nyrok.saobank.utils.BankSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class SAOBank extends JavaPlugin {
    private BankSerializer bankSerializer;
    private File directory;

    @Override
    public void onEnable() {
        this.bankSerializer = new BankSerializer();
        this.directory = new File("/world/sao/bank");
        Objects.requireNonNull(this.getCommand("saobank")).setExecutor(new SAOBankCommand(this));
    }

    public File getDirectory() {
        return directory;
    }

    public BankSerializer getBankSerializer() {
        return this.bankSerializer;
    }

    public void openBank(CommandSender commandSender, @Nullable String targetUuid){
        Player player = this.getServer().getPlayer(commandSender.getName());
        if(targetUuid != null){

        }
        else {

        }
    }
}
