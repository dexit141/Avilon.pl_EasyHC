package pl.godzina.avilon.basic.drop;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import pl.godzina.avilon.AvilonPlugin;
import pl.godzina.avilon.basic.configuration.DropSettings;

@Getter
@Setter
public class Drop {
    private final String name;
    private final double chance;
    private final int exp;
    private final String message;
    private final boolean fortune;
    private final int minHeight;
    private final int maxHeight;
    private final int minAmount;
    private final int maxAmount;
    private final Material what;
    private final Set<UUID> disabled;

    public Drop(String name) {
        this.disabled = new HashSet<>();
        this.name = name;
        this.chance = DropSettings.getConfig().getDouble("random-drops." + name + ".chance");
        this.exp = DropSettings.getConfig().getInt("random-drops." + name + ".exp");
        this.message = DropSettings.getConfig().getString("random-drops." + name + ".message");
        this.fortune = DropSettings.getConfig().getBoolean("random-drops." + name + ".fortune");
        this.minHeight = DropSettings.getConfig().getInt("random-drops." + name + ".height.min");
        this.maxHeight = DropSettings.getConfig().getInt("random-drops." + name + ".height.max");
        this.minAmount = DropSettings.getConfig().getInt("random-drops." + name + ".amount.min");
        this.maxAmount = DropSettings.getConfig().getInt("random-drops." + name + ".amount.max");
        this.what = Material.valueOf(DropSettings.getConfig().getString("random-drops." + name + ".drop.what"));
    }

    public void changeStatus(UUID uuid) {
        if (this.disabled.contains(uuid)) {
            this.disabled.remove(uuid);
        } else {
            this.disabled.add(uuid);
        }
    }

    public void setStatus(UUID uuid, boolean b) {
        if (b) {
            this.disabled.remove(uuid);
        } else {
            this.disabled.add(uuid);
        }
    }

    public boolean isDisabled(UUID uuid) {
        return this.disabled.contains(uuid);
    }

    public Set<UUID> getDisabled() {
        return this.disabled;
    }
}
