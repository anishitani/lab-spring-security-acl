insert into writers(writer_id, name)
values ('30c88e64-b5ee-4abe-a352-f51be2cce3d1', 'usera'),
       ('a18fc079-9973-4cd0-bfbd-3d3309ff7089', 'userb');

insert into posts(title,subject,content,writer_id)
values ('beginner''s path', 'tutorial', 'Fusce eu maximus nunc et feugiat.', '30c88e64-b5ee-4abe-a352-f51be2cce3d1'),
       ('lorem ipsum survey', 'survvey', 'Pellentesque vitae tortor cursus, congue purus.', '30c88e64-b5ee-4abe-a352-f51be2cce3d1'),
       ('cooking like a boss', 'cook', 'Pick your phone and order pizza! ;)', 'a18fc079-9973-4cd0-bfbd-3d3309ff7089');