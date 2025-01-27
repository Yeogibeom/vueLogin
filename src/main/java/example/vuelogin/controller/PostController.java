package example.vuelogin.controller;

import example.vuelogin.entity.Post;
import example.vuelogin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
 private final PostRepository postRepository;
 @PostMapping("/write")
    public  String  write(@RequestBody Post post) {
      var po = post;

     System.out.println( po.getContent());
     System.out.println( po.getTitle());
     System.out.println(po.getCreatedAt());
     System.out.println(po.getUsername());
//     postRepository.save( po);
     return null;
 }
}
