package pl.godzina.avilon.menus;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.helpers.ChatHelper;

import java.util.Arrays;

public class EffectMenus {
    private final AvilonPlugin plugin;

    public EffectMenus(AvilonPlugin plugin) {
        this.plugin = plugin;
    }
    public void open(Player p) {

        Gui gui = new Gui(6, ChatHelper.fixColor("&8>> &d&lEfekty"));
        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7)));
        gui.setItem(10,
                ItemBuilder.from(Material.WOOD_PICKAXE)
                        .setName(ChatHelper.fixColor("&dHaste 1."))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dHASTE 1&8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L1 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dHASTE 1 &8&m]------]-----]")))
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
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dSPEED 1 &8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L6 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dSPEED 1 &8&m]------]-----]")))
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
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dJUMP BOOST 1 &8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L7 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dJUMP BOOST 1 &8&m]------]-----]")))
                        .asGuiItem());
//                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 7), 1)) {
//                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 7));
//                                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 300, 1));
//                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dJUMP BOOST");
//                            }else{
//                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
//                            }

        gui.setItem(19,
                ItemBuilder.from(Material.IRON_PICKAXE)
                        .setName(ChatHelper.fixColor("&dHaste 2"))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dHASTE 2 &8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L8 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dHASTE 2 &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 8), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 8));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 300, 2));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dHASTE");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(21,
                ItemBuilder.from(Material.SUGAR)
                        .setName(ChatHelper.fixColor("&dSpeed 2"))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dSPEED 2 &8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L10 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dSPEED 2 &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 10), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 10));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 300, 2));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dSPEED");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(23,
                ItemBuilder.from(Material.CARROT)
                        .setName(ChatHelper.fixColor("&dJump Boost 2."))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dJUMP BOOST 2 &8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L12 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dJUMP BOOST 2 &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 12), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 12));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 300, 2));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dJUMP BOOST");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(25,
                ItemBuilder.from(Material.CARROT)
                        .setName(ChatHelper.fixColor("&dHaste 3"))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dHASTE 3&8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L12 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dHASTE 3 &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 12), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 12));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 300, 3));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dHASTE");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(27,
                ItemBuilder.from(Material.MILK_BUCKET)
                        .setName(ChatHelper.fixColor("&dCzyszczenie wszystkich efektów."))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dCZYSZCZENIE EFEKTÓW &8&m]------]-----]",
                                "",
                                "&a&lKliknij aby wyczyścić wszystkie efekty.",
                                "&fKoszt: &d24 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dCZYSZCZENIE EFEKTÓW &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 24), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 24));
                                p.getActivePotionEffects().removeAll(p.getActivePotionEffects());
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles czyszczenie efektów");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(29,
                ItemBuilder.from(Material.CARROT)
                        .setName(ChatHelper.fixColor("&dWidzenie w ciemnosci."))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dWIDZENIE W CIEMNOSCI&8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fKoszt &d&L2 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dJUMP BOOST 1 &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 2), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 2));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 300, 1));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles efekt &dWIDZENIE W CIEMNOSCI");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(31,
                ItemBuilder.from(Material.DIAMOND_PICKAXE)
                        .setName(ChatHelper.fixColor("&fPakiet efektów: &dKOPACZ"))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dPAKIET KOPACZ &8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fEfekty: SPEED 2, HASTE 3, WIDZENIE W CIEMNOSCI",
                                "&8>> &fKoszt &d&L32 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dPAKIET KOPACZ &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 32), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 32));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 300, 2));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 300, 3));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 300, 1));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles pakiet efektów &dKOPACZ");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setItem(36,
                ItemBuilder.from(Material.GOLDEN_CARROT)
                        .setName(ChatHelper.fixColor("&fPakiet efektów: &dUCIEKINIER"))
                        .setLore(ChatHelper.fixColor(Arrays.asList(
                                "&8&m[------[-----&r &dPAKIET UCIEKINIER &8&m]------]-----]",
                                "",
                                "&8>> &fCzas trwania: &d5min.",
                                "&8>> &fEfekty: SPEED 2, HASTE 3, JUMP BOOST 2",
                                "&8>> &fKoszt &d&L40 LAPIS BLOCK",
                                "",
                                "&8&m[------[-----&r &dPAKIET UCIEKINIER &8&m]------]-----]")))
                        .asGuiItem(event -> {
                            if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_BLOCK, 40), 1)) {
                                p.getInventory().removeItem(new ItemStack(Material.LAPIS_BLOCK, 40));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 300, 2));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 300, 3));
                                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 300, 2));
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fPomyslnie zakupiles pakiet efektów &dUCIEKINIER");
                            }else{
                                ChatHelper.sendMessage(p, "&d&lAvilon &8>> &fNie posiadasz wymaganych przedmiotów");
                            }
                        }));
        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });
        gui.open(p);

    }
}

