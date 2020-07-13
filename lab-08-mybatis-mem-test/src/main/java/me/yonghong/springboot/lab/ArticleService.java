package me.yonghong.springboot.lab;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> {
}
