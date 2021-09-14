package pl.godzina.avilon.menus;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.godzina.avilon.helpers.ChatHelper;

public class EffectMenus {
    public static void open(Player p) {

        Gui gui = new Gui(6, ChatHelper.fixColor("&8>> &d&lEfekty"));
        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7)));
        gui.setItem(10,
                ItemBuilder.from(Material.WOOD_PICKAXE)
                        .setName(ChatHelper.fixColor("&dHaste 1."))
                        .setLore(ChatHelper.fixColor("&8&m[------[-----&r &dHASTE 1 &8&m]------]-----]"))
                        .setLore(ChatHelper.fixColor(""))
                        .setLore(ChatHelper.fixColor("&8>> &fCzas trwania: &d5min."))
                        .setLore(ChatHelper.fixColor("&8>> &fKoszt: &d&l16 LAPIS BLOCK"))
                        .setLore(ChatHelper.fixColor(""))
                        .setLore(ChatHelper.fixColor("&8&m[------[-----&r &dHASTE 1 &8&m]------]-----]"))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 16), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 16));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 300, 1));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dHaste 1");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(13,
                ItemBuilder.from(Material.SUGAR)
                        .setName(ChatHelper.fixColor("&dSpeed 1."))
                        .setLore(ChatHelper.fixColor("&8&m[------[-----&r &dSPEED 1&8&m]------]-----]"))
                        .setLore(ChatHelper.fixColor(""))
                        .setLore(ChatHelper.fixColor("&8>> &fCzas trwania: &d5min."))
                        .setLore(ChatHelper.fixColor("&8>> &fKoszt: &d&l6 LAPIS BLOCK"))
                        .setLore(ChatHelper.fixColor(""))
                        .setLore(ChatHelper.fixColor("&8&m[------[-----&r &dSPEED 1 &8&m]------]-----]"))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 6), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 6));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 300, 1));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dSpeed 1");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(16,
                ItemBuilder.from(Material.CARROT)
                        .setName(ChatHelper.fixColor("&dJump Boost 1."))
                        .setLore(ChatHelper.fixColor("&8&m[------[-----&r &dJUMP BOOST 1 &8&m]------]-----]"))
                        .setLore(ChatHelper.fixColor(""))
                        .setLore(ChatHelper.fixColor("&8>> &fCzas trwania: &d5min."))
                        .setLore(ChatHelper.fixColor("&8>> &fKoszt: &d&l7 LAPIS BLOCK"))
                        .setLore(ChatHelper.fixColor(""))
                        .setLore(ChatHelper.fixColor("&8&m[------[-----&r &dJUMP BOOST 1 &8&m]------]-----]"))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 7), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 7));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 300, 1));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dJUMP BOOST");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.open(p);

    }
}

