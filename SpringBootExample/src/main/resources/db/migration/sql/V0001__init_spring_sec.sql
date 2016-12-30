DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  userid   INT(11)      NOT NULL AUTO_INCREMENT,
  username VARCHAR(45)  NOT NULL,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(60)  NOT NULL,
  locked   BOOLEAN      NOT NULL DEFAULT FALSE,
  enabled  BOOLEAN      NOT NULL DEFAULT TRUE,
  PRIMARY KEY (userid)
);

CREATE TABLE user_roles (
  user_role_id INT(11)     NOT NULL AUTO_INCREMENT,
  userid       INT(11)     NOT NULL,
  role         VARCHAR(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES users (userid)
);

INSERT INTO users (username, email, password, enabled) VALUES ('admin', 'admin@maochen.org', 'admin', TRUE);

INSERT INTO user_roles (userid, role) VALUES (001, 'ADMIN');