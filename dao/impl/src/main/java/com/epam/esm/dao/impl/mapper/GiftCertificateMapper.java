package com.epam.esm.dao.impl.mapper;

import com.epam.esm.dao.api.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;

import static com.epam.esm.dao.impl.util.SqlColumnName.GIFT_CERTIFICATE_ID;
import static com.epam.esm.dao.impl.util.SqlColumnName.GIFT_CERTIFICATE_NAME;
import static com.epam.esm.dao.impl.util.SqlColumnName.GIFT_CERTIFICATE_DESCRIPTION;
import static com.epam.esm.dao.impl.util.SqlColumnName.GIFT_CERTIFICATE_PRICE;
import static com.epam.esm.dao.impl.util.SqlColumnName.GIFT_CERTIFICATE_DURATION;
import static com.epam.esm.dao.impl.util.SqlColumnName.GIFT_CERTIFICATE_CREATE_DATE;
import static com.epam.esm.dao.impl.util.SqlColumnName.GIFT_CERTIFICATE_LAST_UPDATE_DATE;

@Component
public class GiftCertificateMapper implements RowMapper<GiftCertificate> {

    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new GiftCertificate(
                resultSet.getLong(GIFT_CERTIFICATE_ID),
                resultSet.getString(GIFT_CERTIFICATE_NAME),
                resultSet.getString(GIFT_CERTIFICATE_DESCRIPTION),
                resultSet.getBigDecimal(GIFT_CERTIFICATE_PRICE),
                resultSet.getInt(GIFT_CERTIFICATE_DURATION),
                convertSqlDateToLocalDateTime(resultSet.getDate(GIFT_CERTIFICATE_CREATE_DATE),
                        resultSet.getTime(GIFT_CERTIFICATE_CREATE_DATE)),
                convertSqlDateToLocalDateTime(resultSet.getDate(GIFT_CERTIFICATE_LAST_UPDATE_DATE),
                        resultSet.getTime(GIFT_CERTIFICATE_LAST_UPDATE_DATE))
        );
    }

    private LocalDateTime convertSqlDateToLocalDateTime(Date sqlDate, Time sqlTime) {
        return LocalDateTime.of(sqlDate.toLocalDate(), sqlTime.toLocalTime());

    }
}
