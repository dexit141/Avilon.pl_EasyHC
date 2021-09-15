package pl.godzina.avilon.basic.needsave;

public abstract class NeedSaveSystem {
    private boolean needSave = false;

    public boolean isNeedSave() {
        return this.needSave;
    }

    public void setNeedSave(boolean needSave) {
        this.needSave = needSave;
    }
}
