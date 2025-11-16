CREATE TABLE camion (
  id SERIAL PRIMARY KEY,
  dominio VARCHAR(30) NOT NULL,
  nombreTransportista VARCHAR(120),
  telefono VARCHAR(40),
  capacidadPeso_kg NUMERIC(12,2),
  capacidadVolumen_m3 NUMERIC(10,3),
  disponible BOOLEAN DEFAULT TRUE,
  costoBasePorKm NUMERIC(10,2), 
  consumoPorKm NUMERIC(10,3) 
);

