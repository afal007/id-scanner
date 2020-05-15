package ru.afal.idscanner;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class IDScannerPlugin extends JavaPlugin {

  private final Logger LOGGER;

  public IDScannerPlugin() {
    super();
    this.LOGGER = getLogger();
  }

  @Override
  public void onEnable() {
    LOGGER.info("Plugin has started!");

    PluginCommand idscanner = getCommand("idscanner");
    if (idscanner == null) {
      LOGGER.warning("Command scan was not registered");
      setEnabled(false);
      return;
    }

    idscanner.setExecutor(new IDScannerCommandExecutor(LOGGER));
    getServer().getPluginManager().registerEvents(new ScanEventListener(), this);
  }

  @Override
  public void onDisable() {
    LOGGER.info("Plugin has stopped!");
  }
}
