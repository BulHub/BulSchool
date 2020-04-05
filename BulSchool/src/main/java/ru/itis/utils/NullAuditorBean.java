package ru.itis.utils;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NullAuditorBean implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {
        return Optional.empty();
    }
}
