package program.zadania;

import java.time.LocalDateTime;
import java.time.Duration;

public class Task {
    private String title;
    private String description;
    private String assignedTo;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "Nowe";
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isNew() {
        return "Nowe".equals(this.status);
    }

    public boolean isInProgress() {
        return "W trakcie".equals(this.status);
    }

    public boolean isCompleted() {
        return "Zakończone".equals(this.status);
    }

    public void start() {
        this.startTime = LocalDateTime.now();
    }

    public void stop() {
        this.endTime = LocalDateTime.now();
    }

    public String getElapsedTime() {
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            long seconds = duration.getSeconds() % 60;
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else if (startTime != null) {
            Duration duration = Duration.between(startTime, LocalDateTime.now());
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            long seconds = duration.getSeconds() % 60;
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return "00:00:00";
    }

    @Override
    public String toString() {
        if (isCompleted()) {
            return title + " - " + status + " - Czas: " + getElapsedTime();
        } else {
            return title + " - " + status;
        }
    }
}
