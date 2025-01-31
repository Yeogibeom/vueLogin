package example.vuelogin.controller;

import example.vuelogin.entity.Item;
import example.vuelogin.entity.Post;
import example.vuelogin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     postRepository.save(po);
     return null;
 }

 @GetMapping("/post")
 public List<Post> getPostList() {
  return postRepository.findAll();
 }
 @GetMapping("/post/{id}")
 public ResponseEntity<Post> getItem(@PathVariable Long id) {
  Optional<Post> optionalPost = postRepository.findById(id);

  if (optionalPost.isPresent()) {
   Post post = optionalPost.get();
   post.setViews(post.getViews() + 1);
   System.out.println(post);
   postRepository.save(post);
   return ResponseEntity.ok(post);
  } else {
   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
 }

 @GetMapping("/search")
 public List<Post> getPostListByTitle(@RequestParam String type,@RequestParam String keyword) {
  if("title".equals(type)) {
    var k = postRepository.findByTitleContaining(keyword);
   System.out.println(k);
   return postRepository.findByTitleContaining(keyword);
  }else if("content".equals(type)) {
   return postRepository.findByContentContaining(keyword);
  }else if("username".equals(type)) {
   return postRepository.findByUsernameContaining(keyword);
  }
  return new ArrayList<>();
 }
}
