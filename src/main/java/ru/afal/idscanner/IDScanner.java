package ru.afal.idscanner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class IDScanner extends ItemStack {

  public static final IDScanner INSTANCE = new IDScanner();

  private IDScanner() {
    super(Material.BLACK_GLAZED_TERRACOTTA);
    ItemMeta itemMeta = getItemMeta();
    if (itemMeta != null) {
      itemMeta.setDisplayName("ID Scanner"); // TODO: I want cooler name :(
      itemMeta.setLore(Collections.singletonList("You feel as though this item can help you to identify the inhabitants of this realm..."));
      setItemMeta(itemMeta);
    }
  }
}
