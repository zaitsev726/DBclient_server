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

CREATE TABLE Librarians
(
    id_librarian integer PRIMARY KEY,
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

CREATE TABLE AllReaders
(
    id_reader  integer PRIMARY KEY,
    type       VARCHAR(50),
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

insert into allreaders values (1, 'worker', 'Агафонов', 'Константин', 'Федосеевич', 1);
insert into allreaders values (2, 'worker', 'Белоусова', 'Виктория', 'Рудольфовна', 4);
insert into allreaders values (3, 'worker', 'Кудряшов', 'Роберт', 'Лукьевич', 4);
insert into allreaders values (4, 'worker', 'Королёв', 'Дональд', 'Дмитриевич', 4);
insert into allreaders values (5, 'worker', 'Блинов', 'Роман', 'Всеволодович', 2);
insert into allreaders values (6, 'worker', 'Логинова', 'Амира', 'Евгеньевна', 3);
insert into allreaders values (7, 'worker', 'Лобанова', 'Эжени', 'Куприяновна', 5);
insert into allreaders values (8, 'worker', 'Шашкова', 'Триана', 'Дамировна', 3);
insert into allreaders values (9, 'worker', 'Ковалёв', 'Архип', 'Альвианович', 2);
insert into allreaders values (10, 'worker', 'Носков', 'Любомир', 'Олегович', 1);
insert into allreaders values (11, 'pensioners', 'Одинцов', 'Альфред', 'Русланович', 4);
insert into allreaders values (12, 'pensioners', 'Козлова', 'Юфеза', 'Феликсовна', 2);
insert into allreaders values (13, 'pensioners', 'Молчанова', 'Ляля', 'Созоновна', 4);
insert into allreaders values (14, 'pensioners', 'Никонов', 'Терентий', 'Семёнович', 3);
insert into allreaders values (15, 'pensioners', 'Николаева', 'Дарина', 'Онисимовна', 1);
insert into allreaders values (16, 'pensioners', 'Устинов', 'Аскольд', 'Оскарович', 5);
insert into allreaders values (17, 'pensioners', 'Щукин', 'Эрнест', 'Кириллович', 5);
insert into allreaders values (18, 'pensioners', 'Титова', 'Наоми', 'Петровна', 4);
insert into allreaders values (19, 'pensioners', 'Филиппов', 'Игорь', 'Лукьянович', 3);
insert into allreaders values (20, 'pensioners', 'Исакова', 'Уля', 'Валерьевна', 4);
insert into allreaders values (21, 'schoolkid', 'Борисов', 'Гурий', 'Пантелеймонович', 5);
insert into allreaders values (22, 'schoolkid', 'Зиновьев', 'Лука', 'Михайлович', 2);
insert into allreaders values (23, 'schoolkid', 'Петрова', 'Агата', 'Тимофеевна', 4);
insert into allreaders values (24, 'schoolkid', 'Горшков', 'Тимур', 'Петрович', 5);
insert into allreaders values (25, 'schoolkid', 'Казакова', 'Элла', 'Валентиновна', 1);
insert into allreaders values (26, 'schoolkid', 'Логинов', 'Велор', 'Романович', 2);
insert into allreaders values (27, 'schoolkid', 'Лихачёв', 'Тимур', 'Дмитрьевич', 1);
insert into allreaders values (28, 'schoolkid', 'Крылов', 'Григорий', 'Данилович', 3);
insert into allreaders values (29, 'schoolkid', 'Баранов', 'Аверьян', 'Германович', 4);
insert into allreaders values (30, 'schoolkid', 'Пестов', 'Денис', 'Иванович', 5);
insert into allreaders values (31, 'teacher', 'Федосеева', 'Крис', 'Рубеновна', 2);
insert into allreaders values (32, 'teacher', 'Якушев', 'Рудольф', 'Георгьевич', 1);
insert into allreaders values (33, 'teacher', 'Вишняков', 'Ефим', 'Павлович', 4);
insert into allreaders values (34, 'teacher', 'Кононова', 'Габи', 'Глебовна', 4);
insert into allreaders values (35, 'teacher', 'Архипова', 'Лейла', 'Павловна', 3);
insert into allreaders values (36, 'teacher', 'Егорова', 'Мирра', 'Юрьевна', 5);
insert into allreaders values (37, 'teacher', 'Беспалова', 'Ангелина', 'Авдеевна', 4);
insert into allreaders values (38, 'teacher', 'Лихачёва', 'Аксинья', 'Аркадьевна', 1);
insert into allreaders values (39, 'teacher', 'Марков', 'Никифор', 'Михайлович', 3);
insert into allreaders values (40, 'teacher', 'Гаврилов', 'Самуил', 'Адольфович', 2);
insert into allreaders values (41, 'student', 'Мельникова', 'Анита', 'Эльдаровна', 2);
insert into allreaders values (42, 'student', 'Воронов', 'Аркадий', 'Юлианович', 2);
insert into allreaders values (43, 'student', 'Елисеева', 'Лина', 'Владиславовна', 5);
insert into allreaders values (44, 'student', 'Власов', 'Вольдемар', 'Витальевич', 4);
insert into allreaders values (45, 'student', 'Евдокимова', 'Аэлита', 'Улебовна', 5);
insert into allreaders values (46, 'student', 'Власова', 'Аюна', 'Евгеньевна', 4);
insert into allreaders values (47, 'student', 'Филиппова', 'Нева', 'Данииловна', 3);
insert into allreaders values (48, 'student', 'Меркушева', 'Сабрина', 'Федосеевна', 4);
insert into allreaders values (49, 'student', 'Зуева', 'Патрисия', 'Алексеевна', 3);
insert into allreaders values (50, 'student', 'Овчинников', 'Кондрат', 'Филиппович', 5);
insert into allreaders values (51, 'scientist', 'Смирнова', 'Элина', 'Валерьевна', 1);
insert into allreaders values (52, 'scientist', 'Мухин', 'Тихон', 'Валентинович', 4);
insert into allreaders values (53, 'scientist', 'Панова', 'Сара', 'Станиславовна', 2);
insert into allreaders values (54, 'scientist', 'Кошелева', 'Андриана', 'Валерьевна', 1);
insert into allreaders values (55, 'scientist', 'Бобров', 'Илья', 'Лаврентьевич', 4);
insert into allreaders values (56, 'scientist', 'Филатова', 'Тала', 'Вадимовна', 1);
insert into allreaders values (57, 'scientist', 'Беляев', 'Лазарь', 'Степанович', 2);
insert into allreaders values (58, 'scientist', 'Хохлова', 'Маргарита', 'Григорьевна', 1);
insert into allreaders values (59, 'scientist', 'Самойлова', 'Милда', 'Кимовна', 4);
insert into allreaders values (60, 'scientist', 'Мухин', 'Кондрат', 'Русланович', 3);



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

insert into pensioners values (11, 'pensioners',61752);
insert into pensioners values (12, 'pensioners',70169);
insert into pensioners values (13, 'pensioners',52297);
insert into pensioners values (14, 'pensioners',83174);
insert into pensioners values (15, 'pensioners',42569);
insert into pensioners values (16, 'pensioners',8940);
insert into pensioners values (17, 'pensioners',62135);
insert into pensioners values (18, 'pensioners',25230);
insert into pensioners values (19, 'pensioners',85017);
insert into pensioners values (20, 'pensioners',85329);