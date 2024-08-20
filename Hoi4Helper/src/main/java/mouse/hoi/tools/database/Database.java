package mouse.hoi.tools.database;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mouse.hoi.tools.database.repository.CountryRepository;
import mouse.hoi.tools.database.repository.EffectsRepository;
import mouse.hoi.tools.database.repository.TriggersRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class Database {
    private final CountryRepository countryRepository;
    private final EffectsRepository effectsRepository;
    private final TriggersRepository triggersRepository;
}
