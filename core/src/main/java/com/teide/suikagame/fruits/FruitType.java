package com.teide.suikagame.fruits;

public enum FruitType {
    CHERRY {
        @Override
        public Fruit create(float x, float y) {
            System.out.println("test 1");
            return new Cherry(x, y);
        }
    },
    APPLE {
        @Override
        public Fruit create(float x, float y) {
            return new Apple(x, y);
        }
    },
    GRAPE {
        @Override
        public Fruit create(float x, float y) {
            return new Grape(x, y);
        }
    },
    BANANA {
        @Override
        public Fruit create(float x, float y) {
            return new Banana(x, y);
        }
    },
    ORANGE {
        @Override
        public Fruit create(float x, float y) {
            return new Orange(x, y);
        }
    },
    PEACH {
        @Override
        public Fruit create(float x, float y) {
            return new Peach(x, y);
        }
    },
    MELON {
        @Override
        public Fruit create(float x, float y) {
            return new Melon(x, y);
        }
    };

    /**
     * Creates a new instance of the fruit at the specified position.
     *
     * @param x The x-coordinate for the fruit.
     * @param y The y-coordinate for the fruit.
     * @return A new instance of the corresponding Fruit subclass.
     */
    public abstract Fruit create(float x, float y);

    /**
     * Returns the next fruit type in progression, or null if it's the last type.
     *
     * @return The next FruitType, or null if none exists.
     */
    public FruitType getNextType() {
        int nextOrdinal = this.ordinal() + 1;
        return nextOrdinal < FruitType.values().length ? FruitType.values()[nextOrdinal] : null;
    }
}
