SET FOREIGN_KEY_CHECKS=0;

truncate table blog_post;
truncate table author;
truncate table comment;
truncate table author_posts;

INSERT INTO blog_post(id,title,content)
VALUES(41,'Title post 1','post content 1'),
(42,'Title post 2','post content 2'),
(43,'Title post 3','post content 3'),
(44,'Title post 4','post content 4'),
(45,'Title post 5','post content 5');

SET FOREIGN_KEY_CHECKS=1;
