package me.yonghong.springboot.lab;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * 因为在 {@link SecurityConfig} 中，
     * 配置了 .anyRequest().authenticated() ，
     * 任何请求，访问的用户都需要经过认证。所以这里 @PermitAll 注解实际是不生效的。
     * <p>
     * 也就是说，Java Config 配置的权限，和注解配置的权限，两者是叠加的
     */
    @PermitAll
    @GetMapping("/echo")
    public String demo() {
        return "示例返回";
    }

    @GetMapping("/home")
    public String home() {
        return "我是首页";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "我是管理员";
    }

    @PreAuthorize("hasRole('ROLE_NORMAL')")
    @GetMapping("/normal")
    public String normal() {
        return "我是普通用户";
    }
}
