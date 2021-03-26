
package com.alkemy.blog.alkemy.models.services;

import com.alkemy.blog.alkemy.models.dao.IPostDao;
import com.alkemy.blog.alkemy.models.entity.Post;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements IPostService{
    @Autowired
    private IPostDao iPostDao;

    @Override
    public List<Post> findAll() {
        return iPostDao.findAll();
    }

    @Override
    public Post findById(Long id) {
        return iPostDao.findById(id).orElse(null);
    }

    @Override
    public Post save(Post post) {
        return iPostDao.save(post);
    }

    @Override
    public void delete(Long id) {
        iPostDao.deleteById(id);
    }
    
}
