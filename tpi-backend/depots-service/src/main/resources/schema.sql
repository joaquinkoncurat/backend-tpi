CREATE TABLE deposito (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(150) NOT NULL,
  direccion VARCHAR(250),
  latitud DOUBLE PRECISION,
  longitud DOUBLE PRECISION,
  costoEstadiaDiario NUMERIC(10,2)
);