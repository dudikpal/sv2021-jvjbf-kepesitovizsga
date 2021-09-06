package training360.guinessapp;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;
import training360.guinessapp.dto.RecorderShortDto;

import java.util.List;

@Service
@AllArgsConstructor
public class RecorderService {

    private RecorderRepository recorderRepository;

    private ModelMapper modelMapper;

    public List<RecorderShortDto> listRecorders() {
        return recorderRepository.listRecordersWithName().stream()
                .map(rec -> modelMapper.map(rec, RecorderShortDto.class))
                .toList();

    }

    public RecorderDto createRecorder(RecorderCreateCommand command) {
        Recorder recorder = recorderRepository.save(new Recorder(command.getName(), command.getDateOfBirth()));
        return modelMapper.map(recorder, RecorderDto.class);
    }

    public RecorderDto findRecorderById(long id) {
        return modelMapper.map(recorderRepository.findById(id).get(), RecorderDto.class);
    }
}
