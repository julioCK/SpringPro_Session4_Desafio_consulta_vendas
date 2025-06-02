package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SalesReportProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value =  "SELECT ss.id, ss.amount, ss.date, sll.name " +
                                        "FROM TB_SALES  ss " +
                                        "INNER JOIN TB_SELLER sll ON ss.seller_id = sll.id " +
                                        "WHERE ss.date BETWEEN CAST(:minDate AS DATE) AND CAST(:maxDate AS DATE) " +
                                        "AND UPPER(sll.name) LIKE UPPER(CONCAT('%', :name, '%'))",

                           countQuery = "SELECT COUNT(*) " +
                                        "FROM TB_SALES  ss " +
                                        "INNER JOIN TB_SELLER sll ON ss.seller_id = sll.id " +
                                        "WHERE ss.date BETWEEN CAST(:minDate AS DATE) AND CAST(:maxDate AS DATE) " +
                                        "AND UPPER(sll.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SalesReportProjection> reportSearch(String minDate, String maxDate, String name, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT  sll.name, SUM(ss.amount) AS total " +
                                       "FROM TB_SALES  ss " +
                                       "INNER JOIN TB_SELLER sll ON ss.seller_id = sll.id " +
                                       "WHERE ss.date BETWEEN CAST(:minDate AS DATE) AND CAST(:maxDate AS DATE) " +
                                       "GROUP BY sll.name",

                          countQuery = "SELECT  COUNT(*) " +
                                        "FROM TB_SALES  ss " +
                                        "INNER JOIN TB_SELLER sll ON ss.seller_id = sll.id " +
                                        "WHERE ss.date BETWEEN CAST(:minDate AS DATE) AND CAST(:maxDate AS DATE) " +
                                        "GROUP BY sll.name")
    Page<SalesSummaryProjection> summarySearch(String minDate, String maxDate, Pageable pageable);
}
