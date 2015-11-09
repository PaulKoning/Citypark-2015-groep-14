-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Machine: 127.0.0.1
-- Gegenereerd op: 09 nov 2015 om 19:53
-- Serverversie: 5.6.21
-- PHP-versie: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `bank`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `afschrijvingen`
--

CREATE TABLE IF NOT EXISTS `afschrijvingen` (
`Afschrift_ID` int(11) NOT NULL,
  `Datum` datetime NOT NULL,
  `Geslaagd` tinyint(1) NOT NULL,
  `Rekening_Rekening_ID` int(11) NOT NULL,
  `Bedrag` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `rekening`
--

CREATE TABLE IF NOT EXISTS `rekening` (
`Rekening_ID` int(11) NOT NULL,
  `Saldo` double NOT NULL,
  `Limiet` double NOT NULL,
  `Rekeningtype_Rekeningtype_ID` int(11) NOT NULL,
  `Rekeningshouder_Rekeningshouder_ID` int(11) NOT NULL,
  `Pincode` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `rekening`
--

INSERT INTO `rekening` (`Rekening_ID`, `Saldo`, `Limiet`, `Rekeningtype_Rekeningtype_ID`, `Rekeningshouder_Rekeningshouder_ID`, `Pincode`) VALUES
(111, 2154, -204, 2, 1, 1320),
(459, 34000, -1000, 2, 2, 1232),
(461, 4589, -300, 2, 3, 4578),
(911, 12500, -350, 2, 4, 9000);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `rekeningshouder`
--

CREATE TABLE IF NOT EXISTS `rekeningshouder` (
`Rekeningshouder_ID` int(11) NOT NULL,
  `Voornaam` varchar(45) NOT NULL,
  `Achternaam` varchar(45) NOT NULL,
  `Geboortedatum` varchar(45) NOT NULL,
  `Woonplaats` varchar(45) NOT NULL,
  `Postcode` varchar(6) NOT NULL,
  `Huisnummer` int(11) NOT NULL,
  `Telefoonnummer` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `rekeningshouder`
--

INSERT INTO `rekeningshouder` (`Rekeningshouder_ID`, `Voornaam`, `Achternaam`, `Geboortedatum`, `Woonplaats`, `Postcode`, `Huisnummer`, `Telefoonnummer`) VALUES
(1, 'Gerbrand', 'de Groot', '05-05-1995', 'Groningen', '9712JS', 4, '0611121314'),
(2, 'Kevin', 'Freeman', '06-03-1989', 'Bula', '8934FG', 89, '3456237856'),
(3, 'Jessica', 'Lewis', '06-03-1967', 'San RamÃ³n', '3423FG', 23, '0634563456'),
(4, 'Heather', 'Gardner', '12-12-1956', 'Melbourne', '3456ER', 90, '050123456');
(10000, 'Bank', 'Bank', 01-01-1950',  'Assen', '1234XD', 12, '123456789');
-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `rekeningtype`
--

CREATE TABLE IF NOT EXISTS `rekeningtype` (
`Rekeningtype_ID` int(11) NOT NULL,
  `Rekeningsbeschrijving` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `rekeningtype`
--

INSERT INTO `rekeningtype` (`Rekeningtype_ID`, `Rekeningsbeschrijving`) VALUES
(2, 'Betaalrekening'),
(1, 'Spaarrekening');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `afschrijvingen`
--
ALTER TABLE `afschrijvingen`
 ADD PRIMARY KEY (`Afschrift_ID`), ADD UNIQUE KEY `Afschrift_ID_UNIQUE` (`Afschrift_ID`), ADD KEY `fk_Afschrijvingen_Rekening_idx` (`Rekening_Rekening_ID`);

--
-- Indexen voor tabel `rekening`
--
ALTER TABLE `rekening`
 ADD PRIMARY KEY (`Rekening_ID`), ADD UNIQUE KEY `Rekening_ID_UNIQUE` (`Rekening_ID`), ADD KEY `fk_Rekening_Rekeningtype1_idx` (`Rekeningtype_Rekeningtype_ID`), ADD KEY `fk_Rekening_Rekeningshouder1_idx` (`Rekeningshouder_Rekeningshouder_ID`);

--
-- Indexen voor tabel `rekeningshouder`
--
ALTER TABLE `rekeningshouder`
 ADD PRIMARY KEY (`Rekeningshouder_ID`), ADD UNIQUE KEY `Rekeningshouder_ID_UNIQUE` (`Rekeningshouder_ID`);

--
-- Indexen voor tabel `rekeningtype`
--
ALTER TABLE `rekeningtype`
 ADD PRIMARY KEY (`Rekeningtype_ID`), ADD UNIQUE KEY `Rekeningtype_ID_UNIQUE` (`Rekeningtype_ID`), ADD UNIQUE KEY `Rekeningsbeschrijving_UNIQUE` (`Rekeningsbeschrijving`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `afschrijvingen`
--
ALTER TABLE `afschrijvingen`
MODIFY `Afschrift_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `rekening`
--
ALTER TABLE `rekening`
MODIFY `Rekening_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1001;
--
-- AUTO_INCREMENT voor een tabel `rekeningshouder`
--
ALTER TABLE `rekeningshouder`
MODIFY `Rekeningshouder_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1001;
--
-- AUTO_INCREMENT voor een tabel `rekeningtype`
--
ALTER TABLE `rekeningtype`
MODIFY `Rekeningtype_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `afschrijvingen`
--
ALTER TABLE `afschrijvingen`
ADD CONSTRAINT `fk_Afschrijvingen_Rekening` FOREIGN KEY (`Rekening_Rekening_ID`) REFERENCES `rekening` (`Rekening_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `rekening`
--
ALTER TABLE `rekening`
ADD CONSTRAINT `fk_Rekening_Rekeningshouder1` FOREIGN KEY (`Rekeningshouder_Rekeningshouder_ID`) REFERENCES `rekeningshouder` (`Rekeningshouder_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_Rekening_Rekeningtype1` FOREIGN KEY (`Rekeningtype_Rekeningtype_ID`) REFERENCES `rekeningtype` (`Rekeningtype_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
