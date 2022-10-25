package by.roman.company.converter;

import java.util.List;

public interface Converter<E, D> {

    D toDTO(E entity);

    List<D> toListDTO(List<E> entity);

    E toEntity(D dto);
}
