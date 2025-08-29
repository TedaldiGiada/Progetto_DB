-- Database Section
-- ________________ 

DROP DATABASE IF EXISTS dbAgriTerra;

CREATE DATABASE dbAgriTerra;
USE dbAgriTerra;

-- DBSpace Section
-- _______________


-- Tables Section
-- _____________ 

CREATE TABLE Dipendente (
    CF 			CHAR(16) 		PRIMARY KEY,
    nome		VARCHAR(100) 	NOT NULL,
    cognome		VARCHAR(100) 	NOT NULL,
    telefono	VARCHAR(20),
    via			VARCHAR(100),
    num_civ		VARCHAR(10),
    città	    VARCHAR(50),
    note	    VARCHAR(255)
);

CREATE TABLE Cliente (
    CF 			    CHAR(16) 		PRIMARY KEY,
    partita_IVA		CHAR(20) 		UNIQUE,
    nome			VARCHAR(100) 	NOT NULL,
    cognome		    VARCHAR(100) 	NOT NULL,
    via			    VARCHAR(100)	NOT NULL,
    num_civ		    VARCHAR(10) 	NOT NULL,
    città			VARCHAR(50) 	NOT NULL,
    telefono		VARCHAR(20),
    tipo			VARCHAR(50),
    email			VARCHAR(100)
);

CREATE TABLE Fornitore (
    ID_Fornitore	INT 			PRIMARY KEY,
    nome_azienda	VARCHAR(100) 	NOT NULL,
    via			    VARCHAR(100) 	NOT NULL,
    num_civ		    VARCHAR(10) 	NOT NULL,
    città			VARCHAR(50) 	NOT NULL,
    partita_IVA		CHAR(20) 		NOT NULL,
    tipo			VARCHAR(50)
);

CREATE TABLE Spesa_Agricola (
    ID_Spesa		INT 			PRIMARY KEY,
    data			DATETIME 		NOT NULL,
    desccrizione	VARCHAR(255) 	NOT NULL,
    prezzo		    DECIMAL(10,2) 	NOT NULL CHECK(prezzo >= 0),
    anno			INT 			NOT NULL,
    ID_Fornitore	INT 			NOT NULL,
    FOREIGN KEY(ID_Fornitore) REFERENCES Fornitore(ID_Fornitore)
    	ON DELETE CASCADE 
        ON UPDATE NO ACTION
);

CREATE TABLE Pianta (
    ID_Pianta		    INT 			PRIMARY KEY,
    tipo 			    VARCHAR(50) 	NOT NULL,
    stagione 		    VARCHAR(50) 	NOT NULL,
    nome			    VARCHAR(100) 	NOT NULL,
    durata_colt_media	INT 			NOT NULL,
    descrizione		    VARCHAR(255),
    ID_Spesa		    INT 			NOT NULL,
    FOREIGN KEY(ID_Spesa) REFERENCES Spesa_Agricola(ID_Spesa)
    	ON DELETE CASCADE 
 		ON UPDATE NO ACTION
);

CREATE TABLE Trattamento (
    ID_Trattamento	INT 			PRIMARY KEY,
    data			DATETIME 		NOT NULL,
    qta			    DECIMAL(10,2) 	CHECK(qta>=0),
    costo			DECIMAL(10,2) 	CHECK(costo>=0),
    tipo			VARCHAR(50),
    descrizione 	VARCHAR(255),
    ID_Spesa		INT 			NOT NULL,
    FOREIGN KEY(ID_Spesa) REFERENCES Spesa_Agricola(ID_Spesa)
    	ON DELETE CASCADE 
 	    ON UPDATE NO ACTION
);

CREATE TABLE Terreno (
    ID_Terreno		INT 			PRIMARY KEY,
    ettari			DECIMAL(10,2) 	NOT NULL CHECK(ettari >= 0),
    descrizione		VARCHAR(255),
    tipo			VARCHAR(50),
    stato 			VARCHAR(50),
    ID_Trattamento	INT,
    FOREIGN KEY(ID_Trattamento) REFERENCES Trattamento(ID_Trattamento)
    	ON DELETE SET NULL 
 	    ON UPDATE NO ACTION
);

CREATE TABLE Vendita (
    ID_Vendita		INT 			PRIMARY KEY,
    prezzo		    DECIMAL(10,2) 	NOT NULL CHECK(prezzo>=0),
    qta			    INT 			NOT NULL CHECK(qta>=0),
    descrizione		VARCHAR(255),
    data		    DATETIME		NOT NULL,
    CF 			    CHAR(16) 		NOT NULL,
    FOREIGN KEY(CF) REFERENCES Cliente(CF)
    	ON DELETE CASCADE 
 	    ON UPDATE NO ACTION
);

CREATE TABLE Macchinario (
    ID_Macchinario	VARCHAR(9) 			PRIMARY KEY,
    marca_modello	VARCHAR(100) 	NOT NULL,
    nome			VARCHAR(100) 	NOT NULL,
    categoria		VARCHAR(50),
    descrizione		VARCHAR(255),
    ID_Spesa		INT 			NOT NULL,
    FOREIGN KEY(ID_Spesa) REFERENCES Spesa_Agricola(ID_Spesa)
    	ON DELETE CASCADE 
 	    ON UPDATE NO ACTION
);

CREATE TABLE Assegnazione_Trattamento (
    CF	 			CHAR(16) 		NOT NULL,
    ID_Trattamento 	INT 			NOT NULL,
    data 			DATETIME 		NOT NULL,
    Note 			VARCHAR(255),
    PRIMARY KEY(CF, ID_Trattamento, data),
    FOREIGN KEY(CF) REFERENCES Dipendente(CF)
    	ON DELETE CASCADE
		ON UPDATE NO ACTION,
    FOREIGN KEY(ID_Trattamento) REFERENCES Trattamento(ID_Trattamento)
    	ON DELETE CASCADE 
		ON UPDATE NO ACTION
);

CREATE TABLE Assegnazione_Macchinario (
    CF 				CHAR(16)		NOT NULL,
    ID_Macchinario 	VARCHAR(9) 			NOT NULL,
    data 			DATETIME 		NOT NULL,
    Note 			VARCHAR(255),
    PRIMARY KEY(CF, ID_Macchinario, data),
    FOREIGN KEY(CF) REFERENCES Dipendente(CF)
    	ON DELETE CASCADE 
    	ON UPDATE NO ACTION,
    FOREIGN KEY(ID_Macchinario) REFERENCES Macchinario(ID_Macchinario)
    	ON DELETE CASCADE 
    	ON UPDATE NO ACTION
);

CREATE TABLE Assegnazione_Terreno (
    ID_Asssegnazione	 INT PRIMARY KEY,
    data_inizio		 	 DATETIME 		NOT NULL,
    data_fine		 	 DATETIME,
    responsabilità	 	 CHAR(16)		NOT NULL,
    descrizione	         VARCHAR(255),
    ID_Terreno		 	 INT			NOT NULL,
    CF 			 		 CHAR(16) 		NOT NULL,
    FOREIGN KEY(ID_Terreno) REFERENCES Terreno(ID_Terreno)
    	ON DELETE CASCADE 
    	ON UPDATE NO ACTION,
    FOREIGN KEY(CF) REFERENCES Dipendente(CF)
    	ON DELETE CASCADE
	 	ON UPDATE NO ACTION
);
   
CREATE TABLE Busta_Paga (
    ID_BustaPaga 	INT 			PRIMARY KEY,
    CF 				CHAR(16) 		NOT NULL,
    descrizione	    VARCHAR(255) 	NOT NULL,
    DataPagamento 	DATETIME 		NOT NULL,
    valore			DECIMAL(10,2)   NOT NULL CHECK(valore>=0),
    FOREIGN KEY(CF) REFERENCES Dipendente(CF)
    	ON DELETE CASCADE 
        ON UPDATE NO ACTION
);

CREATE TABLE Ciclo_Colturale (
    ID_Ciclo		INT 			PRIMARY KEY,
    ID_Pianta		INT 			NOT NULL,
    anno		    INT 			NOT NULL,
    data_inizio		DATETIME 		NOT NULL,
    data_fine		DATETIME,
    rendimento 	    INT,
    unità_misura	VARCHAR(10),
    descrizione 	VARCHAR(255),
    ID_Terreno		INT 			NOT NULL,
    ID_Vendita		INT,
    FOREIGN KEY(ID_Pianta) REFERENCES Pianta(ID_Pianta)
    	ON DELETE CASCADE 
    	ON UPDATE NO ACTION,
    FOREIGN KEY(ID_Terreno) REFERENCES Terreno(ID_Terreno)
            ON DELETE CASCADE 
            ON UPDATE NO ACTION,
    FOREIGN KEY(ID_Vendita) REFERENCES Vendita(ID_Vendita)
            ON DELETE SET NULL 
            ON UPDATE NO ACTION
);

CREATE TABLE Manutenzione_Riparazione (
    ID_Manutenzione	INT 			PRIMARY KEY,
    azienda		    VARCHAR(100)	NOT NULL,
    descrizione		VARCHAR(255) 	NOT NULL,
    tipo			VARCHAR(50) 	NOT NULL,
    ID_Spesa		INT 			NOT NULL,
    ID_Macchinario	VARCHAR(9) 			NOT NULL,
    FOREIGN KEY(ID_Spesa) REFERENCES Spesa_Agricola(ID_Spesa)
    	ON DELETE CASCADE 
		ON UPDATE NO ACTION,
    FOREIGN KEY(ID_Macchinario) REFERENCES Macchinario(ID_Macchinario)
        ON DELETE CASCADE 
 		ON UPDATE NO ACTION
);


CREATE TABLE Utilizzo_Macchinario (
    ID_Utilizzo		    INT 			PRIMARY KEY,
    data_utilizzo		DATETIME 		NOT NULL,
    ore_utilizzo		DECIMAL(5,2) 	NOT NULL CHECK(ore_utilizzo>=0),
    tipo_operazione	    VARCHAR(50) 	NOT NULL,
    ID_Terreno		    INT 			NOT NULL,
    CF 			        CHAR(16) 		NOT NULL,
    ID_Ciclo		    INT 			NOT NULL,
    ID_Macchinario	    VARCHAR(9) 			NOT NULL,
    FOREIGN KEY(ID_Terreno) REFERENCES Terreno(ID_Terreno)
    	ON DELETE CASCADE 
 		ON UPDATE NO ACTION,
    FOREIGN KEY(CF) REFERENCES Dipendente(CF)
    	ON DELETE CASCADE 
 		ON UPDATE NO ACTION,
    FOREIGN KEY(ID_Ciclo) REFERENCES Ciclo_Colturale(ID_Ciclo)
		ON DELETE CASCADE 
 		ON UPDATE NO ACTION,
    FOREIGN KEY(ID_Macchinario) REFERENCES Macchinario(ID_Macchinario)
		ON DELETE CASCADE 
 		ON UPDATE NO ACTION
);
CREATE TABLE Valore_di_Mercato (
    ID_Valore			INT 			PRIMARY KEY,
    stagione			VARCHAR(50) 	NOT NULL,
    prezzo_unitario		DECIMAL(10,2) 	NOT NULL CHECK(prezzo_unitario>=0),
    anno				INT 			NOT NULL,
    data_aggiornamento	DATETIME 		NOT NULL,
    ID_Pianta	 		INT 			NOT NULL,
    FOREIGN KEY(ID_Pianta) REFERENCES Pianta(ID_Pianta)
    	ON DELETE CASCADE 
 		ON UPDATE NO ACTION
);

-- Insertions Section
-- _____________ 

INSERT INTO Dipendente VALUES
('RSSMRA80A01H501Z','Mario','Rossi','333111222','Via Roma','10','Firenze','Capo squadra'),
('VRDLGI85B02H501X','Luigi','Verdi','333444555','Via Milano','22','Firenze','Operaio'),
('BNCGPP70A01H501F','Giuseppe','Bianchi','333777888','Via Napoli','5','Pisa','Operaio stagionale');

INSERT INTO Cliente VALUES
('BNCGPP70A01H501F',NULL,'Giuseppe','Bianchi','Via Napoli','5','Pisa','333777888','Privato','g.bianchi@email.com'),
('CNTPLA85C15H501Y','IT1122334455','Consorzio','Planta','Via Lazio','20','Roma','055222333','Pubblico','info@consorzioplanta.it');

INSERT INTO Fornitore VALUES
(1,'AgriFor Srl','Via Campagna','5','Bologna','IT12345678901','Fertilizzanti'),
(2,'TecnoMacchine','Via Industria','8','Parma','IT98765432100','Macchinari'),
(3,'GreenSeed','Via Veneto','15','Roma','IT55566677788','Semi e piante');

INSERT INTO Spesa_Agricola VALUES
(1,'2025-02-15','Acquisto fertilizzante',150.00,2025,1),
(2,'2025-03-10','Acquisto trattore usato',8000.00,2025,2),
(3,'2025-03-15','Acquisto semi di grano',500.00,2025,3);

INSERT INTO Macchinario VALUES
(1,'FIAT 505','Trattore','Agricolo','Trattore per aratura',2),
(2,'Claas Dominator','Mietitrebbia','Agricolo','Mietitrebbia per raccolto cereali',2);

INSERT INTO Manutenzione_Riparazione VALUES
(1,'Officina Meccanica Rossi','Sostituzione cinghia','Riparazione',2,1),
(2,'TecnoService','Tagliando annuale','Manutenzione',2,2);

INSERT INTO Pianta VALUES
(1,'Cereale','Primavera','Grano',120,'Coltura cerealicola',3),
(2,'Ortaggio','Estate','Pomodoro',90,'Coltura in serra',3);

INSERT INTO Trattamento VALUES
(1,'2025-03-20',20,150,'Fertilizzante','Azotato primaverile',1),
(2,'2025-05-10',5,200,'Antiparassitario','Contro afidi',1);

INSERT INTO Terreno VALUES
(1,5.5,'Terreno collinare','Seminativo','Attivo',NULL),
(2,2.0,'Serra ortaggi','Protetto','Attivo',NULL);

INSERT INTO Vendita VALUES
(1,2500.00,10000,'Vendita grano raccolto','2025-07-15','BNCGPP70A01H501F'),
(2,800.00,1000,'Vendita pomodori','2025-08-05','CNTPLA85C15H501Y');

INSERT INTO Ciclo_Colturale VALUES
(1,1,2025,'2025-03-01','2025-07-10',10000,'kg','Ciclo cereali 2025',1,1),
(2,2,2025,'2025-04-01','2025-08-01',1000,'kg','Ciclo pomodori 2025',2,2);

INSERT INTO Valore_di_Mercato VALUES
(1,'Primavera',0.25,2025,'2025-04-01',1),
(2,'Estate',0.80,2025,'2025-07-01',2);

INSERT INTO Busta_Paga VALUES
(1,'RSSMRA80A01H501Z','Stipendio marzo 2025','2025-03-31',1800.00),
(2,'VRDLGI85B02H501X','Stipendio marzo 2025','2025-03-31',1500.00),
(3,'BNCGPP70A01H501F','Compenso stagionale','2025-07-31',1200.00);

INSERT INTO Assegnazione_Terreno VALUES
(1,'2025-01-01','2025-12-31','RSSMRA80A01H501Z','Responsabile coltivazioni grano',1,'RSSMRA80A01H501Z'),
(2,'2025-03-01','2025-09-30','VRDLGI85B02H501X','Responsabile serra pomodori',2,'VRDLGI85B02H501X');

INSERT INTO Assegnazione_Trattamento VALUES
('VRDLGI85B02H501X',1,'2025-03-20','Applicazione fertilizzante grano'),
('VRDLGI85B02H501X',2,'2025-05-10','Trattamento antiparassitario pomodori');

INSERT INTO Assegnazione_Macchinario VALUES
('VRDLGI85B02H501X','1','2025-03-10','Aratura con trattore FIAT 505'),
('BNCGPP70A01H501F','2','2025-07-05','Raccolta con mietitrebbia Claas');

INSERT INTO Utilizzo_Macchinario VALUES
(1,'2025-03-10',5,'Aratura',1,'VRDLGI85B02H501X',1,'1'),
(2,'2025-07-05',8,'Raccolta grano',1,'BNCGPP70A01H501F',1,'2'),
(3,'2025-06-15',4,'Irrigazione serra',2,'VRDLGI85B02H501X',2,'1');

