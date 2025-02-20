-- Criando a tabela 'user' se não existir
CREATE TABLE IF NOT EXISTS user (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID único do usuário, gerado automaticamente',
    user_fullName VARCHAR(255) NOT NULL COMMENT 'Nome completo do usuário',
    user_email VARCHAR(255) NOT NULL UNIQUE COMMENT 'Endereço de e-mail único e validado',
    user_phone VARCHAR(20) COMMENT 'Número de telefone no formato internacional (ex.: +55 11 99999-9999)',
    user_birthDate DATE COMMENT 'Data de nascimento do usuário',
    user_userType ENUM('ADMIN', 'EDITOR', 'VIEWER') NOT NULL COMMENT 'Tipo de usuário: ADMIN, EDITOR ou VIEWER'
) COMMENT='Tabela de usuários do sistema';