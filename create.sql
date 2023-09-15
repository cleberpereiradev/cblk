CREATE TABLE games (
    id SERIAL PRIMARY KEY,
    game_genre SMALLINT CHECK (game_genre BETWEEN 0 AND 9),
    hours_played NUMERIC(10, 1),
    rating NUMERIC(3, 1),
    release_date INTEGER NOT NULL,
    full_description TEXT,
    game_img_url VARCHAR(255),
    short_description VARCHAR(255),
    title VARCHAR(255)
);
-- Tabela 'profiles'
CREATE TABLE profiles (
    id SERIAL PRIMARY KEY,
    birthday DATE,
    about_text TEXT,
    course VARCHAR(255),
    img_url VARCHAR(255),
    name VARCHAR(255)
);

-- Tabela 'skills'
CREATE TABLE skills (
    id SERIAL PRIMARY KEY,
    level SMALLINT CHECK (level BETWEEN 0 AND 2),
    description TEXT,
    name VARCHAR(255),
    skill_img_url VARCHAR(255)
);

-- Tabela 'contents'
CREATE TABLE contents (
    id SERIAL PRIMARY KEY,
    media_type SMALLINT CHECK (media_type BETWEEN 0 AND 2),
    rating NUMERIC(3, 1) NOT NULL,
    author VARCHAR(255),
    content_link VARCHAR(255),
    full_description TEXT,
    short_description VARCHAR(255)
);

INSERT INTO profiles (name, birthday, course, img_url, about_text)
VALUES (
           'Cleber Pereira',
           '1994-09-26',
           'Desenvolvimento de Software Multiplataforma',
           'https://github.com/cleberpereiradev/imgs-cblk/blob/main/profile-img.jpg',
           'Me chamo Cleber, e este é o meu site pessoal, um espaço que abrange tanto a minha trajetória profissional quanto a minha vida pessoal. Atualmente, estou no 4º semestre do curso de Desenvolvimento de Software na Fatec de Itapira, onde me especializo no desenvolvimento back-end com Java, embora também esteja ampliando meus horizontes com o Angular. Sou um grande entusiasta de jogos, especialmente apaixonado por RPGs como Chrono Trigger e CyberPunk 2077, mas também curto desafios competitivos, como Valorant, e momentos descontraídos com jogos mobile e Stardew Valley. Fora do mundo virtual, minha paixão é estar em contato com a natureza, seja acampando, pescando ou compartilhando risadas com amigos. Este site é um reflexo da minha busca contínua por aprendizado e da minha apreciação pela vida em todas as suas formas. Agradeço por sua visita e convido você a fazer parte desta jornada. Se desejar entrar em contato ou compartilhar experiências, fique à vontade.'
       );

