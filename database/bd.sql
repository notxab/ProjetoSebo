CREATE TABLE usuario ( 
    id_usuario INT AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(35) NOT NULL, 
    senha VARCHAR(30) NOT NULL 
);


CREATE TABLE prateleira ( 
    id_prateleira INT AUTO_INCREMENT PRIMARY KEY, 
    numero INTEGER NULL, 
    tipo VARCHAR(30) NULL, 
    lugar VARCHAR(50) NULL 
);


CREATE TABLE venda ( 
    id_venda INT AUTO_INCREMENT PRIMARY KEY, 
    codigo_recibo VARCHAR(50) UNIQUE, 
    data_venda DATETIME, 
    valor_total DECIMAL(10,2) NOT NULL, 
    forma_pagamento VARCHAR(20) NOT NULL, 
    id_usuario INT, 
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) 
);


CREATE TABLE livro ( 
    id_livro INT AUTO_INCREMENT PRIMARY KEY, 
    titulo VARCHAR(100) NOT NULL, 
    autor VARCHAR(50) NOT NULL, -- Removi o unique pq se nao, nao daria pra ter mais de um autor
    genero VARCHAR(50) NULL, 
    preco DECIMAL(10,2) NOT NULL, 
    status1 VARCHAR(20) DEFAULT 'disponivel', 
    id_prateleira INT, 
    id_venda INT, 
    FOREIGN KEY (id_prateleira) REFERENCES prateleira(id_prateleira), 
    FOREIGN KEY (id_venda) REFERENCES venda(id_venda) 
);

DELIMITER $$

--
-- PROCEDURES DE USUARIO
-- 
CREATE PROCEDURE insere_usuario(u_nome VARCHAR(50), u_senha VARCHAR(30)) 
BEGIN 
    INSERT INTO usuario(nome, senha) VALUES(u_nome, u_senha); 
    SELECT * FROM usuario; 
END$$


CREATE PROCEDURE delete_usuario(ua_id INT) 
BEGIN 
    DELETE FROM usuario WHERE id_usuario = ua_id; 
    SELECT * FROM usuario; 
END$$


CREATE PROCEDURE sel_usuarios() 
BEGIN 
    SELECT id_usuario, nome FROM usuario ORDER BY id_usuario; 
END$$

CREATE PROCEDURE up_usuario(IN upe_usuario INT, IN up_nome VARCHAR(50), IN up_senha VARCHAR(30)) 
BEGIN 
    UPDATE usuario SET nome = up_nome, senha = up_senha WHERE id_usuario = upe_usuario; 
END$$

-- 
-- PROCEDURES DE PRATELEIRA
--
CREATE PROCEDURE insere_prateleira(p_numero INTEGER, p_lugar VARCHAR(50), p_tipo VARCHAR(30)) 
BEGIN 
    INSERT INTO prateleira(numero, lugar, tipo) VALUES (p_numero, p_lugar, p_tipo); 
    SELECT * FROM prateleira; 
END$$

CREATE PROCEDURE delete_prateleira(pd_id INT) 
BEGIN 
    DELETE FROM prateleira WHERE id_prateleira = pd_id; 
    SELECT * FROM prateleira; 
END$$

CREATE PROCEDURE sel_prateleira() 
BEGIN 
    SELECT id_prateleira, numero, lugar, tipo FROM prateleira ORDER BY id_prateleira; 
END$$

CREATE PROCEDURE up_prateleira(IN upe_prateleira INT, IN up_numero INT, IN up_lugar VARCHAR(50), IN up_tipo VARCHAR(30)) 
BEGIN 
    UPDATE prateleira SET numero = up_numero, lugar = up_lugar, tipo = up_tipo WHERE id_prateleira = upe_prateleira; 
END$$

--
-- PROCEDURES DE LIVRO
-- 
CREATE PROCEDURE insere_livro(l_titulo VARCHAR(100), l_autor VARCHAR(50), l_genero VARCHAR(50), l_preco DECIMAL(10,2), l_status VARCHAR(20), l_id_prateleira INT) 
BEGIN 
    IF l_preco <= 0 THEN 
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Valor unitario deve ser maior que zero'; 
    END IF;

    INSERT INTO livro(titulo, autor, genero, preco, status1, id_prateleira) 
    VALUES (l_titulo, l_autor, l_genero, l_preco, l_status, l_id_prateleira); 
    
    SELECT * FROM livro; 
END$$


CREATE PROCEDURE delete_livro(ld_id INT) 
BEGIN 
    DELETE FROM livro WHERE id_livro = ld_id; 
    SELECT * FROM livro; 
END$$


CREATE PROCEDURE sel_livro() 
BEGIN 
    SELECT id_livro, titulo, autor, genero, preco, status1, id_prateleira, id_venda FROM livro ORDER BY id_livro; 
END$$


CREATE PROCEDURE up_livro(IN upe_livro INT, IN up_titulo VARCHAR(100), IN up_autor VARCHAR(50), IN up_genero VARCHAR(50), IN up_preco DECIMAL(10,2), IN up_status VARCHAR(20), IN up_prateleira INT, IN up_venda INT) 
BEGIN 
    UPDATE livro 
    SET titulo = up_titulo, autor = up_autor, genero = up_genero, preco = up_preco, status1 = up_status, id_prateleira = up_prateleira, id_venda = up_venda 
    WHERE id_livro = upe_livro; 
END$$

--
-- PROCEDURES DE VENDA
-- 
CREATE PROCEDURE insere_venda(n_valor DECIMAL(10,2), n_codigo VARCHAR(50), n_formapag VARCHAR(20), n_data DATETIME, n_id_usuario INT) 
BEGIN 
    INSERT INTO venda(valor_total, codigo_recibo, forma_pagamento, data_venda, id_usuario) 
    VALUES (n_valor, n_codigo, n_formapag, n_data, n_id_usuario); 
    SELECT * FROM venda; 
END$$


CREATE PROCEDURE delete_venda(nd_id INT) 
BEGIN 
    DELETE FROM venda WHERE id_venda = nd_id; 
    SELECT * FROM venda; 
END$$


CREATE PROCEDURE sel_venda() 
BEGIN 
    SELECT id_venda, valor_total, codigo_recibo, forma_pagamento, data_venda, id_usuario FROM venda ORDER BY id_venda; 
END$$


CREATE PROCEDURE up_venda(IN upe_venda INT, IN up_valor DECIMAL(10,2), IN up_codigo VARCHAR(50), IN up_formapag VARCHAR(20), IN up_data DATETIME, IN up_usuario INT) 
BEGIN 
    UPDATE venda 
    SET valor_total = up_valor, codigo_recibo = up_codigo, forma_pagamento = up_formapag, data_venda = up_data, id_usuario = up_usuario 
    WHERE id_venda = upe_venda; 
END$$


DELIMITER ;


DELIMITER $$

DROP PROCEDURE IF EXISTS up_livro$$

CREATE PROCEDURE up_livro(
    IN upe_livro INT, 
    IN up_titulo VARCHAR(100), 
    IN up_autor VARCHAR(50), 
    IN up_genero VARCHAR(50), 
    IN up_preco DECIMAL(10,2), 
    IN up_status VARCHAR(20), 
    IN up_prateleira INT
) 
BEGIN 
    UPDATE livro 
    SET titulo = up_titulo, 
        autor = up_autor, 
        genero = up_genero, 
        preco = up_preco, 
        status1 = up_status, 
        id_prateleira = up_prateleira
    WHERE id_livro = upe_livro; 
END$$

DELIMITER ;

