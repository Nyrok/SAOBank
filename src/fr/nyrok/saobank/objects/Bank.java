package fr.nyrok.saobank.objects;

import java.util.UUID;

final public class Bank {
    private final UUID uuid;
    private String contents;
    private int size;

    public Bank(UUID uuid, String contents, int size) {
        this.uuid = uuid;
        this.contents = contents;
        this.size = size;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}