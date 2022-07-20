package io.saliou.msscyerbaservice.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp ts) {
        if(ts == null) {
            return null;
        }
        return OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(), ts.toLocalDateTime().getMinute(),
                ts.toLocalDateTime().getSecond(), ts.toLocalDateTime().getNano(), ZoneOffset.UTC);
    }

    public Timestamp asTimestamp(OffsetDateTime odt) {
        if(odt == null) {
            return null;
        }
        return Timestamp.valueOf(odt.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
    }
}
