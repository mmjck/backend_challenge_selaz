CREATE TABLE IF NOT EXISTS tb_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    nivel VARCHAR(10)
);


CREATE UNIQUE INDEX IF NOT EXISTS username_idx ON tb_users (username);

CREATE TABLE IF NOT EXISTS tb_tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    status VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date TIMESTAMP,
    user_id BIGINT,

    FOREIGN KEY (user_id) REFERENCES tb_users(id)
);


