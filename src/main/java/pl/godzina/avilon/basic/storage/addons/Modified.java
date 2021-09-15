package pl.godzina.avilon.basic.storage.addons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Modified {
    private boolean needUpdate = false, needInsert = false;
}
