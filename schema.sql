create database clinica_sangabriel_bd;
use clinica_sangabriel_bd;

create table especialidad (
	id int auto_increment primary key,
    nombre varchar(50) not null,
    descripcion varchar(250) not null
);

create table medico (
	id int auto_increment primary key,
    nroColegiatura varchar(6) not null,
    nombre varchar(50) not null,
    apePat varchar(20) not null,
    apeMat varchar(20) not null,
    telefono varchar(9) not null,
    correo varchar(250) not null,
    estado boolean not null,
    especialidad_id int not null,
    fechaCreacion datetime,
    
    constraint fk_especialidad foreign key (especialidad_id) references especialidad(id)
);

create table paciente (
	id int not null auto_increment primary key,
    dni varchar(8) not null,
    nombre varchar(50) not null,
    apePat varchar(20) not null,
    apeMat varchar(20) not null,
    fechaNacimiento datetime not null,
    genero char not null,
    direccion varchar(250) not null,
    telefono varchar(9) not null,
    correo varchar(250) not null,
    cttoEmergencia varchar(50) not null,
    nroCttoEmergencia varchar(9) not null,
    fechaCreacion datetime not null,
    activo boolean not null
);

create table cita (
	id int not null auto_increment primary key,
    paciente_id int not null,
    especialidad_id int not null,
    medico_id int not null,
    horaInicio datetime not null,
    horaFinal datetime not null,
    fechaCreacion datetime not null,
    
    constraint fk_paciente foreign key (paciente_id) references paciente(id),
    constraint fk_especialidad foreign key (especialidad_id) references especialidad(id),
    constraint fk_medico foreign key (medico_id) references medico(id)
);