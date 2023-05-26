package hello.ex.controller;

import hello.ex.domain.file.FileStore;
import hello.ex.domain.item.*;
import hello.ex.repository.item.ItemRepository;
import hello.ex.repository.item.dto.ItemEditDTO;
import hello.ex.repository.item.dto.ItemSaveDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @ModelAttribute("regions")
    public Region[] regions() {
        return Region.values();
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCode")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("빠른 배송", "FAST"));
        deliveryCodes.add(new DeliveryCode("빠른 배송", "FAST"));
        deliveryCodes.add(new DeliveryCode("빠른 배송", "FAST"));
        return deliveryCodes;
    }

    @GetMapping("/items")
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/items/add")
    public String addForm(@ModelAttribute ItemSaveDTO itemSaveDTO) {
        return "items/addForm";
    }

    @PostMapping("/items/add")
    public String add(@Validated @ModelAttribute ItemSaveDTO itemSaveDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes, Model model) throws IOException {

        if (itemSaveDTO.getPrice() != null && itemSaveDTO.getQuantity() != null) {
            int result = itemSaveDTO.getPrice() * itemSaveDTO.getQuantity();
            if (result < 10000) {
                bindingResult.reject("maxPriceError");
            }
        }

        if (bindingResult.hasErrors()) {
            return "items";
        }
        Item saveItem = itemRepository.save(itemSaveDTO);
        model.addAttribute("item", saveItem);
        redirectAttributes.addAttribute("id", saveItem.getId());
        return "redirect:/items/{id}";
    }

    @GetMapping("/items/{id}")
    public String item(@PathVariable Long id, Model model) {
        Item findItem = itemRepository.findById(id).orElse(null);
        model.addAttribute("item", findItem);
        return "/items/item";
    }

    @GetMapping("/items/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Item findItem = itemRepository.findById(id).orElse(null);
        model.addAttribute("item", findItem);
        return "/items/editForm";
    }

    @PostMapping("/items/{id}/edit")
    public String edit(@PathVariable Long id, @Validated @ModelAttribute ItemEditDTO itemEditDTO, BindingResult bindingResult) throws IOException {
        if (itemEditDTO.getPrice() != null && itemEditDTO.getQuantity() != null) {
            int result = itemEditDTO.getPrice() * itemEditDTO.getQuantity();
            if (result < 10000) {
                bindingResult.reject("maxPriceError");
            }
        }

        if (bindingResult.hasErrors()) {
            return "items/editForm";
        }

        itemRepository.update(id, itemEditDTO);
        return "redirect:/items/{id}";
    }
}