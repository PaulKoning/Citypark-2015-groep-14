-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Machine: 127.0.0.1
-- Gegenereerd op: 09 nov 2015 om 19:54
-- Serverversie: 5.6.21
-- PHP-versie: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `citypark`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `abbonementen`
--

CREATE TABLE IF NOT EXISTS `abbonementen` (
`Abbonoment_ID` int(11) NOT NULL,
  `Betaald` tinyint(1) NOT NULL,
  `Contract tot` datetime NOT NULL,
  `Begin Contract` datetime NOT NULL,
  `Abbonementtype_Abbonementtype` int(11) NOT NULL,
  `Pas_Pas_ID` int(11) NOT NULL,
  `Uren_Dezeweek` double NOT NULL,
  `Actief` tinyint(1) NOT NULL,
  `Bezoeker_Pas_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `abbonementen`
--

INSERT INTO `abbonementen` (`Abbonoment_ID`, `Betaald`, `Contract tot`, `Begin Contract`, `Abbonementtype_Abbonementtype`, `Pas_Pas_ID`, `Uren_Dezeweek`, `Actief`, `Bezoeker_Pas_ID`) VALUES
(1, 1, '2015-11-20 00:00:00', '2015-10-23 00:00:00', 1, 1999, 31, 1, NULL),
(2, 1, '2016-03-17 00:00:00', '2015-10-01 00:00:00', 1, 1998, 12, 1, 1002),
(3, 1, '2016-02-18 00:00:00', '2015-10-08 00:00:00', 1, 2000, 5, 1, 1003),
(1001, 1, '2015-12-08 09:00:00', '2015-11-08 08:00:00', 2, 1001, 12, 1, 1999),
(1002, 1, '2016-01-06 00:00:00', '2015-09-09 08:00:00', 2, 1002, 21, 1, NULL),
(1003, 0, '2015-12-03 00:00:00', '2015-11-03 00:00:00', 2, 1003, 34, 1, NULL);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `abbonementtype`
--

CREATE TABLE IF NOT EXISTS `abbonementtype` (
`Abbonementtype` int(11) NOT NULL,
  `Bedrag_p_maand` double NOT NULL,
  `Beschrijving` varchar(100) NOT NULL,
  `Uren` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `abbonementtype`
--

INSERT INTO `abbonementtype` (`Abbonementtype`, `Bedrag_p_maand`, `Beschrijving`, `Uren`) VALUES
(1, 0, 'Bezoekerspas', 16),
(2, 299.99, 'Abbonee', 144);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `factuur`
--

CREATE TABLE IF NOT EXISTS `factuur` (
`Factuur_ID` int(11) NOT NULL,
  `Datum` datetime NOT NULL,
  `Inrijden_inrijd_id` int(11) NOT NULL,
  `Bedrag` double DEFAULT NULL,
  `Voldaan` tinyint(1) DEFAULT NULL,
  `Abbonementen_Abbonoment_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `gebruiker`
--

CREATE TABLE IF NOT EXISTS `gebruiker` (
`Gebruiker_ID` int(11) NOT NULL,
  `Voornaam` varchar(45) NOT NULL,
  `Achternaam` varchar(45) NOT NULL,
  `Rekeningsnummer` int(11) NOT NULL,
  `Adres` varchar(45) NOT NULL,
  `Woonplaats` varchar(45) NOT NULL,
  `Postcode` varchar(6) NOT NULL,
  `Telefoonnummer` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Wachtwoord` varchar(45) NOT NULL,
  `Gebruikersnaam` varchar(45) NOT NULL,
  `Gebruiker_niveau` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `gebruiker`
--

INSERT INTO `gebruiker` (`Gebruiker_ID`, `Voornaam`, `Achternaam`, `Rekeningsnummer`, `Adres`, `Woonplaats`, `Postcode`, `Telefoonnummer`, `Email`, `Wachtwoord`, `Gebruikersnaam`, `Gebruiker_niveau`) VALUES
(1, 'Gerbrand', 'de Groot', 111, 'Schoolstraat', 'Groningen', '9712JS', '0611121314', 'leeg@leeg.nl', 'degroot', 'gerbrand', 1),
(2, 'Jessica', 'Lewis', 461, '7 Ridgeway Street', 'San RamÃ³n', '7067', '598-(346)221-4464', 'jlewis0@over-blog.com', 'S3GXrS1', 'jlewis0', 1),
(3, 'Kevin', 'Freeman', 459, '126 Dahle Place', 'Lindavista', '4646', '52-(809)104-3603', 'kfreeman1@uol.com.br', 'Ri0XYumPoIMg', 'kfreeman1', 1),
(4, 'Heather', 'Gardner', 911, '2780 Bashford Alley', 'YÃ©limanÃ©', '8711', '223-(913)813-0444', 'hgardner2@quantcast.com', 'UTjXWGSc', 'hgardner2', 1),
(5, 'Lois', 'Harvey', 913, '7838 Marquette Place', 'Melbourne', '7826', '1-(321)769-6114', 'lharvey3@state.gov', 'EbPAtI9MlpN', 'lharvey3', 1),
(6, 'Richard', 'Green', 194, '461 Algoma Circle', 'Guingamp', '7529', '33-(251)584-7827', 'rgreen4@hatena.ne.jp', '7RUJmbI1noS', 'rgreen4', 1),
(7, 'Teresa', 'Jones', 425, '074 Burrows Pass', 'TrollhÃ¤ttan', '6857', '46-(397)161-5470', 'tjones5@eventbrite.com', 'horGwS9LOSl', 'tjones5', 1),
(8, 'Anne', 'Alexander', 299, '68109 Darwin Center', 'PapÃ¡gou', '5019', '30-(130)919-1805', 'aalexander6@paypal.com', 'cpk93Q', 'aalexander6', 1),
(9, 'Henry', 'Patterson', 568, '537 Eastwood Road', 'Chuanbu', '4797', '86-(838)857-8871', 'hpatterson7@samsung.com', 'V4Sjzkj', 'hpatterson7', 1),
(10, 'Ronald', 'Nichols', 260, '85871 Merry Center', 'Luxi', '7327', '86-(528)998-2060', 'rnichols8@quantcast.com', 'mMspCa', 'rnichols8', 1),
(11, 'Tina', 'Perry', 893, '32641 Leroy Crossing', 'Gobang', '5284', '62-(453)289-4251', 'tperry9@gov.uk', '22FMCv9kc', 'tperry9', 1),
(12, 'Paul', 'Grant', 995, '12 Eastwood Center', 'Melbourne', '1247', '61-(627)692-7632', 'pgranta@hhs.gov', '5NwzsgDe', 'pgranta', 1),
(13, 'Margaret', 'Bowman', 523, '922 Fordem Road', 'Takanosu', '7246', '81-(864)329-6830', 'mbowmanb@mashable.com', 'GRX0Kk3HQ2', 'mbowmanb', 1),
(14, 'Brenda', 'Nichols', 144, '74240 Mcbride Plaza', 'Iparia', '3993', '51-(498)335-6937', 'bnicholsc@hao123.com', 'iHrhlXV', 'bnicholsc', 1),
(15, 'Cynthia', 'Hunt', 332, '62706 Florence Parkway', 'JardÃ­n AmÃ©rica', '9339', '54-(946)877-2464', 'chuntd@bigcartel.com', 'w2oauiHJodgl', 'chuntd', 1),
(16, 'Todd', 'Snyder', 593, '965 Pankratz Lane', 'Songbai', '9271', '86-(546)175-3017', 'tsnydere@blinklist.com', 'G6Dhhb6', 'tsnydere', 1),
(17, 'Theresa', 'Hicks', 318, '62770 John Wall Hill', 'Manadhoo', '6401', '960-(910)710-8005', 'thicksf@pcworld.com', 'UVigGjaER', 'thicksf', 1),
(18, 'Scott', 'Washington', 956, '2 Hayes Center', 'ÅŒta', '7960', '81-(635)419-1443', 'swashingtong@slashdot.org', 'nlVsULko', 'swashingtong', 1),
(19, 'Cheryl', 'Daniels', 938, '975 Londonderry Crossing', 'Loufan', '9786', '86-(303)557-2407', 'cdanielsh@themeforest.net', 'TzFGjU5ntH9', 'cdanielsh', 1),
(20, 'Teresa', 'Brown', 769, '7716 Butterfield Road', 'Villeneuve-d''Ascq', '2205', '33-(244)306-4213', 'tbrowni@technorati.com', 'Dg7rFq14m', 'tbrowni', 1),
(21, 'Tina', 'Franklin', 69, '6716 Forest Alley', 'NazrÄ“t', '4538', '251-(480)813-1860', 'tfranklinj@miitbeian.gov.cn', 'taush9hQ', 'tfranklinj', 1),
(22, 'Roger', 'Fuller', 789, '5374 Crowley Road', 'Klang', '3821', '60-(669)751-6976', 'rfullerk@so-net.ne.jp', 'zGXXjh9A0', 'rfullerk', 1),
(23, 'Ronald', 'Perkins', 874, '59 Sunfield Trail', 'Gaplokan', '7596', '62-(637)305-9438', 'rperkinsl@vistaprint.com', 'BXKa97EWG48O', 'rperkinsl', 1),
(24, 'Carlos', 'Wood', 326, '3327 Autumn Leaf Court', 'Skalbmierz', '3143', '48-(525)410-0160', 'cwoodm@acquirethisname.com', 'O5kUEEMnB1', 'cwoodm', 1),
(25, 'Kathryn', 'Warren', 697, '0 Grasskamp Point', 'San Isidro', '4447', '52-(468)839-4506', 'kwarrenn@technorati.com', 'ACbulwR32K3', 'kwarrenn', 1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `inrijden`
--

CREATE TABLE IF NOT EXISTS `inrijden` (
`inrijd_id` int(11) NOT NULL,
  `Begintijd` datetime DEFAULT NULL,
  `Eindtijd` datetime DEFAULT NULL,
  `Betaald` tinyint(1) DEFAULT NULL,
  `Abbonementen_Abbonoment_ID` int(11) DEFAULT NULL,
  `Pas_Pas_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `pas`
--

CREATE TABLE IF NOT EXISTS `pas` (
`Pas_ID` int(11) NOT NULL,
  `Pastype_Pastype_ID` int(11) NOT NULL,
  `Actief` tinyint(1) NOT NULL,
  `Gebruiker_Gebruiker_ID` int(11) NOT NULL,
  `Cardid` varchar(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `pas`
--

INSERT INTO `pas` (`Pas_ID`, `Pastype_Pastype_ID`, `Actief`, `Gebruiker_Gebruiker_ID`, `Cardid`) VALUES
(1, 1, 1, 2, 'STX DCF5740ACR LF '),
(2, 1, 0, 3, 'STX EEC9387ACR LF '),
(1001, 2, 0, 1, 'STX EEC9193ACR LF '),
(1002, 2, 0, 5, 'STX EEBAA28ACR LF '),
(1003, 2, 1, 6, 'STX DF1FAB5ACR LF '),
(1337, 5, 1, 7, 'STX E1D5F5FDCR LF '),
(1998, 4, 1, 8, 'STX D4FDD59CCR LF '),
(1999, 4, 1, 9, 'STX EEC94C2ACR LF '),
(2000, 4, 1, 10, 'STX EEC9381ACR LF ');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `pastype`
--

CREATE TABLE IF NOT EXISTS `pastype` (
`Pastype_ID` int(11) NOT NULL,
  `Beschrijving_Type` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `pastype`
--

INSERT INTO `pastype` (`Pastype_ID`, `Beschrijving_Type`) VALUES
(2, 'Abbonement'),
(1, 'Ad-Hoc'),
(4, 'Bezoekerspas'),
(5, 'Dit is een OV-Chipkaart gek.'),
(3, 'Leeg');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tarief`
--

CREATE TABLE IF NOT EXISTS `tarief` (
`tarief_id` int(10) unsigned NOT NULL,
  `bedragpuur` double NOT NULL,
  `max` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Gegevens worden geëxporteerd voor tabel `tarief`
--

INSERT INTO `tarief` (`tarief_id`, `bedragpuur`, `max`) VALUES
(1, 2.2, 10),
(2, 2.8, 15),
(3, 1.1, 10);

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `abbonementen`
--
ALTER TABLE `abbonementen`
 ADD PRIMARY KEY (`Abbonoment_ID`), ADD UNIQUE KEY `Abbonomenttype_UNIQUE` (`Abbonoment_ID`), ADD UNIQUE KEY `Pas_Pas_ID` (`Pas_Pas_ID`), ADD KEY `fk_Abbonementen_Abbonementtype1_idx` (`Abbonementtype_Abbonementtype`), ADD KEY `fk_Abbonementen_Pas1_idx` (`Pas_Pas_ID`);

--
-- Indexen voor tabel `abbonementtype`
--
ALTER TABLE `abbonementtype`
 ADD PRIMARY KEY (`Abbonementtype`), ADD UNIQUE KEY `Abbonementtype_UNIQUE` (`Abbonementtype`);

--
-- Indexen voor tabel `factuur`
--
ALTER TABLE `factuur`
 ADD PRIMARY KEY (`Factuur_ID`), ADD UNIQUE KEY `Factuur_ID_UNIQUE` (`Factuur_ID`), ADD KEY `fk_Factuur_Inrijden1_idx` (`Inrijden_inrijd_id`), ADD KEY `fk_Factuur_Abbonementen1_idx` (`Abbonementen_Abbonoment_ID`);

--
-- Indexen voor tabel `gebruiker`
--
ALTER TABLE `gebruiker`
 ADD PRIMARY KEY (`Gebruiker_ID`), ADD UNIQUE KEY `Gebruiker_ID_UNIQUE` (`Gebruiker_ID`), ADD UNIQUE KEY `Email_UNIQUE` (`Email`), ADD UNIQUE KEY `Gebruikersnaam_UNIQUE` (`Gebruikersnaam`);

--
-- Indexen voor tabel `inrijden`
--
ALTER TABLE `inrijden`
 ADD PRIMARY KEY (`inrijd_id`), ADD UNIQUE KEY `inrijd_id_UNIQUE` (`inrijd_id`), ADD KEY `fk_Inrijden_Abbonementen1_idx` (`Abbonementen_Abbonoment_ID`), ADD KEY `fk_Inrijden_Pas1_idx` (`Pas_Pas_ID`);

--
-- Indexen voor tabel `pas`
--
ALTER TABLE `pas`
 ADD PRIMARY KEY (`Pas_ID`), ADD UNIQUE KEY `Pasnummer_UNIQUE` (`Pas_ID`), ADD KEY `fk_Pas_Pastype1_idx` (`Pastype_Pastype_ID`), ADD KEY `fk_Pas_Gebruiker1_idx` (`Gebruiker_Gebruiker_ID`);

--
-- Indexen voor tabel `pastype`
--
ALTER TABLE `pastype`
 ADD PRIMARY KEY (`Pastype_ID`), ADD UNIQUE KEY `Pastype_ID_UNIQUE` (`Pastype_ID`), ADD UNIQUE KEY `Beschrijving_Type_UNIQUE` (`Beschrijving_Type`);

--
-- Indexen voor tabel `tarief`
--
ALTER TABLE `tarief`
 ADD PRIMARY KEY (`tarief_id`), ADD UNIQUE KEY `tarief_id_UNIQUE` (`tarief_id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `abbonementen`
--
ALTER TABLE `abbonementen`
MODIFY `Abbonoment_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1004;
--
-- AUTO_INCREMENT voor een tabel `abbonementtype`
--
ALTER TABLE `abbonementtype`
MODIFY `Abbonementtype` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT voor een tabel `factuur`
--
ALTER TABLE `factuur`
MODIFY `Factuur_ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `gebruiker`
--
ALTER TABLE `gebruiker`
MODIFY `Gebruiker_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=1002;
--
-- AUTO_INCREMENT voor een tabel `inrijden`
--
ALTER TABLE `inrijden`
MODIFY `inrijd_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=156;
--
-- AUTO_INCREMENT voor een tabel `pas`
--
ALTER TABLE `pas`
MODIFY `Pas_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2001;
--
-- AUTO_INCREMENT voor een tabel `pastype`
--
ALTER TABLE `pastype`
MODIFY `Pastype_ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT voor een tabel `tarief`
--
ALTER TABLE `tarief`
MODIFY `tarief_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `abbonementen`
--
ALTER TABLE `abbonementen`
ADD CONSTRAINT `fk_Abbonementen_Pas1` FOREIGN KEY (`Pas_Pas_ID`) REFERENCES `pas` (`Pas_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_abbonementen_abbonmenten` FOREIGN KEY (`Abbonementtype_Abbonementtype`) REFERENCES `abbonementtype` (`Abbonementtype`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `factuur`
--
ALTER TABLE `factuur`
ADD CONSTRAINT `fk_Factuur_Abbonementen1` FOREIGN KEY (`Abbonementen_Abbonoment_ID`) REFERENCES `abbonementen` (`Abbonoment_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Factuur_Inrijden1` FOREIGN KEY (`Inrijden_inrijd_id`) REFERENCES `inrijden` (`inrijd_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Beperkingen voor tabel `inrijden`
--
ALTER TABLE `inrijden`
ADD CONSTRAINT `fk_Inrijden_Abbonementen1` FOREIGN KEY (`Abbonementen_Abbonoment_ID`) REFERENCES `abbonementen` (`Abbonoment_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Inrijden_Pas1` FOREIGN KEY (`Pas_Pas_ID`) REFERENCES `pas` (`Pas_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Beperkingen voor tabel `pas`
--
ALTER TABLE `pas`
ADD CONSTRAINT `fk_Pas_Pastype1` FOREIGN KEY (`Pastype_Pastype_ID`) REFERENCES `pastype` (`Pastype_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_gebruiker_gebruiker_id` FOREIGN KEY (`Gebruiker_Gebruiker_ID`) REFERENCES `gebruiker` (`Gebruiker_ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
