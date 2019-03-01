package karpuzoglu.enes.com.fastdialog;

public class FolderButton {
    private String id;
    private String name;
    private int index;
    private int drawableId;
    private boolean isActive = true;

    public FolderButton(String id, String name, int index, int drawableId) {
        this.id = id;
        this.name = name;
        this.index = index;
        this.drawableId = drawableId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
