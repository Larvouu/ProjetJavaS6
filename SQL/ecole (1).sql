-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 28 mai 2019 à 16:03
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ecole`
--

-- --------------------------------------------------------

--
-- Structure de la table `anneescolaire`
--

DROP TABLE IF EXISTS `anneeScolaire`;
CREATE TABLE IF NOT EXISTS `anneeScolaire` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `bulletin`
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) DEFAULT NULL,
  `ecole_id` varchar(250) DEFAULT NULL,
  `niveau_id` int(100) DEFAULT NULL,
  `anneeScolaire_id` int(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ecole_id` (`ecole_id`),
  KEY `niveau_id` (`niveau_id`),
  KEY `anneeScolaire_id` (`anneeScolaire_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `detailbulletin`
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `nom` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ecole`
--

DROP TABLE IF EXISTS `ecole`;
CREATE TABLE IF NOT EXISTS `ecole` (
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `enseignement`
--

DROP TABLE IF EXISTS `enseignement`;
CREATE TABLE IF NOT EXISTS `enseignement` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `classe_id` int(250) DEFAULT NULL,
  `discipline_id` int(100) DEFAULT NULL,
  `personne_id` int(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `classe_id` (`classe_id`),
  KEY `discipline_id` (`discipline_id`),
  KEY `personne_id` (`personne_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `detailBulletin_id` int(250) DEFAULT NULL,
  `note` double DEFAULT NULL,
  `appreciation` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `detailBulletin_id` (`detailBulletin_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `classe_id` int(250) DEFAULT NULL,
  `personne_id` int(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `classe_id` (`classe_id`),
  KEY `personne_id` (`personne_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `id` int(250) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id` int(200) NOT NULL AUTO_INCREMENT,
  `nom` varchar(200) DEFAULT NULL,
  `prenom` varchar(200) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `trimestre`
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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
