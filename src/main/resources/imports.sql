-- Inserindo 30 tasks alternando user_id de 1 a 6
INSERT INTO tb_tarefa (nome, descricao, data, duracao, criado, completo, id_user) VALUES
('Tarefa01', 'descricao tarefa 01', '2025-08-01', 2, NOW(), false, 1),
('Tarefa02', 'descricao tarefa 02', '2025-08-02', 3, NOW(), false, 2),
('Tarefa03', 'descricao tarefa 03', '2025-08-03', 1, NOW(), true, 3),

-- Mesmo dia (03/08) para testar duplicados
('Tarefa04', 'descricao tarefa 04', '2025-08-03', 4, NOW(), false, 4),
('Tarefa05', 'descricao tarefa 05', '2025-08-03', 2, NOW(), false, 5),

('Tarefa06', 'descricao tarefa 06', '2025-08-05', 2, NOW(), false, 6),
('Tarefa07', 'descricao tarefa 07', '2025-08-07', 5, NOW(), false, 1),
('Tarefa08', 'descricao tarefa 08', '2025-08-08', 3, NOW(), false, 2),
('Tarefa09', 'descricao tarefa 09', '2025-08-09', 2, NOW(), false, 3),

-- Mesmo mês e semana (agosto, segunda semana)
('Tarefa10', 'descricao tarefa 10', '2025-08-10', 3, NOW(), false, 4),
('Tarefa11', 'descricao tarefa 11', '2025-08-11', 4, NOW(), false, 5),
('Tarefa12', 'descricao tarefa 12', '2025-08-12', 2, NOW(), false, 6),

('Tarefa13', 'descricao tarefa 13', '2025-08-13', 1, NOW(), true, 1),
('Tarefa14', 'descricao tarefa 14', '2025-08-14', 2, NOW(), false, 2),
('Tarefa15', 'descricao tarefa 15', '2025-08-15', 3, NOW(), false, 3),
('Tarefa16', 'descricao tarefa 16', '2025-08-16', 4, NOW(), false, 4),
('Tarefa17', 'descricao tarefa 17', '2025-08-17', 1, NOW(), true, 5),
('Tarefa18', 'descricao tarefa 18', '2025-08-18', 2, NOW(), false, 6),
('Tarefa19', 'descricao tarefa 19', '2025-08-19', 3, NOW(), false, 1),
('Tarefa20', 'descricao tarefa 20', '2025-08-20', 2, NOW(), false, 2),

-- Mesmo mês (agosto), outra semana
('Tarefa21', 'descricao tarefa 21', '2025-08-21', 4, NOW(), false, 3),
('Tarefa22', 'descricao tarefa 22', '2025-08-22', 2, NOW(), false, 4),
('Tarefa23', 'descricao tarefa 23', '2025-08-23', 1, NOW(), true, 5),
('Tarefa24', 'descricao tarefa 24', '2025-08-24', 3, NOW(), false, 6),
('Tarefa25', 'descricao tarefa 25', '2025-08-25', 2, NOW(), false, 1),
('Tarefa26', 'descricao tarefa 26', '2025-08-26', 2, NOW(), false, 2),
('Tarefa27', 'descricao tarefa 27', '2025-08-27', 3, NOW(), false, 3),
('Tarefa28', 'descricao tarefa 28', '2025-08-28', 4, NOW(), false, 4),
('Tarefa29', 'descricao tarefa 29', '2025-08-29', 2, NOW(), false, 5),
('Tarefa30', 'descricao tarefa 30', '2025-08-30', 1, NOW(), true, 6);
