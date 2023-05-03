package hello.ex.domain.item;

public enum Region {
    SEOUL("서울"), BUSAN("부산");

    private String desc;

    Region(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
