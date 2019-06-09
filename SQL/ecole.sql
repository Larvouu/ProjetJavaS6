-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Jun 09, 2019 at 09:13 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `ecole`
--

-- --------------------------------------------------------

--
-- Table structure for table `anneescolaire`
--

CREATE TABLE `anneescolaire` (
  `id` int(250) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

CREATE TABLE `bulletin` (
  `id` int(250) NOT NULL,
  `trimestre_id` int(250) DEFAULT NULL,
  `inscription_id` int(250) DEFAULT NULL,
  `appreciation` varchar(300) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

CREATE TABLE `classe` (
  `nom` varchar(250) NOT NULL,
  `ecole_id` varchar(250) DEFAULT NULL,
  `niveau_id` varchar(100) DEFAULT NULL,
  `anneescolaire_id` int(250) DEFAULT NULL,
  `nbInscrits` int(250) DEFAULT NULL
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

CREATE TABLE `detailbulletin` (
  `id` int(250) NOT NULL,
  `bulletin_id` int(250) DEFAULT NULL,
  `enseignement_id` int(250) DEFAULT NULL,
  `appreciation` varchar(300) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

CREATE TABLE `discipline` (
  `nom` varchar(250) NOT NULL
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

CREATE TABLE `ecole` (
  `nom` varchar(250) NOT NULL
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

CREATE TABLE `enseignement` (
  `id` int(250) NOT NULL,
  `classe_id` varchar(250) DEFAULT NULL,
  `discipline_id` varchar(100) DEFAULT NULL,
  `personne_id` int(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

CREATE TABLE `evaluation` (
  `id` int(250) NOT NULL,
  `detailBulletin_id` int(250) DEFAULT NULL,
  `note` double DEFAULT NULL,
  `appreciation` varchar(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
(12, 6, 19, 'Tres bien');

-- --------------------------------------------------------

--
-- Table structure for table `inscription`
--

CREATE TABLE `inscription` (
  `id` int(250) NOT NULL,
  `classe_id` varchar(250) DEFAULT NULL,
  `personne_id` int(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inscription`
--

INSERT INTO `inscription` (`id`, `classe_id`, `personne_id`) VALUES
(1, 'CE1_A', 2),
(2, 'CE2_B', 4);

-- --------------------------------------------------------

--
-- Table structure for table `niveau`
--

CREATE TABLE `niveau` (
  `nom` varchar(250) NOT NULL
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

CREATE TABLE `personne` (
  `id` int(250) NOT NULL,
  `nom` varchar(200) DEFAULT NULL,
  `prenom` varchar(200) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `type`) VALUES
(1, 'Hina', 'Manolo', 'prof'),
(2, 'Ghiassi', 'Antoine', 'eleve'),
(3, 'Seg', 'JP', 'prof'),
(4, 'Le', 'Sarah', 'eleve'),
(5, 'Id', 'Wal', 'prof'),
(6, 'Si', 'Manon', 'prof');

-- --------------------------------------------------------

--
-- Table structure for table `trimestre`
--

CREATE TABLE `trimestre` (
  `id` int(250) NOT NULL,
  `numero` int(10) DEFAULT NULL,
  `debut` varchar(200) DEFAULT NULL,
  `fin` varchar(200) DEFAULT NULL,
  `anneeScolaire_id` int(250) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anneescolaire`
--
ALTER TABLE `anneescolaire`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bulletin`
--
ALTER TABLE `bulletin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `trimestre_id` (`trimestre_id`),
  ADD KEY `inscription_id` (`inscription_id`);

--
-- Indexes for table `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`nom`),
  ADD KEY `ecole_id` (`ecole_id`),
  ADD KEY `niveau_id` (`niveau_id`),
  ADD KEY `anneescolaire_id` (`anneescolaire_id`);

--
-- Indexes for table `detailbulletin`
--
ALTER TABLE `detailbulletin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bulletin_id` (`bulletin_id`),
  ADD KEY `enseignement_id` (`enseignement_id`);

--
-- Indexes for table `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`nom`);

--
-- Indexes for table `ecole`
--
ALTER TABLE `ecole`
  ADD PRIMARY KEY (`nom`);

--
-- Indexes for table `enseignement`
--
ALTER TABLE `enseignement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `classe_id` (`classe_id`),
  ADD KEY `discipline_id` (`discipline_id`),
  ADD KEY `personne_id` (`personne_id`);

--
-- Indexes for table `evaluation`
--
ALTER TABLE `evaluation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detailBulletin_id` (`detailBulletin_id`);

--
-- Indexes for table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `classe_id` (`classe_id`),
  ADD KEY `personne_id` (`personne_id`);

--
-- Indexes for table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`nom`);

--
-- Indexes for table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `trimestre`
--
ALTER TABLE `trimestre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `anneeScolaire_id` (`anneeScolaire_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anneescolaire`
--
ALTER TABLE `anneescolaire`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2017;

--
-- AUTO_INCREMENT for table `bulletin`
--
ALTER TABLE `bulletin`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `detailbulletin`
--
ALTER TABLE `detailbulletin`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `enseignement`
--
ALTER TABLE `enseignement`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `evaluation`
--
ALTER TABLE `evaluation`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `personne`
--
ALTER TABLE `personne`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `trimestre`
--
ALTER TABLE `trimestre`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT;
