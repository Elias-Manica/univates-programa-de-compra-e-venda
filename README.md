Este é um projeto a matéria PROGRAMAÇÃO DE APLICAÇÕES da Univates - Realizado por Elias Manica

Sequência de comandos para criação do banco de dados localmente:

create database projeto-de-vendas

CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(45),
    telefone VARCHAR(45),
    cnpj VARCHAR(45) NOT NULL
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    e_mail VARCHAR(45),
    cpf VARCHAR(45) NOT NULL,
    telefone VARCHAR(45)
);

CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(45),
    cep VARCHAR(10)
);

CREATE TABLE cliente_endereco (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    endereco_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE compra (
    id SERIAL PRIMARY KEY,
    data VARCHAR(45),
    fornecedor_id INT NOT NULL,
    FOREIGN KEY (fornecedor_id) REFERENCES fornecedor(id)
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(150),
    valor_unitario DECIMAL(10, 2),
    qtde_estoque VARCHAR(45)
);

CREATE TABLE item_compra (
    id SERIAL PRIMARY KEY,
    compra_id INT NOT NULL,
    produto_id INT NOT NULL,
    qtde DOUBLE PRECISION,
    valor DECIMAL(10, 2),
    FOREIGN KEY (compra_id) REFERENCES compra(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    data DATE,
    endereco_entrega VARCHAR(45),
    observacao VARCHAR(500),
    cliente_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE item_pedido (
    id SERIAL PRIMARY KEY,
    produto_id INT NOT NULL,
    pedido_id INT NOT NULL,
    qtde DOUBLE PRECISION,
    valor_item DECIMAL(10, 2),
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);
