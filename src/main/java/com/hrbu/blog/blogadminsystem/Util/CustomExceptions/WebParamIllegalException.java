package com.hrbu.blog.blogadminsystem.Util.CustomExceptions;

public class WebParamIllegalException extends Exception{
    public WebParamIllegalException(String paramname) {
        super(paramname+" 前端传递参数未找到(null值)!");
    }
}
