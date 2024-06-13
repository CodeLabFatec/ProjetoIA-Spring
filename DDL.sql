create database projeto_ia;

use projeto_ia;

CREATE TABLE `papel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `id_papel` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_papel_em_usuario_idx` (`id_papel`),
  CONSTRAINT `id_papel_em_usuario` FOREIGN KEY (`id_papel`) REFERENCES `papel` (`id`)
);

CREATE TABLE `area` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `descricao` varchar(1000) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `redzone` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(1000) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `id_area` bigint DEFAULT NULL,
  `status` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_area_in_redzone_fk_idx` (`id_area`),
  CONSTRAINT `id_area_in_redzone_fk` FOREIGN KEY (`id_area`) REFERENCES `area` (`id`)
);

CREATE TABLE `entrada_redzone` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `id_redzone` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_redzone_entrada_idx` (`id_redzone`),
  CONSTRAINT `id_redzone_entrada` FOREIGN KEY (`id_redzone`) REFERENCES `redzone` (`id`)
);

CREATE TABLE `saida_redzone` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `id_redzone` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_redzone_saida_idx` (`id_redzone`),
  CONSTRAINT `id_redzone_saida` FOREIGN KEY (`id_redzone`) REFERENCES `redzone` (`id`)
);

create table usuario_area(
id_usuario bigint not null,
id_area bigint not null,
primary key(id_usuario, id_area));

create table usuario_redzone(
id_usuario bigint not null,
id_redzone bigint not null,
primary key(id_usuario, id_redzone));

create table usuario_password_token(
id bigint not null auto_increment primary key,
id_usuario bigint not null,
token varchar(50) not null,
datahora_expiracao datetime not null,
foreign key(id_usuario) references usuario(id));

insert into usuario(nome, email, password, id_papel) values
('Administrador', 'admin@admin.com', '$2a$10$hGOrv4ya8KeuA3Vc4FUkKuMHM2FangSnjuJhYCizxKwHtWqIpb7iO', 1);