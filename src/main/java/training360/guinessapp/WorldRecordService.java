package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class WorldRecordService {

    private WorldRecordRepository worldRecordRepository;

    private RecorderService recorderService;

    private ModelMapper modelMapper;


    @Transactional
    public WorldRecordDto createWorldRecord(WorldRecordCreateCommand command) {
        WorldRecord worldRecord = new WorldRecord(
                command.getDescription(),
                command.getValue(),
                command.getUnitOfMeasure(),
                command.getDateOfRecord(),
                command.getRecorderId()
        );
        worldRecordRepository.save(worldRecord);
        String recorderName = recorderService.findRecorderById(command.getRecorderId()).getName();
        WorldRecordDto worldRecordDto = new WorldRecordDto(
                worldRecord.getId(),
                worldRecord.getDescription(),
                worldRecord.getValue(),
                worldRecord.getUnitOfMeasure(),
                worldRecord.getDateOfRecord(),
                recorderName
        );
        return worldRecordDto;
    }

    @Transactional
    public BeatWorldRecordDto beatWorldRecord(long id, BeatWorldRecordCommand command) {
        WorldRecord worldRecord = findWorldRecordById(id);
        BeatWorldRecordDto beat = new BeatWorldRecordDto();
        RecorderDto oldRecorder = recorderService.findRecorderById(worldRecord.getRecorderId());
        RecorderDto newRecorder = recorderService.findRecorderById(command.getNewRecorderId());
        beat.setDescription(worldRecord.getDescription());
        beat.setUnitOfMeasure(worldRecord.getUnitOfMeasure());
        beat.setOldRecorderName(oldRecorder.getName());
        beat.setOldRecord(worldRecord.getValue());
        beat.setNewRecorderName(newRecorder.getName());
        beat.setNewRecordValue(command.getValue());
        beat.setRecordDifference(worldRecord.getValue() - command.getValue());

        return beat;
    }

    private WorldRecord findWorldRecordById(long id) {
        return worldRecordRepository.findById(id).get();
    }
}
