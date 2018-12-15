package com.vaddya.hotelbooking.generator;

import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class GeneratorOptions {

    public enum Cluster {
        CITY,
        HOTEL,
        RESERVATION
    }

    private final Option helpOption = new Option("?", "help", false, "print help");

    private final Option clusterOption = new Option("c", "cluster", true, "cluster type: [city | hotel | reservation]");
    private final Option clusterNumberOption = new Option("n", "number", true, "base number of cluster, default 1000");

    private final Option allOption = new Option("a", "all", false, "generate all entities");
    private final Option bonusPenaltiesOption = new Option("b", "bonus-penalties", true, "number of bonuses or penalties, default: 20");
    private final Option cancellationsOption = new Option("l", "cancellations", true, "number of cancellations, default: 100");
    private final Option citiesOption = new Option("i", "cities", true, "number of cities, default: 100");
    private final Option countriesOption = new Option("o", "counties", true, "number of countries, default: 10");
    private final Option facilitiesOption = new Option("f", "facilities", true, "number of facilities, default: 100");
    private final Option guestsOption = new Option("g", "guests", false, "generate guests");
    private final Option hotelsOption = new Option("h", "hotels", true, "number of hotels, default: 50");
    private final Option houseRulesOption = new Option("s", "house-rules", true, "number of house rules, default: 250");
    private final Option pricesOption = new Option("p", "prices", false, "generate prices for room types");
    private final Option reservationsOption = new Option("v", "reservations", true, "number of reservations, default: 50000");
    private final Option reviewsOption = new Option("w", "reviews", true, "number of reviews, default: 20000");
    private final Option roomsOption = new Option("r", "rooms", true, "number of rooms, default: 1000");
    private final Option roomTypesOption = new Option("t", "room-types", false, "generate room types");
    private final Option usersOption = new Option("u", "users", true, "number of users, default: 5000");

    private final Option minBonusPenaltiesPerReservation = new Option(null, "min-bp", true, "minimum number of bonuses or penalties per reservation, default: 0");
    private final Option maxBonusPenaltiesPerReservation = new Option(null, "max-bp", true, "maximum number of bonuses or penalties per reservation, default: 2");

    private final Option minFacilitiesPerRoom = new Option(null, "min-facilities", true, "minimum number of facilities per room, default 10");
    private final Option maxFacilitiesPerRoom = new Option(null, "max-facilities", true, "maximum number of facilities per room, default 30");

    private final Options options;
    private final CommandLine cli;
    private final HelpFormatter formatter;

    public GeneratorOptions(String[] args) throws ParseException {
        options = new Options()
                .addOption(helpOption)
                .addOption(clusterOption)
                .addOption(clusterNumberOption)
                .addOption(allOption)
                .addOption(bonusPenaltiesOption)
                .addOption(cancellationsOption)
                .addOption(citiesOption)
                .addOption(countriesOption)
                .addOption(facilitiesOption)
                .addOption(guestsOption)
                .addOption(hotelsOption)
                .addOption(houseRulesOption)
                .addOption(pricesOption)
                .addOption(reservationsOption)
                .addOption(reviewsOption)
                .addOption(roomsOption)
                .addOption(roomTypesOption)
                .addOption(usersOption)
                .addOption(minBonusPenaltiesPerReservation)
                .addOption(maxBonusPenaltiesPerReservation)
                .addOption(minFacilitiesPerRoom)
                .addOption(maxFacilitiesPerRoom);
        cli = new DefaultParser().parse(options, args);
        formatter = new HelpFormatter();
    }

    public boolean hasHelpOption() {
        return cli.hasOption(helpOption.getOpt());
    }

    public Optional<Cluster> getClusterOption() {
        var opt = clusterOption.getOpt();
        return cli.hasOption(opt) ? Optional.of(Cluster.valueOf(cli.getOptionValue(opt).toUpperCase())) : Optional.empty();
    }

    public int getClusterBaseNumber() {
        return ifElse(clusterNumberOption, 1000);
    }

    public boolean hasAllOption() {
        return cli.hasOption(allOption.getOpt());
    }

    public boolean hasBonusPenalties() {
        return cli.hasOption(bonusPenaltiesOption.getOpt());
    }

    public int getBonusPenalties() {
        return ifElse(bonusPenaltiesOption, 100);
    }

    public boolean hasFacilities() {
        return cli.hasOption(facilitiesOption.getOpt());
    }

    public int getFacilities() {
        return ifElse(facilitiesOption, 100);
    }

    public boolean hasGuests() {
        return cli.hasOption(guestsOption.getOpt());
    }

    public boolean hasHotels() {
        return cli.hasOption(hotelsOption.getOpt());
    }

    public int getHotels() {
        return ifElse(hotelsOption, 50);
    }

    public boolean hasCities() {
        return cli.hasOption(citiesOption.getOpt());
    }

    public int getCities() {
        return ifElse(citiesOption, 100);
    }

    public boolean hasCancellations() {
        return cli.hasOption(cancellationsOption.getOpt());
    }

    public int getCancellation() {
        return ifElse(cancellationsOption, 5000);
    }

    public boolean hasCountries() {
        return cli.hasOption(countriesOption.getOpt());
    }

    public int getCountries() {
        return ifElse(countriesOption, 10);
    }

    public boolean hasPrices() {
        return cli.hasOption(pricesOption.getOpt());
    }

    public boolean hasRooms() {
        return cli.hasOption(roomsOption.getOpt());
    }

    public int getRooms() {
        return ifElse(roomsOption, 5000);
    }

    public boolean hasHouseRules() {
        return cli.hasOption(houseRulesOption.getOpt());
    }

    public int getHouseRules() {
        return ifElse(houseRulesOption, 250);
    }

    public boolean hasRoomTypes() {
        return cli.hasOption(roomTypesOption.getOpt());
    }

    public boolean hasUsers() {
        return cli.hasOption(usersOption.getOpt());
    }

    public int getUsers() {
        return ifElse(usersOption, 5000);
    }

    public boolean hasReservations() {
        return cli.hasOption(reservationsOption.getOpt());
    }

    public int getReservations() {
        return ifElse(reservationsOption, 50000);
    }

    public boolean hasReviews() {
        return cli.hasOption(reviewsOption.getOpt());
    }

    public int getReviews() {
        return ifElse(reviewsOption, 20000);
    }

    public int getMinBonusPenalties() {
        return ifElse(minBonusPenaltiesPerReservation, 0);
    }

    public int getMaxBonusPenalties() {
        return ifElse(maxBonusPenaltiesPerReservation, 3);
    }

    public int getMinFacilities() {
        return ifElse(minFacilitiesPerRoom, 10);
    }

    public int getMaxFacilities() {
        return ifElse(maxFacilitiesPerRoom, 30);
    }

    public void printHelp() {
        formatter.printHelp("generator", options, true);
    }

    private int ifElse(Option opt, int n) {
        if (opt.getOpt() == null) {
            return cli.hasOption(opt.getLongOpt())
                    ? Integer.parseInt(cli.getOptionValue(opt.getOpt()))
                    : n;
        }
        return cli.hasOption(opt.getOpt()) || cli.hasOption(opt.getLongOpt())
                ? Integer.parseInt(cli.getOptionValue(opt.getOpt()))
                : n;
    }

}
