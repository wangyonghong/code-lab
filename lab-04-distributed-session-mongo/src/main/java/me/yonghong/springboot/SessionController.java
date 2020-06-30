package me.yonghong.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/set") // 其实 PostMapping 更合适，单纯为了方便
    public void set(HttpSession session,
                    @RequestParam("key") String key,
                    @RequestParam("value") String value) {
        log.info("host: {}", System.getenv("HOSTNAME"));
        session.setAttribute(key, value);
    }

    @GetMapping("/get_all")
    public Map<String, Object> getAll(HttpSession session) {
        log.info("host: {}", System.getenv("HOSTNAME"));
        Map<String, Object> result = new HashMap<>();
        // 遍历
        for (Enumeration<String> enumeration = session.getAttributeNames();
             enumeration.hasMoreElements(); ) {
            String key = enumeration.nextElement();
            Object value = session.getAttribute(key);
            result.put(key, value);
        }
        // 返回
        return result;
    }
}
