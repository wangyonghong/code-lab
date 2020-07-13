package me.yonghong.springboot.lab;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseEntity {

    private Long authorId;

    private String title;

    @Column(columnDefinition = "text")
    private String content;

    private String customUrl;

    private Long reads = 0L;

    private Long likes = 0L;
}