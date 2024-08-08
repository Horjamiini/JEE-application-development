
--
-- Tietokanta: `jeedb1`
--

-- --------------------------------------------------------

--
-- Rakenne taululle `asiakkaat`
--

CREATE TABLE IF NOT EXISTS `asiakkaat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nimi` varchar(50) NOT NULL,
  `osoite` varchar(100) NOT NULL,
  `puhelin` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `salasana` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Vedos taulusta `asiakkaat`
--

INSERT INTO `asiakkaat` (`id`, `nimi`, `osoite`, `puhelin`, `email`, `salasana`) VALUES
(1, 'Aulis Asiakas', 'Asiakkaantie 13', '040-123456', 'aulis.asiakas@jkl.fi', 'aulis');


