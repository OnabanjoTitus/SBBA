SET FOREIGN_KEY_CHECKS=0;

truncate table blog_post;
truncate table author;
truncate table comment;
truncate table author_posts;

INSERT INTO blog_post(id,title,content,date_created)
VALUES(41,'Title post 1','post content 1',"2021-06-03T11:34:36"),
(42,'Title post 2','post content 2',"2021-06-03T12:34:36"),
(43,'Title post 3','post content 3',"2021-06-03T13:34:36"),
(44,'Title post 4','post content 4',"2021-06-03T14:34:36" ),
(45,'Title post 5','post content 5',"2021-06-03T15:34:36" );

SET FOREIGN_KEY_CHECKS=1;
