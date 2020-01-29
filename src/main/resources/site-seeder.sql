USE blog_db;

TRUNCATE TABLE users;
INSERT INTO users(username, password, bio)
VALUES
('SomeUser01', 'pass', 'some info'),
('Another User', 'pass', 'insert creative bio here'),
('River', 'pass', 'oof'),
('Someone', 'pass', 'insert creative bio here');

TRUNCATE TABLE posts;
INSERT INTO posts(user_id, title, body) VALUES
(1, 'Some thing', 'I am blogging about a thing'),
(1, 'Another thing', 'I, the same person, am blogging about another thing'),
(2, 'A different thing', 'I, a different person, am blogging a different thing');

TRUNCATE TABLE comments;
INSERT INTO comments(user_id, post_id, body) VALUES
(3, 2, 'no u lol');
