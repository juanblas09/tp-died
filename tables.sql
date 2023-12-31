--Creacion de tablas
CREATE TABLE sucursal (
    id_sucursal INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_sucursal PRIMARY KEY,
    nombre VARCHAR,
    horarioApertura INTEGER,
    horarioCierre INTEGER,
    operativa VARCHAR
);

CREATE TABLE producto (
    id_producto INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_producto PRIMARY KEY,
    nombre VARCHAR,
    descripcion VARCHAR,
    precioUnitario INTEGER,
    peso INTEGER
);

CREATE TABLE camino(
    id_camino INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_camino PRIMARY KEY,
    sucursalOrigen INTEGER,
    sucursalDestino INTEGER,
    tiempoTransito INTEGER,
    capacidadMaxima INTEGER,
    operativa VARCHAR
);

CREATE TABLE ordenProvision(
    id_orden INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_orden PRIMARY KEY,
    fecha DATE,
    sucursalDestino INTEGER,
    plazoMaximo INTEGER,
    estado INTEGER
);

CREATE TABLE ordenProvisionItem(
    id_orden INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_ordenitem PRIMARY KEY,
    orden INTEGER,
    cantidad INTEGER,
    producto INTEGER
);

CREATE TABLE stock(
    id_stock INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_id PRIMARY KEY,
    stock INTEGER
);

CREATE TABLE recorrido(
    id_recorrido INTEGER GENERATED ALWAYS AS IDENTITY CONSTRAINT pk_id PRIMARY KEY,
    recorrido VARCHAR
);

--Creacion FK

ALTER TABLE
    stock
ADD
    COLUMN id_sucursal INTEGER;

ALTER TABLE
    stock
ADD
    COLUMN id_producto INTEGER;

ALTER TABLE
	recorrido
ADD
	COLUMN id_orden INTEGER;
    
ALTER TABLE
    stock
ADD
    CONSTRAINT fk_sucursal FOREIGN KEY (id_sucursal) REFERENCES sucursal(id_sucursal);

ALTER TABLE
    stock
ADD
    CONSTRAINT fk_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto);

ALTER TABLE
    camino
ADD
    CONSTRAINT fk_sucursalOrigen FOREIGN KEY (sucursalOrigen) REFERENCES sucursal(id_sucursal);

ALTER TABLE
    camino
ADD
    CONSTRAINT fk_sucursalDestino FOREIGN KEY (sucursalDestino) REFERENCES sucursal(id_sucursal);

ALTER TABLE
    ordenProvision
ADD
    CONSTRAINT fk_sucursalDestino FOREIGN KEY (sucursalDestino) REFERENCES sucursal(id_sucursal);

ALTER TABLE
    ordenProvisionItem
ADD
    CONSTRAINT fk_orden FOREIGN KEY (orden) REFERENCES ordenProvision(id_orden);

ALTER TABLE
    ordenProvisionItem
ADD
    CONSTRAINT fk_producto FOREIGN KEY (producto) REFERENCES producto(id_producto);

ALTER TABLE
    recorrido
ADD
    CONSTRAINT fk_orden FOREIGN KEY (id_orden) REFERENCES ordenProvision(id_orden);