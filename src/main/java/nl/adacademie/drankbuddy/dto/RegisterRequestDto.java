package nl.adacademie.drankbuddy.dto;

public record RegisterRequestDto(
    String name,
    String username,
    String password,
    String confirmPassword
) {
}
