package hello.ex.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Getter
@Setter
public class Item {
    private Long id;
    private String itemName;

    @NumberFormat(pattern = "###,###")
    private Integer price;

    @NumberFormat(pattern = "###,###")
    private Integer quantity;
    private boolean open;
    private Region[] regions;
    private ItemType itemType;
    private String deliveryCode;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;

    public Item(String itemName, Integer price, Integer quantity, boolean open, Region[] regions, ItemType itemType, String deliveryCode, UploadFile attachFile, List<UploadFile> imageFiles) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.open = open;
        this.regions = regions;
        this.itemType = itemType;
        this.deliveryCode = deliveryCode;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}