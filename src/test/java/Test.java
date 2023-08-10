import com.hrbu.blog.blogadminsystem.Model.BlogPost;
import com.hrbu.blog.blogadminsystem.Model.User;
import com.hrbu.blog.blogadminsystem.Service.BlogPostService;
import com.hrbu.blog.blogadminsystem.Service.UserService;
import com.hrbu.blog.blogadminsystem.Util.CustomExceptions.QueryErrorException;

import java.util.ArrayList;
import java.util.List;

public class Test {
    @org.junit.jupiter.api.Test
    public void Test01() {
        /* 测试用户 */
        UserService userService = new UserService();
        List<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user3.setUsername("test-rollback-45");
        user3.setPassword("1");
        user3.setUgroup("1");
        user3.setNick("6");
        user2.setUsername("test-rollback-55");
        user2.setPassword("2");
        user2.setUgroup("2");
        user2.setNick("2");
        user1.setUsername("test-rollback-6");
        user1.setPassword("1");
        user1.setUgroup("1");
        user1.setNick("6");
        users.add(user2);
        users.add(user1);
        users.add(user3);

//        try {
//            System.out.println(userService.addUser(users));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        List<User> list = null;
        try {
            list = userService.getUsers(0,5);
        } catch (QueryErrorException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (list != null)
            for (User u : list) {
                System.out.println(u);
            }
    }

    @org.junit.jupiter.api.Test
    public void Test02() {
        BlogPostService blogPostService = new BlogPostService();
        try {
//            System.out.println(blogPostService.getBologPost("1"));
            BlogPost blogPost = new BlogPost();
            blogPost.setAuthor("14");
            blogPost.setContent("测试插入3");
            blogPost.setUid("23");
            blogPost.setName("test004");

            BlogPost blogPost1 = new BlogPost();
            blogPost1.setAuthor("13");
            blogPost1.setContent("测试插入1");
            blogPost1.setUid("24");
            blogPost1.setName("test003");

            List<BlogPost> blogPosts = new ArrayList<>();
            blogPosts.add(blogPost);
            blogPosts.add(blogPost1);
            System.out.println(blogPostService.addBologPost(blogPosts));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            for (BlogPost post : blogPostService.getAllBlogPost()) {
                System.out.println(post);
            }
        } catch (QueryErrorException e) {
            System.out.println(e.getMessage());
        }
    }

}
