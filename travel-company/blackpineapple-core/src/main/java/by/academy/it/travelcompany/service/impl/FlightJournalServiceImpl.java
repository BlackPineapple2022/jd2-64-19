package by.academy.it.travelcompany.service.impl;

import by.academy.it.travelcompany.entity.FlightJournal;
import by.academy.it.travelcompany.repository.FlightJournalRepository;
import by.academy.it.travelcompany.service.FlightJournalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlightJournalServiceImpl implements FlightJournalService {

    @Autowired
    FlightJournalRepository flightJournalRepository;

    @Override
    public void create(FlightJournal flightJournal) {
        log.info("Creating new flight journal");
        flightJournalRepository.save(flightJournal);
    }
}
