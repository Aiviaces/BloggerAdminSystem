package com.hrbu.blog.blogadminsystem.Service;

import java.util.List;
import java.util.Map;

public class PermissionGroupService {
    /* TODO 权限操作管理
     *   权限组列表类型为 List<Map<String,Boolean>> permissionGroup;
     *   包含所有(多个)用户组 每个用户组以 uid 为键 一个 Map 为值
     *   每个用户组的 Map中保存 name(组名) index_jsp admin_jsp user_jsp ... 每个页面有一个权限
     *   需要做的事: 先把Dao层中的方法在Service层调用,返回正确的结果
     *   如何使用Dao层,请看注意事项以及参考我的代码,不懂可以查查网络,或者问我
     *   目前先把Dao层里对应的功能使用明白吧
     *   批量操作参数 统一为 List<实体类名> 类型 (封装为列表的操作在servlet做,也就是controller层)
     * */

}
