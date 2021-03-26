
package com.alkemy.blog.alkemy.models.services;

import com.alkemy.blog.alkemy.models.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.Query;


public interface IPostService {
    /*@Query(value="SELECT * FROM post ORDER BY creation_date DESC",
            nativeQuery = true)*/       
    public List<Post> findAll();
    
    public Post findById(Long id);
    
    public Post save(Post post);
    
    public void delete(Long id);
}
