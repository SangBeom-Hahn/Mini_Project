package hello.ex.web.items.controller;

import hello.ex.domain.file.FileStore;
import hello.ex.domain.item.*;
import hello.ex.domain.member.Member;
import hello.ex.web.Const;
import hello.ex.web.items.form.ItemEditForm;
import hello.ex.web.items.form.ItemSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;
import org.thymeleaf.model.ITemplateEnd;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @ModelAttribute("regions")
    public Region[] regions() {
        return Region.values();
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> result = new ArrayList<>();
        result.add(new DeliveryCode("빠른 배송", "FAST"));
        result.add(new DeliveryCode("일반 배송", "NORMAL"));
        result.add(new DeliveryCode("느린 배송", "SLOW"));

        return result;
    }

    @GetMapping("/items")
    public String items(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(Const.MySessionName);

        if (member != null) {
            model.addAttribute("member", member);
        }

        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items/items";
    }

    @GetMapping("/items/add")
    public String addForm(@ModelAttribute("item") ItemSaveForm form) {
        return "items/addForm";
    }

    @PostMapping("/items/add")
    public String add(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, Model model,
                      RedirectAttributes redirectAttributes) throws IOException {
        if (form.getPrice() != null && form.getQuantity() != null) {
            int result = form.getPrice() * form.getQuantity();
            if (result < 10000) {
                bindingResult.reject("maxPriceError");
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("{}", bindingResult);
            return "items/addForm";
        }
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
        List<UploadFile> imageFiles = fileStore.storeFiles(form.getImageFiles());

        Item item = new Item(form.getItemName(), form.getPrice(), form.getQuantity(), form.getOpen(), form.getRegions(), form.getItemType(), form.getDeliveryCode(),
                attachFile, imageFiles);
        Item saveItem = itemRepository.save(item);

        model.addAttribute("item", saveItem);
        redirectAttributes.addAttribute("id", item.getId());
        return "redirect:/items/{id}";
    }

    @GetMapping("/items/{id}")
    public String item(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);

        return "items/item";
    }

    @GetMapping("/items/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);

        return "items/editForm";
    }

    @PostMapping("/items/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute("item") ItemEditForm form, BindingResult bindingResult) throws IOException {
        if (form.getPrice() != null && form.getQuantity() != null) {
            int result = form.getPrice() * form.getQuantity();
            if (result < 10000) {
                bindingResult.reject("maxPriceError");
            }
        }

        if (bindingResult.hasErrors()) {
            log.info("{}", bindingResult);
            return "items/editForm";
        }

        Item findItem = itemRepository.findById(id);
        findItem.setItemType(form.getItemType());
        findItem.setItemName(form.getItemName());
        findItem.setOpen(form.getOpen());
        findItem.setPrice(form.getPrice());
        findItem.setRegions(form.getRegions());
        findItem.setDeliveryCode(form.getDeliveryCode());
        findItem.setQuantity(form.getQuantity());

        findItem.setAttachFile(fileStore.storeFile(form.getAttachFile()));
        findItem.setImageFiles(fileStore.storeFiles(form.getImageFiles()));

        return "redirect:/items/{id}";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        log.info("요기");
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
        Item item = itemRepository.findById(id);
        String storeFileName = item.getAttachFile().getStoreFileName();
        String uploadFileName = item.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodeUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + uploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

    @PostConstruct
    void init() {
        Item item = new Item("apple", 10000, 10, false, null, null, null, null, null);
        Item item2 = new Item("banana", 20000, 20, false, null, null, null, null, null);
        itemRepository.save(item);
        itemRepository.save(item2);
    }
}
