CREATE TABLE pedidos(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    percentual_desconto DECIMAL(5,2) DEFAULT NULL,
    situacao_pedido VARCHAR(20) NOT NULL
);