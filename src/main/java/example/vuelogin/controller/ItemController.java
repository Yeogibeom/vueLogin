package example.vuelogin.controller;

import example.vuelogin.entity.Item;
import example.vuelogin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ItemController {
    private final ItemRepository itemRepository;
    @GetMapping("/list")
    public Iterable<Item> getItems() {
        var items = itemRepository.findAll();
        System.out.println(items);
        return items;
    }
    @PostMapping("/edit")
    public ResponseEntity<String> editItem(@RequestBody Item item) {
        var items = item;
        System.out.println(items);
        itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.OK).body("상품이 추가되었습니다 ");
    }
    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable Long id) {
        var item = itemRepository.findById(id).orElse(null);
        return item;
    }
    @GetMapping("/update/{id}")
    public Item updateItem(@PathVariable Long id) {
        var item = itemRepository.findById(id).orElse(null);
        return item;
    }
    @PostMapping("/updateitem")
    public ResponseEntity<String> updateItem(@RequestBody Item item) {
        var a = item;//왜 id값이 널일까 우린데이터를 보낼때 저기ㅓㅅ 아이디값도 주는데

        a.setTitle(item.getTitle());
        a.setPrice(item.getPrice());

    itemRepository.save(a);
        return null;
    }
}
