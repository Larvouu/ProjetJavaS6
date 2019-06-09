-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jun 09, 2019 at 10:30 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecole`
--
CREATE DATABASE IF NOT EXISTS `ecole` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ecole`;

-- --------------------------------------------------------

--
-- Table structure for table `anneescolaire`
--

DROP TABLE IF EXISTS `anneescolaire`;
CREATE TABLE IF NOT EXISTS `anneescolaire` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2017 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anneescolaire`
--

INSERT INTO `anneescolaire` (`id`) VALUES
(2011),
(2012),
(2013),
(2014),
(2015),
(2016);

-- --------------------------------------------------------

--
-- Table structure for table `bulletin`
--

DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE IF NOT EXISTS `bulletin` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `trimestre_id` int(250) DEFAULT NULL,
  `inscription_id` int(250) DEFAULT NULL,
  `appreciation` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `trimestre_id` (`trimestre_id`),
  KEY `inscription_id` (`inscription_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bulletin`
--

INSERT INTO `bulletin` (`id`, `trimestre_id`, `inscription_id`, `appreciation`) VALUES
(1, 1, 1, 'Très bel effort tout au long du trimestre'),
(2, 1, 2, 'Attention, les résultats sont en baisse'),
(3, 2, 1, 'Continue comme ça !'),
(4, 2, 2, 'On remonte la pente, bravo');

-- --------------------------------------------------------

--
-- Table structure for table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `nom` varchar(250) NOT NULL,
  `ecole_id` varchar(250) DEFAULT NULL,
  `niveau_id` varchar(100) DEFAULT NULL,
  `anneescolaire_id` int(250) DEFAULT NULL,
  `nbInscrits` int(250) DEFAULT NULL,
  PRIMARY KEY (`nom`),
  KEY `ecole_id` (`ecole_id`),
  KEY `niveau_id` (`niveau_id`),
  KEY `anneescolaire_id` (`anneescolaire_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `classe`
--

INSERT INTO `classe` (`nom`, `ecole_id`, `niveau_id`, `anneescolaire_id`, `nbInscrits`) VALUES
('CP_A', 'Zola', 'CP', 2012, 0),
('CP_B', 'Zola', 'CP', 2012, 0),
('CE1_A', 'Zola', 'CE1', 2012, 0),
('CE1_B', 'Zola', 'CE1', 2012, 0),
('CE2_A', 'Zola', 'CE2', 2012, 0),
('CE2_B', 'Zola', 'CE2', 2012, 0),
('CM1_A', 'Zola', 'CM1', 2012, 0),
('CM1_B', 'Zola', 'CM1', 2012, 0),
('CM2_A', 'Zola', 'CM2', 2012, 0),
('CM2_B', 'Zola', 'CM2', 2012, 0);

-- --------------------------------------------------------

--
-- Table structure for table `detailbulletin`
--

DROP TABLE IF EXISTS `detailbulletin`;
CREATE TABLE IF NOT EXISTS `detailbulletin` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `bulletin_id` int(250) DEFAULT NULL,
  `enseignement_id` int(250) DEFAULT NULL,
  `appreciation` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bulletin_id` (`bulletin_id`),
  KEY `enseignement_id` (`enseignement_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailbulletin`
--

INSERT INTO `detailbulletin` (`id`, `bulletin_id`, `enseignement_id`, `appreciation`) VALUES
(1, 1, 7, 'tres bien Antoine'),
(2, 1, 3, 'Quel musicien !'),
(3, 1, 5, 'Un futur marathonien '),
(4, 2, 4, 'Une très belle voix'),
(5, 2, 6, 'Il faut faire preuve de plus de courage ! Tu peux le faire'),
(6, 2, 8, 'Sarah est très interessée cela est très agréable');

-- --------------------------------------------------------

--
-- Table structure for table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discipline`
--

INSERT INTO `discipline` (`nom`) VALUES
('anglais'),
('EPS'),
('francais'),
('maths'),
('musique'),
('physique'),
('sciences');

-- --------------------------------------------------------

--
-- Table structure for table `ecole`
--

DROP TABLE IF EXISTS `ecole`;
CREATE TABLE IF NOT EXISTS `ecole` (
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ecole`
--

INSERT INTO `ecole` (`nom`) VALUES
('Ecole Zola');

-- --------------------------------------------------------

--
-- Table structure for table `enseignement`
--

DROP TABLE IF EXISTS `enseignement`;
CREATE TABLE IF NOT EXISTS `enseignement` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `classe_id` varchar(250) DEFAULT NULL,
  `discipline_id` varchar(100) DEFAULT NULL,
  `personne_id` int(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `classe_id` (`classe_id`),
  KEY `discipline_id` (`discipline_id`),
  KEY `personne_id` (`personne_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `enseignement`
--

INSERT INTO `enseignement` (`id`, `classe_id`, `discipline_id`, `personne_id`) VALUES
(7, 'CE1_A', 'sciences', 1),
(2, 'CP_B', 'francais', 6),
(3, 'CE1_A', 'musique', 3),
(4, 'CE2_B', 'musique', 3),
(5, 'CE1_A', 'EPS', 5),
(6, 'CE2_B', 'EPS', 5),
(8, 'CE2_B', 'sciences', 1);

-- --------------------------------------------------------

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `detailBulletin_id` int(250) DEFAULT NULL,
  `note` double DEFAULT NULL,
  `appreciation` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `detailBulletin_id` (`detailBulletin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evaluation`
--

INSERT INTO `evaluation` (`id`, `detailBulletin_id`, `note`, `appreciation`) VALUES
(1, 1, 20, 'TB'),
(2, 1, 18, 'TB'),
(3, 2, 20, 'TB'),
(4, 2, 16, 'TB'),
(5, 3, 14, 'bien'),
(6, 3, 19, 'Tres bien'),
(7, 4, 20, 'bien'),
(8, 4, 19, 'Tres bien'),
(9, 5, 14, 'bien'),
(10, 5, 12, 'Oui'),
(11, 6, 20, 'bien'),
(12, 6, 19, 'Tres bien'),
(13, 1, 14, 'bien'),
(14, 1, 12, 'passable'),
(15, 2, 13, 'passable'),
(16, 2, 16, 'bien'),
(17, 3, 17, 'Bien'),
(18, 3, 20, 'Tres bien !'),
(19, 4, 8, 'a retravailler'),
(20, 4, 6, 'Dommage'),
(21, 5, 10, 'insuffisant'),
(22, 5, 17, 'TB'),
(23, 7, 14, 'Bien'),
(24, 7, 17, 'TB');

-- --------------------------------------------------------

--
-- Table structure for table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `classe_id` varchar(250) DEFAULT NULL,
  `personne_id` int(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `classe_id` (`classe_id`),
  KEY `personne_id` (`personne_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inscription`
--

INSERT INTO `inscription` (`id`, `classe_id`, `personne_id`) VALUES
(1, 'CE1_A', 2),
(2, 'CE2_B', 4),
(3, 'CE1_A', 7),
(4, 'CE2_B', 8),
(5, 'CE1_A', 9),
(6, 'CE2_B', 10),
(7, 'CE1_A', 11),
(8, 'CE2_B', 12),
(9, 'CE1_A', 13),
(10, 'CE2_B', 14);

-- --------------------------------------------------------

--
-- Table structure for table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `niveau`
--

INSERT INTO `niveau` (`nom`) VALUES
('CE1'),
('CE2'),
('CM1'),
('CM2'),
('CP');

-- --------------------------------------------------------

--
-- Table structure for table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `nom` varchar(200) DEFAULT NULL,
  `prenom` varchar(200) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `type`) VALUES
(1, 'Hina', 'Manolo', 'prof'),
(2, 'Ghiassi', 'Antoine', 'eleve'),
(3, 'Seg', 'JP', 'prof'),
(4, 'Le', 'Sarah', 'eleve'),
(5, 'Id', 'Wal', 'prof'),
(6, 'Si', 'Manon', 'prof'),
(7, 'Ndaw', 'Inna', 'eleve'),
(8, 'Nelly', 'Pied', 'eleve'),
(9, 'Bruno', 'Charlene', 'eleve'),
(10, 'Romain', 'Gary', 'eleve'),
(11, 'Ga', 'Paul', 'eleve'),
(12, 'Mon', 'Ray', 'eleve'),
(13, 'Sy', 'Omar', 'eleve'),
(14, 'Ne', 'Jean', 'eleve');

-- --------------------------------------------------------

--
-- Table structure for table `trimestre`
--

DROP TABLE IF EXISTS `trimestre`;
CREATE TABLE IF NOT EXISTS `trimestre` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `numero` int(10) DEFAULT NULL,
  `debut` varchar(200) DEFAULT NULL,
  `fin` varchar(200) DEFAULT NULL,
  `anneeScolaire_id` int(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `anneeScolaire_id` (`anneeScolaire_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
