package training360.guinessapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeatWorldRecordDto {

    private String description;

    private String unitOfMeasure;

    private String oldRecorderName;

    private Double oldRecord;

    private String newRecorderName;

    private Double newRecordValue;

    private Double recordDifference;
}
