DROP TABLE IF EXISTS AllReaders CASCADE;
DROP TABLE IF EXISTS Workers;
DROP TABLE IF EXISTS Pensioners;
DROP TABLE IF EXISTS Schoolkids;
DROP TABLE IF EXISTS Teachers;
DROP TABLE IF EXISTS Students;
DROP TABLE IF EXISTS Schoolkid;
DROP TABLE IF EXISTS Scientists;
DROP TABLE IF EXISTS Libraries CASCADE;
DROP TABLE IF EXISTS Editions CASCADE;
DROP TABLE IF EXISTS Librarians CASCADE;
DROP TABLE IF EXISTS IssuedBooks CASCADE;
DROP TABLE IF EXISTS Rules;
DROP TABLE IF EXISTS Characteristic CASCADE;
DROP TABLE IF EXISTS Information CASCADE;
DROP SEQUENCE IF EXISTS AllReaders_generator CASCADE;
DROP SEQUENCE IF EXISTS Librarians_generator CASCADE;


CREATE TABLE Libraries
(
    id_library integer PRIMARY KEY,
    quantity   integer NOT NULL
);


CREATE TABLE Information
(
    author      VARCHAR(50),
    title       VARCHAR(50),
    composition VARCHAR(50) NOT NULL,
    popularity  integer,
    PRIMARY KEY (author, title)
);

CREATE TABLE Characteristic
(
    id_edition   integer PRIMARY KEY,
    type_edition VARCHAR(50) NOT NULL,
    author       VARCHAR(50) NOT NULL,
    title        VARCHAR(50) NOT NULL,
    FOREIGN KEY (author, title) REFERENCES Information (author, title) ON DELETE CASCADE
);

create sequence Librarians_generator
    as integer
    minvalue 0
    maxvalue 2147483647;

CREATE TABLE Librarians
(
    id_librarian integer not null PRIMARY KEY DEFAULT nextval('Librarians_generator'),
    id_library   integer NOT NULL,
    hall_num     integer NOT NULL,
    FOREIGN KEY (id_library) REFERENCES Libraries (id_library) ON DELETE CASCADE
);

CREATE TABLE Editions
(
    id_edition    integer PRIMARY KEY,
    id_library    integer NOT NULL,
    hall_num      integer NOT NULL,
    rack_num      integer NOT NULL,
    shelf_num     integer NOT NULL,
    date_adding   date    NOT NULL,
    date_removing date,
    FOREIGN KEY (id_library) REFERENCES Libraries (id_library) ON DELETE CASCADE,
    FOREIGN KEY (id_edition) REFERENCES Characteristic (id_edition) ON DELETE CASCADE
);

create sequence AllReaders_generator
    as integer
    minvalue 0
    maxvalue 2147483647;

CREATE TABLE AllReaders
(
    id_reader  integer not null PRIMARY KEY DEFAULT nextval('AllReaders_generator'),
    type       VARCHAR(50) NULL ,
    surname    VARCHAR(50)  NOT NULL,
    name       VARCHAR(50)  NOT NULL,
    patronymic VARCHAR(50),
    id_library integer NOT NULL,
    FOREIGN KEY (id_library) REFERENCES Libraries (id_library) ON DELETE CASCADE
);



CREATE TABLE Workers
(
    id_reader integer PRIMARY KEY,
    type      VARCHAR(50) NOT NULL,
    address   VARCHAR(50) NOT NULL,
    firm      VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_reader) REFERENCES AllReaders (id_reader) ON DELETE CASCADE
);

CREATE TABLE Pensioners
(
    id_reader     integer PRIMARY KEY,
    type          VARCHAR(50)  NOT NULL,
    id_pensioners integer NOT NULL,
    FOREIGN KEY (id_reader) REFERENCES AllReaders (id_reader) ON DELETE CASCADE
);

CREATE TABLE Schoolkids
(
    id_reader integer PRIMARY KEY,
    type      VARCHAR(50)  NOT NULL,
    id_school integer NOT NULL,
    grade     integer NOT NULL,
    FOREIGN KEY (id_reader) REFERENCES AllReaders (id_reader) ON DELETE CASCADE
);

CREATE TABLE Teachers
(
    id_reader     integer PRIMARY KEY,
    type          VARCHAR(50)  NOT NULL,
    id_university integer NOT NULL,
    faculty       VARCHAR(50)  NOT NULL,
    FOREIGN KEY (id_reader) REFERENCES AllReaders (id_reader) ON DELETE CASCADE
);

CREATE TABLE Students
(
    id_reader     integer PRIMARY KEY,
    type          VARCHAR(50)  NOT NULL,
    id_university integer NOT NULL,
    faculty       VARCHAR(50)  NOT NULL,
    id_group      integer NOT NULL,
    FOREIGN KEY (id_reader) REFERENCES AllReaders (id_reader) ON DELETE CASCADE
);

CREATE TABLE Scientists
(
    id_reader     integer PRIMARY KEY,
    type          VARCHAR(50)  NOT NULL,
    address       VARCHAR(50)  NOT NULL,
    id_university integer NOT NULL,
    FOREIGN KEY (id_reader) REFERENCES AllReaders (id_reader) ON DELETE CASCADE
);

CREATE TABLE IssuedBooks
(
    id_record        integer PRIMARY KEY,
    id_reader        integer NOT NULL,
    id_edition       integer NOT NULL,
    date_extradition date    NOT NULL,
    date_return      date,
    is_returned      boolean NOT NULL,
    id_librarian     integer NOT NULL,
    FOREIGN KEY (id_librarian) REFERENCES Librarians (id_librarian) ON DELETE CASCADE,
    FOREIGN KEY (id_edition) REFERENCES Editions (id_edition) ON DELETE CASCADE,
    FOREIGN KEY (id_reader) REFERENCES AllReaders (id_reader) ON DELETE CASCADE
);

CREATE TABLE Rules
(
    id_rule    integer PRIMARY KEY,
    id_edition integer NOT NULL,
    rule       VARCHAR(1000)    NOT NULL,
    FOREIGN KEY (id_edition) REFERENCES Editions (id_edition) ON DELETE CASCADE
);


insert into Libraries values (1, 3);
insert into Libraries values (2, 4);
insert into Libraries values (3, 2);
insert into Libraries values (4, 5);
insert into Libraries values (5, 3);

insert into Librarians(id_library,hall_num) values (1,1);
insert into Librarians(id_library,hall_num) values (1,2);
insert into Librarians(id_library,hall_num) values (1,3);
insert into Librarians(id_library,hall_num) values (2,1);
insert into Librarians(id_library,hall_num) values (2,2);
insert into Librarians(id_library,hall_num) values (2,3);
insert into Librarians(id_library,hall_num) values (2,4);
insert into Librarians(id_library,hall_num) values (3,1);
insert into Librarians(id_library,hall_num) values (3,2);
insert into Librarians(id_library,hall_num) values (4,1);
insert into Librarians(id_library,hall_num) values (4,2);
insert into Librarians(id_library,hall_num) values (4,3);
insert into Librarians(id_library,hall_num) values (4,4);
insert into Librarians(id_library,hall_num) values (5,1);
insert into Librarians(id_library,hall_num) values (5,2);

insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Иванов', 'Иван', 'Иванович', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Агафонов', 'Константин', 'Федосеевич', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Белоусова', 'Виктория', 'Рудольфовна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Кудряшов', 'Роберт', 'Лукьевич', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Королёв', 'Дональд', 'Дмитриевич', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Блинов', 'Роман', 'Всеволодович', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Логинова', 'Амира', 'Евгеньевна', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Лобанова', 'Эжени', 'Куприяновна', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Шашкова', 'Триана', 'Дамировна', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Ковалёв', 'Архип', 'Альвианович', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('worker', 'Носков', 'Любомир', 'Олегович', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Одинцов', 'Альфред', 'Русланович', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Козлова', 'Юфеза', 'Феликсовна', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Молчанова', 'Ляля', 'Созоновна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Никонов', 'Терентий', 'Семёнович', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Николаева', 'Дарина', 'Онисимовна', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Устинов', 'Аскольд', 'Оскарович', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Щукин', 'Эрнест', 'Кириллович', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Титова', 'Наоми', 'Петровна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Филиппов', 'Игорь', 'Лукьянович', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('pensioner', 'Исакова', 'Уля', 'Валерьевна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Борисов', 'Гурий', 'Пантелеймонович', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Зиновьев', 'Лука', 'Михайлович', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Петрова', 'Агата', 'Тимофеевна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Горшков', 'Тимур', 'Петрович', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Казакова', 'Элла', 'Валентиновна', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Логинов', 'Велор', 'Романович', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Лихачёв', 'Тимур', 'Дмитрьевич', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Крылов', 'Григорий', 'Данилович', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Баранов', 'Аверьян', 'Германович', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('schoolkid', 'Пестов', 'Денис', 'Иванович', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Федосеева', 'Крис', 'Рубеновна', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Якушев', 'Рудольф', 'Георгьевич', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Вишняков', 'Ефим', 'Павлович', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Кононова', 'Габи', 'Глебовна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Архипова', 'Лейла', 'Павловна', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Егорова', 'Мирра', 'Юрьевна', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Беспалова', 'Ангелина', 'Авдеевна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Лихачёва', 'Аксинья', 'Аркадьевна', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Марков', 'Никифор', 'Михайлович', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('teacher', 'Гаврилов', 'Самуил', 'Адольфович', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Мельникова', 'Анита', 'Эльдаровна', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Воронов', 'Аркадий', 'Юлианович', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Елисеева', 'Лина', 'Владиславовна', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Власов', 'Вольдемар', 'Витальевич', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Евдокимова', 'Аэлита', 'Улебовна', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Власова', 'Аюна', 'Евгеньевна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Филиппова', 'Нева', 'Данииловна', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Меркушева', 'Сабрина', 'Федосеевна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Зуева', 'Патрисия', 'Алексеевна', 3);
insert into allreaders(type, surname, name, patronymic, id_library) values ('student', 'Овчинников', 'Кондрат', 'Филиппович', 5);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Смирнова', 'Элина', 'Валерьевна', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Мухин', 'Тихон', 'Валентинович', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Панова', 'Сара', 'Станиславовна', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Кошелева', 'Андриана', 'Валерьевна', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Бобров', 'Илья', 'Лаврентьевич', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Филатова', 'Тала', 'Вадимовна', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Беляев', 'Лазарь', 'Степанович', 2);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Хохлова', 'Маргарита', 'Григорьевна', 1);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Самойлова', 'Милда', 'Кимовна', 4);
insert into allreaders(type, surname, name, patronymic, id_library) values ('scientist', 'Мухин', 'Кондрат', 'Русланович', 3);

insert into workers values (1, 'worker', 'г. Малая Пурга, ул. Чудновского', 'Бастион');
insert into workers values (2, 'worker', 'г. Урай, ул. Ипатьевский пер', 'Декор');
insert into workers values (3, 'worker', 'г. Подпорожье, ул. Мантинская', 'Арт-стиль');
insert into workers values (4, 'worker', 'г. Касимов, ул. Никольская', 'Жилстрой');
insert into workers values (5, 'worker', 'г. Чурапча, ул. Тюрина', 'Стройспектр');
insert into workers values (6, 'worker', 'г. Чистополь, ул. Граничный пер', 'Выбор');
insert into workers values (7, 'worker', 'г. Тужа, ул. Гороховая  (Адмиралтейский)', 'ЭкоСтрой');
insert into workers values (8, 'worker', 'г. Вишневый, ул. Осенний б-р', 'Статус');
insert into workers values (9, 'worker', 'г. Лунино, ул. Бурлинский Переезд пер', 'Стандарт');
insert into workers values (10, 'worker', 'г. Ключи, ул. Текстильщиков 8-я', 'Прогресс');

insert into pensioners values (0, 'pensioner',123456);
insert into pensioners values (11, 'pensioner',61752);
insert into pensioners values (12, 'pensioner',70169);
insert into pensioners values (13, 'pensioner',52297);
insert into pensioners values (14, 'pensioner',83174);
insert into pensioners values (15, 'pensioner',42569);
insert into pensioners values (16, 'pensioner',8940);
insert into pensioners values (17, 'pensioner',62135);
insert into pensioners values (18, 'pensioner',25230);
insert into pensioners values (19, 'pensioner',85017);
insert into pensioners values (20, 'pensioner',85329);

insert into schoolkids values (21, 'schoolkid',17642, 1);
insert into schoolkids values (22, 'schoolkid',82431, 5);
insert into schoolkids values (23, 'schoolkid',23542, 3);
insert into schoolkids values (24, 'schoolkid',59859, 9);
insert into schoolkids values (25, 'schoolkid',67998, 7);
insert into schoolkids values (26, 'schoolkid',42343, 4);
insert into schoolkids values (27, 'schoolkid',56753, 10);
insert into schoolkids values (28, 'schoolkid',19527, 11);
insert into schoolkids values (29, 'schoolkid',38683, 4);
insert into schoolkids values (30, 'schoolkid',56756, 3);

insert into teachers values (31, 'teacher',14256, 'FIT');
insert into teachers values (32, 'teacher',12434, 'FF');
insert into teachers values (33, 'teacher',14124, 'MMF');
insert into teachers values (34, 'teacher',12434, 'MMF');
insert into teachers values (35, 'teacher',14256, 'ECO');
insert into teachers values (36, 'teacher',14256, 'BIO');
insert into teachers values (37, 'teacher',14124, 'FIT');
insert into teachers values (38, 'teacher',12434, 'FIT');
insert into teachers values (39, 'teacher',12434, 'HIMBIO');
insert into teachers values (40, 'teacher',18543, 'BIO');

insert into students values (41, 'student',14256, 'FIT', 18204);
insert into students values (42, 'student',12434, 'FF', 19104);
insert into students values (43, 'student',14124, 'MMF', 15203);
insert into students values (44, 'student',12434, 'MMF', 16302);
insert into students values (45, 'student',14256, 'ECO', 17203);
insert into students values (46, 'student',14256, 'BIO', 15890);
insert into students values (47, 'student',14124, 'FIT', 14765);
insert into students values (48, 'student',12434, 'FIT', 18234);
insert into students values (49, 'student',12434, 'HIMBIO', 20123);
insert into students values (50, 'student',18543, 'BIO', 19324);

insert into scientists values (51, 'scientist','г. Усть-Омчуг, ул. Куйбышева 2-я, дом 19',14256);
insert into scientists values (52, 'scientist', 'г. Усть-Омчуг, ул. Куйбышева 2-я, дом 19',14256);
insert into scientists values (53, 'scientist','г. Усть-Омчуг, ул. Куйбышева 2-я, дом 19',14256);
insert into scientists values (54, 'scientist','г. Колпна, ул. Загребский пр-кт, дом 88',12434);
insert into scientists values (55, 'scientist','г. Колпна, ул. Загребский пр-кт, дом 88',12434);
insert into scientists values (56, 'scientist','г. Колпна, ул. Загребский пр-кт, дом 88',12434);
insert into scientists values (57, 'scientist','г. Омутинский, ул. Шлиссельбургский пр-кт, дом 21',18543);
insert into scientists values (58, 'scientist','г. Омутинский, ул. Шлиссельбургский пр-кт, дом 21',18543);
insert into scientists values (59, 'scientist','г. Омутинский, ул. Шлиссельбургский пр-кт, дом 21',18543);
insert into scientists values (60, 'scientist','г. Омутинский, ул. Шлиссельбургский пр-кт, дом 21',18543);