package pl.godzina.avilon.menus;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.user.User;
import pl.godzina.avilon.helpers.ChatHelper;

import java.util.Arrays;

public class EnderchestMenus {
    private final AvilonPlugin plugin;

    public EnderchestMenus(AvilonPlugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player p) {
        Gui gui = new Gui(6, ChatHelper.fixColor("&8>> &dEnderchesty"));

        User user = this.plugin.getUserManager().getUser(p);

        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15)));



;
        user.getCec().forEach(enderchest -> {
            ItemStack guiItem = ItemBuilder.from(Material.ENDER_CHEST).setName(ChatHelper.fixColor("&d&l" + enderchest.getName()))
                    .setLore(ChatHelper.fixColor(Arrays.asList("",
                            " &8>> &fStatus Enderchesta: " + (p.hasPermission(enderchest.getPermission()) ? "&a&lDOSTEPNY" : "&cNIEDOSTEPNY"), " &8>> &fMiejsc: &a&l54", " &8>> &fOpis: &d" + enderchest.getLore(), "", "&8>> &fKliknij &a&lLPM &faby wejsc do enderchesta!", "&8>> &fKliknij &a&lPPM &faby ustawic opis!"))).build();
            gui.setItem(enderchest.getSlots(), ItemBuilder.from(guiItem).asGuiItem(event -> {
                if (event.getClick() == ClickType.LEFT) {
                    if (p.hasPermission(enderchest.getPermission())) {
                        user.getEnderchest(ChatColor.stripColor(guiItem.getItemMeta().getDisplayName())).openEnderchest();
                    } else {
                        p.closeInventory();
                        ChatHelper.sendMessage(p, "&cNie mozesz otworzyc tego enderchesta.");
                    }
                } else {
                    new AnvilGUI.Builder()
                            .onComplete((player, text) -> {
                                enderchest.setLore(text);
//                                enderchest.setNeedUpdate(true);
                                open(p);
                                return AnvilGUI.Response.close();
                            })
                            .text("Wprowadz opis")
                            .title("Wprowadz opis")
                            .plugin(plugin)
                            .open(p);
                }
            }));
        });

        gui.setDefaultClickAction(event -> event.setCancelled(true));
        gui.open(p);
    }

}
