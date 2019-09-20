package com.dalana.ShopManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepo extends JpaRepository<Bill, Long> {
    @Query("select B from Bill B order by B.startTime desc ")
    Page<List<Bill>> findTop(Pageable pageable);
}
