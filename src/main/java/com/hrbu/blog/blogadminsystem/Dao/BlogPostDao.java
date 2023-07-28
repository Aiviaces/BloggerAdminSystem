package com.hrbu.blog.blogadminsystem.Dao;

import com.hrbu.blog.blogadminsystem.Model.BlogPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogPostDao {
    BlogPost getBlogPostByUid(String uid);
    List<BlogPost> getAllBlogPost();
    int addBlogPost(List<BlogPost> post);
    /* 获取待审核文章 */
    List<BlogPost> getReviewBlogPost();
    /* 修改文章审核状态(所有要更改文章的uid) */
    int updateReviewState(List<String> uid);
}
