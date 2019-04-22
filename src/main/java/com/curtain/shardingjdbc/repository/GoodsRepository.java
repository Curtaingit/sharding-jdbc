package com.curtain.shardingjdbc.repository;

import com.curtain.shardingjdbc.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Curtain
 * @date 2019/4/22 10:43
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    List<Goods> findAllByGoodsIdBetween(Long start, Long end);

    List<Goods> findAllByGoodsIdIn(List ids);

    List<Goods> findByGoodsType(Long type);
}
