USE blog_db;

TRUNCATE TABLE users;
INSERT INTO users(username, email, password, bio)
VALUES
('SomeUser01', 'email@example.com','pass', 'some info'),
('Second User', 'email@example.com','pass', 'second bio'),
('Another User', 'email@example.com','pass', 'creative text here'),
('oof', 'email@example.com','pass', 'no');


TRUNCATE TABLE posts;
INSERT INTO posts(user_id, title, body) VALUES
(1, 'Some thing', 'I am blogging about a thing'),
(1, 'Another thing', 'I, the same person, am blogging about another thing'),
(2, 'A different thing', 'I, a different person, am blogging a different thing'),
(2, 'Some thing', 'I am blogging about a thing'),
(3, 'Another thing', 'I, the same person, am blogging about another thing'),
(2, 'A different thing', 'I, a different person, am blogging a different thing');

TRUNCATE TABLE comments;
INSERT INTO comments(user_id, post_id, body) VALUES
(3, 2, 'no u lol'),
(3, 2, 'kek'),
(3, 2, 'oof'),
(3, 2, 'comment no. 4');
