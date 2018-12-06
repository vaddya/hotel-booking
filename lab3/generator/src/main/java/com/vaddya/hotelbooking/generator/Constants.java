package com.vaddya.hotelbooking.generator;

public class Constants {

    public static String[] cancelStatuses = {"REQUESTED", "IN_PROGRESS", "REJECTED", "COMPLETED"};

    public static String[] statuses = {"REQUESTED", "IN_PROGRESS", "REJECTED", "COMPLETED"};

    public static RoomTypeInfo[] roomTypes = {
            RoomTypeInfo.of("Single", 1, "A room assigned to one person."),
            RoomTypeInfo.of("Double", 2, "A room assigned to two people"),
            RoomTypeInfo.of("Triple", 3, "A room assigned to three people"),
            RoomTypeInfo.of("Quad", 4, "A room assigned to four people"),
            RoomTypeInfo.of("Queen", 0, "A room with a queen-sized bed"),
            RoomTypeInfo.of("King", 0, "A room with a king-sized bed"),
            RoomTypeInfo.of("Twin", 0, "A room with two beds"),
            RoomTypeInfo.of("Double-double", 0, "A room with two double (or perhaps queen) beds"),
            RoomTypeInfo.of("Studio", 0, "A room with a studio bed - a couch that can be converted into a bed")
    };

    static class RoomTypeInfo {
        private String type;
        private short capacity;
        private String description;

        public static RoomTypeInfo of(String type, int capacity, String description) {
            return new RoomTypeInfo(type, capacity, description);
        }

        private RoomTypeInfo(String type, int capacity, String description) {
            this.type = type;
            this.capacity = (short) capacity;
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public short getCapacity() {
            return capacity > 0 ? capacity : RandomUtils.randomShort();
        }

        public String getDescription() {
            return description;
        }
    }
}
