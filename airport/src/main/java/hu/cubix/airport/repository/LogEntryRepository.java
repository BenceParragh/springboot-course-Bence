package hu.cubix.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.airport.model.LogEntry;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

}
