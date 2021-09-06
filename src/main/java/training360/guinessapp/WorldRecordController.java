package training360.guinessapp;

import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worldrecords")
public class WorldRecordController {

    private WorldRecordService worldRecordService;

    public WorldRecordController(WorldRecordService worldRecordService) {
        this.worldRecordService = worldRecordService;
    }


    @PostMapping
    public WorldRecordDto createWorldRecord(@Valid @RequestBody WorldRecordCreateCommand command) {
        return worldRecordService.createWorldRecord(command);
    }

    @PutMapping("/api/worldrecords/{id}/beatrecords")
    public BeatWorldRecordDto beatWorldRecord(@PathVariable long id, @RequestBody BeatWorldRecordCommand command) {
        return worldRecordService.beatWorldRecord(id, command);
    }
}
