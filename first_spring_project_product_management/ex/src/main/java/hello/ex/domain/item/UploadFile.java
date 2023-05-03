package hello.ex.domain.item;

import lombok.Getter;

@Getter
public class UploadFile {
    private String storeFileName;
    private String uploadFileName;

    public UploadFile(String storeFileName, String uploadFileName) {
        this.storeFileName = storeFileName;
        this.uploadFileName = uploadFileName;
    }
}
