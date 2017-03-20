package com.example.controller;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.util.MD5Encrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.example.util.MD5Encrypt.md5EncodePassword;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method={RequestMethod.GET})
    public String index(){
        return "loadUserHomePage";
    }

    @RequestMapping("/list")
    public String list(){
        return "userList";
    }
    @RequestMapping(params = "method=loadUserList",method = RequestMethod.POST)
    @ResponseBody
    public String loadUserList(){
        Page<User> userPage = userRepository.findAll(new PageRequest(0,10));
        List<User> userList = userPage.getContent();
        //把上面得到的数据，打包转换为一个JSON，返回给Ajax.

        //先构造一个Map，把上面的数据放进去。
        HashMap<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("data",userList);
        ObjectMapper mapper = new ObjectMapper();

        try{
            String json = mapper.writeValueAsString(jsonMap);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }

        return "wrong";
    }
    @RequestMapping(params = "method=saveUser",method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(HttpServletRequest request){
        try{
        String account = request.getParameter("account");
        String token = request.getParameter("token");
        token = md5EncodePassword(token, account);
        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setId(uuid.toString());
        user.setAccount(account);
        user.setNick(account);
        user.setGender("boy");
        user.setToken(token);
        user.setCreateTime(new Date().getTime());
        user.setUpdateTime(new Date().getTime());
            userRepository.save(user);
            HashMap<String,Object> jsonMap = new HashMap<>();
            jsonMap.put("data","success");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(jsonMap);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "wrong";
    }

    @RequestMapping(params = "method=addUser",method={RequestMethod.GET,RequestMethod.POST})
    public String addUser(){
        return "addUser";
    }

    @RequestMapping(params = "method=editUser",method={RequestMethod.GET,RequestMethod.POST})
    public String editUser(HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        User user = userRepository.findUserById(id);
        model.addAttribute("user",user);
        return "editUser";
    }

    @RequestMapping(params = "method=loadUserById",method = RequestMethod.POST)
    @ResponseBody
    public String loadUserById(HttpServletRequest request){
        String id = request.getParameter("id");
        User user = userRepository.findUserById(id);
        HashMap<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("data",user);
        ObjectMapper mapper = new ObjectMapper();

        try{
            String json = mapper.writeValueAsString(jsonMap);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }

        return "wrong";
    }

    @RequestMapping(params = "method=updateUser",method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(HttpServletRequest request){
        String id = request.getParameter("id");
        String token = request.getParameter("token");
        User user = userRepository.findUserById(id);
        token = md5EncodePassword(token, user.getAccount());
        user.setToken(token);
        user.setUpdateTime(new Date().getTime());
        try{
            userRepository.save(user);
            HashMap<String,Object> jsonMap = new HashMap<>();
            jsonMap.put("data","success");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(jsonMap);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "wrong";
    }

    @RequestMapping(params = "method=deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(HttpServletRequest request){
        String ids = request.getParameter("ids");
        String[] id = ids.split(",");
        try{
            for(int i=0,len=id.length;i<len;i++){
                userRepository.deleteUserById(id[i]);
            }
            HashMap<String,Object> jsonMap = new HashMap<>();
            jsonMap.put("data","success");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(jsonMap);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "wrong";
    }

    @RequestMapping(params = "method=findUser",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public User findUser(){
        User user = userRepository.findUser("ronger");
        return user;
    }

}
