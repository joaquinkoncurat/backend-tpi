CREATE TABLE contenedor (
  id SERIAL PRIMARY KEY,
  identificacion_unica VARCHAR(80) NOT NULL UNIQUE,
  peso_kg NUMERIC(10,2),
  volumen_m3 NUMERIC(10,3),
  estado VARCHAR(40), 
  cliente_id INT
);