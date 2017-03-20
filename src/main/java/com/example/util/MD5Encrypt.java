package com.example.util;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5Encrypt {

    public static void main(String[] args)
    {

        //String str = txtTableImport("c://neijiang.xls");    //需要被加密的文件
        String str = md5EncodePassword("111111","user");   //单个用户，只需注释上面一行代码即可md5EncodePassword(param1,param2)
        System.out.println("pass:"+str);               //                            param1:密码;        param2：用户名
    }




    /**
     *
     * 以“用户名+密码”为加密对象，进行MD5加密
     * @param password
     * @param userName
     * @return
     */
    public static String md5EncodePassword(String password, String userName)
    {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);
        String encodedPassword = md5.encodePassword(password, userName);
        return encodedPassword;
    }

}
