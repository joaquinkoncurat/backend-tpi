CREATE TABLE ruta (
  id SERIAL PRIMARY KEY,
  solicitud_id INT NOT NULL,
  cantidad_tramos INT NOT NULL,
  distancia_total_km NUMERIC(8,2),
  duracion_total_min INT
);

CREATE TABLE tramo (
  id SERIAL PRIMARY KEY,
  ruta_id INT NOT NULL,
  tipo VARCHAR(30), 
  estado VARCHAR(30),
  costoAproximado NUMERIC(10,2),
  costoReal NUMERIC(10,2),
  fechaHoraInicio TIMESTAMP,
  fechaHoraFin TIMESTAMP,
  camion_id INT, 
  origen_id INT, 
  destino_id INT 
);