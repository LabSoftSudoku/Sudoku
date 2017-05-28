/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     09/05/2017 9:01:40                           */
/*==============================================================*/


alter table CASELLA
   drop constraint FK_CASELLA_CONTE_SUDOKU;

alter table SUDOKU
   drop constraint FK_SUDOKU_GUARDAR_SUDOKUJU;

drop index CONTE_FK;

drop table CASELLA cascade constraints;

drop index GUARDAR_FK;

drop table SUDOKU cascade constraints;

drop table JUGADOR cascade constraints;

/*CREATE SEQUENCE  S_IDSUDOKU  MINVALUE 1 MAXVALUE 999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;*/


/*==============================================================*/
/* Table: CASELLA                                               */
/*==============================================================*/
create table CASELLA  (
   NOMJUGADOR           VARCHAR2(40)                    not null,
   IDSUDOKU             NUMBER                          not null,
   COORX                NUMBER(1)                       not null
      constraint CKC_COORX_CASELLA check (COORX between 0 and 8),
   COORY                NUMBER(1)                       not null
      constraint CKC_COORY_CASELLA check (COORY between 0 and 8),
   VALOR                NUMBER(1)                       not null
      constraint CKC_VALOR_CASELLA check (VALOR between 0 and 9),
   ISEDITABLE           SMALLINT                        not null,
   constraint PK_CASELLA primary key (NOMJUGADOR, IDSUDOKU, COORX, COORY)
);

/*==============================================================*/
/* Index: CONTE_FK                                              */
/*==============================================================*/
create index CONTE_FK on CASELLA (
   NOMJUGADOR ASC,
   IDSUDOKU ASC
);

/*==============================================================*/
/* Table: SUDOKU                                                */
/*==============================================================*/
create table SUDOKU  (
   NOMJUGADOR           VARCHAR2(40)                    not null,
   HORACREACIO          TIMESTAMP                       not null,
   IDSUDOKU             NUMBER(3)                       not null,
   constraint PK_SUDOKU primary key (NOMJUGADOR, IDSUDOKU)
);

/*==============================================================*/
/* Index: GUARDAR_FK                                            */
/*==============================================================*/
create index GUARDAR_FK on SUDOKU (
   NOMJUGADOR ASC
);

/*==============================================================*/
/* Table: JUGADOR                                         */
/*==============================================================*/
create table JUGADOR  (
   NOMJUGADOR           VARCHAR2(40)                    not null,
   ESTATJUGANT          SMALLINT                        not null,
   constraint PK_JUGADOR primary key (NOMJUGADOR)
);

alter table CASELLA
   add constraint FK_CASELLA_CONTE_SUDOKU foreign key (NOMJUGADOR, IDSUDOKU)
      references SUDOKU (NOMJUGADOR, IDSUDOKU);

alter table SUDOKU
   add constraint FK_SUDOKU_GUARDAR_SUDOKUJU foreign key (NOMJUGADOR)
      references JUGADOR (NOMJUGADOR);



/*
CREATE OR REPLACE TRIGGER INSERIR_ID_SUDOKU 
BEFORE INSERT ON SUDOKU 
FOR EACH ROW 
BEGIN
	SELECT S_IDSUDOKU.NEXTVAL INTO :NEW.IDSUDOKU 
	FROM DUAL;
END;

ALTER TRIGGER INSERIR_ID_SUDOKU ENABLE;
*/