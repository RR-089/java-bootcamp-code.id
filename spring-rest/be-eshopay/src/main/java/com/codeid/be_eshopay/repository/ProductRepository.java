package com.codeid.be_eshopay.repository;

import com.codeid.be_eshopay.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            select p from Product p
            left join fetch p.category c
            where (:search is null or lower(p.name) like lower(concat('%', cast(:search as string), '%')))
            or (:search is null or lower(c.name) like lower(concat('%', cast(:search as string), '%')))
            """
    )
    Page<Product> findAllWithSearch(@Param("search") String search,
                                    Pageable pageable);
}
