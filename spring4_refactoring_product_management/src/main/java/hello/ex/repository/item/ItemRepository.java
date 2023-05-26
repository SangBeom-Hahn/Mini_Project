package hello.ex.repository.item;

import hello.ex.domain.file.FileStore;
import hello.ex.domain.item.Item;
import hello.ex.domain.item.UploadFile;
import hello.ex.repository.item.dto.ItemEditDTO;
import hello.ex.repository.item.dto.ItemSaveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final FileStore fileStore;
    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    public Item save(ItemSaveDTO itemSaveDTO) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(itemSaveDTO.getAttachFile());
        List<UploadFile> uploadFiles = fileStore.storeFiles(itemSaveDTO.getImageFiles());

        Item item = new Item(itemSaveDTO.getItemName(), itemSaveDTO.getPrice(), itemSaveDTO.getQuantity(), itemSaveDTO.getOpen(),
                itemSaveDTO.getRegions(), itemSaveDTO.getItemType(), itemSaveDTO.getDeliveryCode(),
                uploadFile, uploadFiles);

        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, ItemEditDTO itemEditDTO) throws IOException {
        Item item = findById(id).orElse(null);
        item.setItemType(itemEditDTO.getItemType());
        item.setItemName(itemEditDTO.getItemName());
        item.setOpen(itemEditDTO.getOpen());
        item.setPrice(itemEditDTO.getPrice());
        item.setRegions(itemEditDTO.getRegions());
        item.setDeliveryCode(itemEditDTO.getDeliveryCode());
        item.setQuantity(itemEditDTO.getQuantity());

        item.setAttachFile(fileStore.storeFile(itemEditDTO.getAttachFile()));
        item.setImageFiles(fileStore.storeFiles(itemEditDTO.getImageFiles()));
    }
}










