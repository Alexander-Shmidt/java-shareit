package ru.practicum.shareit.exception;

    public enum ErrorType {
        ENTITY_NOT_FOUND("Entity not found by id: %s"),
        ENTITY_NOT_SAVED("Entity not saved: %s"),
        ENTITY_NOT_UPDATED("Entity not updated: %s");

        private final String description;

        ErrorType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
