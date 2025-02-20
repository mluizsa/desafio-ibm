CREATE TYPE user_type AS ENUM ('ADMIN', 'EDITOR', 'VIEWER');

CREATE TABLE IF NOT EXISTS usersystem (
    usersystem_id BIGSERIAL PRIMARY KEY,
    usersystem_full_name VARCHAR(255) NOT NULL,
    usersystem_email VARCHAR(255) UNIQUE NOT NULL,
    usersystem_phone VARCHAR(20),
    usersystem_birth_date DATE,
    usersystem_user_type user_type NOT NULL
);

COMMENT ON TABLE usersystem IS 'Tabela de usuários do sistema';
COMMENT ON COLUMN usersystem.usersystem_id IS 'ID único do usuário, gerado automaticamente';
COMMENT ON COLUMN usersystem.usersystem_full_name IS 'Nome completo do usuário';
COMMENT ON COLUMN usersystem.usersystem_email IS 'Endereço de e-mail único e validado';
COMMENT ON COLUMN usersystem.usersystem_phone IS 'Número de telefone no formato internacional (ex.: +55 11 99999-9999)';
COMMENT ON COLUMN usersystem.usersystem_birth_date IS 'Data de nascimento do usuário';
COMMENT ON COLUMN usersystem.usersystem_user_type IS 'Tipo de usuário: ADMIN, EDITOR ou VIEWER';