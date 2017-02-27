package com.example.controller;

import com.example.model.Article;
import com.example.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "loadBlogHomePage";
    }

    @RequestMapping("/list")
    public String list(){
        return "articleList";
    }

    @RequestMapping(params = "method=loadArticleList",method = RequestMethod.POST)
    @ResponseBody
    public String loadArticleList(){
        Page<Article> articlePage = articleRepository.findAll(new PageRequest(0,10));
        List<Article> articleList = articlePage.getContent();
        //把上面得到的数据，打包转换为一个JSON，返回给Ajax.

        //先构造一个Map，把上面的数据放进去。
        HashMap<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("data",articleList);
        ObjectMapper mapper = new ObjectMapper();

        try{
            String json = mapper.writeValueAsString(jsonMap);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }

        return "wrong";
    }

    @RequestMapping(params = "method=addArticle", method = RequestMethod.GET)
    public String addArticle(){
        return "addArticle";
    }

    @RequestMapping(params = "method=saveArticle",method = RequestMethod.POST)
    @ResponseBody
    public String saveArticle(HttpServletRequest request){
        Article article = new Article();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        article.setTitle(title);
        article.setContent(content);


        HashMap<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("data","success");
        ObjectMapper mapper = new ObjectMapper();

        try{
            String json = mapper.writeValueAsString(jsonMap);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }

        return "wrong";
    }
}
