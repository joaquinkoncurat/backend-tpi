CREATE TABLE factura (
  id SERIAL PRIMARY KEY,
  solicitud_id INT NOT NULL,
  numero_factura VARCHAR(80) NOT NULL,
  fecha TIMESTAMP NOT NULL,
  total NUMERIC(12,2) NOT NULL,
  pagada BOOLEAN DEFAULT FALSE
);

CREATE TABLE detalle_factura (
  id SERIAL PRIMARY KEY,
  factura_id INT NOT NULL,
  descripcion VARCHAR(250),
  monto NUMERIC(12,2) NOT NULL
);

CREATE TABLE auditoria_costos (
  id SERIAL PRIMARY KEY,
  solicitud_id INT,
  fecha TIMESTAMP,
  costo_estimado NUMERIC(12,2),
  costo_real NUMERIC(12,2),
  notas TEXT
);