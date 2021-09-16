package pl.godzina.avilon.menus;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;

import java.util.Arrays;

public class DropMenus {
    private final AvilonPlugin plugin;

    public DropMenus(AvilonPlugin plugin) {
        this.plugin = plugin;
    }


    public void openMain(Player p) {
        Gui gui = new Gui(6, ChatHelper.fixColor("&8>> &dDrop"));

        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15)));

        gui.setItem(20, ItemBuilder.from(Material.STONE).setName(ChatHelper.fixColor("&8>> &fDrop z kamienia.")).asGuiItem(event -> {
            openStone(p);
        }));
        gui.setItem(22, ItemBuilder.from(Material.ENDER_CHEST).setName(ChatHelper.fixColor("&8>> &fDrop z case")).asGuiItem());
        gui.setItem(24, ItemBuilder.from(Material.MOSSY_COBBLESTONE).setName(ChatHelper.fixColor("&8>> &fDrop z cobblex")).asGuiItem());
        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });
        gui.open(p);
    }

    public void openStone(Player p) {
        Gui gui = new Gui(6, ChatHelper.fixColor("&d&lDrop &8-> &dDrop Stone"));

        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15)));
        User user = AvilonPlugin.getInstance().getUserManager().getUser(p);


        this.plugin.getDropManager().getDrops().forEach(drop -> {
            gui.addItem(ItemBuilder.from(drop.getWhat()).setName(ChatHelper.fixColor("&d&l " + drop.getName()))
                            .setLore(ChatHelper.fixColor(Arrays.asList(" ",
                                    "&8>> &fSzansa na wypadnięcie &d" + drop.getChance(), "&f%",
                                    "&8>> &fFortuna: " + (drop.isFortune() ? "&aON" : "&cOFF"),
                                    "&8>> &fAktywny: " + (user.isDisabledDrop(drop) ? "&aTAK" : "&CNIE"),
                                    "&8>> &fWypada pomiędzy &d" + drop.getMinHeight(), "&f, a &d" + drop.getMaxHeight(),
                                    "&fKliknij &a&lLPM &faby &awłączyć&8/&cwyłączyć &fdrop")))
                    .asGuiItem(event -> {
                        if (user.isDisabledDrop(drop)) {
                            user.removeDisabledDrop(drop);
                        } else {
                            user.addDisabledDrop(drop);
                        }
                        openStone(p);
                    }));
        });
        gui.setItem(37, ItemBuilder.from(new ItemStack(Material.getMaterial(351), 1, (short) 10)).setName(ChatHelper.fixColor("&8>> &aKliknij aby włączyć wszystkie dropy!")).glow(true).asGuiItem(event -> {
            this.plugin.getDropManager().getDrops().forEach(drop -> {
                user.removeDisabledDrop(drop);
            });
                }));
        gui.setItem(38, ItemBuilder.from(new ItemStack(Material.getMaterial(351), 1, (short) 1)).setName(ChatHelper.fixColor("&8>> &cKliknij aby wyłączyć wszystkie dropy!")).glow(true).asGuiItem(event -> {
            this.plugin.getDropManager().getDrops().forEach(drop -> {
                user.addDisabledDrop(drop);
            });
        }));
        gui.setItem(43, ItemBuilder.from(Material.COBBLESTONE).setName(ChatHelper.fixColor("&8>> &FKliknij aby &7zmienić &fstatus cobblestone")).setLore(ChatHelper.fixColor("&fAktualny &7status&f cobbla " + (user.isCobbleDrop() ? "&aON" : "&cOFF"))).asGuiItem(event -> {
            user.setCobbleDrop(!user.isCobbleDrop());
        }));
        gui.setItem(49, ItemBuilder.from(Material.BARRIER).setName(ChatHelper.fixColor("&cPowrót")).asGuiItem(event -> {
            openMain(p);
        }));
        gui.setDefaultClickAction(event -> {
            event.setCancelled(true);
        });
        gui.open(p);
    }
}
