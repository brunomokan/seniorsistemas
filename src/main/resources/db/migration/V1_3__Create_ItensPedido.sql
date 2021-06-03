CREATE TABLE itens_pedidos
(
    id    UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    pedido_id          UUID           NOT NULL,
    produto_servico_id UUID           NOT NULL,
    quantidade         DECIMAL(10, 2) NOT NULL,
    valor_unitario     DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES "pedidos" (id),
    FOREIGN KEY (produto_servico_id) REFERENCES "produtos_servicos" (id));