CREATE TABLE IF NOT EXISTS employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL
);

INSERT IGNORE INTO employees (name, email, department)
VALUES
('Sai Kumar', 'sai@example.com', 'DevOps'),
('John Doe', 'john@example.com', 'Engineering');
