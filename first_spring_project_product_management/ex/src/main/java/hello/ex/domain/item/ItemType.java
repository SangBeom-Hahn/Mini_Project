package hello.ex.domain.item;

public enum ItemType {
    BOOK("책"), FOOD("음식");
    private String desc;

    public String getDesc() {
        return desc;
    }

    ItemType(String desc) {
        this.desc = desc;
    }
}
