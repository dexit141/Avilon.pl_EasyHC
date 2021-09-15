package pl.godzina.avilon.menus;

import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.godzina.avilon.helpers.ChatHelper;

public class DropMenus {
    public void open(Player p) {
        Gui gui = new Gui(6, ChatHelper.fixColor("&8>> &dDrop"));

        gui.getFiller().fillBorder(new GuiItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15)));

        gui.setItem(20, ItemBuilder.from(Material.STONE).setName(ChatHelper.fixColor("&8>> &fDrop z kamienia.")).asGuiItem(event -> {

        }));
    }
}
