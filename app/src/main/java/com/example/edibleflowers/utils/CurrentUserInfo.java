package com.example.edibleflowers.utils;

import com.example.edibleflowers.bean.User;

/**
 * @author 65667
 */
public class CurrentUserInfo {
    public static User user = null;
    public static Integer uno = 0;  //当前用户id
    public static String name = "";
    public static String nick_name = ""; //昵称
    public static String password = "";
    public static String profilePhoto = "";//头像url

    public static Integer like = 10;   //已喜欢
    public static Integer publish = 10; //已发表
    public static Integer praise = 10;   //被赞

}