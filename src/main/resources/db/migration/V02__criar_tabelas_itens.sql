CREATE TABLE itens (
    id INT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(80) NOT NULL,
    preco_unitario DOUBLE NOT NULL,
    qtd INT NOT NULL,
    pedido_id VARCHAR(6) NOT NULL,
    PRIMARY KEY (id),
    INDEX idx_pedido (pedido_id ASC),
    CONSTRAINT fk_itens_pedido FOREIGN KEY (pedido_id)
        REFERENCES pedidos (id)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);
