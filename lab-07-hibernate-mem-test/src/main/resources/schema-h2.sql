CREATE TABLE `article`
(
    id            bigint        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `create_time` timestamp     NOT NULL DEFAULT NOW(),
    `update_time` timestamp     NOT NULL DEFAULT NOW(),
    `deleted`     tinyint       NOT NULL DEFAULT 0,
    `author_id`   bigint        NOT NULL DEFAULT 0,
    `title`       varchar(255)  NOT NULL DEFAULT '',
    `content`     varchar(1023) NOT NULL,
    `custom_url`  varchar(255)  NOT NULL DEFAULT '',
    `reads`       bigint        NOT NULL DEFAULT 0,
    `likes`       bigint        NOT NULL DEFAULT 0
);
