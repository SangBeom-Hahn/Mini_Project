package hello.ex.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Log {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    private String message;

    public Log() {
    }

    public Log(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}