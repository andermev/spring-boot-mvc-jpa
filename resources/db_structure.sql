CREATE DATABASE IF NOT EXISTS universidad;

-- Table structure for table `estudiante`

DROP TABLE IF EXISTS `estudiante`;
CREATE TABLE `estudiante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `carrera` varchar(255) DEFAULT NULL,
  `materia_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Table structure for table `aula`

DROP TABLE IF EXISTS `aula`;
CREATE TABLE `aula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoAula` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  `materia_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Table structure for table `profesor`

DROP TABLE IF EXISTS `profesor`;
CREATE TABLE `profesor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `materia_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Table structure for table `materia`

DROP TABLE IF EXISTS `materia`;
CREATE TABLE `materia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `hora` date DEFAULT NULL,
  `estudiante_id` int(11) NOT NULL,
  `profesor_id` int(11) NOT NULL,
  `aula_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Table structure for table `estudiante_materia`

DROP TABLE IF EXISTS `estudiante_materia`;
CREATE TABLE `estudiante_materia` (
  `estudiante_id` int(11) NOT NULL,
  `materia_id` int(11) NOT NULL,
  PRIMARY KEY (`estudiante_id`,`materia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table structure for table `profesor_materia`

DROP TABLE IF EXISTS `profesor_materia`;
CREATE TABLE `profesor_materia` (
  `profesor_id` int(11) NOT NULL,
  `materia_id` int(11) NOT NULL,
  PRIMARY KEY (`profesor_id`,`materia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `aula` ADD CONSTRAINT fk_aula_materia_id FOREIGN KEY (`materia_id`) REFERENCES `materia` (`id`);

ALTER TABLE `materia` ADD CONSTRAINT fk_materia_estudiante_id FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante_materia` (`estudiante_id`);
ALTER TABLE `materia` ADD CONSTRAINT fk_materia_profesor_id FOREIGN KEY (`profesor_id`) REFERENCES `profesor_materia` (`profesor_id`);
ALTER TABLE `materia` ADD CONSTRAINT fk_materia_aula_id FOREIGN KEY (`aula_id`) REFERENCES `aula` (`id`);

ALTER TABLE `estudiante` ADD CONSTRAINT fk_estudiante_materia_id FOREIGN KEY (`materia_id`) REFERENCES `estudiante_materia` (`estudiante_id`);
ALTER TABLE `profesor` ADD CONSTRAINT fk_profesor_materia_id FOREIGN KEY (`materia_id`) REFERENCES `profesor_materia` (`profesor_id`);

ALTER TABLE `estudiante_materia` ADD CONSTRAINT fk_estudiante_materia FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante` (`id`);
ALTER TABLE `estudiante_materia` ADD CONSTRAINT fk_materia_estudiante FOREIGN KEY (`materia_id`) REFERENCES `materia` (`id`);

ALTER TABLE `profesor_materia` ADD CONSTRAINT fk_profesor_materia FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`);
ALTER TABLE `profesor_materia` ADD CONSTRAINT fk_materia_profesor FOREIGN KEY (`materia_id`) REFERENCES `materia` (`id`);
