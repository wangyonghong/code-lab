package me.yonghong.springboot.lab;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article extends Model<Article> {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long authorId;

    private String title;

    private String content;

    private String customUrl;

    private Long reads = 0L;

    private Long likes = 0L;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private boolean deleted;
}