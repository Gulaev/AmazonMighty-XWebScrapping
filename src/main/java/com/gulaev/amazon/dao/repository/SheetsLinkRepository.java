package com.gulaev.amazon.dao.repository;

import com.gulaev.amazon.entity.SheetsLink;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface SheetsLinkRepository {

  Optional<SheetsLink> getLinkByAsinAndShopTitle(@Param("asin")String asin, @Param("shopTitle")String shopTitle);

  List<SheetsLink> getAll();

}
