CREATE TABLE USERS (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    birth_date DATE,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE POSTS (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    local_date_time TIMESTAMP NOT NULL,
    total_likes INT,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)     --- referência a tabela USERS
);

CREATE TABLE LIKES (
    like_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES POSTS(post_id),
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE TABLE COMMENTS (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    local_date_time TIMESTAMP NOT NULL,
    FOREIGN KEY (post_id) REFERENCES POSTS(post_id),
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE TABLE FRIENDS(
    friend_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
)