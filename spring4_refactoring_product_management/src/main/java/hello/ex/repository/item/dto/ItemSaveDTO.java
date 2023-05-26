package hello.ex.repository.item.dto;

import hello.ex.domain.item.ItemType;
import hello.ex.domain.item.Region;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemSaveDTO {
    @NotBlank
    private String itemName;
    @NotNull
    @Range(min = 1000, max = 1000000)
    @NumberFormat(pattern = "###,###")
    private Integer price;
    @NotNull
    @Max(9999)
    @NumberFormat(pattern = "###,###")
    private Integer quantity;
    private Boolean open;
    private Region[] regions;
    private ItemType itemType;
    private String deliveryCode;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}
