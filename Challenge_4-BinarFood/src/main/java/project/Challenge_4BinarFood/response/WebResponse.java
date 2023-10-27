package project.Challenge_4BinarFood.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//generic class
public class WebResponse<T> {
    private int countRecord;
    private T data;
    private String messages;
}