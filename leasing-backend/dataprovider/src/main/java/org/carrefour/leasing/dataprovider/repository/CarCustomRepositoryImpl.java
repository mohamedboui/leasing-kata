package org.carrefour.leasing.dataprovider.repository;

import org.carrefour.leasing.common.model.CarSearchCriteria;
import org.carrefour.leasing.common.model.CarType;
import org.carrefour.leasing.dataprovider.model.CarEntity;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;


public class CarCustomRepositoryImpl implements CarCustomRepository {

    private final DatabaseClient databaseClient;

    public CarCustomRepositoryImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Flux<CarEntity> findAvailableCarsByCriteria(CarSearchCriteria criteria) {
        StringBuilder sql = new StringBuilder("SELECT * FROM cars WHERE leased = false");

        if (criteria.getBrand() != null) {
            sql.append(" AND LOWER(brand) = LOWER(:brand)");
        }
        if (criteria.getModel() != null) {
            sql.append(" AND LOWER(model) = LOWER(:model)");
        }
        if (criteria.getType() != null) {
            sql.append(" AND LOWER(type) = LOWER(:type)");
        }
        if (criteria.getLocation() != null) {
            sql.append(" AND LOWER(location) = LOWER(:location)");
        }

        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(sql.toString());

        if (criteria.getBrand() != null) {
            spec = spec.bind("brand", criteria.getBrand());
        }
        if (criteria.getModel() != null) {
            spec = spec.bind("model", criteria.getModel());
        }
        if (criteria.getType() != null) {
            spec = spec.bind("type", criteria.getType());
        }
        if (criteria.getLocation() != null) {
            spec = spec.bind("location", criteria.getLocation());
        }

        return spec
                .map((row, metadata) -> new CarEntity(
                        row.get("id", Long.class),
                        row.get("brand", String.class),
                        row.get("model", String.class),
                        CarType.valueOf(row.get("type", String.class)),
                        row.get("location", String.class),
                        row.get("basePricePerDay", Double.class),
                        Boolean.TRUE.equals(row.get("leased", Boolean.class))
                ))
                .all();
    }
}
