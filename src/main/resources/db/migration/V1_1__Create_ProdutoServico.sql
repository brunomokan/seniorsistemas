CREATE TABLE produtos_servicos
(
    id  UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome                VARCHAR(60) NOT NULL,
    tipo_produto_sevico VARCHAR(20) NOT NULL,
    ativo               BOOLEAN     NOT NULL
);