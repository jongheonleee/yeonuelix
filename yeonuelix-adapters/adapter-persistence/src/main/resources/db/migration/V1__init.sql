DROP TABLE IF EXISTS `USERS`;
CREATE TABLE `USERS`
(
    user_id	        VARCHAR(255)	NOT NULL	COMMENT 'uuid로 저장',
    user_name	    VARCHAR(50)	    NOT NULL,
    password	    VARCHAR(255)	NOT NULL	COMMENT '암호화 처리',
    email	        VARCHAR(255)	NOT NULL,
    phone	        VARCHAR(255)	NOT NULL,


    created_at	    DATETIME	    NOT NULL,
    created_by	    VARCHAR(50)	    NOT NULL,
    modified_at	    DATETIME	    NOT NULL,
    modified_by	    VARCHAR(50)	    NOT NULL,

    PRIMARY KEY (user_id)
);


DROP TABLE IF EXISTS `sample`;
CREATE TABLE `sample`
(
    SAMPLE_ID	    VARCHAR(255)	    NOT NULL,
    SAMPLE_NAME	    VARCHAR(255)	    NOT NULL,
    SAMPLE_DESC	    VARCHAR(255)	    NOT NULL,

    PRIMARY KEY (SAMPLE_ID)
);
