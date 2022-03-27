package com.sofkau.libraryReactive.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ResourceDto {
    private String id;
    @NotBlank
    @NotNull
    private String title;
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String description;
    @NotBlank
    @NotNull
    private String type;
    @NotBlank
    @NotNull
    private String theme;

    private LocalDate lastBorrowingDate;
    private Boolean isAvailable;

    public ResourceDto(String title, String description, String type, String theme) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.theme = theme;
    }

    public ResourceDto(String id, String title, String description, String type, String theme, LocalDate lastBorrowingDate, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.theme = theme;
        this.lastBorrowingDate = lastBorrowingDate;
        this.isAvailable = isAvailable;
    }

    public ResourceDto(String id, String title, String description, String type, String theme, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.theme = theme;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDate getLastBorrowingDate() {
        return lastBorrowingDate;
    }

    public void setLastBorrowingDate(LocalDate lastBorrowingDate) {
        this.lastBorrowingDate = lastBorrowingDate;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
