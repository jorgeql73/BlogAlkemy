
package com.alkemy.blog.alkemy.models.dao;

import com.alkemy.blog.alkemy.models.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface IPostDao extends JpaRepository<Post, Long>{
    @Query(value="SELECT * FROM post WHERE post.deleted=false ORDER BY creation_date DESC",
            nativeQuery = true)
    @Transactional(readOnly = true)
    @Override
    List<Post> findAll();
}
