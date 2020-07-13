package me.yonghong.springboot.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private FindByIndexNameSessionRepository sessionRepository;

    @GetMapping("/list")
    public Map<String, ? extends Session> list(@RequestParam("username") String username) {
        return sessionRepository.findByPrincipalName(username);
    }

}
