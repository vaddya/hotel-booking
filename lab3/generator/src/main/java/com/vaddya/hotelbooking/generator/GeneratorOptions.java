package com.vaddya.hotelbooking.generator;

import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class GeneratorOptions {

    private final Option helpOption = new Option("?", "help", false, "print help");

    private final Option clusterOption = new Option("c", "cluster", true, "cluster type: [city | hotel | reservation]");
    private final Option clusterNumberOption = new Option("n", "number", true, "base number of cluster, default 1000");

    private final Option bonusPenaltyOption = new Option("b", "bonus-penalties", true, "number of bonus or penalties, default: 20");
    private final Option cancellationOption = new Option("l", "cancellations", true, "number of cancellations, default: 5000");
    private final Option cityOption = new Option("i", "cities", true, "number of cities, default: 200");
    private final Option countryOption = new Option("o", "counties", true, "number of countries, default: 10");
    private final Option facilityOption = new Option("f", "facilities", true, "number of facilities, default: 100");
    private final Option guestOption = new Option("g", "guests", true, "number of guests, default: 20000");
    private final Option hotelOption = new Option("h", "hotels", true, "number of hotels, default: 1000");
    private final Option houseRulesOption = new Option("s", "house-rules", true, "number of house rules, default: 500");
    private final Option priceOption = new Option("p", "prices", true, "number of prices, default: 50000");
    private final Option reservationOption = new Option("v", "reservations", true, "number of reservations, default: 10000");
    private final Option reviewOption = new Option("w", "reviews", true, "number of reviews, default: 3000");
    private final Option roomOption = new Option("r", "rooms", true, "number of rooms, default: 30000");
    private final Option roomTypeOption = new Option("t", "room-types", true, "number of room types, default: 3000");
    private final Option userOption = new Option("u", "users", true, "number of users, default: 5000");

    private final Option minBonusPenaltiesPerReservation = new Option(null, "min-bp", true, "minimum number of bonuses or penalties per reservation, default: 0");
    private final Option maxBonusPenaltiesPerReservation = new Option(null, "max-bp", true, "maximum number of bonuses or penalties per reservation, default: 3");

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
                .addOption(bonusPenaltyOption)
                .addOption(cancellationOption)
                .addOption(cityOption)
                .addOption(countryOption)
                .addOption(facilityOption)
                .addOption(guestOption)
                .addOption(hotelOption)
                .addOption(houseRulesOption)
                .addOption(priceOption)
                .addOption(reservationOption)
                .addOption(reviewOption)
                .addOption(roomOption)
                .addOption(roomTypeOption)
                .addOption(userOption);
        cli = new DefaultParser().parse(options, args);
        formatter = new HelpFormatter();
    }

    public boolean hasHelpOption() {
        return cli.hasOption(helpOption.getOpt());
    }

    public Optional<ClusterOptions> getClusterOption() {
        var opt = clusterOption.getOpt();
        return cli.hasOption(opt) ? Optional.of(ClusterOptions.valueOf(cli.getOptionValue(opt))) : Optional.empty();
    }

    public int getClusterBaseNumber() {
        return ifElse(clusterNumberOption, 1000);
    }

    public int getBonusPenalties() {
        return ifElse(bonusPenaltyOption, 100);
    }

    public int getFacilities() {
        return ifElse(facilityOption, 100);
    }

    public int getGuests() {
        return ifElse(guestOption, 20000);
    }

    public int getHotels() {
        return ifElse(hotelOption, 1000);
    }

    public int getCities() {
        return ifElse(cityOption, 200);
    }

    public int getCancellation() {
        return ifElse(cancellationOption, 5000);
    }

    public int getCountries() {
        return ifElse(countryOption, 10);
    }

    public int getPrices() {
        return ifElse(priceOption, 50000);
    }

    public int getRooms() {
        return ifElse(roomOption, 30000);
    }

    public int getHouseRules() {
        return ifElse(houseRulesOption, 500);
    }

    public int getRoomTypes() {
        return ifElse(roomTypeOption, 3000);
    }

    public int getUsers() {
        return ifElse(userOption, 5000);
    }

    public int getReservations() {
        return ifElse(reservationOption, 10000);
    }

    public int getReviews() {
        return ifElse(reviewOption, 3000);
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
        return cli.hasOption(opt.getOpt()) ? Integer.parseInt(cli.getOptionValue(opt.getOpt())) : n;
    }

}
