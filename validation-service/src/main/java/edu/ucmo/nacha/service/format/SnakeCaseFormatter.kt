package edu.ucmo.nacha.service.format

/**
 * Formats strings using snake case.
 */
class SnakeCaseFormatter {

    /**
     * Format a string from CONSTANT_CASE to snakeCase
     */
    fun fromConstantForm(input: String, trimFront: Boolean = false): String {
        // Get the name pieces
        val allNameParts: List<String> = input.split("_")

        // Drop the first part if directed to do so
        val nameParts: List<String> = if (trimFront) {
            allNameParts.subList(1, allNameParts.size)
        } else {
            allNameParts
        }

        // Convert the name to mostly camel case
        val name: String = nameParts.joinToString("") {
            it[0].toUpperCase() + it.substring(1).toLowerCase()
        }

        // Lowercase the first letter
        return name[0].toLowerCase() + name.substring(1)
    }
}
