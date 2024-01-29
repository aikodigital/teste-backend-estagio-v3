package me.dri.aiko.exception;

public class NotFoundEquipment extends RuntimeException {

    public NotFoundEquipment() {

    }
    public NotFoundEquipment(String s) {
        super(s);
    }
}
