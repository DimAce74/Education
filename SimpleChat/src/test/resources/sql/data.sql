INSERT INTO chat (id, chat_name) VALUES (1, 'first chat');
INSERT INTO chat (id, chat_name) VALUES (2, 'second chat');
INSERT INTO chat (id, chat_name) VALUES (3, 'Crazy Chat');

INSERT INTO chat_member (chat_id, user_id, last_message_id) VALUES (1, 3, 1);
INSERT INTO chat_member (chat_id, user_id, last_message_id) VALUES (2, 2, 2);
INSERT INTO chat_member (chat_id, user_id, last_message_id) VALUES (3, 1, null);

INSERT INTO chat_user (id, login, password, name) VALUES (1, 'Marsel', '$2a$10$R8VzbM8K1CHQ/RXAHH3BdOeCppQZv4x55dkSwjVcVWI6wlbTw8Ll.', 'Marsel');
INSERT INTO chat_user (id, login, password, name) VALUES (2, 'DimAce', '$2a$10$LQn2Ix.p3Q5HXw.ZPubVGu9f0gsUEB44CzZFC2kfwE7ZU91hnuCDm', 'Dimon');
INSERT INTO chat_user (id, login, password, name) VALUES (3, 'Qwerty', '$2a$10$4YwIQUjbFs24FZ0FdNfYzuKTMXf7gsEQYipVeXNHByzoBLpiB3ENe', 'Qwerty');

INSERT INTO message (id, chat_id, user_id, text) VALUES (1, 1, 3, 'hello');
INSERT INTO message (id, chat_id, user_id, text) VALUES (2, 2, 2, 'hello2');
INSERT INTO message (id, chat_id, user_id, text) VALUES (3, 3, 1, 'hello3');

INSERT INTO token_storage (token, user_id) VALUES ('BdP8Bmqv8lBcLJOnIHQF', 1);
INSERT INTO token_storage (token, user_id) VALUES ('FvWRiDIQv7rRsBJttCZf', 2);
INSERT INTO token_storage (token, user_id) VALUES ('pOvD8b1gHXsTIdZkX6zP', 3);