package com.tansci.service.system;

/**
 * @ClassName： AuthorizedService.java
 * @ClassPath： com.tansci.service.system.AuthorizedService.java
 * @Description： 三方授权
 * @Author： tanyp
 * @Date： 2022/3/1 10:42
 **/
public interface AuthorizedService {

    String wxLogin();

    String wxCallback(String code, String state);

}
