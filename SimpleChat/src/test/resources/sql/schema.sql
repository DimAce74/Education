CREATE TABLE chat
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  chat_name VARCHAR NOT NULL
);

CREATE TABLE chat_member
(
  chat_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  last_message_id BIGINT,
  CONSTRAINT chat_member_chat_id_user_id_pk PRIMARY KEY (chat_id, user_id),
  CONSTRAINT chat_chat_member_fk FOREIGN KEY (chat_id) REFERENCES chat (id),
  CONSTRAINT chat_user_chat_member_fk FOREIGN KEY (user_id) REFERENCES chat_user (id),
  CONSTRAINT chat_member_message_id_fk FOREIGN KEY (last_message_id) REFERENCES message (id)
);
CREATE INDEX fki_chat_chat_member_fk ON chat_member (chat_id);
CREATE INDEX fki_chat_user_chat_member_fk ON chat_member (user_id);

CREATE TABLE chat_user
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX chat_user_login_uindex ON chat_user (login);

CREATE TABLE message
(
  id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  chat_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  text VARCHAR(255),
  CONSTRAINT chat_message_fk FOREIGN KEY (chat_id) REFERENCES chat (id),
  CONSTRAINT user_message_fk FOREIGN KEY (user_id) REFERENCES chat_user (id)
);
CREATE INDEX fki_chat_message_fk ON message (chat_id);
CREATE INDEX fki_user_message_fk ON message (user_id);

CREATE TABLE token_storage
(
  token VARCHAR NOT NULL,
  user_id INTEGER NOT NULL,
  CONSTRAINT user_session_fk FOREIGN KEY (user_id) REFERENCES chat_user (id)
);
CREATE INDEX fki_user_session_fk ON token_storage (user_id);