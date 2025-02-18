DROP TABLE IF EXISTS `sample`;
CREATE TABLE `sample`
(
    `sample_id`   VARCHAR(255)	 NOT NULL,
    `sample_name` VARCHAR(255)   NOT NULL,
    `sample_desc` VARCHAR(255)   NOT NULL
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
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

DROP TABLE IF EXISTS `tokens`;
CREATE TABLE `tokens`
(
    TOKEN_ID                 VARCHAR(255) NOT NULL COMMENT '토큰 PK',
    USER_ID                  VARCHAR(255) NOT NULL COMMENT '사용자 ID',
    ACCESS_TOKEN             VARCHAR(255) COMMENT '액세스 토큰',
    REFRESH_TOKEN            VARCHAR(255) COMMENT '리프레시 토큰',
    ACCESS_TOKEN_EXPIRES_AT  DATETIME COMMENT '액세스 토큰 만료시간',
    REFRESH_TOKEN_EXPIRES_AT DATETIME COMMENT '리프레시 토큰 만료시간',

    CREATED_AT               DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY               VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT              DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY              VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (TOKEN_ID)
);


DROP TABLE IF EXISTS `social_users`;
CREATE TABLE `social_users`
(
    SOCIAL_USER_ID VARCHAR(255) NOT NULL COMMENT '소셜 사용자 ID (UUID)',
    USER_NAME      VARCHAR(50)  NOT NULL COMMENT '소셜 사용자 이름',
    PROVIDER       VARCHAR(255) NOT NULL COMMENT '소셜 프로바이더 (구글, 카카오, 네이버 등)',
    PROVIDER_ID    VARCHAR(255) NOT NULL COMMENT '프로바이더 ID',

    CREATED_AT     DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY     VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT    DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY    VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (SOCIAL_USER_ID)
);

DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies`
(
    MOVIE_ID    VARCHAR(255) NOT NULL COMMENT '영화 ID',
    MOVIE_NAME  VARCHAR(255) NOT NULL COMMENT '영화 명',
    IS_ADULT    TINYINT(1) COMMENT '성인영화 여부',
    GENRE       VARCHAR(255) COMMENT '장르',
    OVERVIEW    VARCHAR(255) COMMENT '설명',
    RELEASED_AT VARCHAR(255) COMMENT '출시일자',

    CREATED_AT  DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY  VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (MOVIE_ID)
);



