package uz.pdp.portfolio.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageDTO {
    String name;
    String email;
    String subject;
    String text;
}
