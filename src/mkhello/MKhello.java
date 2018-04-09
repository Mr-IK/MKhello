package mkhello;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MKhello extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			if(args.length == 1) {
			        getLogger().info("Consoleに対応してません！");
			        return true;
			}
		}
		Player p = (Player)sender;
		if(args.length == 0) {
			String hello = config1.getString(p.getUniqueId().toString(),"こんにちは！");
			p.chat(hello);
			return true;
		}else if(args.length == 1) {
			String hello = args[0];
			config1.set(p.getUniqueId().toString(), hello);
			p.sendMessage("挨拶を"+hello+"に設定しました");
			saveConfig();
		}
		return true;
	}

	@Override
	public void onDisable() {
		reloadConfig();
		super.onDisable();
	}
	public static FileConfiguration config1;
	@Override
	public void onEnable() {
		getCommand("hi").setExecutor(this);
		saveDefaultConfig();
	    FileConfiguration config = getConfig();
        config1 = config;
		super.onEnable();
	}

}
