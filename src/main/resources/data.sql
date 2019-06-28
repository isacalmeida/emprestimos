INSERT into funcionario (nome, cpf, setor, cargo, data_de_admissao, observacao) VALUES 
('Bruno Fernandes Rodrigues', '000.111.222-33', 'vendas', 'atendente', '2012-02-15', 'funcionario bom'),
('Fernando Gonzaga', '111.222.333-44', 'comercial', 'vendedor externo', '2013-03-16', 'funcionario otimo'),
('Luiz Marenco', '222.333.444-55', 'administrativo', 'auxiliar', '2014-04-17', 'funcionario regular')
ON CONFLICT DO NOTHING;
