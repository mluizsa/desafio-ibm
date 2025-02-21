CREATE OR REPLACE FUNCTION buscar_usuarios(parametro_busca TEXT)
RETURNS SETOF usersystem AS $$
BEGIN
  RETURN QUERY
  SELECT * FROM usersystem
   WHERE
    usersystem_full_name ILIKE '%' || parametro_busca || '%' OR
    usersystem_email ILIKE '%' || parametro_busca || '%' OR
    usersystem_phone ILIKE '%' || parametro_busca || '%';
END;
$$ LANGUAGE plpgsql;