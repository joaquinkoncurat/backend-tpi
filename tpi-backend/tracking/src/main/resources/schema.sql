CREATE TABLE tracking_tramo (
  id SERIAL PRIMARY KEY,
  tramo_id INT NOT NULL,
  fecha_inicio TIMESTAMP,
  fecha_fin TIMESTAMP,
  estado VARCHAR(40)
);