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
DROP SEQUENCE IF EXISTS Characteristics_generator CASCADE;
DROP SEQUENCE IF EXISTS Information_generator CASCADE;
DROP SEQUENCE IF EXISTS Rules_generator CASCADE;
DROP SEQUENCE IF EXISTS Issued_generator CASCADE;


CREATE TABLE Libraries
(
    id_library integer PRIMARY KEY,
    quantity   integer NOT NULL
);

create sequence Information_generator
    as integer
    minvalue 1
    maxvalue 2147483647;


create sequence Characteristics_generator
    as integer
    minvalue 1
    maxvalue 2147483647;


create sequence Rules_generator
    as integer
    minvalue 1
    maxvalue 2147483647;

create sequence Issued_generator
    as integer
    minvalue 1
    maxvalue 2147483647;


CREATE TABLE Characteristic
(
    id_edition   integer not null PRIMARY KEY DEFAULT nextval('Characteristics_generator'),
    type_edition VARCHAR(50) NOT NULL,
    author       VARCHAR(150) NOT NULL,
    title        VARCHAR(150) NOT NULL
    --FOREIGN KEY (author, title) REFERENCES Information (id_information)  ON DELETE CASCADE
);

CREATE TABLE Information
(
    id_record integer not null DEFAULT nextval('Information_generator'),
    id_edition  integer not null,
    author      VARCHAR(150),
    composition VARCHAR(150) NOT NULL,
    popularity  integer,
    PRIMARY KEY (id_edition, id_record),
    FOREIGN KEY (id_edition) REFERENCES Characteristic(id_edition) ON DELETE CASCADE
);

create sequence Librarians_generator
    as integer
    minvalue 1
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
    id_record        integer not null PRIMARY KEY DEFAULT nextval('Issued_generator'),
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
    id_rule integer not null PRIMARY KEY DEFAULT nextval('Rules_generator'),
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

insert into Characteristic(type_edition,author,title) values ('Сборник','Чуковский','Сборник стихотворений А.С.Пушкина');
insert into Characteristic(type_edition,author,title) values ('Сборник','Гудзий','Сборник сочинений А.С.Пушкина');
insert into Characteristic(type_edition,author,title) values ('Сборник','Псков','Сборник стихотворений А.С.Пушкина');
insert into Characteristic(type_edition,author,title) values ('Сборник','Неизвестно','Сборник поэм и стихотворений М.Ю.Лермонтова');
insert into Characteristic(type_edition,author,title) values ('Сборник','Михельсон','Сборник стихотворений М.Ю.Лермонтова');

insert into Characteristic(type_edition,author,title) values ('Альбом','Ковальских','Параллелошар мира');
insert into Characteristic(type_edition,author,title) values ('Альбом','Ковальских','Ленинградский андеграунд');
insert into Characteristic(type_edition,author,title) values ('Альбом','Воинов','Фабула предмета');

insert into Characteristic(type_edition,author,title) values ('Научное издание','Бабошина','Образовательные подходы и технологии в высшей школе');
insert into Characteristic(type_edition,author,title) values ('Научное издание','Воронин','Актуальные проблемы аграрно-правовой науки в Российской Федерации');
insert into Characteristic(type_edition,author,title) values ('Научное издание','Горожанин','Российская полиция на страже имперской государственности');
insert into Characteristic(type_edition,author,title) values ('Научное издание','Ульянова','Межвузовский сборник научных статей докторантов и аспирантов');
insert into Characteristic(type_edition,author,title) values ('Научное издание','Корнилов','Актуальные вопросы филологии');
insert into Characteristic(type_edition,author,title) values ('Научное издание','Волкова','Гуманитарные аспекты профессионального образования');
insert into Characteristic(type_edition,author,title) values ('Научное издание','Быков','Всероссийский конкурс на лучшие работы студентов по техническим наукам');

insert into Characteristic(type_edition,author,title) values ('Справочник ','Прохоров','Большая советская энциклопедия');
insert into Characteristic(type_edition,author,title) values ('Справочник ','Мильчин','Издательский словарь-справочник');
insert into Characteristic(type_edition,author,title) values ('Справочник ','Интент','Справочник технического переводчика');
insert into Characteristic(type_edition,author,title) values ('Справочник ','Клубков','Словарь по информации, библиотечному и издательскому делу');
insert into Characteristic(type_edition,author,title) values ('Справочник ','Богданов','Российский гуманитарный энциклопедический словарь');

insert into Characteristic(type_edition,author,title) values ('Роман','Кинг','Лавка дурных снов');
insert into Characteristic(type_edition,author,title) values ('Роман','Кинг','Оно');
insert into Characteristic(type_edition,author,title) values ('Роман','Булгаков','Мастер и Маргарита');
insert into Characteristic(type_edition,author,title) values ('Роман','Булгаков','Белая гвардия');
insert into Characteristic(type_edition,author,title) values ('Роман','Достоевский','Преступление и наказание');
insert into Characteristic(type_edition,author,title) values ('Роман','Достоевский','Братья Карамзовы');
insert into Characteristic(type_edition,author,title) values ('Роман','Достоевский','Подросток');
insert into Characteristic(type_edition,author,title) values ('Роман','Достоевский','Идиот');
insert into Characteristic(type_edition,author,title) values ('Роман','Толстой','Война и мир');
insert into Characteristic(type_edition,author,title) values ('Роман','Толстой','Анна Каренина');
insert into Characteristic(type_edition,author,title) values ('Роман','Толстой','Воскресенье');
insert into Characteristic(type_edition,author,title) values ('Роман','Твен','Приключения Тома Сойера');
insert into Characteristic(type_edition,author,title) values ('Роман','Твен','Приключения Гекльберри Финна');

insert into Information(id_edition, author, composition, popularity) values (1,'Пушкин', 'Евгений Онегин', 0);
insert into Information(id_edition, author, composition, popularity) values (1,'Пушкин', 'Руслан и Людмила', 0);
insert into Information(id_edition, author, composition, popularity) values (1,'Пушкин', 'Сказка о рыбаке и рыбке', 0);

insert into Information(id_edition, author, composition, popularity) values (2,'Пушкин', 'Сказка о царе Салтане', 0);
insert into Information(id_edition, author, composition, popularity) values (2,'Пушкин', 'Медный всадник', 0);
insert into Information(id_edition, author, composition, popularity) values (2,'Пушкин', 'Полтава', 0);

insert into Information(id_edition, author, composition, popularity) values (3,'Пушкин', 'Сказка о золтом петушке', 0);
insert into Information(id_edition, author, composition, popularity) values (3,'Пушкин', 'Анчар', 0);
insert into Information(id_edition, author, composition, popularity) values (3,'Пушкин', 'Кавказский пленник', 0);

insert into Information(id_edition, author, composition, popularity) values (4,'Лермонтов', 'Мцыри', 0);
insert into Information(id_edition, author, composition, popularity) values (4,'Лермонтов', 'Бородино', 0);
insert into Information(id_edition, author, composition, popularity) values (4,'Лермонтов', 'Демон', 0);

insert into Information(id_edition, author, composition, popularity) values (5,'Лермонтов', 'Валерик', 0);
insert into Information(id_edition, author, composition, popularity) values (5,'Лермонтов', 'Беглец', 0);
insert into Information(id_edition, author, composition, popularity) values (5,'Лермонтов', 'Сашка', 0);

insert into Information(id_edition, author, composition, popularity) values (6,'Ковальских','Параллелошар мира',0);
insert into Information(id_edition, author, composition, popularity) values (7,'Ковальских','Ленинградский андеграунд',0);
insert into Information(id_edition, author, composition, popularity) values (8,'Воинов','Фабула предмета',0);

insert into Information(id_edition, author, composition, popularity) values (9,'Бабошина','Образовательные подходы и технологии в высшей школе',0);
insert into Information(id_edition, author, composition, popularity) values (10,'Воронин','Актуальные проблемы аграрно-правовой науки в Российской Федерации',0);
insert into Information(id_edition, author, composition, popularity) values (11,'Горожанин','Российская полиция на страже имперской государственности',0);
insert into Information(id_edition, author, composition, popularity) values (12,'Ульянова','Межвузовский сборник научных статей докторантов и аспирантов',0);
insert into Information(id_edition, author, composition, popularity) values (13,'Корнилов','Актуальные вопросы филологии',0);
insert into Information(id_edition, author, composition, popularity) values (14,'Волкова','Гуманитарные аспекты профессионального образования',0);
insert into Information(id_edition, author, composition, popularity) values (15,'Быков','Всероссийский конкурс на лучшие работы студентов по техническим наукам',0);

insert into Information(id_edition, author, composition, popularity) values (16,'Прохоров','Большая советская энциклопедия',0);
insert into Information(id_edition, author, composition, popularity) values (17,'Мильчин','Издательский словарь-справочник',0);
insert into Information(id_edition, author, composition, popularity) values (18,'Интент','Справочник технического переводчика',0);
insert into Information(id_edition, author, composition, popularity) values (19,'Клубков','Словарь по информации, библиотечному и издательскому делу',0);
insert into Information(id_edition, author, composition, popularity) values (20,'Богданов','Российский гуманитарный энциклопедический словарь',0);

insert into Information(id_edition, author, composition, popularity) values (21,'Кинг','Лавка дурных снов',0);
insert into Information(id_edition, author, composition, popularity) values (22,'Кинг','Оно',0);
insert into Information(id_edition, author, composition, popularity) values (23,'Булгаков','Мастер и Маргарита',0);
insert into Information(id_edition, author, composition, popularity) values (24,'Булгаков','Белая гвардия',0);
insert into Information(id_edition, author, composition, popularity) values (25,'Достоевский','Преступление и наказание',0);
insert into Information(id_edition, author, composition, popularity) values (26,'Достоевский','Братья Карамзовы',0);
insert into Information(id_edition, author, composition, popularity) values (27,'Достоевский','Подросток',0);
insert into Information(id_edition, author, composition, popularity) values (28,'Достоевский','Идиот',0);
insert into Information(id_edition, author, composition, popularity) values (29,'Толстой','Война и мир',0);
insert into Information(id_edition, author, composition, popularity) values (30,'Толстой','Анна Каренина',0);
insert into Information(id_edition, author, composition, popularity) values (31,'Толстой','Воскресенье',0);
insert into Information(id_edition, author, composition, popularity) values (32,'Твен','Приключения Тома Сойера',0);
insert into Information(id_edition, author, composition, popularity) values (33,'Твен','Приключения Гекльберри Финна',0);

insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (1,1,1,8,9,date '1999-01-16',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (2,3,1,3,6,date '1999-05-23',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (3,4,1,3,10,date '1999-11-14',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (4,5,3,5,8,date '1999-03-26',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (5,3,2,2,2,date '1999-09-03', NULL);

insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (6,3,2,2,1,date '2000-03-16',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (7,1,1,1,9,date '2001-12-21',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (8,3,1,7,9,date '2002-09-30',date '2020-06-10');

insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (9,2,1,1,9,date '2000-04-19', NULL);
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (10,2,3,1,2,date '2001-10-12',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (11,2,2,9,3,date '2002-08-01',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (12,3,1,10,7,date '2001-11-25',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (13,4,2,10,8,date '2000-11-08',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (14,1,1,9,3,date '2003-05-28', NULL);
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (15,5,1,10,9,date '2002-11-09',date '2010-06-10');

insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (16,2,4,2,5,date '2009-10-25',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (17,1,1,8,4,date '2000-12-07',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (18,1,1,6,9,date '2002-11-22',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (19,4,3,6,4,date '2007-05-24', NULL);
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (20,5,1,10,7,date '2009-03-23',date '2010-06-10');

insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (21,3,1,6,1,date '2005-08-27',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (22,4,3,8,8,date '2006-02-08', NULL);
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (23,2,4,2,4,date '1999-12-06',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (24,5,2,6,6,date '2001-05-18',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (25,1,1,8,3,date '2010-08-19',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (26,1,1,7,2,date '2008-04-04', NULL);
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (27,2,1,5,9,date '2006-07-02',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (28,5,3,7,2,date '2002-12-17',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (29,1,1,10,1,date '2006-04-06', NULL);
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (30,3,2,3,2,date '2007-10-22',date '2020-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (31,4,4,6,3,date '2003-01-28',date '2010-06-10');
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (32,2,2,8,4,date '2010-02-18', NULL);
insert into Editions(id_edition, id_library, hall_num, rack_num, shelf_num, date_adding, date_removing) values (33,5,2,8,1,date '2004-03-06',date '2020-06-10');

insert into Rules(id_edition, rule) values (1, 'Не читай книгу во время еды');      --5, 3 , 7 , 5 ,13
insert into Rules(id_edition, rule) values (2, 'Не клади в книгу карандашей');
insert into Rules(id_edition, rule) values (3, 'Не пиши в книге, не делай пометок');
insert into Rules(id_edition, rule) values (4, 'Не перегибай книгу: вырываются листы');
insert into Rules(id_edition, rule) values (5, 'Не загибай углы страниц – сделай закладку');

insert into Rules(id_edition, rule) values (6, 'Не пиши в книге, не делай пометок');
insert into Rules(id_edition, rule) values (7, 'Не пиши в книге, не делай пометок');
insert into Rules(id_edition, rule) values (8, 'Не перегибай книгу: вырываются листы');

insert into Rules(id_edition, rule) values (9, 'Не клади в книгу карандашей');
insert into Rules(id_edition, rule) values (10, 'Не читай книгу во время еды');
insert into Rules(id_edition, rule) values (11, 'Не клади в книгу карандашей');
insert into Rules(id_edition, rule) values (12, 'Не читай книгу во время еды');
insert into Rules(id_edition, rule) values (13, 'Не клади в книгу карандашей');
insert into Rules(id_edition, rule) values (14, 'Не читай книгу во время еды');
insert into Rules(id_edition, rule) values (15, 'Не клади в книгу карандашей');

insert into Rules(id_edition, rule) values (16, 'Не читай книгу во время еды');
insert into Rules(id_edition, rule) values (17, 'Не перегибай книгу: вырываются листы');
insert into Rules(id_edition, rule) values (18, 'Не читай книгу во время еды');
insert into Rules(id_edition, rule) values (19, 'Не перегибай книгу: вырываются листы');
insert into Rules(id_edition, rule) values (20, 'Не перегибай книгу: вырываются листы');


insert into Rules(id_edition, rule) values (21, 'Не перегибай книгу: вырываются листы');
insert into Rules(id_edition, rule) values (22, 'Оберни книгу в бумагу – не испортишь обложку');
insert into Rules(id_edition, rule) values (23, 'Не загибай углы страниц – сделай закладку');
insert into Rules(id_edition, rule) values (24, 'Оберни книгу в бумагу – не испортишь обложку');
insert into Rules(id_edition, rule) values (25, 'Не загибай углы страниц – сделай закладку');
insert into Rules(id_edition, rule) values (26, 'Оберни книгу в бумагу – не испортишь обложку');
insert into Rules(id_edition, rule) values (27, 'Не загибай углы страниц – сделай закладку');
insert into Rules(id_edition, rule) values (28, 'Оберни книгу в бумагу – не испортишь обложку');
insert into Rules(id_edition, rule) values (29, 'Не загибай углы страниц – сделай закладку');
insert into Rules(id_edition, rule) values (30, 'Не загибай углы страниц – сделай закладку');
insert into Rules(id_edition, rule) values (31, 'Оберни книгу в бумагу – не испортишь обложку');


insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (1,9,date '2003-03-12',date '2007-10-22',true,4);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (2,14,date '2007-05-27',NULL,false,3);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (3,5,date '2000-10-07',date '2002-01-19',true,8);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (4,31,date '2004-06-06',date '2005-10-02',true,10);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (5,19,date '2007-05-25',date '2008-05-24',true,13);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (6,13,date '2001-11-02',date '2002-10-03',true,10);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (7,21,date '2006-02-08',date '2015-03-29',true,8);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (8,3,date '2000-09-25',date '2002-05-13',true,11);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (9,27,date '2006-08-02',date '2010-01-22',true,5);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (10,1,date '1999-01-17',date '2003-07-17',true,3);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (11,22,date '2010-03-26',date '2011-08-01',false,12);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (12,6,date '2002-07-03',NULL,false,8);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (13,31,date '2006-12-28',date '2009-03-26',true,12);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (14,25,date '2011-04-12',date '2015-11-07',true,1);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (15,11,date '2004-06-13',date '2008-12-28',true,7);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (16,10,date '2002-10-15',date '2010-07-15',true,6);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (17,19,date '2009-02-18',date '2010-08-09',true,12);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (18,32,date '2011-05-13',NULL,false,6);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (19,27,date '2011-05-21',date '2016-04-18',true,5);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (20,4,date '2003-01-23',date '2006-02-07',true,14);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (21,15,date '2002-11-10',date '2009-11-03',true,14);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (22,9,date '2007-10-27', date '2010-02-16' ,true,5);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (23,30,date '2008-10-20',date '2010-09-28',true,9);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (24,18,date '2003-05-25',date '2006-03-18',true,2);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (25,22,date '2012-10-08',date '2014-02-28',true,10);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (26,28,date '2002-12-25',date '2009-12-25',true,14);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (27,7,date '2003-07-04',date '2007-08-02',true,6);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (28,33,date '2005-11-26',date '2015-03-11',true,15);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (29,12,date '2001-11-26',date '2020-02-16',true,8);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (30,9,date '2015-10-03',NULL,false,4);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (31,3,date '2006-06-18',date '2010-08-24',true,10);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (32,24,date '2002-08-27',date '2008-09-02',true,15);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (33,22,date '2018-04-15',NULL,false,13);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (34,27,date '2017-02-10',date '2020-01-28',true,7);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (35,17,date '2008-12-01',date '2009-03-19',true,1);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (36,26,date '2017-05-04',NULL,false,3);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (37,13,date '2005-11-04',date '2008-10-22',true,11);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (38,11,date '2016-03-23',date '2019-07-12',true,4);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (39,25,date '2012-06-12',date '2018-12-20',true,2);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (40,19,date '2019-04-20', NULL ,false,12);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (41,8,date '2006-11-14',date '2015-03-06',true,8);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (42,20,date '2009-03-24',date '2009-04-29',true,14);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (43,1,date '2005-11-09', date'2007-01-12' ,true,3);
insert into IssuedBooks(id_reader, id_edition, date_extradition, date_return, is_returned, id_librarian) values (44,18,date '2010-05-17',date '2018-12-20',true,2);