CREATE TABLE IF NOT EXISTS `Employees` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `EmployeeINN` varchar(255) NOT NULL,
  `EmployeeFIO` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Employees_EmployeeINN_unique` (`EmployeeINN`)
);

CREATE TABLE IF NOT EXISTS `Cities` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `CityName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `RouteRepetitions` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `RepetitionName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `RouteRepetitions_RepetitionName_unique` (`RepetitionName`)
);

CREATE TABLE IF NOT EXISTS `TravelCompanies` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(255) NOT NULL,
  `CompanyINN` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TravelCompanies_CompanyName_unique` (`CompanyName`),
  UNIQUE KEY `TravelCompanies_CompanyINN_unique` (`CompanyINN`)
);

CREATE TABLE IF NOT EXISTS `Busses` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `CarNumber` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `SitPlaces` tinyint unsigned NOT NULL CHECK (`SitPlaces` > 0),
  `GeneralCapacity` tinyint unsigned NOT NULL,
  `TravelCompanyId` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Busses_CarNumber_unique` (`CarNumber`),
  KEY `busses_TravelCompanyId_foreign` (`TravelCompanyId`),
  CONSTRAINT `Busses_TravelCompanyId_foreign` FOREIGN KEY (`TravelCompanyId`) REFERENCES `TravelCompanies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CHECK(`GeneralCapacity` >= `SitPlaces`)
);

CREATE TABLE IF NOT EXISTS `Routes` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `DepartureCityId` bigint unsigned NOT NULL,
  `ArrivalCityId` bigint unsigned NOT NULL,
  `RepetitionId` bigint unsigned NOT NULL,
  `AverageTravelTime` int unsigned NOT NULL COMMENT 'in minutes',
  `TravelDistance` double(8,2) unsigned NOT NULL COMMENT 'in kilometers',
  PRIMARY KEY (`id`),
  KEY `Routes_DepartureCityId_foreign` (`DepartureCityId`),
  KEY `Routes_ArrivalCityId_foreign` (`ArrivalCityId`),
  KEY `Routes_RepetitionId_foreign` (`RepetitionId`),
  CONSTRAINT `Routes_ArrivalCityId_foreign` FOREIGN KEY (`ArrivalCityId`) REFERENCES `Cities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Routes_DepartureCityId_foreign` FOREIGN KEY (`DepartureCityId`) REFERENCES `Cities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Routes_RepetitionId_foreign` FOREIGN KEY (`RepetitionId`) REFERENCES `RouteRepetitions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `Flights` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `DepartureTime` varchar(255) NOT NULL,
  `ArrivalTime` varchar(255) NOT NULL,
  `AvailableTickets` tinyint unsigned NOT NULL CHECK (`AvailableTickets` >= 0),
  `TicketPrice` mediumint unsigned NOT NULL,
  `RouteId` bigint unsigned NOT NULL,
  `BusId` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Flights_RouteId_foreign` (`RouteId`),
  KEY `Flights_BusId_foreign` (`BusId`),
  CONSTRAINT `Flights_BusId_foreign` FOREIGN KEY (`BusId`) REFERENCES `Busses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Flights_RouteId_foreign` FOREIGN KEY (`RouteId`) REFERENCES `Routes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `SoldTickets` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `TicketPrice` mediumint unsigned NOT NULL,
  `SitNumber` tinyint unsigned NOT NULL,
  `FlightId` bigint unsigned NOT NULL,
  `SellerId` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `SoldTickets_FlightId_foreign` (`FlightId`),
  KEY `SoldTickets_SellerId_foreign` (`SellerId`),
  CONSTRAINT `SoldTickets_FlightId_foreign` FOREIGN KEY (`FlightId`) REFERENCES `Flights` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `SoldTickets_SellerId_foreign` FOREIGN KEY (`SellerId`) REFERENCES `Employees` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

