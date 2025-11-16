CREATE TABLE cliente (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  telefono VARCHAR(40)
);

CREATE TABLE ubicacion (
  id SERIAL PRIMARY KEY,
  tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('ORIGEN','DESTINO','DEPOSITO')),
  direccion VARCHAR(200) NOT NULL,
  latitud DOUBLE PRECISION NOT NULL,
  longitud DOUBLE PRECISION NOT NULL
);

CREATE TABLE solicitud (
  id SERIAL PRIMARY KEY,
  numero VARCHAR(60) NOT NULL,
  contenedor_id INT,
  cliente_id INT NOT NULL,
  origen_id INT,
  destino_id INT,
  costoEstimado NUMERIC(12,2),
  tiempoEstimado INT, -- minutos
  costoFinal NUMERIC(12,2),
  tiempoRealMin INT,
  estado VARCHAR(40)
);