package com.github.camilormz.finalreality.model.character;

/**
 * Enumeration of the domains that any character must have
 * Understanding "domain" as a set of characteristics and behaviours of the character relative to
 * the game mechanics
 *
 * In the current implementation, it defines if a character is playable or not (enemy).
 *
 * @author Camilo Ramírez Canales
 */

public enum CharacterDomain {
    PLAYABLE("Playable Character"), ENEMY("Enemy");

    private final String field_description;

    /**
     * Enum constructor, requires a string description
     */
    CharacterDomain(String description)  {
        this.field_description = description;
    }

    /**
     * Returns the string description of the enum
     */
    public String getDescription() {
        return this.field_description;
    }
}
