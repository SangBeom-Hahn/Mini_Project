package hello.ex.web.items.form;

import hello.ex.domain.item.ItemType;
import hello.ex.domain.item.Region;
import hello.ex.domain.item.UploadFile;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ItemEditForm {
    @NotNull
    private Long id;
    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    @NumberFormat(pattern = "###,###")
    private Integer price;

    @NotNull
    @NumberFormat(pattern = "###,###")
    private Integer quantity;
    private Boolean open;
    private Region[] regions;
    private ItemType itemType;
    private String deliveryCode;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}
