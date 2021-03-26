
package com.alkemy.blog.alkemy.controllers;

import com.alkemy.blog.alkemy.models.entity.Post;
import com.alkemy.blog.alkemy.models.services.IPostService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PostController {
        
    @Autowired
    private IPostService iPostService;
    
    @GetMapping(path="/") 
    public String home(Model model){
        List<Post> posts = iPostService.findAll();
        model.addAttribute("posts", posts);
        return "home";
        
    }
    @GetMapping("formCreate") 
    public String formCreate(Post post){
        return "create";
        
    }
        
    @PostMapping("POST/posts")
    public String save(@Valid Post post, BindingResult result, Model model, @RequestParam("file") MultipartFile img) throws IOException{
        if(result.hasErrors()){
            System.out.println("Hubo errores en el formulario");
             return "create";
        }
        if(!img.isEmpty()){
            Path directorioImagenes = Paths.get("src//main//resources//static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            byte[]bytesImg = img.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta+"//"+img.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            post.setImg(img.getOriginalFilename());
        }
        iPostService.save(post);
        
        return "redirect:/";

        
    }
    @GetMapping("PATCH/posts/{id_post}") 
    public String edit(Post post, Model model){
        post = iPostService.findById(post.getId_post());
        model.addAttribute("post", post);
        return "create";
        
    }
    @GetMapping("GET/posts")
    public String getPost(Model model){
        List<Post> posts = iPostService.findAll();
        model.addAttribute("posts", posts);
        return "posts"; 
    }
    @GetMapping("GET/posts/{id_post}")
    public String detail(Post post, Model model){    
        post = iPostService.findById(post.getId_post());
        model.addAttribute("post", post);
        return "detail";
        
    }
    @GetMapping("DELETE/posts/{id_post}") 
    public String delete(Post post, Model model){
        iPostService.delete(post.getId_post());
        return "redirect:/";
        
    }

}

