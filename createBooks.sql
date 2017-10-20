USE test;
DROP TABLE IF EXISTS books;

CREATE TABLE books(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
title varchar(100) NOT NULL,
description varchar(255) NOT NULL,
author varchar(100) NOT NULL,
isbn varchar(20) NOT NULL,
printYear INT NOT NULL,
isReadAlready BOOLEAN NOT NULL default false
)

ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `test`.books (author, title, description, isbn, printYear) VALUES 
("First author", "you+you", "first description", "5-999-17238-6", 1997),
("First author", "first", "second description", "1-321-003266-6", 2003),
("First author", "second", "third description", "3-321-10233-3", 2000),
("First author", "best title", "another description", "1-111-11111-1", 2015),
("Sophie", "memories", "Sophie, me and etc", "6-111-03338-4", 2017),
("me", "memories", "i love Sophie and our future", "2-888-65400-12", 2017),
("me", "only 4 her", "im struggling", "2-222-17432-5", 2017),
("second author", "last last", "so tied", "2-222-12345-7", 2015),
("second author", "nothing", "no sleep for 2 weeks", "3-333-555-8", 2100),
("second author", "ME+ME", "gotta sleep", "0-000-00000-0", 1999),
("second author", "YOU+ME", "come on", "4-444-4444-5", 2005),
("First author", "never surrender", "when it ends", "4-444-03338-1", 2004),
("First author", "never never", "so tied", "5-486-00238-0", 2004),
("second author", "title", "so soon is ending", "1-432-55422-6", 2004),
("one more author", "test", "just testing", "1-432-10008-5", 2005),
("one more author", "another test", "yeah, i have so much imagination", "6-41217-7777-7", 2005),
("FOURTH", "123", "counting 1,2,33...", "5-486-00238-0", 1743),
("FOURTH", "12-44", "blablabla", "1-551-1231-12", 1999),
("FOURTH", "numbers198", "testing", "6-171819-38-9", 1743),
("better author", "title1743", "bla and bla 1 more bla", "1-000-55432-5", 1999),
("another author", "1777777", "first second...", "12-44-003-6", 2000),
("another author", "1777777-2", "numbers", "1-321-0033211-6", 2000),
("one and the best", "how-to", "become java professional", "3-312-66666-1", 2017);